import moe.snowflake.jwSystem.JWSystem;
import moe.snowflake.jwSystem.course.Course;
import moe.snowflake.jwSystem.manager.CourseSelectManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestLocalSelectCourse {


    @Test
    public void loadLocalCourse(){
        List<Course> courses = CourseSelectManager.loadLocalCourse
                (new File("/courses.csv"));

        for (int i = 1; i < courses.size(); i++) {
            Course course = courses.get(i);
            // 输出课程名称 老师 jxid kcid
            System.out.printf("%s,%s,%s,%s,%s%n", i + 1, course.getName(), course.getTeacher(), course.getJxID(),course.getKcid());
        }
    }

    @Test
    public void test() {
        JWSystem system = new JWSystem().login("username", "password");

        // 要求必须对选课系统进行登录
        // 否则选课系统不会返回任何数据
        if (system.isCourseLogged()) {

            List<Course> courses = CourseSelectManager.loadLocalCourse
                    (new File("/courses.csv"));

            for (int i = 1; i < courses.size(); i++) {
                Course course = courses.get(i);
                // 输出课程名称 老师 jxid kcid
                System.out.printf("%s,%s,%s,%s,%s%n", i + 1, course.getName(), course.getTeacher(), course.getJxID(),course.getKcid());
            }

            Scanner sr = new Scanner(System.in);
            while (true) {
                int select = sr.nextInt() - 1;

                if (select > 0 && select < courses.size()) {
                    Course selected = courses.get(select);
                    // 选择那个课程
                    if (system.getCourseSelectManager().selectCourse(selected)
                    ) {
                        System.out.println("选课成功 ");
                    } else {
                        System.out.println("选课失败");
                    }
                } else if (select == -2) {
                    // -1 取消选课
                    break;
                }
            }
            //关闭流
            sr.close();
            // 退出系统
            system.exit();
        } else {
            System.err.println("登录失败");
        }

    }

}
