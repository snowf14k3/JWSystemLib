package moe.snowflake.jwSystem.manager;

import moe.snowflake.jwSystem.JWSystem;
import moe.snowflake.jwSystem.course.CourseReview;
import moe.snowflake.jwSystem.course.Score;
import moe.snowflake.jwSystem.utils.HttpUtil;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseReviewManager {

    private final JWSystem system;


    public CourseReviewManager(JWSystem system) {
        this.system = system;
    }


    public void review(CourseReview courseReview, Score score) {
        try {
            Connection.Response response = HttpUtil.sendGet(courseReview.getLink());

            if (response == null) {
                System.err.println("评价 " + courseReview.getTerm() + " 失败");
                return;
            }
            Document document = response.parse();

            // 因为只有一个table
            Element table = document.getElementsByTag("table").first();
            // wtf ?
            if (table == null) return;
            // 一个tr就是一行，就是一个评价科目
            Elements subjects = table.getElementsByTag("tr");
            // 不处理第一个
            for (int i = 1; i < subjects.size(); i++) {
                Element tr = subjects.get(i);

                // tr 下面还含有 td a
                Elements tdElements = tr.getElementsByTag("td");
                StringBuilder sb = new StringBuilder();

                for (Element td : tdElements) sb.append(td.ownText()).append(",");

                // 删除多余的 ,
                sb.delete(sb.length() - 2, sb.length() - 1);
                // 拿操作符号的a
                Elements aElements = tr.getElementsByTag("a");
                Element hrefElements = aElements.first();

                if (hrefElements != null) sb.append(URLManager.BASE_URL).append(hrefElements.attr("href"));

                // W.I.P.
                System.out.println(sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 查询目前已有学生课程评价
     *
     * @return 评价表格数据
     */
    public List<CourseReview> getAllCourseReview() {
        Connection.Response response = HttpUtil.sendGet(URLManager.REVIEW_COURSE_FIND, this.system.headers);

        if (response == null) {
            throw new RuntimeException("network error....");
        }

        // 不读取 th 标签的数据
        StringBuilder sb = new StringBuilder();

        ArrayList<CourseReview> courseReviews = new ArrayList<>();

        try {
            Document document = response.parse();

            // 依旧是拿table
            Elements elements = document.getElementsByTag("table");

            // 默认第一个table
            Element element = elements.first();

            if (element == null) {
                throw new RuntimeException("element not found document \n" + document.outerHtml());
            }
            // 再取tr 标签
            Elements trElements = element.getElementsByTag("tr");

            // 不读取第一个tr标签
            for (int i = 1; i < trElements.size(); i++) {
                Element tr = trElements.get(i);

                Elements tdElements = tr.getElementsByTag("td");

                for (Element td : tdElements) sb.append(td.ownText()).append(",");
                // 删除多余的 ,
                sb.delete(sb.length() - 2, sb.length() - 1);

                // 拿操作符号的a
                Elements aElements = tr.getElementsByTag("a");
                Element hrefElements = aElements.first();

                if (hrefElements != null) sb.append(URLManager.BASE_URL).append(hrefElements.attr("href"));

                String[] split = sb.toString().split(",");
                // 序号,学年学期,评价分类,评价批次,开始时间,结束时间
                courseReviews.add(new CourseReview(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4], split[5], split[6]));
            }
        } catch (IOException e) {
            throw new RuntimeException("处理数据时发生异常");
        }
        return courseReviews;
    }

}
