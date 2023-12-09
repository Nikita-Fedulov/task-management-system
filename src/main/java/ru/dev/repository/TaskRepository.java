package ru.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dev.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>   {
}
