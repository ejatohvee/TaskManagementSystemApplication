package org.ejatohvee.taskmanagementsystemweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.ejatohvee.taskmanagementsystemcore.repositories")
@ComponentScan(basePackages = {"org.ejatohvee.taskmanagementsystemservice", "org.ejatohvee.taskmanagementsystemsecurity", "org.ejatohvee.taskmanagementsystemcore.repositories", "org.ejatohvee.taskmanagementsystemweb"})
@EntityScan(basePackages = "org.ejatohvee.taskmanagementsystemcore")
public class TaskManagementSystemWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSystemWebApplication.class, args);
    }
}
