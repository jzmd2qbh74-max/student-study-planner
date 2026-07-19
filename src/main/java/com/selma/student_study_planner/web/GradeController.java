package com.selma.student_study_planner.web;

import com.selma.student_study_planner.application.GradeService;
import com.selma.student_study_planner.domain.Grade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.getGradesForCourse(courseId));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Grade> createGrade(
            @PathVariable Long courseId,
            @Valid @RequestBody Grade grade
    ) {
        return ResponseEntity.ok(gradeService.createGrade(courseId, grade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(
            @PathVariable Long id,
            @Valid @RequestBody Grade updatedData
    ) {
        return ResponseEntity.ok(gradeService.updateGrade(id, updatedData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
