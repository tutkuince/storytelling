package com.storytelling.ws.error;

import com.storytelling.ws.auth.exception.AuthenticationException;
import com.storytelling.ws.shared.Messages;
import com.storytelling.ws.user.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            NotUniqueEmailException.class,
            ActivationNotificationException.class,
            InvalidTokenException.class,
            UserNotFoundException.class,
            AuthenticationException.class,
            AuthorizationException.class

    })
    ResponseEntity<ApiError> handleMethodArgNotValidEx(Exception exception, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        if (exception instanceof MethodArgumentNotValidException) {
            String message = Messages.getMessageForLocale("storytelling.constraints.validation.error", LocaleContextHolder.getLocale());
            apiError.setMessage(message);
            apiError.setStatus(400);
            Map<String, String> validationErrors = new HashMap<>();
            for (FieldError fieldError : ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors()) {
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            apiError.setValidationErrors(validationErrors);
        } else if (exception instanceof NotUniqueEmailException) {
            apiError.setStatus(400);
            apiError.setValidationErrors(((NotUniqueEmailException) exception).getValidationErrors());
        } else if (exception instanceof ActivationNotificationException) {
            apiError.setStatus(502);
        } else if (exception instanceof InvalidTokenException) {
            apiError.setStatus(400);
        } else if (exception instanceof UserNotFoundException) {
            apiError.setStatus(404);
        } else if (exception instanceof AuthenticationException) {
            apiError.setStatus(401);
        } else if (exception instanceof AuthorizationException) {
            apiError.setStatus(403);
        }
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}
