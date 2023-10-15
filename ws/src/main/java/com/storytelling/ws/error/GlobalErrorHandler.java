package com.storytelling.ws.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DisabledException.class, AccessDeniedException.class})
    ResponseEntity<?> handleDisabledException(Exception exception, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(exception.getMessage());
        apiError.setPath(request.getRequestURI());
        if (exception instanceof DisabledException) {
            apiError.setStatus(401);
        } else if (exception instanceof AccessDeniedException) {
            apiError.setStatus(403);
        }
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
