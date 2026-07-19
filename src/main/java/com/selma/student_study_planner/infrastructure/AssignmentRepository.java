package com.selma.student_study_planner.infrastructure;

import com.selma.student_study_planner.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourseId(Long courseId);
}
