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
        session.setUser(user);
        return studySessionRepository.save(session);
    }

    public StudySession updateSessions(Long sessionId, StudySession updatedDate) {
        StudySession session = studySessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Study session not found with id: " + sessionId));

        session.setSubject(updatedDate.getSubject());
        session.setStartTime(updatedDate.getStartTime());
        session.setEndTime(updatedDate.getEndTime());
        session.setNotes(updatedDate.getNotes());

        return studySessionRepository.save(session);
    }

    public void deleteSession(Long sessionId) {
        studySessionRepository.deleteById(sessionId);
    }
}
