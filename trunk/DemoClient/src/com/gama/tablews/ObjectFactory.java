//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.04 at 03:01:11 PM CST 
//


package com.gama.tablews;


import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gama.tablews package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gama.tablews
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryEventResponse }
     * 
     */
    public QueryEventResponse createQueryEventResponse() {
        return new QueryEventResponse();
    }

    /**
     * Create an instance of {@link EventMeterRequest }
     * 
     */
    public EventMeterRequest createEventMeterRequest() {
        return new EventMeterRequest();
    }

    /**
     * Create an instance of {@link EventMeterResult }
     * 
     */
    public EventMeterResult createEventMeterResult() {
        return new EventMeterResult();
    }

    /**
     * Create an instance of {@link QueryEventResponse.Events }
     * 
     */
    public QueryEventResponse.Events createQueryEventResponseEvents() {
        return new QueryEventResponse.Events();
    }

    /**
     * Create an instance of {@link QueryEventRequest }
     * 
     */
    public QueryEventRequest createQueryEventRequest() {
        return new QueryEventRequest();
    }

}
