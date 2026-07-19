package com.selma.student_study_planner.infrastructure;

import com.selma.student_study_planner.domain.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByUserId(Long userId);
}
