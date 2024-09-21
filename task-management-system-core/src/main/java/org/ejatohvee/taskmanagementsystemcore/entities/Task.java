package org.ejatohvee.taskmanagementsystemcore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "tasks_list", name = "t_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "c_title")
    private String title;

    @Column(name = "c_description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "c_status")
    private TaskStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "c_priority")
    private TaskPriority priority;

    @Column(name = "c_author")
    private String author;

    @Column(name = "c_performer")
    private String performer;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments;

    public Task(String title, String description, TaskStatus status, TaskPriority priority, String author, String performer) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.performer = performer;
    }
}
