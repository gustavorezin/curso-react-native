package br.inf.portalfiscal.nfe;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Tipo Retorno Pedido de Consulta de cadastro de contribuintes
 * 
 * <p>Java class for TRetConsCad complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TRetConsCad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infCons">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *                   &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
 *                   &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
 *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUfCons"/>
 *                   &lt;choice>
 *                     &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/>
 *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/>
 *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/>
 *                   &lt;/choice>
 *                   &lt;element name="dhCons" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
 *                   &lt;element name="infCad" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/>
 *                             &lt;choice>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/>
 *                             &lt;/choice>
 *                             &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUf"/>
 *                             &lt;element name="cSit">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;enumeration value="0"/>
 *                                   &lt;enumeration value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="indCredNFe">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="0"/>
 *                                   &lt;enumeration value="1"/>
 *                                   &lt;enumeration value="2"/>
 *                                   &lt;enumeration value="3"/>
 *                                   &lt;enumeration value="4"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="indCredCTe">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="0"/>
 *                                   &lt;enumeration value="1"/>
 *                                   &lt;enumeration value="2"/>
 *                                   &lt;enumeration value="3"/>
 *                                   &lt;enumeration value="4"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="xNome">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="60"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="xFant" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="60"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="xRegApur" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="60"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="CNAE" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;pattern value="[0-9]{6,7}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="dIniAtiv" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="dUltSit" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="dBaixa" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="IEUnica" type="{http://www.portalfiscal.inf.br/nfe}TIe" minOccurs="0"/>
 *                             &lt;element name="IEAtual" type="{http://www.portalfiscal.inf.br/nfe}TIe" minOccurs="0"/>
 *                             &lt;element name="ender" type="{http://www.portalfiscal.inf.br/nfe}TEndereco" minOccurs="0"/>
 *                           &lt;/sequence>
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
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerConsCad" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRetConsCad", propOrder = {
    "infCons"
})
public class TRetConsCad {

    @XmlElement(required = true)
    protected TRetConsCad.InfCons infCons;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versao;

    /**
     * Gets the value of the infCons property.
     * 
     * @return
     *     possible object is
     *     {@link TRetConsCad.InfCons }
     *     
     */
    public TRetConsCad.InfCons getInfCons() {
        return infCons;
    }

    /**
     * Sets the value of the infCons property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRetConsCad.InfCons }
     *     
     */
    public void setInfCons(TRetConsCad.InfCons value) {
        this.infCons = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
     *         &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
     *         &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
     *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUfCons"/>
     *         &lt;choice>
     *           &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/>
     *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/>
     *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/>
     *         &lt;/choice>
     *         &lt;element name="dhCons" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/>
     *         &lt;element name="infCad" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/>
     *                   &lt;choice>
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/>
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/>
     *                   &lt;/choice>
     *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUf"/>
     *                   &lt;element name="cSit">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;enumeration value="0"/>
     *                         &lt;enumeration value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="indCredNFe">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="0"/>
     *                         &lt;enumeration value="1"/>
     *                         &lt;enumeration value="2"/>
     *                         &lt;enumeration value="3"/>
     *                         &lt;enumeration value="4"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="indCredCTe">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="0"/>
     *                         &lt;enumeration value="1"/>
     *                         &lt;enumeration value="2"/>
     *                         &lt;enumeration value="3"/>
     *                         &lt;enumeration value="4"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="xNome">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="60"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="xFant" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="60"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="xRegApur" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="60"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="CNAE" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;pattern value="[0-9]{6,7}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="dIniAtiv" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="dUltSit" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="dBaixa" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="IEUnica" type="{http://www.portalfiscal.inf.br/nfe}TIe" minOccurs="0"/>
     *                   &lt;element name="IEAtual" type="{http://www.portalfiscal.inf.br/nfe}TIe" minOccurs="0"/>
     *                   &lt;element name="ender" type="{http://www.portalfiscal.inf.br/nfe}TEndereco" minOccurs="0"/>
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
        "verAplic",
        "cStat",
        "xMotivo",
        "uf",
        "ie",
        "cnpj",
        "cpf",
        "dhCons",
        "cuf",
        "infCad"
    })
    public static class InfCons {

