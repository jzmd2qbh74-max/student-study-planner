package com.selma.student_study_planner.web;

import com.selma.student_study_planner.application.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.selma.student_study_planner.domain.Assignment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsForCourse(courseId));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Assignment> createAssignment(
            @PathVariable Long courseId,
            @Valid @RequestBody Assignment assignment
    ) {
        return ResponseEntity.ok(assignmentService.createAssignment(courseId, assignment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody Assignment updatedData
    ) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, updatedData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
