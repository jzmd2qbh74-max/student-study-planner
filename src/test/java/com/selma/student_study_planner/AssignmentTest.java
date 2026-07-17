package com.selma.student_study_planner;

import com.selma.student_study_planner.domain.Assignment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {

    @Test
    void newAssignmentIsNotCompletedByDefault() {
        Assignment assignment = new Assignment();
        assertFalse(assignment.isCompleted());
    }
}
