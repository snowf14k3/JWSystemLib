import moe.snowflake.courseSelect.JWSystem;
import moe.snowflake.courseSelect.course.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestMyCourse {

    @Test
    public void test() {
        JWSystem system = new JWSystem().login("username", "password");

        if (system.isCourseLogged()) {

            System.out.println("当前课程查询结果: ");
            ArrayList<Course> courses = system.getCourseSelectManager().getCurrentCourses();

            for (Course course : courses) {
                // 输出课程名称 老师 kcid
                System.out.printf("%s,%s,%s%n", course.getName(), course.getTeacher(), course.getKcid());
            }

            // 进行退课操作
            boolean state = system.getCourseSelectManager().exitSelectedCourse(courses.get(courses.size() - 1), "退课原因");
            if (state) {
                System.out.println("退课成功");
            } else {
                System.out.println("退课失败");
            }
            // 退出系统
            system.exitSystem();
        } else {
            System.err.println("登录失败");
        }

    }

}
