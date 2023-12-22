package moe.select;


import moe.select.course.CourseForm;

import moe.select.course.Course;
import moe.select.utils.HttpUtil;
import moe.select.utils.CourseHandler;
import moe.select.utils.PasswordUtil;
import moe.select.utils.URLConstants;
import org.jsoup.Connection;

import java.util.*;

public class CourseSelectCore {
    public static CourseSelectCore instance;
    public Connection.Response jwLoggedResponse;
    public Connection.Response courseSelectSystemResponse;
    public Map<String, String> headers = new HashMap<>();
    private final boolean selectCourse;

    public CourseSelectCore() {
        instance = this;
        this.selectCourse = true;
    }

    public CourseSelectCore(boolean selectCourse) {
        instance = this;
        this.selectCourse = selectCourse;
    }

    /**
     * 判断教务是否登录成功
     * @return
     */
    public boolean isJWLogged(){
        return this.jwLoggedResponse != null && (this.jwLoggedResponse.statusCode() == 302 || this.jwLoggedResponse.statusCode() == 200);
    }

    /**
     * 判断选课系统是否登录成功
     * @return
     */
    public boolean isCourseLogged(){
        return this.jwLoggedResponse != null && (this.jwLoggedResponse.statusCode() == 302 || this.jwLoggedResponse.statusCode() == 200) &&
                this.courseSelectSystemResponse != null && this.courseSelectSystemResponse.statusCode() == 200;
    }

    /**
     * 设置全局的 headers,包含cookie
     * jsoup设置的cookie无法识别
     *
     * @param cookie cookie
     */
    private void setHeaders(String cookie) {
        headers.put("Cookie", "JSESSIONID=" + cookie);
    }

    /**
     * 直接导向目标API的登录
     *
     * @param username 用户名
     * @param password 密码
     */
    public CourseSelectCore login(String username, String password) {
        Map<String, String> formData = new HashMap<>();
        formData.put("userAccount", username);
        formData.put("userPassword", "");
        formData.put("encoded", new String(Base64.getEncoder().encode(username.getBytes())) + "%%%" + new String(Base64.getEncoder().encode(password.getBytes())));

        // 登录成功的 响应
        Connection.Response response = HttpUtil.sendPost(URLConstants.LOGIN2, formData);
        if (response != null) {
            this.jwLoggedResponse = response;
            this.setHeaders(response.cookie("JSESSIONID"));
            this.loginCourseWeb();
        } else {
            System.err.println("network error...");
        }
        return this;
    }

    /**
     * 用于直接登录选课系统
     * 先登录学生选课系统,让后台存SESSIONID
     * 并且设置登录成功的
     */
    public void loginCourseWeb() {
        this.courseSelectSystemResponse = HttpUtil.sendGet(URLConstants.COURSE_LOGIN, this.headers);
    }

    /**
     * 更慢的登录
     *
     * @param username 账号
     * @param password 密码
     */
    public void login1(String username, String password) {
        Connection.Response keyGet = HttpUtil.sendGet(URLConstants.LOGIN_DATA);
        if (keyGet != null && keyGet.statusCode() == 200) {
            // 拿数据的
            String dataStr = keyGet.body();

            // 先检测他能否拿到加密数据的密钥
            if (dataStr.split("#").length < 2) {
                throw new RuntimeException("server or network error !");
            }

            // 获取加密后的密码数据
            String encoded = PasswordUtil.encodeUserInfo(dataStr, username + "%%%" + password);
            // 测试数据

            Map<String, String> formData = new HashMap<>();
            formData.put("userAccount", "");
            formData.put("userPassword", "");
            formData.put("encoded", encoded);

            Connection.Response response = HttpUtil.sendPost(URLConstants.LOGIN, formData, this.headers);

            if (response != null) {
                // 重定向到 URLConstants.LOGIN2 + method= jwxt + ticqzket= token
                Connection.Response ref = HttpUtil.sendGet(response.header("Location"));
                //  登录成功分发 cookie
                this.jwLoggedResponse = ref;

                this.setHeaders(ref.cookie("JSESSIONID"));
                this.loginCourseWeb();
            }
        } else {
            System.err.println("network error....");
        }
    }

