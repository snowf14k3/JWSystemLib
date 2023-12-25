package moe.snowflake.courseSelect;


import moe.snowflake.courseSelect.manager.CourseSelectManager;
import moe.snowflake.courseSelect.manager.CourseReviewManager;
import moe.snowflake.courseSelect.utils.HttpUtil;
import moe.snowflake.courseSelect.utils.PasswordUtil;
import moe.snowflake.courseSelect.utils.URLConstants;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class JWSystem {
    public Connection.Response jwLoggedResponse;
    public Connection.Response courseSelectSystemResponse;
    public Map<String, String> headers = new HashMap<>();
    private final boolean loginCourseSelectWeb;
    private final CourseSelectManager courseSelectManager;
    private final CourseReviewManager courseReviewManager;

    public JWSystem() {
        this.loginCourseSelectWeb = true;
        this.courseSelectManager = new CourseSelectManager(this);
        this.courseReviewManager = new CourseReviewManager(this);
    }

    public JWSystem(boolean loginCourseSelectWeb) {
        this.loginCourseSelectWeb = loginCourseSelectWeb;
        this.courseSelectManager = new CourseSelectManager(this);
        this.courseReviewManager = new CourseReviewManager(this);
    }

    /**
     * 判断教务是否登录成功
     *
     * @return 是否登录成功
     */
    public boolean isJWLogged() {
        try {
            if (this.jwLoggedResponse != null) {
                // 只要有这个元素即登录失败
                Element element = this.jwLoggedResponse.parse().getElementById("showMsg");
                if (element != null) return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断选课系统是否登录成功
     *
     * @return 选课系统是否登录
     */
    public boolean isCourseLogged() {
        try {
            return this.isJWLogged() &&
                    this.courseSelectSystemResponse != null && !this.courseSelectSystemResponse.parse().title().contains("登录");
        } catch (IOException e) {
            return false;
        }
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
    public JWSystem login(String username, String password) {
        Map<String, String> formData = new HashMap<>();
        formData.put("userAccount", username);
        formData.put("userPassword", "");
        formData.put("encoded", new String(Base64.getEncoder().encode(username.getBytes())) + "%%%" + new String(Base64.getEncoder().encode(password.getBytes())));

        // 登录成功的 响应
        Connection.Response response = HttpUtil.sendPost(URLConstants.LOGIN, formData);
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
     * 先登录学生选课系统,让后台存JSESSIONID
     * 并且设置登录成功的
     */
    public void loginCourseWeb() {
        // 是否登录选课系统
        if (isLoginCourseSelectWeb()) {
            this.courseSelectSystemResponse = HttpUtil.sendGet(URLConstants.COURSE_LOGIN_WEB, this.headers);
        }
    }

    /**
     * 更慢的登录
     *
     * @param username 账号
     * @param password 密码
     */
    @Deprecated
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

            Connection.Response response = HttpUtil.sendPost(URLConstants.LOGIN2, formData, this.headers);

            if (response != null) {
                // 重定向到 URLConstants.LOGIN2 + method= jwxt + ticqzket= token
                Connection.Response ref = HttpUtil.sendGet(response.header("Location"));
                //  登录成功分发 cookie
                this.jwLoggedResponse = ref;

                if (this.jwLoggedResponse != null) {
                    this.setHeaders(ref.cookie("JSESSIONID"));
                    this.loginCourseWeb();
                } else {
                    System.err.println("response error....");
                }
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
            if (exitSelect.body().contains("true")) System.out.println("退出选课系统成功");
            // 教务系统退出
            if (exitAll.body().contains("jsxsd")) System.out.println("退出教务系统成功");
        } else {
            System.err.println("unknown error !");
        }

    }

    public boolean isLoginCourseSelectWeb() {
        return this.loginCourseSelectWeb;
    }

    public CourseSelectManager getCourseSelectManager() {
        return courseSelectManager;
    }

    public CourseReviewManager getCourseReviewManager() {
        return courseReviewManager;
    }

}