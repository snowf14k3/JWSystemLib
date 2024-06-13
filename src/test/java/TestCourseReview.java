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
