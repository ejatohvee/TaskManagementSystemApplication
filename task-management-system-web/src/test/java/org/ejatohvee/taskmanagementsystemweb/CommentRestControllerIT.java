package org.ejatohvee.taskmanagementsystemweb;

import org.ejatohvee.taskmanagementsystemcore.entities.Comment;
import org.ejatohvee.taskmanagementsystemcore.entities.Task;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskStatus;
import org.ejatohvee.taskmanagementsystemcore.repositories.CommentRepository;
import org.ejatohvee.taskmanagementsystemcore.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@WithMockUser(username = "user", authorities = {"ROLE_USER"})
public class CommentRestControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TaskRepository taskRepository;

    @Test
    void handleGetComment_ReturnsOk() throws Exception {
        Task task = new Task("Tasks title", "Description", TaskStatus.IN_PROCESS, TaskPriority.MIDDLE, "Ejatohvee", "Maksim");
        task = taskRepository.save(task);

        Comment comment = new Comment("New body", "Ejatohvee", task);
        comment.setTime("10:00");
        commentRepository.save(comment);


        mockMvc.perform(get("/comments/" + comment.getId())).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json("""
                            {
                              "body": "New body",
                              "author": "Ejatohvee",
                              "time": "10:00"
                            }
                        """)
        );
    }

    @Test
    void handleGetComment_ReturnsErrorResponseEntity() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get("/comments/" + id)).andExpectAll(
                status().is(400),
                content().string("Not found: No comment for such id found")
        );
    }

//    @Test
//    void handleCreateComment_ReturnsOk() throws Exception {
//        Task task = new Task("Tasks title", "Description", TaskStatus.IN_PROCESS, TaskPriority.MIDDLE, "Ejatohvee", "Maksim");
//        task = taskRepository.save(task);
//
//        String json = """
//                {
//                "body": "New body"
//                }
//                """;
//
//        mockMvc.perform(post("/comment/" + task.getId()).contentType(MediaType.APPLICATION_JSON)
//                .content(json)).andExpect(status().isOk());
//    }

    @Test
    void handleDeleteComment_ReturnsOk() throws Exception {
        Task task = new Task("Tasks title", "Description", TaskStatus.IN_PROCESS, TaskPriority.MIDDLE, "Ejatohvee", "Maksim");
        task = taskRepository.save(task);

        Comment comment = new Comment("New body", "Ejatohvee", task);
        comment.setTime("10:00");
        commentRepository.save(comment);


        mockMvc.perform(delete("/comments/" + comment.getId())).andExpect(status().isNoContent());
    }
}
