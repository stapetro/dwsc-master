
/**
 * SaveQoSDataExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package bg.unisofia.fmi.dwsc.qosmodel.api;

public class SaveQoSDataExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1361029511508L;
    
    private bg.unisofia.fmi.dwsc.qosmodel.api.SaveQoSDataException faultMessage;

    
        public SaveQoSDataExceptionException() {
            super("SaveQoSDataExceptionException");
        }

        public SaveQoSDataExceptionException(java.lang.String s) {
           super(s);
        }

        public SaveQoSDataExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SaveQoSDataExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(bg.unisofia.fmi.dwsc.qosmodel.api.SaveQoSDataException msg){
       faultMessage = msg;
    }
    
    public bg.unisofia.fmi.dwsc.qosmodel.api.SaveQoSDataException getFaultMessage(){
       return faultMessage;
    }
}
    