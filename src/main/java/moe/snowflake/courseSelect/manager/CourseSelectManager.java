package moe.snowflake.courseSelect.manager;

import moe.snowflake.courseSelect.JWSystem;
import moe.snowflake.courseSelect.course.Course;
import moe.snowflake.courseSelect.course.FormMap;
import moe.snowflake.courseSelect.utils.CourseDataHandler;
import moe.snowflake.courseSelect.utils.HttpUtil;
import moe.snowflake.courseSelect.utils.URLConstants;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CourseSelectManager {
    private final JWSystem system;


    public CourseSelectManager(JWSystem system) {
        this.system = system;
    }

    /**
     * 获取自己已选课程的List
     *
     * @return 课程列表
     */
    public ArrayList<Course> getCurrentCourses() throws IOException {
        ArrayList<Course> list = new ArrayList<>();
        Connection.Response response = HttpUtil.sendGet(URLConstants.MY_COURSE_LIST, this.system.headers);

        if (response == null) {
            throw new RuntimeException("response was null");
        }

        // 返回值转化成Document
        Document document = response.parse();

        Elements elements = document.getElementsByTag("table");
        // 只有一个table
        Element element = elements.first();

        if (element == null) {
            throw new RuntimeException("element not found !");
        }

        // 获取全部tr tag的标签下的子元素
        Elements trElements = element.getElementsByTag("tr");
        for (Element tr : trElements) {
            Elements tdElements = tr.getElementsByTag("td");
            // 必定大于 5
            // 这边不处理顶部的th元素检测
            if (tdElements.size() < 5) {
                continue;
            }
            Course course = new Course();

            // 固定顺序
            course.setName(tdElements.get(1).ownText());
            course.setTeacher(tdElements.get(4).ownText());

            Element kcidElement = tr.getElementsByTag("a").first();

            if (kcidElement == null) {
                continue;
            }

            // 通过replaceKCID
            String JXID = kcidElement.attr("href")
                    .replace("');", "")
                    .replace("javascript:xstkOper('", "");
            course.setJxID(JXID);
            list.add(course);
        }
        return list;
    }

    /**
     * 获取当前已选选修课
     * 横向排列,无格式化
     */
    public String getCurrentCoursesStr() {
        Connection.Response response = HttpUtil.sendGet(URLConstants.MY_COURSE_LIST, this.system.headers);

        // 是否响应异常
        if (response == null) {
            throw new RuntimeException("response was null");
        }

        try {
            // 返回值转化成Document
            Document document = response.parse();

            Elements elements = document.getElementsByTag("table");
            // 只有一个table
            Element element = elements.first();

            if (element == null) {
                throw new RuntimeException("element not found !");
            }

            // 获取全部tr tag的标签下的子元素
            Elements trElements = element.getElementsByTag("tr");

            StringBuilder result = new StringBuilder();
            for (Element tr : trElements) {
                // 拿三个
                Elements thElements = tr.getElementsByTag("th");
                Elements tdElements = tr.getElementsByTag("td");

                // 判断是否为课程详细的行
                if (thElements.isEmpty()) {
                    // 循环课程表的具体信息
                    for (Element td : tdElements) {
                        result.append(td.ownText()).append(" ");
                    }
                } else {
                    // 循环课程表上的信息
                    for (Element th : thElements) {
                        result.append(th.ownText()).append(" ");
                    }
                }
                result.append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 退出自己已选课程,建议搭配getCurrentCourses()使用.
     * <p>
     * {"success": true}
     * <p>
     * {"success":false,"message":"退课失败：此课堂未开放，不能进行退课！"}
     * <p>
     *
     * @param course 课程实例
     * @param reason 退课原因
     */
    public boolean exitSelectedCourse(Course course, String reason) {
        Connection.Response exitSelectResponse = HttpUtil.sendGet(URLConstants.EXIT_COURSE
                .replace("<jx0404id>", course.getJxID())
                .replace("<reason>", reason), this.system.headers);

        if (exitSelectResponse == null) {
            return false;
        }
        // 退出选课判断
        return exitSelectResponse.body().contains("true");
    }

    /**
     * 选择公共选修课
     *
     * @param course 课程对象
     */
    public boolean selectElectiveCourse(Course course) {
        return selectCourse(URLConstants.ELECTIVE_COURSE_SELECT, course);
    }

    /**
     * 选择公共必修课
     *
     * @param course 课程的对象
     */
    public boolean selectRequiredCourse(Course course) {
        if (!course.getType().equals("P.E")) throw new RuntimeException("please call function  selectElectiveCourse !");

        return selectCourse(URLConstants.REQUIRED_COURSE_SELECT, course);
    }

    /**
     * @param url    选课的 URL
     * @param course 课程的id
     */
    private boolean selectCourse(String url, Course course) {

        // 得事先登录学生选课系统,让后台存JSESSIONID
        Connection.Response response = HttpUtil.sendGet(url
                .replace("<kcid>", course.getKcid())
                .replace("<jx0404id>", course.getJxID()), this.system.headers);

        //response
        // {"success":true,"message":"选课成功","jfViewStr":""}
        // {"success":[true,false],"message":"选课失败：此课堂选课人数已满！"}
        // {"success":false,"message":"选课失败：当前教学班已选择！"}

        // 必修课
        //{"success":false,"message":"选课失败：当前课程已选择其它教学班！"}

        if (response == null) {
            return false;
        }
        return !response.body().contains("false");
    }

    /**
     * 列出全部必修课,理论上必修选课最多课程不超过30个
     */
    public ArrayList<Course> getAllRequiredList() {
        return searchRequiredList(30);
    }

    /**
     * 列出必修课的列表
     *
     * @param size 显示课程大小
     */
    public ArrayList<Course> searchRequiredList(int size) {
        FormMap formMap = new FormMap();
        formMap.putRequiredFormData("3", 0, size);

        Connection.Response response = HttpUtil.sendPost(URLConstants.REQUIRED_COURSE_LIST, formMap, this.system.headers);

        if (response != null) {
            return CourseDataHandler.getCourses(response.body());
        }

        return new ArrayList<>();
    }

    /**
     * @param courseName     课程名称
     * @param teacher        授课老师
     * @param week           星期
     * @param section        节次
     * @param removeFull     过滤已满课程
     * @param removeConflict 过滤冲突课程
     * @param loc            过滤限选课程
     * @param size           显示数量
     */
    public ArrayList<Course> searchElectiveList(String courseName, String teacher, int week, String section, boolean removeFull, boolean removeConflict, String courseType, boolean loc, int size) {
        FormMap formMap = new FormMap();

        String weekStr = "";
        if (week != 0) {
            weekStr = String.valueOf(week);
        }

        formMap.putElectiveFormData("3", 0, size);

        // 查询的参数
        String args = "?kcxx=" + courseName + "&skls=" + teacher + "&skxq=" + weekStr + "&skjc=" + section + "&sfym=" + removeFull + "&sfct=" + removeConflict + "&szjylb=" + courseType + "&sfxx=" + loc;


        Connection.Response response = HttpUtil.sendPost(URLConstants.ELECTIVE_COURSE_LIST + args, formMap, this.system.headers);
        if (response != null) {
            String emptyListJson = response.body();
            return CourseDataHandler.getCourses(emptyListJson);
        }
        // 如果是网络原因返回空
        return new ArrayList<>();
    }

    /**
     * 获取全部公共选修课列表
     */
    public ArrayList<Course> getAllElectiveCourse() {
        return this.searchElectiveList("", "", 0, "", false, false, "", true, 200);
    }

    /**
     * 通过课程名称获取课程
     *
     * @param courseName 课程名称
     */
    public ArrayList<Course> getElectiveCourseByName(String courseName) {
        return this.searchElectiveList(courseName, "", 0, "", false, false, "", true, 200);
    }

    /**
     * 通过老师名称搜索课程,支持模糊搜索
     *
     * @param teacher 老师名称
     */
    public ArrayList<Course> getElectiveCourseByTeacher(String teacher) {
        return this.searchElectiveList("", teacher, 0, "", false, false, "", true, 200);
    }

    /**
     * 按照时间搜索课程
     *
     * @param week    星期
     * @param section 节次
     * @return 筛选出的课程
     */
    public ArrayList<Course> getElectiveCourseByWeek(int week, String section) {
        return this.searchElectiveList("", "", week, section, false, false, "", true, 200);
    }

    /**
     * @param removeFull     过滤已满课程
     * @param removeConflict 过滤冲突课程
     * @param loc            过滤限选课程
     * @return 筛选出的课程
     */
    public ArrayList<Course> getElectiveCourseByStatement(boolean removeFull, boolean removeConflict, boolean loc) {
        return this.searchElectiveList("", "", 0, "", removeFull, removeConflict, "", loc, 200);
    }

}
