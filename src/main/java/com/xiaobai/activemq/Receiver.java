package com.xiaobai.activemq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("receiver")
public class Receiver  implements Runnable{
	
	@Resource
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination destination;
	
	public void receive(){
		new Thread(this).start();
	}
	
	
	public void run() {
		while(true){
			try {
				TextMessage msg = (TextMessage)jmsTemplate.receive(destination);
				if(msg != null) {
					System.out.println("收到的消息：" + msg.getText());
				}
				continue;
			} catch (JmsException e) {
				e.printStackTrace();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
}
