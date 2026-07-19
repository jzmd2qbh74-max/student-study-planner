package com.selma.student_study_planner.application;

import com.selma.student_study_planner.domain.User;
import com.selma.student_study_planner.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String email, String rawPassword, String fullName) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use: " + email);
        }

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setFullName(fullName);

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found by email: " + email));
    }

    public boolean emailExists(String email){
        return userRepository.existsByEmail(email);
    }
}
