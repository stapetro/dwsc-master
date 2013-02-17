
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

    private final static QName _GetServiceQoSDataResponse_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "getServiceQoSDataResponse");
    private final static QName _GetServiceQoSData_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "getServiceQoSData");
    private final static QName _RegisterServiceResponse_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "registerServiceResponse");
    private final static QName _RegisterServiceException_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "RegisterServiceException");
    private final static QName _RegisterService_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "registerService");
    private final static QName _GetServiceQoSDataByCategory_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "getServiceQoSDataByCategory");
    private final static QName _GetServiceQoSDataByCategoryResponse_QNAME = new QName("http://api.qosmodel.dwsc.fmi.unisofia.bg/", "getServiceQoSDataByCategoryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.unisofia.fmi.dwsc.qosmodel.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceQoSData.Operations }
     * 
     */
    public ServiceQoSData.Operations createServiceQoSDataOperations() {
        return new ServiceQoSData.Operations();
    }

    /**
     * Create an instance of {@link RegisterService }
     * 
     */
    public RegisterService createRegisterService() {
        return new RegisterService();
    }

    /**
     * Create an instance of {@link ServiceOperationInvocation }
     * 
     */
    public ServiceOperationInvocation createServiceOperationInvocation() {
        return new ServiceOperationInvocation();
    }

    /**
     * Create an instance of {@link RegisterServiceResponse }
     * 
     */
    public RegisterServiceResponse createRegisterServiceResponse() {
        return new RegisterServiceResponse();
    }

    /**
     * Create an instance of {@link GetServiceQoSData }
     * 
     */
    public GetServiceQoSData createGetServiceQoSData() {
        return new GetServiceQoSData();
    }

    /**
     * Create an instance of {@link ServiceOperation }
     * 
     */
    public ServiceOperation createServiceOperation() {
        return new ServiceOperation();
    }

    /**
     * Create an instance of {@link RegisterServiceException }
     * 
     */
    public RegisterServiceException createRegisterServiceException() {
        return new RegisterServiceException();
    }

    /**
     * Create an instance of {@link GetServiceQoSDataByCategory }
     * 
     */
    public GetServiceQoSDataByCategory createGetServiceQoSDataByCategory() {
        return new GetServiceQoSDataByCategory();
    }

    /**
     * Create an instance of {@link GetServiceQoSDataResponse }
     * 
     */
    public GetServiceQoSDataResponse createGetServiceQoSDataResponse() {
        return new GetServiceQoSDataResponse();
    }

    /**
     * Create an instance of {@link ServiceQoSData }
     * 
     */
    public ServiceQoSData createServiceQoSData() {
        return new ServiceQoSData();
    }

    /**
     * Create an instance of {@link GetServiceQoSDataByCategoryResponse }
     * 
     */
    public GetServiceQoSDataByCategoryResponse createGetServiceQoSDataByCategoryResponse() {
        return new GetServiceQoSDataByCategoryResponse();
    }

    /**
     * Create an instance of {@link ServiceOperation.OperationInvocations }
     * 
     */
    public ServiceOperation.OperationInvocations createServiceOperationOperationInvocations() {
        return new ServiceOperation.OperationInvocations();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceQoSDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "getServiceQoSDataResponse")
    public JAXBElement<GetServiceQoSDataResponse> createGetServiceQoSDataResponse(GetServiceQoSDataResponse value) {
        return new JAXBElement<GetServiceQoSDataResponse>(_GetServiceQoSDataResponse_QNAME, GetServiceQoSDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceQoSData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "getServiceQoSData")
    public JAXBElement<GetServiceQoSData> createGetServiceQoSData(GetServiceQoSData value) {
        return new JAXBElement<GetServiceQoSData>(_GetServiceQoSData_QNAME, GetServiceQoSData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "registerServiceResponse")
    public JAXBElement<RegisterServiceResponse> createRegisterServiceResponse(RegisterServiceResponse value) {
        return new JAXBElement<RegisterServiceResponse>(_RegisterServiceResponse_QNAME, RegisterServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "RegisterServiceException")
    public JAXBElement<RegisterServiceException> createRegisterServiceException(RegisterServiceException value) {
        return new JAXBElement<RegisterServiceException>(_RegisterServiceException_QNAME, RegisterServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "registerService")
    public JAXBElement<RegisterService> createRegisterService(RegisterService value) {
        return new JAXBElement<RegisterService>(_RegisterService_QNAME, RegisterService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceQoSDataByCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "getServiceQoSDataByCategory")
    public JAXBElement<GetServiceQoSDataByCategory> createGetServiceQoSDataByCategory(GetServiceQoSDataByCategory value) {
        return new JAXBElement<GetServiceQoSDataByCategory>(_GetServiceQoSDataByCategory_QNAME, GetServiceQoSDataByCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceQoSDataByCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.qosmodel.dwsc.fmi.unisofia.bg/", name = "getServiceQoSDataByCategoryResponse")
    public JAXBElement<GetServiceQoSDataByCategoryResponse> createGetServiceQoSDataByCategoryResponse(GetServiceQoSDataByCategoryResponse value) {
        return new JAXBElement<GetServiceQoSDataByCategoryResponse>(_GetServiceQoSDataByCategoryResponse_QNAME, GetServiceQoSDataByCategoryResponse.class, null, value);
    }

}
