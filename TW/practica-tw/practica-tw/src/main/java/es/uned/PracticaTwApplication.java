package es.uned;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import es.uned.portalreuniones.constants.Configuration;

/**
 * Configuración de Spring Boot
 * 
 * @author omontes
 *
 */
@SpringBootApplication
public class PracticaTwApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PracticaTwApplication.class, args);
	}

	/**
	 * Configuración para la internacionalización
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(Configuration.MESSAGES_ES_PATH);
		messageSource.setDefaultEncoding(Configuration.DEFAULT_ENCODING);
		return messageSource;
	}

	/**
	 * Se coloca la localización a español
	 * 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Configuration.LOCALE_ES);
		return sessionLocaleResolver;
	}

}
