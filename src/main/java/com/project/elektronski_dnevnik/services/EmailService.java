package com.project.elektronski_dnevnik.services;

import com.project.elektronski_dnevnik.models.EmailObject;

public interface EmailService {
	
	void sendSimpleMessage (EmailObject object);
	void sendTemplateMessage (EmailObject object) throws Exception;
	void sendMessageWithAttachment (EmailObject object,	String pathToAttachment) throws Exception;

}
