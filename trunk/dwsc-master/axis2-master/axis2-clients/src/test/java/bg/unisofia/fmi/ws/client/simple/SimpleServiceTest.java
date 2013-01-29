

/**
 * SimpleServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package bg.unisofia.fmi.ws.client.simple;

    /*
     *  SimpleServiceTest Junit test case
    */

    public class SimpleServiceTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testhelloService() throws java.lang.Exception{

        bg.unisofia.fmi.ws.client.simple.SimpleServiceStub stub =
                    new bg.unisofia.fmi.ws.client.simple.SimpleServiceStub();//the default implementation should point to the right endpoint

           bg.unisofia.fmi.ws.client.simple.SimpleServiceStub.HelloService helloService4=
                                                        (bg.unisofia.fmi.ws.client.simple.SimpleServiceStub.HelloService)getTestObject(bg.unisofia.fmi.ws.client.simple.SimpleServiceStub.HelloService.class);
                    // TODO : Fill in the helloService4 here
                
                        assertNotNull(stub.helloService(
                        helloService4));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStarthelloService() throws java.lang.Exception{
            bg.unisofia.fmi.ws.client.simple.SimpleServiceStub stub = new bg.unisofia.fmi.ws.client.simple.SimpleServiceStub();
             bg.unisofia.fmi.ws.client.simple.SimpleServiceStub.HelloService helloService4=
                                                        (bg.unisofia.fmi.ws.client.simple.SimpleServiceStub.HelloService)getTestObject(bg.unisofia.fmi.ws.client.simple.SimpleServiceStub.HelloService.class);
                    // TODO : Fill in the helloService4 here
                

                stub.starthelloService(
                         helloService4,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends bg.unisofia.fmi.ws.client.simple.SimpleServiceCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResulthelloService(
                         bg.unisofia.fmi.ws.client.simple.SimpleServiceStub.HelloServiceResponse result
                            ) {
                
            }

            public void receiveErrorhelloService(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    