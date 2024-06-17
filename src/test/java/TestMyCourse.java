import moe.snowflake.jwSystem.JWSystem;
import moe.snowflake.jwSystem.course.Course;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestMyCourse {

    @Test
    public void test() throws IOException {
        // 记得选课前去查看,本学期的 zbid 是多少，否则显示的就是上一学期的课程
        // 浏览器url上就有
        JWSystem.zbID = "09EC4EAFA547423EA6494389B2729552";
        JWSystem system = new JWSystem().login("username", "password");

        if (system.isCourseLogged()) {

            System.out.println("当前课程查询结果: ");
            ArrayList<Course> courses = system.getCourseSelectManager().getCurrentCourses();

            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                // 输出课程名称 老师 jxid
                System.out.printf("%s,%s,%s,%s%n", i+1,course.getName(), course.getTeacher(), course.getJxID());
            }

            // 进行退课操作
            Scanner sr = new Scanner(System.in);
            while (true) {
                int select = sr.nextInt() - 1;
                String reason = sr.next();

                if (select > 0 && select < courses.size()) {
                    Course selected = courses.get(select);
                    // 选择那个课程
                    if (system.getCourseSelectManager().exitSelectedCourse(selected,reason)) {
                        System.out.println(selected.getName() + " 退课成功");
                        continue;
                    }
                    System.out.println("退课失败");
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
