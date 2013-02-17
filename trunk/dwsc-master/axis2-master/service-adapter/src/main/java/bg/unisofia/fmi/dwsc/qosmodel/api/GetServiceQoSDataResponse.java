
package bg.unisofia.fmi.dwsc.qosmodel.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getServiceQoSDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getServiceQoSDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://api.qosmodel.dwsc.fmi.unisofia.bg/}ServiceQoSData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServiceQoSDataResponse", propOrder = {
    "_return"
})
public class GetServiceQoSDataResponse {

    @XmlElement(name = "return")
    protected ServiceQoSData _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceQoSData }
     *     
     */
    public ServiceQoSData getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceQoSData }
     *     
     */
    public void setReturn(ServiceQoSData value) {
        this._return = value;
    }

}
