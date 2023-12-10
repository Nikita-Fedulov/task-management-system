package ru.dev.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dev.api.DTO.UserDTO;
import ru.dev.api.exception.NotFoundException;
import ru.dev.api.model.User;
import ru.dev.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        if (existingUser != null) {
            String updatedName = updatedUserDTO.getUsername();
            if (updatedName != null && !updatedName.isBlank()) {
                existingUser.setUsername(updatedName);
            }

            String updatedPassword = updatedUserDTO.getPassword();
            if (updatedPassword != null && !updatedPassword.isBlank()) {
                existingUser.setPassword(updatedPassword);
            }

            return userRepository.save(existingUser);
        }
        return null;
    }

    public Optional<User> getUserByUsername(String usernsme) {
        return userRepository.findByUsername(usernsme);
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}


