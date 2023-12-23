import moe.snowflake.courseSelect.CourseSelectCore;
import org.junit.jupiter.api.Test;

public class TestMyCourse {

    @Test
    public void test(){
        CourseSelectCore core = new CourseSelectCore(false).login("username", "password");

        if (core.isCourseLogged()) {

            System.out.println("当前课程查询结果: ");
            System.out.println(core.getCurrentCourses());

            core.exitSystem();
        }

    }

}
