
/**
 * CTeDistribuicaoDFeCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */

    package br.inf.portalfiscal.www.cte.wsdl.ctedistribuicaodfe;

    /**
     *  CTeDistribuicaoDFeCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CTeDistribuicaoDFeCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CTeDistribuicaoDFeCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CTeDistribuicaoDFeCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for cteDistDFeInteresse method
            * override this method for handling normal response from cteDistDFeInteresse operation
            */
           public void receiveResultcteDistDFeInteresse(
                    br.inf.portalfiscal.www.cte.wsdl.ctedistribuicaodfe.CTeDistribuicaoDFeStub.CteDistDFeInteresseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cteDistDFeInteresse operation
           */
            public void receiveErrorcteDistDFeInteresse(java.lang.Exception e) {
            }
                


    }
    