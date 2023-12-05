package ru.dev.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private String priority;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ElementCollection
    private List<String> comments;


}
