//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.06 at 09:40:46 AM ART 
//


package br.inf.portalfiscal.nfse;

import java.math.BigDecimal;
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
 *         &lt;element ref="{}ModeloDocumento"/>
 *         &lt;element ref="{}Versao"/>
 *         &lt;element ref="{}Evento"/>
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
    "modeloDocumento",
    "versao",
    "evento"
})
@XmlRootElement(name = "EnvioEvento")
public class EnvioEvento {

    @XmlElement(name = "ModeloDocumento", required = true)
    protected String modeloDocumento;
    @XmlElement(name = "Versao", required = true)
    protected BigDecimal versao;
    @XmlElement(name = "Evento", required = true)
    protected Evento evento;

    /**
     * Gets the value of the modeloDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeloDocumento() {
        return modeloDocumento;
    }

    /**
     * Sets the value of the modeloDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeloDocumento(String value) {
        this.modeloDocumento = value;
    }

    /**
     * Gets the value of the versao property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersao() {
        return versao;
    }

    /**
     * Sets the value of the versao property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersao(BigDecimal value) {
        this.versao = value;
    }

    /**
     * Gets the value of the evento property.
     * 
     * @return
     *     possible object is
     *     {@link Evento }
     *     
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Sets the value of the evento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Evento }
     *     
     */
    public void setEvento(Evento value) {
        this.evento = value;
    }

}