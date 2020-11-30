package ai.ecma.appapplicationindividual.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * BY SIROJIDDIN on 27.11.2020
 */


@Component
public class MessageByLang {
    @Autowired
    MessageSource messageSource;


    public String getMessageByKey(String word) {
        return messageSource.getMessage(word, null, LocaleContextHolder.getLocale());
    }
}
