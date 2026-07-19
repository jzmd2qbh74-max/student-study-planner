package com.selma.student_study_planner.web;

import com.selma.student_study_planner.application.CourseService;
import com.selma.student_study_planner.domain.Course;
import com.selma.student_study_planner.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getMyCourses(@AuthenticationPrincipal User user) {
        List<Course> courses = courseService.getCoursesForUser(user.getId());
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody Course course
    ) {
        Course created = courseService.createCourse(user.getId(), course);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
