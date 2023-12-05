package ru.dev.model;

import jakarta.persistence.*;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Task> authoredTasks;

    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks;

}
