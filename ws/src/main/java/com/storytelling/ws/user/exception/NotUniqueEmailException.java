package com.storytelling.ws.user.exception;

import com.storytelling.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException {

    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("storytelling.constraints.validation.error", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("email", Messages.getMessageForLocale("storytelling.constraints.email.notunique", LocaleContextHolder.getLocale()));
    }
}
