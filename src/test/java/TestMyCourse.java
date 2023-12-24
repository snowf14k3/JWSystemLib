import moe.snowflake.courseSelect.CourseSelectCore;
import moe.snowflake.courseSelect.course.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestMyCourse {

    @Test
    public void test() {
        CourseSelectCore core = new CourseSelectCore(false).login("username", "password");

        if (core.isCourseLogged()) {

            System.out.println("当前课程查询结果: ");
            ArrayList<Course> courses = core.getCurrentCourses();

            for (Course course : courses) {
                // 输出课程名称 老师 kcid
                System.out.printf("%s,%s,%s%n", course.getName(), course.getTeacher(), course.getKcid());
            }

            // 进行退课操作
            core.exitSelectedCourse(courses.get(1), "退课原因");
            // 退出系统
            core.exitSystem();
        } else {
            System.err.println("登录失败");
        }

    }

}
