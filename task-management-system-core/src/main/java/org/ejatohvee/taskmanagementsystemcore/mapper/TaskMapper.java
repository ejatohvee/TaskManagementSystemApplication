package org.ejatohvee.taskmanagementsystemcore.mapper;

import org.ejatohvee.taskmanagementsystemcore.dtos.TaskDTO;
import org.ejatohvee.taskmanagementsystemcore.entities.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO taskToTaskDto(Task task);
}
