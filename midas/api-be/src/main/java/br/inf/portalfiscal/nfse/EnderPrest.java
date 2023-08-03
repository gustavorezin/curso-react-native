//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.29 at 01:58:16 PM ART 
//


package br.inf.portalfiscal.nfse;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}TPEnd"/>
 *         &lt;element ref="{}xLgr"/>
 *         &lt;element ref="{}nro" minOccurs="0"/>
 *         &lt;element ref="{}xCpl" minOccurs="0"/>
 *         &lt;element ref="{}xBairro"/>
 *         &lt;element ref="{}cMun"/>
 *         &lt;element ref="{}UF"/>
 *         &lt;element ref="{}CEP"/>
 *         &lt;element ref="{}fone" minOccurs="0"/>
 *         &lt;element ref="{}Email" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tpEnd",
    "xLgr",
    "nro",
    "xCpl",
    "xBairro",
    "cMun",
    "uf",
    "cep",
    "fone",
    "email"
})
@XmlRootElement(name = "enderPrest")
public class EnderPrest {

    @XmlElement(name = "TPEnd", required = true)
    protected String tpEnd;
    @XmlElement(required = true)
    protected String xLgr;
    protected String nro;
    protected String xCpl;
    @XmlElement(required = true)
    protected String xBairro;
    @XmlElement(required = true)
    protected BigInteger cMun;
    @XmlElement(name = "UF", required = true)
    protected String uf;
    @XmlElement(name = "CEP", required = true)
    protected BigInteger cep;
    protected BigInteger fone;
    @XmlElement(name = "Email")
    protected String email;

    /**
     * Gets the value of the tpEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPEnd() {
        return tpEnd;
    }

    /**
     * Sets the value of the tpEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPEnd(String value) {
        this.tpEnd = value;
    }

    /**
     * Gets the value of the xLgr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXLgr() {
        return xLgr;
    }

    /**
     * Sets the value of the xLgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXLgr(String value) {
        this.xLgr = value;
    }

    /**
     * Gets the value of the nro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNro() {
        return nro;
    }

    /**
     * Sets the value of the nro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNro(String value) {
        this.nro = value;
    }

    /**
     * Gets the value of the xCpl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXCpl() {
        return xCpl;
    }

    /**
     * Sets the value of the xCpl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXCpl(String value) {
        this.xCpl = value;
    }

    /**
     * Gets the value of the xBairro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXBairro() {
        return xBairro;
    }

    /**
     * Sets the value of the xBairro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXBairro(String value) {
        this.xBairro = value;
    }

    /**
     * Gets the value of the cMun property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCMun() {
        return cMun;
    }

    /**
     * Sets the value of the cMun property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCMun(BigInteger value) {
        this.cMun = value;
    }

    /**
     * Gets the value of the uf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUF() {
        return uf;
    }

    /**
     * Sets the value of the uf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUF(String value) {
        this.uf = value;
    }

    /**
     * Gets the value of the cep property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCEP() {
        return cep;
    }

    /**
     * Sets the value of the cep property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCEP(BigInteger value) {
        this.cep = value;
    }

    /**
     * Gets the value of the fone property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFone() {
        return fone;
    }

    /**
     * Sets the value of the fone property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFone(BigInteger value) {
        this.fone = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

}