
package bg.unisofia.fmi.dwsc.qosmodel.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.unisofia.fmi.dwsc.qosmodel.api package. 
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

    private final static QName _SaveQoSDataException_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "SaveQoSDataException");
    private final static QName _SaveResponse_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "saveResponse");
    private final static QName _Save_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "save");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.unisofia.fmi.dwsc.qosmodel.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Save }
     * 
     */
    public Save createSave() {
        return new Save();
    }

    /**
     * Create an instance of {@link SaveResponse }
     * 
     */
    public SaveResponse createSaveResponse() {
        return new SaveResponse();
    }

    /**
     * Create an instance of {@link SaveQoSDataException }
     * 
     */
    public SaveQoSDataException createSaveQoSDataException() {
        return new SaveQoSDataException();
    }

    /**
     * Create an instance of {@link MessageQoSData }
     * 
     */
    public MessageQoSData createMessageQoSData() {
        return new MessageQoSData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveQoSDataException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "SaveQoSDataException")
    public JAXBElement<SaveQoSDataException> createSaveQoSDataException(SaveQoSDataException value) {
        return new JAXBElement<SaveQoSDataException>(_SaveQoSDataException_QNAME, SaveQoSDataException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "saveResponse")
    public JAXBElement<SaveResponse> createSaveResponse(SaveResponse value) {
        return new JAXBElement<SaveResponse>(_SaveResponse_QNAME, SaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Save }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "save")
    public JAXBElement<Save> createSave(Save value) {
        return new JAXBElement<Save>(_Save_QNAME, Save.class, null, value);
    }

}
