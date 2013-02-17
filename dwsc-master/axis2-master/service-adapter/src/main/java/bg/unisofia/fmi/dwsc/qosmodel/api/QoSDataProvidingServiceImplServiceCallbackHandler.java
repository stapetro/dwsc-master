
/**
 * QoSDataProvidingServiceImplServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package bg.unisofia.fmi.dwsc.qosmodel.api;

    /**
     *  QoSDataProvidingServiceImplServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class QoSDataProvidingServiceImplServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public QoSDataProvidingServiceImplServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public QoSDataProvidingServiceImplServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getServiceQoSData method
            * override this method for handling normal response from getServiceQoSData operation
            */
           public void receiveResultgetServiceQoSData(
                    bg.unisofia.fmi.dwsc.qosmodel.api.GetServiceQoSDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServiceQoSData operation
           */
            public void receiveErrorgetServiceQoSData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getServiceQoSDataByCategory method
            * override this method for handling normal response from getServiceQoSDataByCategory operation
            */
           public void receiveResultgetServiceQoSDataByCategory(
                    bg.unisofia.fmi.dwsc.qosmodel.api.GetServiceQoSDataByCategoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServiceQoSDataByCategory operation
           */
            public void receiveErrorgetServiceQoSDataByCategory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registerService method
            * override this method for handling normal response from registerService operation
            */
           public void receiveResultregisterService(
                    bg.unisofia.fmi.dwsc.qosmodel.api.RegisterServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registerService operation
           */
            public void receiveErrorregisterService(java.lang.Exception e) {
            }
                


    }
    