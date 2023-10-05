package com.storytelling.ws.auth.exception;

import com.storytelling.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super(Messages.getMessageForLocale("storytelling.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
}
