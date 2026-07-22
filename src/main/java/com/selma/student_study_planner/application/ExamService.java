package com.selma.student_study_planner.application;

import com.selma.student_study_planner.domain.Course;
import com.selma.student_study_planner.domain.Exam;
import com.selma.student_study_planner.domain.StudySession;
import com.selma.student_study_planner.domain.User;
import com.selma.student_study_planner.infrastructure.CourseRepository;
import com.selma.student_study_planner.infrastructure.ExamRepository;
import com.selma.student_study_planner.infrastructure.StudySessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final StudySessionRepository studySessionRepository;
    private final EmailService emailService;

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

    public boolean hasNoStudySessionsScheduled(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        Long userId = exam.getCourse().getUser().getId();
        LocalDateTime examDateTime = exam.getExamDate().atStartOfDay();

        List<StudySession> sessionsBefore = studySessionRepository.findStudySessionsBeforeDate(userId, examDateTime);

        return sessionsBefore.isEmpty();
    }

    public void sendReminderIfNeeded(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        boolean needsStudyTime = hasNoStudySessionsScheduled(examId);

        if (needsStudyTime) {
            User user = exam.getCourse().getUser();
            emailService.sendExamReminder(user.getEmail(), exam.getTitle(), exam.getExamDate());
        }
    }
}
