package ru.dev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dev.api.model.Role;
import ru.dev.api.model.User;
import ru.dev.auth.DTO.AuthenticationResponse;
import ru.dev.auth.DTO.RegisterRequest;
import ru.dev.auth.service.AuthenticationService;
import ru.dev.auth.service.JwtService;
import ru.dev.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest("user@example.com", "password");
        User expectedUser = User.builder().username("user@example.com").password("encodedPassword").role(Role.USER).build();

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.register(registerRequest);

        assertEquals("jwtToken", response.getToken());
    }
}