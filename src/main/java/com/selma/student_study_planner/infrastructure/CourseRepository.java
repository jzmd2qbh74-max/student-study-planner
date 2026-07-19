package com.selma.student_study_planner.infrastructure;

import com.selma.student_study_planner.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUserId(Long userId);
}
