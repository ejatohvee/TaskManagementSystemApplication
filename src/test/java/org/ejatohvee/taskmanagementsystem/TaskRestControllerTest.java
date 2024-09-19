package org.ejatohvee.taskmanagementsystem;


import org.ejatohvee.taskmanagementsystem.controllers.TaskController;
import org.ejatohvee.taskmanagementsystem.dtos.TaskDTO;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;
import org.ejatohvee.taskmanagementsystem.payloads.NewTaskPayload;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskRestControllerTest {
    @Mock
    TaskService taskService;
    @InjectMocks
    TaskController taskController;

    @Test
    void handleAddTask_ReturnsAllTasks() {
        Task task = new Task("Title", "Description", TaskStatus.IN_PROCESS, TaskPriority.MIDDLE, "Ejatohvee", "Ejatohvee");
        TaskDTO taskDTO = new TaskDTO(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getAuthor(), task.getPerformer(), null);
        NewTaskPayload taskPayload = new NewTaskPayload("Title", "Description", TaskStatus.IN_PROCESS, TaskPriority.MIDDLE, "Ejatohvee");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("Ejatohvee");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(taskService.createTask(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(),authentication.getName(), task.getPerformer())).thenReturn(taskDTO);

        var responseEntity = taskController.createTask(taskPayload);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(taskDTO, responseEntity.getBody());

        verify(taskService, times(1)).createTask(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getAuthor(), task.getPerformer());
    }
}
