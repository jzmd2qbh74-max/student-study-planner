package com.selma.student_study_planner.application;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendExamReminder(String toEmail, String examTitle, LocalDate examDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Upcoming Exam Reminder: " + examTitle);
        message.setText("You have an upcoming exam \"" + examTitle + "\" on " + examDate +
                ". Make sure you've scheduled study time for it!");

        mailSender.send(message);
    }
}
