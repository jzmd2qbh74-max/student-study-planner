package com.selma.student_study_planner;

import com.selma.student_study_planner.application.StudySessionService;
import com.selma.student_study_planner.domain.StudySession;
import com.selma.student_study_planner.domain.User;
import com.selma.student_study_planner.infrastructure.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
public class StudySessionServiceIntegrationTest {

    @Container
    @SuppressWarnings("resource")
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private StudySessionService studySessionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void creatingOverlappingSessionThrowsException() {
        User newUser = new User();
        newUser.setEmail("integrationtest@example.com");
        newUser.setPasswordHash(passwordEncoder.encode("password123"));
        newUser.setFullName("Integration Test User");
        User savedUser = userRepository.save(newUser);

        StudySession first = new StudySession();
        first.setSubject("Math");
        first.setStartTime(LocalDateTime.of(2026, 8, 1, 10, 0));
        first.setEndTime(LocalDateTime.of(2026, 8, 1, 12, 0));
        studySessionService.createSession(savedUser.getId(), first);

        StudySession overlapping = new StudySession();
        overlapping.setSubject("Physics");
        overlapping.setStartTime(LocalDateTime.of(2026, 8, 1, 11, 0));
        overlapping.setEndTime(LocalDateTime.of(2026, 8, 1, 13, 0));

        assertThrows(RuntimeException.class, () ->
                studySessionService.createSession(savedUser.getId(), overlapping));
    }
}
