package org.ejatohvee.taskmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_authorities", schema = "users_management")
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_authority")
    private String authority;
}
