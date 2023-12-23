package ru.dev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dev.api.controller.TaskController;
import ru.dev.api.model.Task;
import ru.dev.api.service.TaskService;
import ru.dev.configuration.TestConfig;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
@ContextConfiguration(classes = TestConfig.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;


    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQb3BhQGxlLmNvbSIsImlhdCI6MTcwMjQ5ODI3MSwiZXhwIjoxNzAyNDk5NzExfQ.LBMV6Wb48XEsrXNEnzM1-g65e7X6Ohb3BINNxpmi67c";

    @Test
    public void testGetAllTasks() throws Exception {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
