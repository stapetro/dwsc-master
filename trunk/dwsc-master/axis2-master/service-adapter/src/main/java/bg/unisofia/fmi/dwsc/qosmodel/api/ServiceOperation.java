
package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operationInvocations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="operationInvocation" type="{http://api.qosmodel.dwsc.fmi.unisofia.bg/}serviceOperationInvocation" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "serviceOperation", propOrder = {
    "operationName",
    "operationInvocations"
})
public class ServiceOperation {

    protected String operationName;
    protected ServiceOperation.OperationInvocations operationInvocations;

    /**
     * Gets the value of the operationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * Sets the value of the operationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationName(String value) {
        this.operationName = value;
    }

    /**
     * Gets the value of the operationInvocations property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceOperation.OperationInvocations }
     *     
     */
    public ServiceOperation.OperationInvocations getOperationInvocations() {
        return operationInvocations;
    }

    /**
     * Sets the value of the operationInvocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceOperation.OperationInvocations }
     *     
     */
    public void setOperationInvocations(ServiceOperation.OperationInvocations value) {
        this.operationInvocations = value;
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
     *         &lt;element name="operationInvocation" type="{http://api.qosmodel.dwsc.fmi.unisofia.bg/}serviceOperationInvocation" maxOccurs="unbounded" minOccurs="0"/>
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
        "operationInvocation"
    })
    public static class OperationInvocations {

        protected List<ServiceOperationInvocation> operationInvocation;

        /**
         * Gets the value of the operationInvocation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the operationInvocation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOperationInvocation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ServiceOperationInvocation }
         * 
         * 
         */
        public List<ServiceOperationInvocation> getOperationInvocation() {
            if (operationInvocation == null) {
                operationInvocation = new ArrayList<ServiceOperationInvocation>();
            }
            return this.operationInvocation;
        }

    }

}
