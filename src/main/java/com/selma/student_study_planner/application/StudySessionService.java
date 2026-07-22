package com.selma.student_study_planner.application;

import com.selma.student_study_planner.domain.StudySession;
import com.selma.student_study_planner.domain.User;
import com.selma.student_study_planner.infrastructure.StudySessionRepository;
import com.selma.student_study_planner.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;
    private final UserRepository userRepository;

    public List<StudySession> getMySessions(Long userId) {
        return studySessionRepository.findByUserId(userId);
    }

    public StudySession createSession(Long userId, StudySession session) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        List<StudySession> overlaps = studySessionRepository.findOverlappingSessions(
                userId, session.getStartTime(), session.getEndTime());

        if (!overlaps.isEmpty()) {
            throw new RuntimeException("This session overlaps with an existing study session");
        }

        session.setUser(user);
        return studySessionRepository.save(session);
    }

    public StudySession updateSession(Long userId, Long sessionId, StudySession updatedData) {
        StudySession session = studySessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Study session not found with id: " + sessionId));

        if (!session.getUser().getId().equals(userId)) {
            throw new RuntimeException("This session does not belong to the specified user");
        }

        List<StudySession> overlaps = studySessionRepository.findOverlappingSessionsExcludingSelf(
                userId, sessionId, updatedData.getStartTime(), updatedData.getEndTime());

        if (!overlaps.isEmpty()) {
            throw new RuntimeException("This session overlaps with an existing study session");
        }

        session.setSubject(updatedData.getSubject());
        session.setStartTime(updatedData.getStartTime());
        session.setEndTime(updatedData.getEndTime());
        session.setNotes(updatedData.getNotes());

        return studySessionRepository.save(session);
    }

    public void deleteSession(Long sessionId) {
        studySessionRepository.deleteById(sessionId);
    }
}
