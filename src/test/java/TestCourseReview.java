import moe.snowflake.jwSystem.JWSystem;
import org.junit.jupiter.api.Test;

public class TestCourseReview {


    @Test
    public void testReview() {
        JWSystem system = new JWSystem(false).login("username","password");

        // W.I.P
        if (system.isJWLogged()) {
            System.out.println(system.getCourseReviewManager().getAllCourseReview());
            system.exitSystem();
        }
    }

}
