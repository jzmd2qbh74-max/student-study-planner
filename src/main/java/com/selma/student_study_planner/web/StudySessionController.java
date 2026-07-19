package com.selma.student_study_planner.web;

import com.selma.student_study_planner.application.StudySessionService;
import com.selma.student_study_planner.domain.StudySession;
import com.selma.student_study_planner.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-session")
@RequiredArgsConstructor
public class StudySessionController {

    private final StudySessionService studySessionService;

    @GetMapping
    public ResponseEntity<List<StudySession>> getMySessions(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(studySessionService.getMySessions(user.getId()));
    }

    @PostMapping
    public ResponseEntity<StudySession> createSession(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody StudySession session
    ) {
        return ResponseEntity.ok(studySessionService.createSession(user.getId(), session));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudySession> updateSession(
            @PathVariable Long id,
            @Valid @RequestBody StudySession updatedData
    ) {
        return ResponseEntity.ok(studySessionService.updateSessions(id, updatedData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        studySessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
