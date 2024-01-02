import moe.snowflake.jwSystem.JWSystem;
import moe.snowflake.jwSystem.manager.URLManager;
import org.junit.jupiter.api.Test;

public class TestCourseReview {


    @Test
    public void testReview() {
        // 使用备用路线登录
        URLManager.useBackupLoginServer(2);

        JWSystem system = new JWSystem(false).login("username","password");

        // W.I.P
        if (system.isJWLogged()) {
            System.out.println(system.getCourseReviewManager().getAllCourseReview());
            system.exit();
        }
    }

}
