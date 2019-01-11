package com.imap.settings;

import javax.mail.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailReceiverService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void receive(Message message) {
		
		logger.info(message.toString());
	}

}
