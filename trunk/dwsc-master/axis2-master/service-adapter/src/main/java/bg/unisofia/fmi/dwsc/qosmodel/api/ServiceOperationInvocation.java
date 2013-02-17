
package bg.unisofia.fmi.dwsc.qosmodel.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for serviceOperationInvocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceOperationInvocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="successful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requestReceived" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="responseSent" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="requestMsgSize" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="responseMsgSize" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceOperationInvocation", propOrder = {
    "successful",
    "requestReceived",
    "responseSent",
    "requestMsgSize",
    "responseMsgSize"
})
public class ServiceOperationInvocation {

    protected boolean successful;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestReceived;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar responseSent;
    protected long requestMsgSize;
    protected long responseMsgSize;

    /**
     * Gets the value of the successful property.
     * 
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets the value of the successful property.
     * 
     */
    public void setSuccessful(boolean value) {
        this.successful = value;
    }

    /**
     * Gets the value of the requestReceived property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestReceived() {
        return requestReceived;
    }

    /**
     * Sets the value of the requestReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestReceived(XMLGregorianCalendar value) {
        this.requestReceived = value;
    }

    /**
     * Gets the value of the responseSent property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResponseSent() {
        return responseSent;
    }

    /**
     * Sets the value of the responseSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResponseSent(XMLGregorianCalendar value) {
        this.responseSent = value;
    }

    /**
     * Gets the value of the requestMsgSize property.
     * 
     */
    public long getRequestMsgSize() {
        return requestMsgSize;
    }

    /**
     * Sets the value of the requestMsgSize property.
     * 
     */
    public void setRequestMsgSize(long value) {
        this.requestMsgSize = value;
    }

    /**
     * Gets the value of the responseMsgSize property.
     * 
     */
    public long getResponseMsgSize() {
        return responseMsgSize;
    }

    /**
     * Sets the value of the responseMsgSize property.
     * 
     */
    public void setResponseMsgSize(long value) {
        this.responseMsgSize = value;
    }

}
