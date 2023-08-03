//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.08.12 às 01:49:43 PM BRT 
//


package br.inf.portalfiscal.mdfe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.inf.portalfiscal.mdfe package. 
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
public class ObjectFactory_ence {

	private final static QName _EventoMDFe_QNAME = new QName("http://www.portalfiscal.inf.br/mdfe", "eventoMDFe");
	
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.mdfe
     * 
     */
    public ObjectFactory_ence() {
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
     * Create an instance of {@link TRetEvento }
     * 
     */
    public TRetEvento createTRetEvento() {
        return new TRetEvento();
    }

    /**
     * Create an instance of {@link EvEncMDFe }
     * 
     */
    public EvEncMDFe createEvEncMDFe() {
        return new EvEncMDFe();
    }

    /**
     * Create an instance of {@link TProcEvento }
     * 
     */
    public TProcEvento createTProcEvento() {
        return new TProcEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento.DetEvento }
     * 
     */
    public TEvento.InfEvento.DetEvento createTEventoInfEventoDetEvento() {
        return new TEvento.InfEvento.DetEvento();
    }

    /**
     * Create an instance of {@link TRetEvento.InfEvento }
     * 
     */
    public TRetEvento.InfEvento createTRetEventoInfEvento() {
        return new TRetEvento.InfEvento();
    }
    
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/mdfe", name = "eventoMDFe")
    public JAXBElement<TEnvEvento> createEventoMDFe(TEnvEvento TEnvEvento) {
        return new JAXBElement<TEnvEvento>(_EventoMDFe_QNAME, TEnvEvento.class, null, TEnvEvento);
    }

}
