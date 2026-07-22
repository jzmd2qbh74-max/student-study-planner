package com.selma.student_study_planner.infrastructure;

import com.selma.student_study_planner.domain.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByUserId(Long userId);

    @Query("SELECT s FROM StudySession s WHERE s.user.id = :userId " +
            "AND s.startTime < :endTime AND s.endTime > :startTime")
    List<StudySession> findOverlappingSessions(Long userId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT s FROM StudySession s WHERE s.user.id = :userId " +
            "AND s.id != :sessionId " +
            " AND s.startTime < :endTime AND s.endTime > :startTime")
    List<StudySession> findOverlappingSessionsExcludingSelf(
            @Param("userId") Long userId,
            @Param("sessionId") Long sessionId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    @Query("SELECT s FROM StudySession s WHERE s.user.id = :userId" +
            " AND s.startTime < :examDate")
    List<StudySession> findStudySessionsBeforeDate(Long userId, LocalDateTime examDate);
}
