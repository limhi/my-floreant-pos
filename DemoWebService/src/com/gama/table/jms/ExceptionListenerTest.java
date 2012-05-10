package com.gama.table.jms;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.springframework.jms.connection.CachingConnectionFactory;

                                                                           
// import com.sssw.jms.api.JMQConnectionLostException;

public class ExceptionListenerTest implements ExceptionListener
{
 CachingConnectionFactory cachingConnectionFactory;
 Logger logger=Logger.getLogger(getClass());
 public void onException(JMSException arg0) {
	 logger.error("Exception occurred "+arg0);
  cachingConnectionFactory.onException(arg0);
 }

 public CachingConnectionFactory getCachingConnectionFactory() {
  return cachingConnectionFactory;
 }

 public void setCachingConnectionFactory(
                        CachingConnectionFactory  cachingConnectionFactory) {
  this.cachingConnectionFactory = cachingConnectionFactory;
 }
}