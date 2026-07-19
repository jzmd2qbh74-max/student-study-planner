package com.selma.student_study_planner.application;

import com.selma.student_study_planner.domain.Assignment;
import com.selma.student_study_planner.domain.Course;
import com.selma.student_study_planner.infrastructure.AssignmentRepository;
import com.selma.student_study_planner.infrastructure.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public List<Assignment> getAssignmentsForCourse(Long courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    public Assignment createAssignment(Long courseId, Assignment assignment) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        assignment.setCourse(course);
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Long assignmentId, Assignment updatedDate) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + assignmentId));

        assignment.setTitle(updatedDate.getTitle());
        assignment.setDescription(updatedDate.getDescription());
        assignment.setDueDate(updatedDate.getDueDate());
        assignment.setCompleted(updatedDate.isCompleted());

        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }
}
