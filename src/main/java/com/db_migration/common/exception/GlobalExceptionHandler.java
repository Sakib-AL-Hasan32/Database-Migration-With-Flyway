package com.db_migration.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ProblemDetail handleResourceAlreadyExistsException (ResourceAlreadyExistsException exception) {
        return problemDetail(
                HttpStatus.CONFLICT,
                "Resource Already Exists",
                exception.getMessage()
        );
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ProblemDetail handleInvalidTokenException(InvalidTokenException exception) {
        return problemDetail(
                HttpStatus.UNAUTHORIZED,
                "Invalid token",
                exception.getMessage()
        );
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFound exception) {
        return problemDetail(
                HttpStatus.NOT_FOUND,
                "Resource Not Found",
                exception.getMessage()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDenied(org.springframework.security.access.AccessDeniedException exception) {
        return problemDetail(HttpStatus.FORBIDDEN, "Access Denied", exception.getMessage());
    }

    private ProblemDetail problemDetail(HttpStatus status, String title, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        return problemDetail;
    }
}
