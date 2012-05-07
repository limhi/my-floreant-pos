package com.gama.table.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class MessageListenerTest implements MessageListener {
	Logger logger=Logger.getLogger(getClass());
  public void onMessage(Message message) {

    if (message instanceof TextMessage) {

      try {
    	  logger.debug("Received Message:["+
                      ((TextMessage) message).getText()+"]");
      } 
      catch (Exception ex) {    
    	  logger.error("Exception in onMessage " + ex.toString() + "\n" +
                     ex.getStackTrace());
      }
    } 
    else {
    	logger.debug("Message must be of type TextMessage");
    }
  }
}