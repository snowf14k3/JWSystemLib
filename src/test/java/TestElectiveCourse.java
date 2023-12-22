import moe.snowflake.courseSelect.CourseSelectCore;
import moe.snowflake.courseSelect.course.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class TestElectiveCourse {

    @Test
    public void test() {
        // 登录
        CourseSelectCore core = new CourseSelectCore().login("username", "password");

        // 检测两个系统是否都登录了
        if (core.isCourseLogged()) {
            // 查找课程
            ArrayList<Course> courses = core.getElectiveCourseByTeacher("网络课程");

            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);

                System.out.printf("序号 %d | 老师: %s | 课程名称: %s | 剩余数量: %s %n", i + 1, course.getTeacher(), course.getName(), course.getRemain());
            }

            Scanner sr = new Scanner(System.in);
            while (true) {
                int select = sr.nextInt() - 1;

                if (select > 0 && select < courses.size()) {
                    Course selected = courses.get(select);
                    // 选择那个课程
                    if (core.selectElectiveCourse(selected)) {
                        System.out.println("选课成功 " + (selected.getRemain() - 1));
                    } else {
                        System.out.println("选课失败");
                    }
                } else if (select == -1) {
                    // -1 取消选课
                    break;
                }
            }
            // 关闭流
            sr.close();
            core.exitSystem();
        } else {
            System.out.println("error");
        }

    }

}
