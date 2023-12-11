package ru.dev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dev.api.model.User;
import ru.dev.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        entityManager.persist(user);
        entityManager.flush();

        Optional<User> result = userRepository.findByUsername("testUser");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getUsername()).isEqualTo("testUser");
        assertThat(result.get().getPassword()).isEqualTo("testPassword");
    }

    @Test
    public void testFindByUsernameNotFound() {
        Optional<User> result = userRepository.findByUsername("nonexistentUser");

        assertThat(result.isPresent()).isFalse();
    }

}

