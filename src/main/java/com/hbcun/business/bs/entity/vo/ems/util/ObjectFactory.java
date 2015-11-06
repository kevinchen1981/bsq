
package com.hbcun.business.bs.entity.vo.ems.util;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hbcun.pojo.ems.util package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hbcun.pojo.ems.util
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CallService }
     * 
     */
    public CallService createCallService() {
        return new CallService();
    }

    /**
     * Create an instance of {@link CallServiceResponse }
     * 
     */
    public CallServiceResponse createCallServiceResponse() {
        return new CallServiceResponse();
    }

    /**
     * Create an instance of {@link CallServiceReturn }
     * 
     */
    public CallServiceReturn createCallServiceReturn() {
        return new CallServiceReturn();
    }

    /**
     * Create an instance of {@link CallServiceReturnResponse }
     * 
     */
    public CallServiceReturnResponse createCallServiceReturnResponse() {
        return new CallServiceReturnResponse();
    }

}
