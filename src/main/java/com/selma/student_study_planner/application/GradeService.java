package com.selma.student_study_planner.application;

import com.selma.student_study_planner.domain.Course;
import com.selma.student_study_planner.domain.Grade;
import com.selma.student_study_planner.infrastructure.CourseRepository;
import com.selma.student_study_planner.infrastructure.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    public List<Grade> getGradesForCourse(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    public Grade createGrade(Long courseId, Grade grade) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Long gradeId, Grade updatedDate) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + gradeId));

        grade.setScore(updatedDate.getScore());
        grade.setDateRecorded(updatedDate.getDateRecorded());
        grade.setType(updatedDate.getType());
        grade.setWeight(updatedDate.getWeight());

        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    public Double getWeightedAverageForCourse(Long courseId) {
        List<Grade> grades = gradeRepository.findByCourseId(courseId);

        if (grades.isEmpty()) {
            return null;
        }

        double weightedSum = 0;
        double totalWeight = 0;

        for (Grade grade : grades) {
            weightedSum += grade.getScore() * grade.getWeight();
            totalWeight += grade.getWeight();
        }

        if (totalWeight == 0) {
            return null;
        }

        return weightedSum / totalWeight;
    }
}
