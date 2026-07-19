package com.selma.student_study_planner.infrastructure;

import com.selma.student_study_planner.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCourseId(Long courseId);
}
