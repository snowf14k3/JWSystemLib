package moe.selector;


import moe.selector.list.CourseForm;

import moe.selector.object.Course;
import moe.selector.utils.HttpUtil;
import moe.selector.utils.JsonUtil;
import moe.selector.utils.PasswordUtil;
import moe.selector.utils.URLConstants;
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
            System.out.println(response.cookies());

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
        Connection.Response exitSeletor = HttpUtil.sendGet(URLConstants.EXIT_COURSE_WEN,headers);

        // 退出
        System.out.println(exitSeletor.body());

        Connection.Response exitAll = HttpUtil.sendGet(URLConstants.EXIT_JWSYSTEM,headers);
        System.out.println(exitAll.body());
    }

    /**
     * 用于直接登录选课系统
     */
    public void loginCourseWeb() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "JSESSIONID=" + this.cookie);
        // 先证明我登录了学生选课系统
        HttpUtil.sendGet(URLConstants.COURSE_LOGIN, headers);
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

                // 导向其网站拿
                Connection.Response ref = HttpUtil.sendGet(response.header("Location"));

                System.out.println("=========================");
                System.out.println("encoded: ");
                System.out.println(encoded);
                System.out.println("login form:");
                System.out.println(form);
                System.out.println("=========================");
                System.out.println();

                //  登录成功分发 cookie
                this.loggedResponse = ref;
                this.cookie = ref.cookie("JSESSIONID");

                this.loginCourseWeb();
            }
        } else {
            System.err.println("network error....");
        }
    }

    public void searchRequiredList(String size) {
        CourseForm cf = new CourseForm();
        cf.putRequiredFormData("3", "0", size);

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
    public void searchElectiveList(String courseName, String teacher, String week, String section, boolean removeFull, boolean removeConflict, String courseType, boolean loc, String size) {
        CourseForm cf = new CourseForm(new LinkedHashMap<>());

        if (size.isEmpty()) {
            // 第一次请求 1一个长度
            cf.putElectiveFormData("1", "0", "1");
        } else {
            cf.putElectiveFormData("3", "0", size);
        }

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


    public void getAllElectiveCourse() {
        searchElectiveList("", "", "", "", false, false, "", true, "300");
    }






}
