//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.10.04 às 11:26:14 AM BRT 
//
package br.inf.portalfiscal.cte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.w3._2000._09.xmldsig.SignatureType;
import org.w3c.dom.Element;

/**
 * Tipo Evento
 * 
 * <p>
 * Classe Java de TEvento complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="TEvento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infEvento">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cOrgao" type="{http://www.portalfiscal.inf.br/cte}TCOrgaoIBGE"/>
 *                   &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/cte}TAmb"/>
 *                   &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpj"/>
 *                   &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/cte}TChNFe"/>
 *                   &lt;element name="dhEvento" type="{http://www.portalfiscal.inf.br/cte}TDateTimeUTC"/>
 *                   &lt;element name="tpEvento">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[0-9]{6}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="nSeqEvento">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="[1-9][0-9]|0?[1-9]"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="detEvento">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;any processContents='skip'/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="versaoEvento" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;whiteSpace value="preserve"/>
 *                                 &lt;pattern value="3\.(0[0-9]|[1-9][0-9])"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Id" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
 *                       &lt;pattern value="ID[0-9]{52}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/cte}TVerEvento" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEvento", propOrder = { "infEvento", "signature" })
public class TEvento {
	@XmlElement(required = true)
	protected TEvento.InfEvento infEvento;
	@XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
	protected SignatureType signature;
	@XmlAttribute(name = "versao", required = true)
	protected String versao;

	/**
	 * Obtém o valor da propriedade infEvento.
	 * 
	 * @return possible object is {@link TEvento.InfEvento }
	 * 
	 */
	public TEvento.InfEvento getInfEvento() {
		return infEvento;
	}

	/**
	 * Define o valor da propriedade infEvento.
	 * 
	 * @param value allowed object is {@link TEvento.InfEvento }
	 * 
	 */
	public void setInfEvento(TEvento.InfEvento value) {
		this.infEvento = value;
	}

	/**
	 * Obtém o valor da propriedade signature.
	 * 
	 * @return possible object is {@link SignatureType }
	 * 
	 */
	public SignatureType getSignature() {
		return signature;
	}

	/**
	 * Define o valor da propriedade signature.
	 * 
	 * @param value allowed object is {@link SignatureType }
	 * 
	 */
	public void setSignature(SignatureType value) {
		this.signature = value;
	}

	/**
	 * Obtém o valor da propriedade versao.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVersao() {
		return versao;
	}

	/**
	 * Define o valor da propriedade versao.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setVersao(String value) {
		this.versao = value;
	}

	/**
	 * <p>
	 * Classe Java de anonymous complex type.
	 * 
	 * <p>
	 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
	 * desta classe.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="cOrgao" type="{http://www.portalfiscal.inf.br/cte}TCOrgaoIBGE"/>
	 *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/cte}TAmb"/>
	 *         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpj"/>
	 *         &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/cte}TChNFe"/>
	 *         &lt;element name="dhEvento" type="{http://www.portalfiscal.inf.br/cte}TDateTimeUTC"/>
	 *         &lt;element name="tpEvento">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;whiteSpace value="preserve"/>
	 *               &lt;pattern value="[0-9]{6}"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="nSeqEvento">
	 *           &lt;simpleType>
	 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *               &lt;whiteSpace value="preserve"/>
	 *               &lt;pattern value="[1-9][0-9]|0?[1-9]"/>
	 *             &lt;/restriction>
	 *           &lt;/simpleType>
	 *         &lt;/element>
	 *         &lt;element name="detEvento">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;any processContents='skip'/>
	 *                 &lt;/sequence>
	 *                 &lt;attribute name="versaoEvento" use="required">
	 *                   &lt;simpleType>
	 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                       &lt;whiteSpace value="preserve"/>
	 *                       &lt;pattern value="3\.(0[0-9]|[1-9][0-9])"/>
	 *                     &lt;/restriction>
	 *                   &lt;/simpleType>
	 *                 &lt;/attribute>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *       &lt;attribute name="Id" use="required">
	 *         &lt;simpleType>
	 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
	 *             &lt;pattern value="ID[0-9]{52}"/>
	 *           &lt;/restriction>
	 *         &lt;/simpleType>
	 *       &lt;/attribute>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "cOrgao", "tpAmb", "cnpj", "chCTe", "dhEvento", "tpEvento", "nSeqEvento",
			"detEvento" })
	public static class InfEvento {
		@XmlElement(required = true)
		protected String cOrgao;
		@XmlElement(required = true)
		protected String tpAmb;
		@XmlElement(name = "CNPJ", required = true)
		protected String cnpj;
		@XmlElement(required = true)
		protected String chCTe;
		@XmlElement(required = true)
		protected String dhEvento;
		@XmlElement(required = true)
		protected String tpEvento;
		@XmlElement(required = true)
		protected String nSeqEvento;
		@XmlElement(required = true)
		protected TEvento.InfEvento.DetEvento detEvento;
		@XmlAttribute(name = "Id", required = true)
		@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
		@XmlID
		protected String id;

		/**
		 * Obtém o valor da propriedade cOrgao.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCOrgao() {
			return cOrgao;
		}

		/**
		 * Define o valor da propriedade cOrgao.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCOrgao(String value) {
			this.cOrgao = value;
		}

		/**
		 * Obtém o valor da propriedade tpAmb.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTpAmb() {
			return tpAmb;
		}

		/**
		 * Define o valor da propriedade tpAmb.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setTpAmb(String value) {
			this.tpAmb = value;
		}

		/**
		 * Obtém o valor da propriedade cnpj.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCNPJ() {
			return cnpj;
		}

		/**
		 * Define o valor da propriedade cnpj.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setCNPJ(String value) {
			this.cnpj = value;
		}

		/**
		 * Obtém o valor da propriedade chCTe.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getChCTe() {
			return chCTe;
		}

		/**
		 * Define o valor da propriedade chCTe.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setChCTe(String value) {
			this.chCTe = value;
		}

		/**
		 * Obtém o valor da propriedade dhEvento.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getDhEvento() {
			return dhEvento;
		}

		/**
		 * Define o valor da propriedade dhEvento.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setDhEvento(String value) {
			this.dhEvento = value;
		}

		/**
		 * Obtém o valor da propriedade tpEvento.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTpEvento() {
			return tpEvento;
		}

		/**
		 * Define o valor da propriedade tpEvento.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setTpEvento(String value) {
			this.tpEvento = value;
		}

		/**
		 * Obtém o valor da propriedade nSeqEvento.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getNSeqEvento() {
			return nSeqEvento;
		}

		/**
		 * Define o valor da propriedade nSeqEvento.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setNSeqEvento(String value) {
			this.nSeqEvento = value;
		}

		/**
		 * Obtém o valor da propriedade detEvento.
		 * 
		 * @return possible object is {@link TEvento.InfEvento.DetEvento }
		 * 
		 */
		public TEvento.InfEvento.DetEvento getDetEvento() {
			return detEvento;
		}

		/**
		 * Define o valor da propriedade detEvento.
		 * 
		 * @param value allowed object is {@link TEvento.InfEvento.DetEvento }
		 * 
		 */
		public void setDetEvento(TEvento.InfEvento.DetEvento value) {
			this.detEvento = value;
		}

		/**
		 * Obtém o valor da propriedade id.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getId() {
			return id;
		}

		/**
		 * Define o valor da propriedade id.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setId(String value) {
			this.id = value;
		}

		/**
		 * <p>
		 * Classe Java de anonymous complex type.
		 * 
		 * <p>
		 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
		 * desta classe.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;any processContents='skip'/>
		 *       &lt;/sequence>
		 *       &lt;attribute name="versaoEvento" use="required">
		 *         &lt;simpleType>
		 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *             &lt;whiteSpace value="preserve"/>
		 *             &lt;pattern value="3\.(0[0-9]|[1-9][0-9])"/>
		 *           &lt;/restriction>
		 *         &lt;/simpleType>
		 *       &lt;/attribute>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "any", "evCancCTe", "evCCeCTe" })
		public static class DetEvento {
			@XmlAnyElement
			protected Element any;
			@XmlAttribute(name = "versaoEvento", required = true)
			protected String versaoEvento;
			@XmlElement(name = "evCancCTe", required = true)
			protected EvCancCTe evCancCTe;
			@XmlElement(name = "evCCeCTe", required = true)
			protected EvCCeCTe evCCeCTe;

			/**
			 * Gets the value of the any property.
			 * 
			 * @return possible object is {@link Element }
			 * 
			 */
			public Element getAny() {
				return any;
			}

			/**
			 * Sets the value of the any property.
			 * 
			 * @param value allowed object is {@link Element }
			 * 
			 */
			public void setAny(Element value) {
				this.any = value;
			}

			/**
			 * Gets the value of the versaoEvento property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getVersaoEvento() {
				return versaoEvento;
			}

			public EvCancCTe getEvCancCTe() {
				return evCancCTe;
			}

			public EvCCeCTe getEvCCeCTe() {
				return evCCeCTe;
			}

			/**
			 * Sets the value of the versaoEvento property.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setVersaoEvento(String value) {
				this.versaoEvento = value;
			}

			public void setEvCancCTe(EvCancCTe value) {
				this.evCancCTe = value;
			}

			public void setEvCCeCTe(EvCCeCTe value) {
				this.evCCeCTe = value;
			}
		}
	}
}
