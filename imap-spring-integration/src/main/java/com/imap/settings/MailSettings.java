package com.imap.settings;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailSettings {
	
	@Bean(name = "javaMailProperties")
    @Value("${mail.debug}")
    public Properties javaMailProperties(String debug) {
		
        Properties javaMailProperties = new Properties();

        javaMailProperties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        javaMailProperties.setProperty("mail.imap.socketFactory.fallback","false");
        javaMailProperties.setProperty("mail.store.protocol","imaps");
        javaMailProperties.setProperty("mail.debug", debug);

        return javaMailProperties;
    }
	
}
