package moe.select;

import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) throws Throwable {
        Scanner sr = new Scanner(System.in);

        String username = sr.next();
        String password = sr.next();

        SelectorCore core = new SelectorCore();
        // 登录
        core.login(username, password);

        /* 是否登录成功 */
        if (core.loggedResponse != null && (core.loggedResponse.statusCode() == 302 || core.loggedResponse.statusCode() == 200)) {
//            core.getElectiveCourseByTeacher("陆");

            System.out.println(core.cookie);
            // 退出系统
            core.exitSystem();
        } else {
            System.err.println("error !");
        }
    }

}









