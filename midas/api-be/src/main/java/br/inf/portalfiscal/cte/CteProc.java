//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.04 at 10:24:31 AM ART 
//


package br.inf.portalfiscal.cte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CTe" type="{http://www.portalfiscal.inf.br/cte}TCTe"/>
 *         &lt;element name="protCTe" type="{http://www.portalfiscal.inf.br/cte}TProtCTe"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/cte}TVerCTe" />
 *       &lt;attribute name="ipTransmissor" type="{http://www.portalfiscal.inf.br/cte}TIPv4" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cTe",
    "protCTe"
})
@XmlRootElement(name = "cteProc")
public class CteProc {

    @XmlElement(name = "CTe", required = true)
    protected TCTe cTe;
    @XmlElement(required = true)
    protected TProtCTe protCTe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;
    @XmlAttribute(name = "ipTransmissor")
    protected String ipTransmissor;

    /**
     * Gets the value of the cTe property.
     * 
     * @return
     *     possible object is
     *     {@link TCTe }
     *     
     */
    public TCTe getCTe() {
        return cTe;
    }

    /**
     * Sets the value of the cTe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCTe }
     *     
     */
    public void setCTe(TCTe value) {
        this.cTe = value;
    }

    /**
     * Gets the value of the protCTe property.
     * 
     * @return
     *     possible object is
     *     {@link TProtCTe }
     *     
     */
    public TProtCTe getProtCTe() {
        return protCTe;
    }

    /**
     * Sets the value of the protCTe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TProtCTe }
     *     
     */
    public void setProtCTe(TProtCTe value) {
        this.protCTe = value;
    }

    /**
     * Gets the value of the versao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Sets the value of the versao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
    }

    /**
     * Gets the value of the ipTransmissor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpTransmissor() {
        return ipTransmissor;
    }

    /**
     * Sets the value of the ipTransmissor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpTransmissor(String value) {
        this.ipTransmissor = value;
    }

}
