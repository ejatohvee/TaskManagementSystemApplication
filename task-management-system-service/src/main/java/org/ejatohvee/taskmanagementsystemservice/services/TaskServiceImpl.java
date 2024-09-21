package org.ejatohvee.taskmanagementsystemservice.services;

import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystemcore.dtos.TaskDTO;
import org.ejatohvee.taskmanagementsystemcore.entities.Task;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskStatus;
import org.ejatohvee.taskmanagementsystemcore.mapper.TaskMapper;
import org.ejatohvee.taskmanagementsystemcore.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public Iterable<TaskDTO> getAllTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        return StreamSupport.stream(tasks.spliterator(), false).map(taskMapper::taskToTaskDto).toList();
    }

    @Override
    public List<TaskDTO> getTasksByUsername(String username) {
        return taskRepository.getTasksByAuthor(username).stream().map(taskMapper::taskToTaskDto).toList();
    }

    @Override
    public TaskDTO findTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return taskMapper.taskToTaskDto(task);
    }

    @Override
    @Transactional
    public TaskDTO createTask(String title, String description, TaskStatus status, TaskPriority priority, String author, String performer) {
        return taskMapper.taskToTaskDto(taskRepository.save(new Task(title, description, status, priority, author, performer)));
    }

    @Override
    @Transactional
    public void updateTask(UUID id, String title, String description, TaskStatus status, TaskPriority priority, String performer) {
        taskRepository.findById(id).ifPresentOrElse(task -> {
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            task.setPriority(priority);
            task.setPerformer(performer);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public boolean deleteTask(UUID id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
            return true;
        } else return false;
    }

    @Override
    public String getTaskAuthorUsername(UUID id) {
        return taskRepository.findById(id).map(
                Task::getAuthor).orElse(null);
    }

}
