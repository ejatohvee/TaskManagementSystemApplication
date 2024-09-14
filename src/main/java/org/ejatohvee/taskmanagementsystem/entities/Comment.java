package org.ejatohvee.taskmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "tasks_list", name = "t_comment")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "c_body")
    public String body;

    @Column(name = "c_author")
    public String author;

    @Column(name = "c_time")
    public String time;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference
    public Task task;

    public Comment(String body, String author, Task task) {
        this.body = body;
        this.author = author;
        this.task = task;
        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"));
    }
}
