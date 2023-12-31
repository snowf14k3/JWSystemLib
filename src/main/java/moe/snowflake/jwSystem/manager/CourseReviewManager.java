package moe.snowflake.jwSystem.manager;

import moe.snowflake.jwSystem.JWSystem;
import moe.snowflake.jwSystem.utils.HttpUtil;
import moe.snowflake.jwSystem.utils.URLConstants;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CourseReviewManager {

    private final JWSystem system;


    public CourseReviewManager(JWSystem system) {
        this.system = system;
    }


    /**
     * 查询目前已有学生课程评价
     * @return 评价表格数据
     */
    public String getAllCourseReview() {
        Connection.Response response = HttpUtil.sendGet(URLConstants.REVIEW_COURSE_FIND, this.system.headers);

        // 不读取 th 标签的数据
        StringBuilder sb = new StringBuilder("序号,学年学期,评价分类,评价批次,开始时间,结束时间\n");

        if (response == null) {
            throw new RuntimeException("network error....");
        }

        try {
            Document document = response.parse();

            // 依旧是直接拿table
            Elements elements = document.getElementsByTag("table");

            // 默认第一个table
            Element element = elements.first();

            if (element == null) {
                throw new RuntimeException("element not found");
            }
            // 再取tr 标签
            Elements trElements = element.getElementsByTag("tr");

            // 不读取第一个tr标签
            for (int i = 1; i < trElements.size(); i++) {
                Element tr = trElements.get(i);

                Elements tdElements = tr.getElementsByTag("td");

                for (int j = 0; j < tdElements.size(); j++) {
                    Element td = tdElements.get(j);
                    sb.append(td.ownText());

                    if (j + 1 != tdElements.size()) sb.append(",");
                }

                // 拿操作符号的a
                Elements aElements = tr.getElementsByTag("a");

                Element hrefElements = aElements.first();

                if (hrefElements != null) {
                    sb.append(URLConstants.BASE_URL).append(hrefElements.attr("href"));
                }
                // 换行
                sb.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("处理数据时发送异常");
        }
        return sb.toString();
    }


}
