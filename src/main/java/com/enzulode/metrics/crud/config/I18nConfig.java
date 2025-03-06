package com.enzulode.metrics.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class I18nConfig {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    var ms = new ResourceBundleMessageSource();
    ms.setDefaultEncoding("UTF-8");
    ms.setBasename("i18n/messages");
    ms.setDefaultLocale(Locale.of("en", "GB"));
    ms.setUseCodeAsDefaultMessage(true);
    ms.setCacheSeconds(3600);
    return ms;
  }

  @Bean
  public LocaleResolver localeResolver() {
    AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
    resolver.setSupportedLocales(List.of(
        Locale.of("en", "GB"),
        Locale.of("en", "US")
    ));
    return resolver;
  }
}
