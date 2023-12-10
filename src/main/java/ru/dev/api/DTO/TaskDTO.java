package ru.dev.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dev.api.model.Priority;
import ru.dev.api.model.TaskStatus;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private UserDTO author;
    private UserDTO executor;
    private List<String> comments;
    private Date createdDate;

}
