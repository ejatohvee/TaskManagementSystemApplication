package org.ejatohvee.taskmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_users", schema = "users_management")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "c_username")
    private String username;

    @Column(name = "c_email")
    private String email;

    @Column(name = "c_password")
    private String password;

    @ManyToMany
    @JoinTable(
          name = "t_user_authority", schema = "users_management",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_authority")
    )
    private Collection<Authority> authorities;

    public User(String username, String email, String password, Collection<Authority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
}
