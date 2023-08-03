//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2021.05.04 às 03:45:00 PM BRT 
//


package br.inf.portalfiscal.mdfe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="descEvento">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;enumeration value="Pagamento Operação MDF-e"/>
 *               &lt;enumeration value="Pagamento Operacao MDF-e"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nProt" type="{http://www.portalfiscal.inf.br/mdfe}TProt"/>
 *         &lt;element name="infViagens">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="qtdViagens">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;pattern value="[0-9]{5}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="nroViagem">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;pattern value="[0-9]{5}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="infPag" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="xNome" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                         &lt;maxLength value="60"/>
 *                         &lt;minLength value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;choice>
 *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                     &lt;element name="idEstrangeiro">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;whiteSpace value="preserve"/>
 *                           &lt;minLength value="2"/>
 *                           &lt;maxLength value="20"/>
 *                           &lt;pattern value="([!-ÿ]{0}|[!-ÿ]{5,20})?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                   &lt;/choice>
 *                   &lt;element name="Comp" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="tpComp">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="01"/>
 *                                   &lt;enumeration value="02"/>
 *                                   &lt;enumeration value="03"/>
 *                                   &lt;enumeration value="99"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="vComp" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
 *                             &lt;element name="xComp" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="vContrato" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
 *                   &lt;element name="indPag">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;enumeration value="0"/>
 *                         &lt;enumeration value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="vAdiant" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302" minOccurs="0"/>
 *                   &lt;element name="infPrazo" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nParcela">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="[0-9]{3}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="dVenc" type="{http://www.portalfiscal.inf.br/mdfe}TData"/>
 *                             &lt;element name="vParcela" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302Opc"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infBanc">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;sequence>
 *                               &lt;element name="codBanco">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                     &lt;minLength value="3"/>
 *                                     &lt;maxLength value="5"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="codAgencia">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                     &lt;minLength value="1"/>
 *                                     &lt;maxLength value="10"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                             &lt;/sequence>
 *                             &lt;element name="CNPJIPEF" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                             &lt;element name="PIX">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "descEvento",
    "nProt",
    "infViagens",
    "infPag"
})
@XmlRootElement(name = "evPagtoOperMDFe")
public class EvPagtoOperMDFe {

    @XmlElement(required = true)
    protected String descEvento;
    @XmlElement(required = true)
    protected String nProt;
    @XmlElement(required = true)
    protected EvPagtoOperMDFe.InfViagens infViagens;
    @XmlElement(required = true)
    protected List<EvPagtoOperMDFe.InfPag> infPag;

    /**
     * Obtém o valor da propriedade descEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEvento() {
        return descEvento;
    }

    /**
     * Define o valor da propriedade descEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEvento(String value) {
        this.descEvento = value;
    }

    /**
     * Obtém o valor da propriedade nProt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNProt() {
        return nProt;
    }

    /**
     * Define o valor da propriedade nProt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNProt(String value) {
        this.nProt = value;
    }

    /**
     * Obtém o valor da propriedade infViagens.
     * 
     * @return
     *     possible object is
     *     {@link EvPagtoOperMDFe.InfViagens }
     *     
     */
    public EvPagtoOperMDFe.InfViagens getInfViagens() {
        return infViagens;
    }

    /**
     * Define o valor da propriedade infViagens.
     * 
     * @param value
     *     allowed object is
     *     {@link EvPagtoOperMDFe.InfViagens }
     *     
     */
    public void setInfViagens(EvPagtoOperMDFe.InfViagens value) {
        this.infViagens = value;
    }

