//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.06 at 09:40:46 AM ART 
//


package br.inf.portalfiscal.nfse;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
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

	private final static QName _NFE_QNAME = new QName("http://www.portalfiscal.inf.br/nfse", "envioEvento");
    private final static QName _RPSNumero_QNAME = new QName("", "RPSNumero");
    private final static QName _EveCodigo_QNAME = new QName("", "EveCodigo");
    private final static QName _TpAmb_QNAME = new QName("", "tpAmb");
    private final static QName _EveMotivo_QNAME = new QName("", "EveMotivo");
    private final static QName _NFSeNumero_QNAME = new QName("", "NFSeNumero");
    private final static QName _Versao_QNAME = new QName("", "Versao");
    private final static QName _RPSSerie_QNAME = new QName("", "RPSSerie");
    private final static QName _ModeloDocumento_QNAME = new QName("", "ModeloDocumento");
    private final static QName _EveTp_QNAME = new QName("", "EveTp");
    private final static QName _CNPJ_QNAME = new QName("", "CNPJ");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Evento }
     * 
     */
    public Evento createEvento() {
        return new Evento();
    }

    /**
     * Create an instance of {@link EnvioEvento }
     * 
     */
    public EnvioEvento createEnvioEvento() {
        return new EnvioEvento();
    }
    
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "envioEvento")
   	public JAXBElement<EnvioEvento> createEnvEvento(EnvioEvento value) {
   		return new JAXBElement<EnvioEvento>(_NFE_QNAME, EnvioEvento.class, null, value);
   	}

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RPSNumero")
    public JAXBElement<BigInteger> createRPSNumero(BigInteger value) {
        return new JAXBElement<BigInteger>(_RPSNumero_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EveCodigo")
    public JAXBElement<String> createEveCodigo(String value) {
        return new JAXBElement<String>(_EveCodigo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "tpAmb")
    public JAXBElement<BigInteger> createTpAmb(BigInteger value) {
        return new JAXBElement<BigInteger>(_TpAmb_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EveMotivo")
    public JAXBElement<String> createEveMotivo(String value) {
        return new JAXBElement<String>(_EveMotivo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "NFSeNumero")
    public JAXBElement<BigInteger> createNFSeNumero(BigInteger value) {
        return new JAXBElement<BigInteger>(_NFSeNumero_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Versao")
    public JAXBElement<BigDecimal> createVersao(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Versao_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RPSSerie")
    public JAXBElement<String> createRPSSerie(String value) {
        return new JAXBElement<String>(_RPSSerie_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ModeloDocumento")
    public JAXBElement<String> createModeloDocumento(String value) {
        return new JAXBElement<String>(_ModeloDocumento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EveTp")
    public JAXBElement<BigInteger> createEveTp(BigInteger value) {
        return new JAXBElement<BigInteger>(_EveTp_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CNPJ")
    public JAXBElement<String> createCNPJ(String value) {
        return new JAXBElement<String>(_CNPJ_QNAME, String.class, null, value);
    }

}