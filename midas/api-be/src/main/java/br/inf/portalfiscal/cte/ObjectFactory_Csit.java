//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.04 at 10:29:37 AM ART 
//


package br.inf.portalfiscal.cte;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.inf.portalfiscal.cte package. 
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
public class ObjectFactory_Csit {

    private final static QName _ConsSitCTe_QNAME = new QName("http://www.portalfiscal.inf.br/cte", "consSitCTe");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.cte
     * 
     */
    public ObjectFactory_Csit() {
    }

    /**
     * Create an instance of {@link TRetConsSitCTe }
     * 
     */
    public TRetConsSitCTe createTRetConsSitCTe() {
        return new TRetConsSitCTe();
    }

    /**
     * Create an instance of {@link TConsSitCTe }
     * 
     */
    public TConsSitCTe createTConsSitCTe() {
        return new TConsSitCTe();
    }

    /**
     * Create an instance of {@link TRetConsSitCTe.ProtCTe }
     * 
     */
    public TRetConsSitCTe.ProtCTe createTRetConsSitCTeProtCTe() {
        return new TRetConsSitCTe.ProtCTe();
    }

    /**
     * Create an instance of {@link TRetConsSitCTe.RetCancCTe }
     * 
     */
    public TRetConsSitCTe.RetCancCTe createTRetConsSitCTeRetCancCTe() {
        return new TRetConsSitCTe.RetCancCTe();
    }

    /**
     * Create an instance of {@link TRetConsSitCTe.ProcEventoCTe }
     * 
     */
    public TRetConsSitCTe.ProcEventoCTe createTRetConsSitCTeProcEventoCTe() {
        return new TRetConsSitCTe.ProcEventoCTe();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TConsSitCTe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/cte", name = "consSitCTe")
    public static JAXBElement<TConsSitCTe> createConsSitCTe(TConsSitCTe value) {
        return new JAXBElement<TConsSitCTe>(_ConsSitCTe_QNAME, TConsSitCTe.class, null, value);
    }

}