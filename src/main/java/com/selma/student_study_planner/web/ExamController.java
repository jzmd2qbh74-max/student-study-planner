package com.selma.student_study_planner.web;

import com.selma.student_study_planner.application.ExamService;
import com.selma.student_study_planner.domain.Exam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Exam>> getExamsForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(examService.getExamForCourse(courseId));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Exam> createExam(
            @PathVariable Long courseId,
            @Valid @RequestBody Exam exam
    ) {
        return ResponseEntity.ok(examService.createExam(courseId, exam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(
            @PathVariable Long id,
            @Valid @RequestBody Exam updatedData
    ) {
        return ResponseEntity.ok(examService.updateExam(id, updatedData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/needs-study-time")
    public ResponseEntity<Boolean> needsStudyTime(@PathVariable Long id) {
        return ResponseEntity.ok(examService.hasNoStudySessionsScheduled(id));
    }

    @PostMapping("/{id}/send-reminder")
    public ResponseEntity<String> sendReminder(@PathVariable Long id) {
        examService.sendReminderIfNeeded(id);
        return ResponseEntity.ok("Reminder check complete");
    }
}
