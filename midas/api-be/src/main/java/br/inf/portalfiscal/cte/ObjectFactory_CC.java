//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.04 at 10:23:18 AM ART 
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
public class ObjectFactory_CC {


	private final static QName _CTE_QNAME = new QName("http://www.portalfiscal.inf.br/cte", "envEvento");
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.cte
     * 
     */
    public ObjectFactory_CC() {
    }

    /**
     * Create an instance of {@link EvCCeCTe }
     * 
     */
    public EvCCeCTe createEvCCeCTe() {
        return new EvCCeCTe();
    }

    /**
     * Create an instance of {@link TRetEvento }
     * 
     */
    public TRetEvento createTRetEvento() {
        return new TRetEvento();
    }

    /**
     * Create an instance of {@link TEvento }
     * 
     */
    public TEvento createTEvento() {
        return new TEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento }
     * 
     */
    public TEvento.InfEvento createTEventoInfEvento() {
        return new TEvento.InfEvento();
    }

    /**
     * Create an instance of {@link EvCCeCTe.InfCorrecao }
     * 
     */
    public EvCCeCTe.InfCorrecao createEvCCeCTeInfCorrecao() {
        return new EvCCeCTe.InfCorrecao();
    }

    /**
     * Create an instance of {@link TProcEvento }
     * 
     */
    public TProcEvento createTProcEvento() {
        return new TProcEvento();
    }

    /**
     * Create an instance of {@link TRetEvento.InfEvento }
     * 
     */
    public TRetEvento.InfEvento createTRetEventoInfEvento() {
        return new TRetEvento.InfEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento.DetEvento }
     * 
     */
    public TEvento.InfEvento.DetEvento createTEventoInfEventoDetEvento() {
        return new TEvento.InfEvento.DetEvento();
    }
    
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "envEvento")
   	public JAXBElement<TEnvEvento> createEnvEvento(TEnvEvento value) {
   		return new JAXBElement<TEnvEvento>(_CTE_QNAME, TEnvEvento.class, null, value);
   	}

}
