package com.storytelling.ws.user.exception;

import com.storytelling.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super(Messages.getMessageForLocale("storytelling.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