    /**
     * Gets the value of the infPag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infPag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfPag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvPagtoOperMDFe.InfPag }
     * 
     * 
     */
    public List<EvPagtoOperMDFe.InfPag> getInfPag() {
        if (infPag == null) {
            infPag = new ArrayList<EvPagtoOperMDFe.InfPag>();
        }
        return this.infPag;
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
     *         &lt;element name="xNome" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *               &lt;maxLength value="60"/>
     *               &lt;minLength value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;choice>
     *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
     *           &lt;element name="idEstrangeiro">
     *             &lt;simpleType>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                 &lt;whiteSpace value="preserve"/>
     *                 &lt;minLength value="2"/>
     *                 &lt;maxLength value="20"/>
     *                 &lt;pattern value="([!-ÿ]{0}|[!-ÿ]{5,20})?"/>
     *               &lt;/restriction>
     *             &lt;/simpleType>
     *           &lt;/element>
     *         &lt;/choice>
     *         &lt;element name="Comp" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="tpComp">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="01"/>
     *                         &lt;enumeration value="02"/>
     *                         &lt;enumeration value="03"/>
     *                         &lt;enumeration value="99"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="vComp" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
     *                   &lt;element name="xComp" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="60"/>
     *                         &lt;minLength value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="vContrato" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
     *         &lt;element name="indPag">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;enumeration value="0"/>
     *               &lt;enumeration value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="vAdiant" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302" minOccurs="0"/>
     *         &lt;element name="infPrazo" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nParcela">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="[0-9]{3}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="dVenc" type="{http://www.portalfiscal.inf.br/mdfe}TData"/>
     *                   &lt;element name="vParcela" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302Opc"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infBanc">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;sequence>
     *                     &lt;element name="codBanco">
     *                       &lt;simpleType>
     *                         &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                           &lt;minLength value="3"/>
     *                           &lt;maxLength value="5"/>
     *                         &lt;/restriction>
     *                       &lt;/simpleType>
     *                     &lt;/element>
     *                     &lt;element name="codAgencia">
     *                       &lt;simpleType>
     *                         &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                           &lt;minLength value="1"/>
     *                           &lt;maxLength value="10"/>
     *                         &lt;/restriction>
     *                       &lt;/simpleType>
     *                     &lt;/element>
     *                   &lt;/sequence>
     *                   &lt;element name="CNPJIPEF" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
     *                   &lt;element name="PIX">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="60"/>
     *                         &lt;minLength value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/choice>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "xNome",
        "cpf",
        "cnpj",
        "idEstrangeiro",
        "comp",
        "vContrato",
        "indPag",
        "vAdiant",
        "infPrazo",
        "infBanc"
    })
    public static class InfPag {

        protected String xNome;
        @XmlElement(name = "CPF")
        protected String cpf;
        @XmlElement(name = "CNPJ")
        protected String cnpj;
        protected String idEstrangeiro;
        @XmlElement(name = "Comp", required = true)
        protected List<EvPagtoOperMDFe.InfPag.Comp> comp;
        @XmlElement(required = true)
        protected String vContrato;
        @XmlElement(required = true)
        protected String indPag;
        protected String vAdiant;
        protected List<EvPagtoOperMDFe.InfPag.InfPrazo> infPrazo;
        @XmlElement(required = true)
        protected EvPagtoOperMDFe.InfPag.InfBanc infBanc;

        /**
         * Obtém o valor da propriedade xNome.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXNome() {
            return xNome;
        }

        /**
         * Define o valor da propriedade xNome.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXNome(String value) {
            this.xNome = value;
        }

        /**
         * Obtém o valor da propriedade cpf.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCPF() {
            return cpf;
        }

        /**
         * Define o valor da propriedade cpf.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCPF(String value) {
            this.cpf = value;
        }

        /**
         * Obtém o valor da propriedade cnpj.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCNPJ() {
            return cnpj;
        }

        /**
         * Define o valor da propriedade cnpj.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCNPJ(String value) {
            this.cnpj = value;
        }

        /**
         * Obtém o valor da propriedade idEstrangeiro.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdEstrangeiro() {
            return idEstrangeiro;
        }

        /**
         * Define o valor da propriedade idEstrangeiro.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdEstrangeiro(String value) {
            this.idEstrangeiro = value;
        }

        /**
         * Gets the value of the comp property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the comp property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComp().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvPagtoOperMDFe.InfPag.Comp }
         * 
         * 
         */
        public List<EvPagtoOperMDFe.InfPag.Comp> getComp() {
            if (comp == null) {
                comp = new ArrayList<EvPagtoOperMDFe.InfPag.Comp>();
            }
            return this.comp;
        }

        /**
         * Obtém o valor da propriedade vContrato.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVContrato() {
            return vContrato;
        }

        /**
         * Define o valor da propriedade vContrato.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVContrato(String value) {
            this.vContrato = value;
        }

        /**
         * Obtém o valor da propriedade indPag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIndPag() {
            return indPag;
        }

        /**
         * Define o valor da propriedade indPag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIndPag(String value) {
            this.indPag = value;
        }

        /**
         * Obtém o valor da propriedade vAdiant.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVAdiant() {
            return vAdiant;
        }

        /**
         * Define o valor da propriedade vAdiant.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVAdiant(String value) {
            this.vAdiant = value;
        }

        /**
         * Gets the value of the infPrazo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infPrazo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfPrazo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvPagtoOperMDFe.InfPag.InfPrazo }
         * 
         * 
         */
        public List<EvPagtoOperMDFe.InfPag.InfPrazo> getInfPrazo() {
            if (infPrazo == null) {
                infPrazo = new ArrayList<EvPagtoOperMDFe.InfPag.InfPrazo>();
            }
            return this.infPrazo;
        }

        /**
         * Obtém o valor da propriedade infBanc.
         * 
         * @return
         *     possible object is
         *     {@link EvPagtoOperMDFe.InfPag.InfBanc }
         *     
         */
        public EvPagtoOperMDFe.InfPag.InfBanc getInfBanc() {
            return infBanc;
        }

        /**
         * Define o valor da propriedade infBanc.
         * 
         * @param value
         *     allowed object is
         *     {@link EvPagtoOperMDFe.InfPag.InfBanc }
         *     
         */
        public void setInfBanc(EvPagtoOperMDFe.InfPag.InfBanc value) {
            this.infBanc = value;
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
         *         &lt;element name="tpComp">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="01"/>
         *               &lt;enumeration value="02"/>
         *               &lt;enumeration value="03"/>
         *               &lt;enumeration value="99"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="vComp" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
         *         &lt;element name="xComp" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="60"/>
         *               &lt;minLength value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
            "tpComp",
            "vComp",
            "xComp"
        })
        public static class Comp {

            @XmlElement(required = true)
            protected String tpComp;
            @XmlElement(required = true)
            protected String vComp;
            protected String xComp;

            /**
             * Obtém o valor da propriedade tpComp.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpComp() {
                return tpComp;
            }

            /**
             * Define o valor da propriedade tpComp.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpComp(String value) {
                this.tpComp = value;
            }

            /**
             * Obtém o valor da propriedade vComp.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVComp() {
                return vComp;
            }

            /**
             * Define o valor da propriedade vComp.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVComp(String value) {
                this.vComp = value;
            }

            /**
             * Obtém o valor da propriedade xComp.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXComp() {
                return xComp;
            }

            /**
             * Define o valor da propriedade xComp.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXComp(String value) {
                this.xComp = value;
            }

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
         *       &lt;choice>
         *         &lt;sequence>
         *           &lt;element name="codBanco">
         *             &lt;simpleType>
         *               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                 &lt;minLength value="3"/>
         *                 &lt;maxLength value="5"/>
         *               &lt;/restriction>
         *             &lt;/simpleType>
         *           &lt;/element>
         *           &lt;element name="codAgencia">
         *             &lt;simpleType>
         *               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                 &lt;minLength value="1"/>
         *                 &lt;maxLength value="10"/>
         *               &lt;/restriction>
         *             &lt;/simpleType>
         *           &lt;/element>
         *         &lt;/sequence>
         *         &lt;element name="CNPJIPEF" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
         *         &lt;element name="PIX">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="60"/>
         *               &lt;minLength value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *       &lt;/choice>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "codBanco",
            "codAgencia",
            "cnpjipef",
            "pix"
        })
        public static class InfBanc {

            protected String codBanco;
            protected String codAgencia;
            @XmlElement(name = "CNPJIPEF")
            protected String cnpjipef;
            @XmlElement(name = "PIX")
            protected String pix;

            /**
             * Obtém o valor da propriedade codBanco.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodBanco() {
                return codBanco;
            }

            /**
             * Define o valor da propriedade codBanco.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodBanco(String value) {
                this.codBanco = value;
            }

            /**
             * Obtém o valor da propriedade codAgencia.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodAgencia() {
                return codAgencia;
            }

            /**
             * Define o valor da propriedade codAgencia.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodAgencia(String value) {
                this.codAgencia = value;
            }

            /**
             * Obtém o valor da propriedade cnpjipef.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCNPJIPEF() {
                return cnpjipef;
            }

            /**
             * Define o valor da propriedade cnpjipef.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCNPJIPEF(String value) {
                this.cnpjipef = value;
            }

            /**
             * Obtém o valor da propriedade pix.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPIX() {
                return pix;
            }

            /**
             * Define o valor da propriedade pix.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPIX(String value) {
                this.pix = value;
            }

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
         *         &lt;element name="nParcela">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="[0-9]{3}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="dVenc" type="{http://www.portalfiscal.inf.br/mdfe}TData"/>
         *         &lt;element name="vParcela" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302Opc"/>
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
            "nParcela",
            "dVenc",
            "vParcela"
        })
        public static class InfPrazo {

            @XmlElement(required = true)
            protected String nParcela;
            @XmlElement(required = true)
            protected String dVenc;
            @XmlElement(required = true)
            protected String vParcela;

            /**
             * Obtém o valor da propriedade nParcela.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNParcela() {
                return nParcela;
            }

            /**
             * Define o valor da propriedade nParcela.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNParcela(String value) {
                this.nParcela = value;
            }

            /**
             * Obtém o valor da propriedade dVenc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDVenc() {
                return dVenc;
            }

            /**
             * Define o valor da propriedade dVenc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDVenc(String value) {
                this.dVenc = value;
            }

            /**
             * Obtém o valor da propriedade vParcela.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVParcela() {
                return vParcela;
            }

            /**
             * Define o valor da propriedade vParcela.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVParcela(String value) {
                this.vParcela = value;
            }

        }

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
     *         &lt;element name="qtdViagens">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="[0-9]{5}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="nroViagem">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="[0-9]{5}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
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
        "qtdViagens",
        "nroViagem"
    })
    public static class InfViagens {

        @XmlElement(required = true)
        protected String qtdViagens;
        @XmlElement(required = true)
        protected String nroViagem;

        /**
         * Obtém o valor da propriedade qtdViagens.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQtdViagens() {
            return qtdViagens;
        }

        /**
         * Define o valor da propriedade qtdViagens.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQtdViagens(String value) {
            this.qtdViagens = value;
        }

        /**
         * Obtém o valor da propriedade nroViagem.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNroViagem() {
            return nroViagem;
        }

        /**
         * Define o valor da propriedade nroViagem.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNroViagem(String value) {
            this.nroViagem = value;
        }

    }

}
