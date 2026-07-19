package com.selma.student_study_planner.application;

import com.selma.student_study_planner.domain.Course;
import com.selma.student_study_planner.domain.User;
import com.selma.student_study_planner.infrastructure.CourseRepository;
import com.selma.student_study_planner.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> getCoursesForUser(Long userId) {
        return courseRepository.findByUserId(userId);
    }

    public Course createCourse(Long userId, Course course) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        course.setUser(user);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {

        courseRepository.deleteById(courseId);
    }
}
