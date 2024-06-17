import moe.snowflake.jwSystem.JWSystem;
import moe.snowflake.jwSystem.course.CourseReview;
import moe.snowflake.jwSystem.course.Score;
import moe.snowflake.jwSystem.manager.URLManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCourseReview {


    @Test
    public void testReview() {
        // 使用备用路线登录
//        URLManager.useLocalNetServer(1);

        // 记得选课前去查看,本学期的 zbid 是多少，否则显示的就是上一学期的课程
        // 浏览器url上就有
        JWSystem.zbID = "09EC4EAFA547423EA6494389B2729552";

        JWSystem system = new JWSystem().login("username", "password");

        // W.I.P
        if (system.isJWLogged()) {
            // 拿到全部可评价的
            List<CourseReview> courseReviewList = system.getCourseReviewManager().getAllCourseReview();

            // 获取第一个进行评价
            system.getCourseReviewManager().review(courseReviewList.get(0), Score.excellence);

            system.exit();
        }
    }

}