    /**
     * 退出系统使用
     */
    public void exitSystem() {
        // 退出选课系统
        Connection.Response exitSelect = HttpUtil.sendGet(URLConstants.EXIT_COURSE_WEB, this.headers);
        // 退出JW整个系统
        Connection.Response exitAll = HttpUtil.sendGet(URLConstants.EXIT_JWSYSTEM, this.headers);

        // DEBUG INFO
        if (exitSelect != null && exitAll != null) {
            // 退出选课系统的response body
            System.out.println(exitSelect.body());
            // 教务系统退出
            System.out.println(exitAll.body());
        } else {
            System.err.println("unknown error !");
        }

    }

    /**
     * 选择公共选修课
     *
     * @param course 课程对象
     */
    public void selectElectiveCourse(Course course) {
        selectCourse(URLConstants.ELECTIVE_COURSE_SELECT, course);
    }

    /**
     * 选择公共必修课
     *
     * @param course 课程的对象
     */
    public void selectRequiredCourse(Course course) {
        selectCourse(URLConstants.REQUIRED_COURSE_SELECT, course);
    }

    /**
     *
     * @param url 选课的 URL
     * @param course 课程的id
     */
    private void selectCourse(String url, Course course) {

        // 得事先登录学生选课系统,让后台存SESSIONID
        Connection.Response response = HttpUtil.sendGet(url
                .replace("<kcid>", course.getKcid())
                .replace("<jx0404id>", course.getJxID()), this.headers);

        //response
        // {"success":true,"message":"选课成功","jfViewStr":""}
        // {"success":[true,false],"message":"选课失败：此课堂选课人数已满！"}

        String message = response.body();
        if (message.contains("true")) {
            System.out.println("选课成功 剩余人数 " + (Integer.parseInt(course.getRemain()) - 1));
            System.out.println(course);
        } else if (message.contains("true,false")) {
            System.out.println("选课失败");
            System.out.println(course);
        }

    }

    /**
     * 列出全部必修课
     */
    public ArrayList<Course> searchAllRequiredList() {
        return searchRequiredList(30);
    }

    /**
     * 列出必修课的列表
     *
     * @param size
     */
    public ArrayList<Course> searchRequiredList(int size) {
        CourseForm cf = new CourseForm();
        cf.putRequiredFormData("3", 0, size);

        Connection.Response response = HttpUtil.sendPost(URLConstants.REQUIRED_COURSE_LIST, cf.getFormData(), this.headers);

        return CourseHandler.getCourses(response.body());
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
        CourseForm cf = new CourseForm(new LinkedHashMap<>());

        String weekStr = "";
        if (week != 0) {
            weekStr = String.valueOf(week);
        }

        cf.putElectiveFormData("3", 0, size);

        // 查询的参数
        String args = "?kcxx=" + courseName + "&skls=" + teacher + "&skxq=" + weekStr + "&skjc=" + section + "&sfym=" + removeFull + "&sfct=" + removeConflict + "&szjylb=" + courseType + "&sfxx=" + loc;


        Connection.Response response = HttpUtil.sendPost(URLConstants.ELECTIVE_COURSE_LIST + args, cf.getFormData(), this.headers);
        if (response != null) {
            String emptyListJson = response.body();
            return CourseHandler.getCourses(emptyListJson);
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
     *
     * @param removeFull     过滤已满课程
     * @param removeConflict 过滤冲突课程
     * @param loc            过滤限选课程
     * @return 筛选出的课程
     */
    public ArrayList<Course> getElectiveCourseByStatement(boolean removeFull, boolean removeConflict, boolean loc){
        return this.searchElectiveList("", "", 0, "", removeFull, removeConflict, "", loc, 200);
    }

    public boolean isSelectCourse() {
        return selectCourse;
    }

}