        @XmlElement(required = true)
        protected String verAplic;
        @XmlElement(required = true)
        protected String cStat;
        @XmlElement(required = true)
        protected String xMotivo;
        @XmlElement(name = "UF", required = true)
        protected TUfCons uf;
        @XmlElement(name = "IE")
        protected String ie;
        @XmlElement(name = "CNPJ")
        protected String cnpj;
        @XmlElement(name = "CPF")
        protected String cpf;
        @XmlElement(required = true)
        protected XMLGregorianCalendar dhCons;
        @XmlElement(name = "cUF", required = true)
        protected String cuf;
        protected List<TRetConsCad.InfCons.InfCad> infCad;

        /**
         * Gets the value of the verAplic property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVerAplic() {
            return verAplic;
        }

        /**
         * Sets the value of the verAplic property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVerAplic(String value) {
            this.verAplic = value;
        }

        /**
         * Gets the value of the cStat property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCStat() {
            return cStat;
        }

        /**
         * Sets the value of the cStat property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCStat(String value) {
            this.cStat = value;
        }

        /**
         * Gets the value of the xMotivo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXMotivo() {
            return xMotivo;
        }

        /**
         * Sets the value of the xMotivo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXMotivo(String value) {
            this.xMotivo = value;
        }

        /**
         * Gets the value of the uf property.
         * 
         * @return
         *     possible object is
         *     {@link TUfCons }
         *     
         */
        public TUfCons getUF() {
            return uf;
        }

        /**
         * Sets the value of the uf property.
         * 
         * @param value
         *     allowed object is
         *     {@link TUfCons }
         *     
         */
        public void setUF(TUfCons value) {
            this.uf = value;
        }

