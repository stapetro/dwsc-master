
/**
 * RegisterServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package bg.unisofia.fmi.dwsc.qosmodel.api;

public class RegisterServiceExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1360494461213L;
    
    private bg.unisofia.fmi.dwsc.qosmodel.api.RegisterServiceException faultMessage;

    
        public RegisterServiceExceptionException() {
            super("RegisterServiceExceptionException");
        }

        public RegisterServiceExceptionException(java.lang.String s) {
           super(s);
        }

        public RegisterServiceExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public RegisterServiceExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(bg.unisofia.fmi.dwsc.qosmodel.api.RegisterServiceException msg){
       faultMessage = msg;
    }
    
    public bg.unisofia.fmi.dwsc.qosmodel.api.RegisterServiceException getFaultMessage(){
       return faultMessage;
    }
}
    