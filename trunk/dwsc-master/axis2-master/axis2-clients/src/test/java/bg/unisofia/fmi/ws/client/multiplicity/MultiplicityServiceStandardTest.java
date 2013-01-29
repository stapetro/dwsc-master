

/**
 * MultiplicityServiceStandardTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package bg.unisofia.fmi.ws.client.multiplicity;

import bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.MultiplyResponse;

    /*
     *  MultiplicityServiceStandardTest Junit test case
    */

    public class MultiplicityServiceStandardTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testmultiply() throws java.lang.Exception{

        bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub stub =
                    new bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub();//the default implementation should point to the right endpoint

           bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.Multiply multiply4=
                                                        (bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.Multiply)getTestObject(bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.Multiply.class);
                multiply4.setA(3F);
                multiply4.setB(4F);
                MultiplyResponse result = stub.multiply(multiply4);
                assertNotNull(result);
                assertEquals(12.0, result.get_return());
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartmultiply() throws java.lang.Exception{
            bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub stub = new bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub();
             bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.Multiply multiply4=
                                                        (bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.Multiply)getTestObject(bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.Multiply.class);
             multiply4.setA(3F);
             multiply4.setB(4F);
                stub.startmultiply(
                         multiply4,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResultmultiply(
                         bg.unisofia.fmi.ws.client.multiplicity.MultiplicityServiceStandardStub.MultiplyResponse result
                            ) {
                
            }

            public void receiveErrormultiply(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    