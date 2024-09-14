package org.ejatohvee.taskmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "tasks_list", name = "t_task")
//@ToString(exclude = "comments")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "c_title")
    public String title;

    @Column(name = "c_description")
    public String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "c_status")
    public TaskStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "c_priority")
    public TaskPriority priority;

    @Column(name = "c_author")
    public String author;

    @Column(name = "c_performer")
    public String performer;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    public List<Comment> comments;

    public Task(String title, String description, TaskStatus status, TaskPriority priority, String author, String performer) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.performer = performer;
    }
}
