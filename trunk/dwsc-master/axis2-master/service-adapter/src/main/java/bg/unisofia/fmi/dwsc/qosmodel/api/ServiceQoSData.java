
package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceQoSData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceQoSData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceEndpointUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="operation" type="{http://api.qosmodel.dwsc.fmi.unisofia.bg/}serviceOperation" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ServiceQoSData", propOrder = {
    "serviceName",
    "serviceKey",
    "serviceCategory",
    "serviceEndpointUrl",
    "operations"
})
public class ServiceQoSData {

    protected String serviceName;
    protected String serviceKey;
    protected String serviceCategory;
    protected String serviceEndpointUrl;
    protected ServiceQoSData.Operations operations;

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the serviceKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceKey() {
        return serviceKey;
    }

    /**
     * Sets the value of the serviceKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceKey(String value) {
        this.serviceKey = value;
    }

    /**
     * Gets the value of the serviceCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCategory() {
        return serviceCategory;
    }

    /**
     * Sets the value of the serviceCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCategory(String value) {
        this.serviceCategory = value;
    }

    /**
     * Gets the value of the serviceEndpointUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEndpointUrl() {
        return serviceEndpointUrl;
    }

    /**
     * Sets the value of the serviceEndpointUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEndpointUrl(String value) {
        this.serviceEndpointUrl = value;
    }

    /**
     * Gets the value of the operations property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceQoSData.Operations }
     *     
     */
    public ServiceQoSData.Operations getOperations() {
        return operations;
    }

    /**
     * Sets the value of the operations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceQoSData.Operations }
     *     
     */
    public void setOperations(ServiceQoSData.Operations value) {
        this.operations = value;
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
     *         &lt;element name="operation" type="{http://api.qosmodel.dwsc.fmi.unisofia.bg/}serviceOperation" maxOccurs="unbounded" minOccurs="0"/>
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
        "operation"
    })
    public static class Operations {

        protected List<ServiceOperation> operation;

        /**
         * Gets the value of the operation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the operation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOperation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ServiceOperation }
         * 
         * 
         */
        public List<ServiceOperation> getOperation() {
            if (operation == null) {
                operation = new ArrayList<ServiceOperation>();
            }
            return this.operation;
        }

    }

}
