package com.imap.controller;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mail.ImapIdleChannelAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imap.settings.EmailReceiverService;
import com.imap.settings.GeneralImapAdapter;
import com.imap.settings.MailSettings;


@RestController
public class Controller {
	
	private ConfigurableApplicationContext contextForChannels;
	
	@GetMapping("/stop")
	public String stopChannel() {
		
		ImapIdleChannelAdapter adapter;
		if(contextForChannels != null) {
			adapter = contextForChannels.getBean(ImapIdleChannelAdapter.class);
			adapter.stop();

			return "Stopped - channel";
		}
		
		return "Done";
	
	}
	
	@GetMapping("/start")
	public String startChannel() {
		
		ImapIdleChannelAdapter adapter;
		if(contextForChannels != null) {
			adapter = contextForChannels.getBean(ImapIdleChannelAdapter.class);
			adapter.start();

			return "Started - channel";
		}
		return "Done";
	
	}
	
	@GetMapping("/debug-on")
	public String debugOn() {
		
		if(contextForChannels != null) {
			contextForChannels.close();
		}
		
		contextForChannels = createNewContext(
			"imaps://***PASSWORD***:993/Inbox",
			"true",
			"60000"
		);
		contextForChannels.refresh();
		

		return "Debug ON";
	
	}
	
	@GetMapping("/debug-off")
	public String debugOff() {
		
		if(contextForChannels != null) {
			contextForChannels.close();
		}

		
		contextForChannels = createNewContext(
			"imaps://***PASSWORD***:993/Inbox",
			"false",
			"60000"
		);
		contextForChannels.refresh();
		

		return "Debug OFF";
	
	}
	
	private AnnotationConfigApplicationContext createNewContext(String imapURL, String debug, String poller) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
        StandardEnvironment env = new StandardEnvironment();
        
        Properties props = new Properties();
        
        props.setProperty("imap.url", imapURL);
        props.setProperty("mail.debug", debug);
        props.setProperty("poller.interval", poller);
        
        PropertiesPropertySource pps = new PropertiesPropertySource("imapprops", props);
        env.getPropertySources().addLast(pps);
        
        context.setEnvironment(env);
        
        context.register(
       		GeneralImapAdapter.class, 
       		EmailReceiverService.class, 
       		MailSettings.class
       );
        
        return context;
	}
		
	
	
	
	
}
