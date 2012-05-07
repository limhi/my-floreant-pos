//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.04 at 03:01:11 PM CST 
//


package com.gama.tablews;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="events" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="EventId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="EventTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="BillIn" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="Note1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Note2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Note3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Note4" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "events"
})
@XmlRootElement(name = "QueryEventResponse")
public class QueryEventResponse {

    protected List<QueryEventResponse.Events> events;

    /**
     * Gets the value of the events property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the events property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryEventResponse.Events }
     * 
     * 
     */
    public List<QueryEventResponse.Events> getEvents() {
        if (events == null) {
            events = new ArrayList<QueryEventResponse.Events>();
        }
        return this.events;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="MNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="EventId" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="EventTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="BillIn" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="Note1" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Note2" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Note3" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Note4" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "mNum",
        "eventId",
        "eventTime",
        "billIn",
        "note1",
        "note2",
        "note3",
        "note4"
    })
    public static class Events {

        @XmlElement(name = "MNum")
        protected int mNum;
        @XmlElement(name = "EventId")
        protected int eventId;
        @XmlElement(name = "EventTime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar eventTime;
        @XmlElement(name = "BillIn")
        protected double billIn;
        @XmlElement(name = "Note1")
        protected int note1;
        @XmlElement(name = "Note2")
        protected int note2;
        @XmlElement(name = "Note3")
        protected int note3;
        @XmlElement(name = "Note4")
        protected int note4;

        /**
         * Gets the value of the mNum property.
         * 
         */
        public int getMNum() {
            return mNum;
        }

        /**
         * Sets the value of the mNum property.
         * 
         */
        public void setMNum(int value) {
            this.mNum = value;
        }

        /**
         * Gets the value of the eventId property.
         * 
         */
        public int getEventId() {
            return eventId;
        }

        /**
         * Sets the value of the eventId property.
         * 
         */
        public void setEventId(int value) {
            this.eventId = value;
        }

        /**
         * Gets the value of the eventTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEventTime() {
            return eventTime;
        }

        /**
         * Sets the value of the eventTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEventTime(XMLGregorianCalendar value) {
            this.eventTime = value;
        }

        /**
         * Gets the value of the billIn property.
         * 
         */
        public double getBillIn() {
            return billIn;
        }

        /**
         * Sets the value of the billIn property.
         * 
         */
        public void setBillIn(double value) {
            this.billIn = value;
        }

        /**
         * Gets the value of the note1 property.
         * 
         */
        public int getNote1() {
            return note1;
        }

        /**
         * Sets the value of the note1 property.
         * 
         */
        public void setNote1(int value) {
            this.note1 = value;
        }

        /**
         * Gets the value of the note2 property.
         * 
         */
        public int getNote2() {
            return note2;
        }

        /**
         * Sets the value of the note2 property.
         * 
         */
        public void setNote2(int value) {
            this.note2 = value;
        }

        /**
         * Gets the value of the note3 property.
         * 
         */
        public int getNote3() {
            return note3;
        }

        /**
         * Sets the value of the note3 property.
         * 
         */
        public void setNote3(int value) {
            this.note3 = value;
        }

        /**
         * Gets the value of the note4 property.
         * 
         */
        public int getNote4() {
            return note4;
        }

        /**
         * Sets the value of the note4 property.
         * 
         */
        public void setNote4(int value) {
            this.note4 = value;
        }

    }

}
