package ru.dev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.api.exception.NotFoundException;
import ru.dev.api.model.User;
import ru.dev.api.service.UserService;
import ru.dev.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGetAllUsers() {

        List<User> userList = Arrays.asList(new User(), new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.getAllUsers();
        assertThat(result).hasSize(3);
    }

    @Test
    public void testGetUserById() {

        Long userId = 1L;
        User user = new User();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User result = userService.getUserById(userId);
        assertThat(result).isNotNull();
    }

    @Test(expected = NotFoundException.class)
    public void testGetUserByIdNotFound() {

        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
        userService.getUserById(userId);
    }

}

