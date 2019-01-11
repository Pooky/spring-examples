package com.imap.settings;


import java.util.Properties;

import javax.mail.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.mail.ImapIdleChannelAdapter;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.integration.mail.MailReceivingMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import org.springframework.integration.annotation.Poller;

@Configuration
@EnableIntegration
public class GeneralImapAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier(value = "javaMailProperties")
	private Properties mailSettings;
	
	@Autowired
	@Qualifier(value = "emailChannel")
	private MessageChannel directChannel;
	
    @Autowired
    private EmailReceiverService emailReceiverService;

    
    @Bean(name = "emailChannel")
    public MessageChannel channel() {
        return new DirectChannel();
    }
    
    @Bean
    public ImapIdleChannelAdapter mailMessageSource(ImapMailReceiver imapMailReceiver) {
    	ImapIdleChannelAdapter adapter = new ImapIdleChannelAdapter(imapMailReceiver);
    	adapter.setAutoStartup(false);
    	adapter.setOutputChannel(directChannel);
    	return adapter;
    }
  
    @Bean
    @Value("${imap.url}")
    public ImapMailReceiver imapMailReceiver(String imapUrl) {
    	
        ImapMailReceiver imapMailReceiver = new ImapMailReceiver(imapUrl);
        imapMailReceiver.setShouldMarkMessagesAsRead(true);
        imapMailReceiver.setShouldDeleteMessages(false);
        imapMailReceiver.setJavaMailProperties(mailSettings);
        
        // other setters here
        return imapMailReceiver;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
    
    @ServiceActivator(inputChannel = "emailChannel")
    public void emailMessageSource(javax.mail.Message message) {
        emailReceiverService.receive(message);
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    @Value("${poller.interval}")
    public PollerMetadata defaultPoller(String interval) {

        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(Integer.parseInt(interval)));
        return pollerMetadata;
    }

}


