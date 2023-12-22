package moe.select;


import moe.select.course.CourseForm;

import moe.select.course.Course;
import moe.select.utils.HttpUtil;
import moe.select.utils.JsonUtil;
import moe.select.utils.PasswordUtil;
import moe.select.utils.URLConstants;
import org.jsoup.Connection;

import javax.swing.*;
import java.util.*;

public class SelectorCore {
    public static SelectorCore instance;
    public Connection.Response loggedResponse;
    public String cookie;

    public SelectorCore() {
        instance = this;
    }

    /**
     * 直接导向目标API的登录
     *
     * @param username 用户名
     * @param password 密码
     */
    public void login(String username, String password) {
        Map<String, String> form = new HashMap<>();
        form.put("userAccount", username);
        form.put("userPassword", "");
        form.put("encoded", new String(Base64.getEncoder().encode(username.getBytes())) + "%%%" + new String(Base64.getEncoder().encode(password.getBytes())));

        // 登录成功的 响应
        Connection.Response response = HttpUtil.sendPost(URLConstants.LOGIN2, form);
        if (response != null) {

            this.loggedResponse = response;
            this.cookie = response.cookie("JSESSIONID");

            this.loginCourseWeb();
        } else {
            System.err.println("network error...");
        }
    }


    public void exitSystem() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "JSESSIONID=" + this.cookie);
        Connection.Response exitSelect = HttpUtil.sendGet(URLConstants.EXIT_COURSE_WEB, headers);

        Connection.Response exitAll = HttpUtil.sendGet(URLConstants.EXIT_JWSYSTEM, headers);

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
     * 用于直接登录选课系统
     */
    public void loginCourseWeb() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "JSESSIONID=" + this.cookie);
        // 得事先登录学生选课系统,让后台存SESSIONID
        HttpUtil.sendGet(URLConstants.COURSE_LOGIN, headers);
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

    public void selectCourse(String url, Course course) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "JSESSIONID=" + this.cookie);
        // 得事先登录学生选课系统,让后台存SESSIONID
        Connection.Response response = HttpUtil.sendGet(url
                .replace("<kcid>", course.getKcid())
                .replace("<jx0404id>", course.getJxID()), headers);

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
     * 更慢的登录
     *
     * @param username 账号
     * @param password 密码
     * @throws RuntimeException
     */
    public void login1(String username, String password) throws RuntimeException {
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

            Map<String, String> form = new HashMap<>();
            form.put("userAccount", "");
            form.put("userPassword", "");
            form.put("encoded", encoded);

            // 用header 加cookie
            // 自带是cookie 无法识别
            Map<String, String> headers = new HashMap<>();
            headers.put("Cookie", "JSESSIONID=" + keyGet.cookie("JSESSIONID"));
            Connection.Response response = HttpUtil.sendPost(URLConstants.LOGIN, form, headers);

            if (response != null) {
                // 重定向到 URLConstants.LOGIN2 + method= jwxt + ticqzket= token
                Connection.Response ref = HttpUtil.sendGet(response.header("Location"));
                //  登录成功分发 cookie
                this.loggedResponse = ref;
                this.cookie = ref.cookie("JSESSIONID");

                this.loginCourseWeb();
            }
        } else {
            System.err.println("network error....");
        }
    }

    /**
     * 列出全部必修课
     */
    public void searchAllRequiredList(){
        searchRequiredList(30);
    }

    /**
     * 列出必修课的列表
     * @param size
     */
    public void searchRequiredList(int size) {
        CourseForm cf = new CourseForm();
        cf.putRequiredFormData("3", "0", String.valueOf(size));

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "JSESSIONID=" + this.cookie);
        Connection.Response response = HttpUtil.sendPost(URLConstants.REQUIRED_COURSE_LIST, cf.getFormData(), headers);

        StringBuilder sb = new StringBuilder();
        ArrayList<Course> courses = JsonUtil.getCourses(response.body());
        for (Course course : courses) {
            sb.append(course.toString()).append("\n");
        }
        // 显示全部
        JOptionPane.showInputDialog(null, "", sb.toString());
    }

    /**
     * @param courseName     课程名称
     * @param teacher        授课老师
     * @param week           星期
     * @param section        节次
     * @param removeFull     过滤已满课程
     * @param removeConflict 过滤冲突课程
     * @param loc            过滤限选课程
     * @param size           显示数量 空字符串显示全部
     */
    public void searchElectiveList(String courseName, String teacher, String week, String section, boolean removeFull, boolean removeConflict, String courseType, boolean loc, int size) {
        CourseForm cf = new CourseForm(new LinkedHashMap<>());

        cf.putElectiveFormData("3", "0", String.valueOf(size));

        // 查询的参数
        String args = "?kcxx=" + courseName + "&skls=" + teacher + "&skxq=" + week + "&skjc=" + section + "&sfym=" + removeFull + "&sfct=" + removeConflict + "&szjylb=" + courseType + "&sfxx=" + loc;

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "JSESSIONID=" + this.cookie);
        Connection.Response response = HttpUtil.sendPost(URLConstants.ELECTIVE_COURSE_LIST + args, cf.getFormData(), headers);
        String emptyListJson = response.body();

        StringBuilder sb = new StringBuilder();
        ArrayList<Course> courses = JsonUtil.getCourses(emptyListJson);
        for (Course course : courses) {
            sb.append(course.toString()).append("\n");
        }
        JOptionPane.showInputDialog(null, "", sb.toString());
    }

    /**
     * 获取全部公共选修课列表
     */
    public void getAllElectiveCourse() {
        searchElectiveList("", "", "", "", false, false, "", true, 300);
    }

    /**
     * 通过课程名称获取课程
     * @param courseName 课程名称
     */
    public void getElectiveCourseByName(String courseName) {
        searchElectiveList(courseName, "", "", "", false, false, "", true, 300);
    }

    /**
     * 通过老师名称搜索课程,支持模糊搜索
     * @param teacher 老师名称
     */
    public void getElectiveCourseByTeacher(String teacher) {
        searchElectiveList("", teacher, "", "", false, false, "", true, 300);
    }


}
