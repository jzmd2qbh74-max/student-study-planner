package com.selma.student_study_planner.application;

import com.selma.student_study_planner.domain.Course;
import com.selma.student_study_planner.domain.Exam;
import com.selma.student_study_planner.infrastructure.CourseRepository;
import com.selma.student_study_planner.infrastructure.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public List<Exam> getExamForCourse(Long courseId) {
        return examRepository.findByCourseId(courseId);
    }

    public Exam createExam(Long courseId, Exam exam) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        exam.setCourse(course);
        return examRepository.save(exam);
    }

    public Exam updateExam(Long examId, Exam updateDate) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        exam.setTitle(updateDate.getTitle());
        exam.setTopic(updateDate.getTopic());
        exam.setExamDate(updateDate.getExamDate());

        return examRepository.save(exam);
    }

    public void deleteExam(long examId) {
        examRepository.deleteById(examId);
    }
}
