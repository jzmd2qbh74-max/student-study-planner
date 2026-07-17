import com.selma.student_study_planner.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    void newUserHasNoCoursesByDefault() {
        User user = new User();
        assertTrue(user.getCourses().isEmpty());
    }
}
