package org.ejatohvee.taskmanagementsystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    public int status;
    public String message;
}
