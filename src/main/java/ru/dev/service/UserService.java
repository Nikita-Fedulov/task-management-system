package ru.dev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.DTO.UserDTO;
import ru.dev.exception.NotFoundException;
import ru.dev.model.User;
import ru.dev.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public User createUser(UserDTO userDTO) {
        User user = User
                .builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        if (existingUser != null) {
            String updatedEmail = updatedUserDTO.getEmail();
            if (updatedEmail != null && !updatedEmail.isBlank()) {
                existingUser.setEmail(updatedEmail);
            }

            String updatedPassword = updatedUserDTO.getPassword();
            if (updatedPassword != null && !updatedPassword.isBlank()) {
                existingUser.setPassword(updatedPassword);
            }

            return userRepository.save(existingUser);
        }
        return null;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}


