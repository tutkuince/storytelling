package com.storytelling.ws.user.exception;

import com.storytelling.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationException extends RuntimeException {

    public ActivationNotificationException() {
        super(Messages.getMessageForLocale("storytelling.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
