package com.storytelling.ws.user.exception;

import com.storytelling.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(Messages.getMessageForLocale("storytelling.user.not.found.exception", LocaleContextHolder.getLocale()));
    }
}
