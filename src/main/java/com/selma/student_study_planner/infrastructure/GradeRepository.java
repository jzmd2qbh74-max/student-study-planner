package com.selma.student_study_planner.infrastructure;

import com.selma.student_study_planner.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByCourseId(Long courseId);
}
