

/**
 * PowServiceStandardTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package bg.unisofia.fmi.ws.client.pow;

import bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.PowResponse;

    /*
     *  PowServiceStandardTest Junit test case
    */

    public class PowServiceStandardTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testpow() throws java.lang.Exception{

        bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub stub =
                    new bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub();//the default implementation should point to the right endpoint

           bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.Pow pow4=
                                                        (bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.Pow)getTestObject(bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.Pow.class);
           pow4.setA(3F);
           pow4.setB(4F);
           PowResponse result = stub.pow(pow4);
	        assertNotNull(result);
	        assertEquals(0F, result.get_return());      



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartpow() throws java.lang.Exception{
            bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub stub = new bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub();
             bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.Pow pow4=
                                                        (bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.Pow)getTestObject(bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.Pow.class);
             pow4.setA(3F);
             pow4.setB(4F);
                stub.startpow(
                         pow4,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends bg.unisofia.fmi.ws.client.pow.PowServiceStandardCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResultpow(
                         bg.unisofia.fmi.ws.client.pow.PowServiceStandardStub.PowResponse result
                            ) {
                
            }

            public void receiveErrorpow(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    