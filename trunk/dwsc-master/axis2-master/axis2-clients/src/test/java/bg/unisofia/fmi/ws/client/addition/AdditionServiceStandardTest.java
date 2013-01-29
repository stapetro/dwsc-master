

/**
 * AdditionServiceStandardTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package bg.unisofia.fmi.ws.client.addition;

import bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.AddResponse;

    /*
     *  AdditionServiceStandardTest Junit test case
    */

    public class AdditionServiceStandardTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testadd() throws java.lang.Exception{

        bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub stub =
                    new bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub();//the default implementation should point to the right endpoint

           bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.Add add4=
                                                        (bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.Add)getTestObject(bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.Add.class);
                    // TODO : Fill in the add4 here
           add4.setA(3L);
           add4.setA(4L);
           AddResponse response = stub.add(add4);
           assertNotNull(response);
           assertEquals(7L, response.get_return());
        }
        
         /**
         * Auto generated test method
         */
        public  void testStartadd() throws java.lang.Exception{
            bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub stub = new bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub();
             bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.Add add4=
                                                        (bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.Add)getTestObject(bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.Add.class);
                    // TODO : Fill in the add4 here
                

                stub.startadd(
                         add4,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResultadd(
                         bg.unisofia.fmi.ws.client.addition.AdditionServiceStandardStub.AddResponse result
                            ) {
                
            }

            public void receiveErroradd(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    