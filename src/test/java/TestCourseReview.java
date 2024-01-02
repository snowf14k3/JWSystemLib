import moe.snowflake.jwSystem.JWSystem;
import moe.snowflake.jwSystem.utils.URLConstants;
import org.junit.jupiter.api.Test;

public class TestCourseReview {


    @Test
    public void testReview() {
        // 使用备用路线登录
        URLConstants.useBackupLoginServer();

        JWSystem system = new JWSystem(false).login("username","password");

        // W.I.P
        if (system.isJWLogged()) {
            System.out.println(system.getCourseReviewManager().getAllCourseReview());
            system.exitSystem();
        }
    }

}
