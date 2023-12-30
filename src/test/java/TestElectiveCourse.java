import moe.snowflake.courseSelect.JWSystem;
import moe.snowflake.courseSelect.course.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class TestElectiveCourse {

    @Test
    public void test() {
        // 登录
        JWSystem system = new JWSystem().login("username", "password");

        // 检测两个系统是否都登录了
        if (system.isCourseLogged()) {
            // 查找课程
//            ArrayList<Course> courses = system.getCourseSelectManager().getAllElectiveCourse();
//            ArrayList<Course> courses = system.getCourseSelectManager().getElectiveCourseByTeacher("网络课程");
            ArrayList<Course> courses = system.getCourseSelectManager().getAllRequiredList();

            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
//                System.out.printf("%s,%s,%s,%s,%s,%s%n",course.getName(),course.getType(),course.getTeacher(),course.getKcid(),course.getJxID(),course.getScore());
                System.out.printf("序号 %d | 老师: %s | 课程名称: %s | 剩余数量: %s %n", i + 1, course.getTeacher(), course.getName(), course.getRemain());
            }

            Scanner sr = new Scanner(System.in);
            while (true) {
                int select = sr.nextInt() - 1;

                if (select > 0 && select < courses.size()) {
                    Course selected = courses.get(select);
                    // 选择那个课程
                    if (system.getCourseSelectManager().selectRequiredCourse(selected)) {
                        System.out.println("选课成功 " + (selected.getRemain() - 1));
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
            system.exitSystem();
        } else {
            System.out.println("error");
        }

    }

}
