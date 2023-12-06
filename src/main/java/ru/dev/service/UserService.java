package ru.dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.model.User;
import ru.dev.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    public Long createUser(String username, String email, String password) {
        User user = User
                .builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public void updateUser(Long userId, String newUsername, String newEmail, String newPassword) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        existingUser.setUsername(newUsername);
        existingUser.setEmail(newEmail);
        existingUser.setPassword(newPassword);

        userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}


