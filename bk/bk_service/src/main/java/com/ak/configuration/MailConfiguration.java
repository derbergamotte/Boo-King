package com.ak.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Properties;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class MailConfiguration {

    @Value("${mail.debug}")
    private String mailDebugProperty;

    @Value("${mail.smtp.auth}")
    private String mailSmtpAuthProperty;

    @Value("${mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnableProperty;

    @Value("${mail.transport.protocol}")
    private String mailTransportProtocolProperty;

   
    //TODO: delete
    @Value("${input.encoding}")
    private String velocityInputEncodingProperty;

    @Value("${output.encoding}")
    private String velocityOutputEncodingProperty;

    @Value("${resource.loader}")
    private String velocityResourceLoaderProperty;

    @Value("${class.resource.loader.class}")
    private String velocityClassResourceLoaderClassProperty;

    @Bean
    public JavaMailSender mailSender() {
	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	mailSender.setHost("smtp.gmail.com");
	mailSender.setPort(587);
	mailSender.setUsername("GreatRogerWilko.gmail.com");
	mailSender.setPassword("Gfhjkm55");
	Properties javaMailProperties = new Properties();
	javaMailProperties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnableProperty);
	javaMailProperties.put("mail.smtp.auth", mailSmtpAuthProperty);
	javaMailProperties.put("mail.transport.protocol", mailTransportProtocolProperty);
	javaMailProperties.put("mail.debug", mailDebugProperty);
	mailSender.setJavaMailProperties(javaMailProperties);
	return mailSender;
    }


    @Async("threadPoolTaskExecutor")
    public void asyncMethodWithConfiguredExecutor() {
	System.out.println("Execute method with configured executor - " + Thread.currentThread().getName());
    }

}
