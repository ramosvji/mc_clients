package com.ramosvji.clients;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class McClientsApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(McClientsApplication.class, args);
	}
	
	 @Bean
	    public MessageSource messageSource() {
	        ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("classpath:messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }

	    @Bean
	    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
	        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	        bean.setValidationMessageSource(messageSource);
	        return bean;
	    }

	    @Bean
	    public LocaleResolver localeResolver(){
	        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	        
	        Locale local = new Locale("es","ES");
	        localeResolver.setDefaultLocale(local);
	        return  localeResolver;
	    }

	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("lang");
	        return localeChangeInterceptor;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry){
	        registry.addInterceptor(localeChangeInterceptor());
	    }
	
}
