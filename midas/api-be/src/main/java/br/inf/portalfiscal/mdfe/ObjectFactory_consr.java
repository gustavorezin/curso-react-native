//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.08.12 às 02:09:03 PM BRT 
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
public class ObjectFactory_consr {

    private final static QName _ConsReciMDFe_QNAME = new QName("http://www.portalfiscal.inf.br/mdfe", "consReciMDFe");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.mdfe
     * 
     */
    public ObjectFactory_consr() {
    }

    /**
     * Create an instance of {@link TProtMDFe }
     * 
     */
    public TProtMDFe createTProtMDFe() {
        return new TProtMDFe();
    }

    /**
     * Create an instance of {@link TConsReciMDFe }
     * 
     */
    public TConsReciMDFe createTConsReciMDFe() {
        return new TConsReciMDFe();
    }

    /**
     * Create an instance of {@link TRetConsReciMDFe }
     * 
     */
    public TRetConsReciMDFe createTRetConsReciMDFe() {
        return new TRetConsReciMDFe();
    }

    /**
     * Create an instance of {@link TProtMDFe.InfProt }
     * 
     */
    public TProtMDFe.InfProt createTProtMDFeInfProt() {
        return new TProtMDFe.InfProt();
    }

    /**
     * Create an instance of {@link TProtMDFe.InfFisco }
     * 
     */
    public TProtMDFe.InfFisco createTProtMDFeInfFisco() {
        return new TProtMDFe.InfFisco();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TConsReciMDFe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/mdfe", name = "consReciMDFe")
    public static JAXBElement<TConsReciMDFe> createConsReciMDFe(TConsReciMDFe value) {
        return new JAXBElement<TConsReciMDFe>(_ConsReciMDFe_QNAME, TConsReciMDFe.class, null, value);
    }
    
    

}
