package com.gama.table.ws.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-applicationContext-jms.xml"})
public class SpringJMSSendTest {
	 @Autowired
	 @Qualifier("barJmsTemplate")
 private JmsTemplate jmsTemplate;


@Test
 public void send(){
	 final String txt="barJmsTemplate";
     if(jmsTemplate==null){
        System.out.println("Template is null!!");
     }

     System.out.println("Sending Message:["+txt+"]");
     jmsTemplate.send( 
           new MessageCreator() {
                public Message createMessage(Session session) 
                throws JMSException {
                     return session.createTextMessage(txt);
         }
           }
     );
  
     System.out.println("Message Sent");
 }

 
}