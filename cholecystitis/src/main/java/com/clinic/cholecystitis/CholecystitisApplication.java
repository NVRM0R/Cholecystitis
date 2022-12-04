package com.clinic.cholecystitis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class CholecystitisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CholecystitisApplication.class, args);
	}


@Bean
public LocaleResolver localeResolver() {
	SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	Locale rus = new Locale("ru", "RU");
	localeResolver.setDefaultLocale(rus);
	return localeResolver;
}
@Bean
public ResourceBundleMessageSource messageSource() {
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	messageSource.setUseCodeAsDefaultMessage(true);
	messageSource.setBasenames("messages");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
	}

@Bean
InfoContributor getInfoContributor() {
	Map<String, Object> details = new HashMap<>();
	details.put("Microservice", "Cholecystitis");
	details.put("Developer name", "Vladimir Andreev");
	details.put("Developer nick","NVRM0R");
	details.put("Tester", "Nikita Dubinin");
	Map<String, Object> wrapper = new HashMap<>();
	wrapper.put("info", details);
	return new MapInfoContributor(wrapper);
}

}