        /**
         * Gets the value of the ie property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIE() {
            return ie;
        }

        /**
         * Sets the value of the ie property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIE(String value) {
            this.ie = value;
        }

        /**
         * Gets the value of the cnpj property.
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
         * Sets the value of the cnpj property.
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
         * Gets the value of the cpf property.
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
         * Sets the value of the cpf property.
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
         * Gets the value of the dhCons property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDhCons() {
            return dhCons;
        }

        /**
         * Sets the value of the dhCons property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDhCons(XMLGregorianCalendar value) {
            this.dhCons = value;
        }

        /**
         * Gets the value of the cuf property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCUF() {
            return cuf;
        }

        /**
         * Sets the value of the cuf property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCUF(String value) {
            this.cuf = value;
        }

        /**
         * Gets the value of the infCad property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infCad property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfCad().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TRetConsCad.InfCons.InfCad }
         * 
         * 
         */
        public List<TRetConsCad.InfCons.InfCad> getInfCad() {
            if (infCad == null) {
                infCad = new ArrayList<TRetConsCad.InfCons.InfCad>();
            }
            return this.infCad;
        }


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
         *         &lt;element name="IE" type="{http://www.portalfiscal.inf.br/nfe}TIe"/>
         *         &lt;choice>
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpjVar"/>
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/nfe}TCpfVar"/>
         *         &lt;/choice>
         *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUf"/>
         *         &lt;element name="cSit">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;enumeration value="0"/>
         *               &lt;enumeration value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="indCredNFe">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="0"/>
         *               &lt;enumeration value="1"/>
         *               &lt;enumeration value="2"/>
         *               &lt;enumeration value="3"/>
         *               &lt;enumeration value="4"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="indCredCTe">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="0"/>
         *               &lt;enumeration value="1"/>
         *               &lt;enumeration value="2"/>
         *               &lt;enumeration value="3"/>
         *               &lt;enumeration value="4"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="xNome">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="60"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="xFant" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="60"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="xRegApur" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="60"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="CNAE" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;pattern value="[0-9]{6,7}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="dIniAtiv" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="dUltSit" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="dBaixa" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="IEUnica" type="{http://www.portalfiscal.inf.br/nfe}TIe" minOccurs="0"/>
         *         &lt;element name="IEAtual" type="{http://www.portalfiscal.inf.br/nfe}TIe" minOccurs="0"/>
         *         &lt;element name="ender" type="{http://www.portalfiscal.inf.br/nfe}TEndereco" minOccurs="0"/>
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
            "ie",
            "cnpj",
            "cpf",
            "uf",
            "cSit",
            "indCredNFe",
            "indCredCTe",
            "xNome",
            "xFant",
            "xRegApur",
            "cnae",
            "dIniAtiv",
            "dUltSit",
            "dBaixa",
            "ieUnica",
            "ieAtual",
            "ender"
        })
        public static class InfCad {

            @XmlElement(name = "IE", required = true)
            protected String ie;
            @XmlElement(name = "CNPJ")
            protected String cnpj;
            @XmlElement(name = "CPF")
            protected String cpf;
            @XmlElement(name = "UF", required = true)
            protected TUf uf;
            @XmlElement(required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String cSit;
            @XmlElement(required = true)
            protected String indCredNFe;
            @XmlElement(required = true)
            protected String indCredCTe;
            @XmlElement(required = true)
            protected String xNome;
            protected String xFant;
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String xRegApur;
            @XmlElement(name = "CNAE")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String cnae;
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dIniAtiv;
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dUltSit;
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar dBaixa;
            @XmlElement(name = "IEUnica")
            protected String ieUnica;
            @XmlElement(name = "IEAtual")
            protected String ieAtual;
            protected TEndereco ender;

            /**
             * Gets the value of the ie property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIE() {
                return ie;
            }

            /**
             * Sets the value of the ie property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIE(String value) {
                this.ie = value;
            }

            /**
             * Gets the value of the cnpj property.
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
             * Sets the value of the cnpj property.
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
             * Gets the value of the cpf property.
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
             * Sets the value of the cpf property.
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
             * Gets the value of the uf property.
             * 
             * @return
             *     possible object is
             *     {@link TUf }
             *     
             */
            public TUf getUF() {
                return uf;
            }

            /**
             * Sets the value of the uf property.
             * 
             * @param value
             *     allowed object is
             *     {@link TUf }
             *     
             */
            public void setUF(TUf value) {
                this.uf = value;
            }

            /**
             * Gets the value of the cSit property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCSit() {
                return cSit;
            }

            /**
             * Sets the value of the cSit property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCSit(String value) {
                this.cSit = value;
            }

            /**
             * Gets the value of the indCredNFe property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIndCredNFe() {
                return indCredNFe;
            }

            /**
             * Sets the value of the indCredNFe property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIndCredNFe(String value) {
                this.indCredNFe = value;
            }

            /**
             * Gets the value of the indCredCTe property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIndCredCTe() {
                return indCredCTe;
            }

            /**
             * Sets the value of the indCredCTe property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIndCredCTe(String value) {
                this.indCredCTe = value;
            }

            /**
             * Gets the value of the xNome property.
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
             * Sets the value of the xNome property.
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
             * Gets the value of the xFant property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXFant() {
                return xFant;
            }

            /**
             * Sets the value of the xFant property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXFant(String value) {
                this.xFant = value;
            }

            /**
             * Gets the value of the xRegApur property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXRegApur() {
                return xRegApur;
            }

            /**
             * Sets the value of the xRegApur property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXRegApur(String value) {
                this.xRegApur = value;
            }

            /**
             * Gets the value of the cnae property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCNAE() {
                return cnae;
            }

            /**
             * Sets the value of the cnae property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCNAE(String value) {
                this.cnae = value;
            }

            /**
             * Gets the value of the dIniAtiv property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDIniAtiv() {
                return dIniAtiv;
            }

            /**
             * Sets the value of the dIniAtiv property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDIniAtiv(XMLGregorianCalendar value) {
                this.dIniAtiv = value;
            }

            /**
             * Gets the value of the dUltSit property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDUltSit() {
                return dUltSit;
            }

            /**
             * Sets the value of the dUltSit property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDUltSit(XMLGregorianCalendar value) {
                this.dUltSit = value;
            }

            /**
             * Gets the value of the dBaixa property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDBaixa() {
                return dBaixa;
            }

            /**
             * Sets the value of the dBaixa property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDBaixa(XMLGregorianCalendar value) {
                this.dBaixa = value;
            }

            /**
             * Gets the value of the ieUnica property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIEUnica() {
                return ieUnica;
            }

            /**
             * Sets the value of the ieUnica property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIEUnica(String value) {
                this.ieUnica = value;
            }

            /**
             * Gets the value of the ieAtual property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIEAtual() {
                return ieAtual;
            }

            /**
             * Sets the value of the ieAtual property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIEAtual(String value) {
                this.ieAtual = value;
            }

            /**
             * Gets the value of the ender property.
             * 
             * @return
             *     possible object is
             *     {@link TEndereco }
             *     
             */
            public TEndereco getEnder() {
                return ender;
            }

            /**
             * Sets the value of the ender property.
             * 
             * @param value
             *     allowed object is
             *     {@link TEndereco }
             *     
             */
            public void setEnder(TEndereco value) {
                this.ender = value;
            }

        }

    }

}