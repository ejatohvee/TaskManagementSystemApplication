package org.ejatohvee.taskmanagementsystem;

import org.ejatohvee.taskmanagementsystem.dtos.TaskDTO;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.mapper.TaskMapper;
import org.ejatohvee.taskmanagementsystem.repositories.TaskRepository;
import org.ejatohvee.taskmanagementsystem.services.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Test
    void handleGetTasksByUsername_ReturnsUsersTasks() {
        String usernameOne = "Maxim";
        String usernameTwo = "Ejatohvee";
        Task taskOne = new Task();
        UUID taskOneId = UUID.randomUUID();
        taskOne.setTitle("First task");
        taskOne.setAuthor(usernameOne);

        Task taskTwo = new Task();
        taskTwo.setTitle("Second task");
        taskTwo.setAuthor(usernameTwo);

        Task taskThree = new Task();
        UUID taskThreeId = UUID.randomUUID();
        taskThree.setTitle("Third task");
        taskThree.setAuthor(usernameOne);


        List<Task> userOneTasks = List.of(taskOne, taskThree);
        when(taskRepository.getTasksByAuthor(usernameOne)).thenReturn(userOneTasks);

        TaskDTO taskDTOOne = new TaskDTO(taskOneId, taskOne.getTitle(), null, null, null, usernameOne, null, null);
        TaskDTO taskDTOThree = new TaskDTO(taskThreeId, taskThree.getTitle(), null, null, null, usernameOne, null, null);

        when(taskMapper.taskToTaskDto(taskOne)).thenReturn(taskDTOOne);
        when(taskMapper.taskToTaskDto(taskThree)).thenReturn(taskDTOThree);


        List<TaskDTO> expectedResult = List.of(taskDTOOne, taskDTOThree);
        List<TaskDTO> actualResult = taskService.getTasksByUsername(usernameOne).stream().toList();

        assertEquals(expectedResult, actualResult);

        verify(taskRepository, times(1)).getTasksByAuthor(usernameOne);

        verify(taskMapper, times(1)).taskToTaskDto(taskOne);
        verify(taskMapper, times(1)).taskToTaskDto(taskThree);
        verify(taskMapper, never()).taskToTaskDto(taskTwo);
    }
}
