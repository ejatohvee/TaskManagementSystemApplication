package org.ejatohvee.taskmanagementsystemweb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    public int status;
    public String message;
}
