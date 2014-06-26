package com.xiaobai.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Sender {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-jms.xml");
		
		Receiver consumer = context.getBean(Receiver.class);
		consumer.receive();
		
		JmsTemplate template = (JmsTemplate) context.getBean(JmsTemplate.class);
		Destination destination = (Destination) context.getBean(Destination.class);
		template.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("hello world");
			}
		});
	}
	
}
