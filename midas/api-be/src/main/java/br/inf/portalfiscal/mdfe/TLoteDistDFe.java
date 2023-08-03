//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.08.12 às 11:34:19 AM BRT 
//


package br.inf.portalfiscal.mdfe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * Schema XML de validação da área de dados descompactada
 * 
 * <p>Classe Java de TLoteDistDFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TLoteDistDFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="50">
 *         &lt;element name="proc">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any processContents='skip'/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="schema" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="NSU" use="required" type="{http://www.portalfiscal.inf.br/mdfe}TNSU" />
 *                 &lt;attribute name="ipTransmissor" type="{http://www.portalfiscal.inf.br/mdfe}TIPv4" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/mdfe}TVerDFe" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLoteDistDFe", propOrder = {
    "proc"
})
public class TLoteDistDFe {

    @XmlElement(required = true)
    protected List<TLoteDistDFe.Proc> proc;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Gets the value of the proc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TLoteDistDFe.Proc }
     * 
     * 
     */
    public List<TLoteDistDFe.Proc> getProc() {
        if (proc == null) {
            proc = new ArrayList<TLoteDistDFe.Proc>();
        }
        return this.proc;
    }

    /**
     * Obtém o valor da propriedade versao.
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
     * Define o valor da propriedade versao.
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
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;any processContents='skip'/>
     *       &lt;/sequence>
     *       &lt;attribute name="schema" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="NSU" use="required" type="{http://www.portalfiscal.inf.br/mdfe}TNSU" />
     *       &lt;attribute name="ipTransmissor" type="{http://www.portalfiscal.inf.br/mdfe}TIPv4" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "any"
    })
    public static class Proc {

        @XmlAnyElement
        protected Element any;
        @XmlAttribute(name = "schema", required = true)
        protected String schema;
        @XmlAttribute(name = "NSU", required = true)
        protected String nsu;
        @XmlAttribute(name = "ipTransmissor")
        protected String ipTransmissor;

        /**
         * Obtém o valor da propriedade any.
         * 
         * @return
         *     possible object is
         *     {@link Element }
         *     
         */
        public Element getAny() {
            return any;
        }

        /**
         * Define o valor da propriedade any.
         * 
         * @param value
         *     allowed object is
         *     {@link Element }
         *     
         */
        public void setAny(Element value) {
            this.any = value;
        }

        /**
         * Obtém o valor da propriedade schema.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSchema() {
            return schema;
        }

        /**
         * Define o valor da propriedade schema.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSchema(String value) {
            this.schema = value;
        }

        /**
         * Obtém o valor da propriedade nsu.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNSU() {
            return nsu;
        }

        /**
         * Define o valor da propriedade nsu.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNSU(String value) {
            this.nsu = value;
        }

        /**
         * Obtém o valor da propriedade ipTransmissor.
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
         * Define o valor da propriedade ipTransmissor.
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

}
