//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2020.03.16 às 01:39:52 PM BRT 
//


package br.inf.portalfiscal.mdfe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2000._09.xmldsig.SignatureType;
import org.w3c.dom.Element;


/**
 * Tipo Manifesto de Documentos Fiscais Eletrônicos
 * 
 * <p>Classe Java de TMDFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TMDFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infMDFe">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ide">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/mdfe}TCodUfIBGE"/>
 *                             &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/mdfe}TAmb"/>
 *                             &lt;element name="tpEmit" type="{http://www.portalfiscal.inf.br/mdfe}TEmit"/>
 *                             &lt;element name="tpTransp" type="{http://www.portalfiscal.inf.br/mdfe}TTransp" minOccurs="0"/>
 *                             &lt;element name="mod" type="{http://www.portalfiscal.inf.br/mdfe}TModMD"/>
 *                             &lt;element name="serie" type="{http://www.portalfiscal.inf.br/mdfe}TSerie"/>
 *                             &lt;element name="nMDF" type="{http://www.portalfiscal.inf.br/mdfe}TNF"/>
 *                             &lt;element name="cMDF">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="[0-9]{8}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="cDV">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="[0-9]{1}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="modal" type="{http://www.portalfiscal.inf.br/mdfe}TModalMD"/>
 *                             &lt;element name="dhEmi" type="{http://www.portalfiscal.inf.br/mdfe}TDateTimeUTC"/>
 *                             &lt;element name="tpEmis">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="1"/>
 *                                   &lt;enumeration value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="procEmi">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TProcEmi">
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="verProc">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="UFIni" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
 *                             &lt;element name="UFFim" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
 *                             &lt;element name="infMunCarrega" maxOccurs="50">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="cMunCarrega" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
 *                                       &lt;element name="xMunCarrega">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                             &lt;minLength value="2"/>
 *                                             &lt;maxLength value="60"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="infPercurso" maxOccurs="25" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="UFPer" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="dhIniViagem" type="{http://www.portalfiscal.inf.br/mdfe}TDateTimeUTC" minOccurs="0"/>
 *                             &lt;element name="indCanalVerde" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="indCarregaPosterior" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="emit">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;choice>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                             &lt;/choice>
 *                             &lt;element name="IE">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIe">
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="xNome">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="xFant" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="enderEmit" type="{http://www.portalfiscal.inf.br/mdfe}TEndeEmi"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infModal">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;any processContents='skip'/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="versaoModal" use="required">
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
 *                   &lt;element name="infDoc">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="infMunDescarga" maxOccurs="1000">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="cMunDescarga" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
 *                                       &lt;element name="xMunDescarga">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                             &lt;minLength value="2"/>
 *                                             &lt;maxLength value="60"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="infCTe" maxOccurs="1000" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/mdfe}TChCTe"/>
 *                                                 &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
 *                                                 &lt;element name="indReentrega" minOccurs="0">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;enumeration value="1"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
 *                                                 &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="nONU">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                                 &lt;pattern value="[0-9]{4}|ND"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="xNomeAE" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="150"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="xClaRisco" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="40"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="grEmb" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="6"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="qTotProd">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="20"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="qVolTipo" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="60"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="infEntregaParcial" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="qtdTotal" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
 *                                                           &lt;element name="qtdParcial" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="infNFe" maxOccurs="1000" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
 *                                                 &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
 *                                                 &lt;element name="indReentrega" minOccurs="0">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;enumeration value="1"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
 *                                                 &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="nONU">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                                 &lt;pattern value="[0-9]{4}|ND"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="xNomeAE" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="150"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="xClaRisco" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="40"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="grEmb" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="6"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="qTotProd">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="20"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="qVolTipo" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="60"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="infMDFeTransp" maxOccurs="1000" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="chMDFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
 *                                                 &lt;element name="indReentrega" minOccurs="0">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;enumeration value="1"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
 *                                                 &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="nONU">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                                 &lt;pattern value="[0-9]{4}|ND"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="xNomeAE" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="150"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="xClaRisco" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="40"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="grEmb" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="6"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="qTotProd">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="20"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="qVolTipo" minOccurs="0">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                                                 &lt;minLength value="1"/>
 *                                                                 &lt;maxLength value="60"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="seg" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="infResp">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="respSeg">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;whiteSpace value="preserve"/>
 *                                             &lt;minLength value="1"/>
 *                                             &lt;maxLength value="1"/>
 *                                             &lt;enumeration value="1"/>
 *                                             &lt;enumeration value="2"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;choice minOccurs="0">
 *                                         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
 *                                         &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                                       &lt;/choice>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="infSeg" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="xSeg">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                             &lt;minLength value="1"/>
 *                                             &lt;maxLength value="30"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="nApol" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="nAver" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="40"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="prodPred" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="tpCarga">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="01"/>
 *                                   &lt;enumeration value="02"/>
 *                                   &lt;enumeration value="03"/>
 *                                   &lt;enumeration value="04"/>
 *                                   &lt;enumeration value="05"/>
 *                                   &lt;enumeration value="06"/>
 *                                   &lt;enumeration value="07"/>
 *                                   &lt;enumeration value="08"/>
 *                                   &lt;enumeration value="09"/>
 *                                   &lt;enumeration value="10"/>
 *                                   &lt;enumeration value="11"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="xProd">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="120"/>
 *                                   &lt;minLength value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="cEAN" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="SEM GTIN|[0-9]{0}|[0-9]{8}|[0-9]{12,14}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NCM" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="[0-9]{2}|[0-9]{8}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="infLotacao" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="infLocalCarrega">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;choice>
 *                                                 &lt;element name="CEP">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                       &lt;pattern value="[0-9]{8}"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;sequence>
 *                                                   &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
 *                                                   &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
 *                                                 &lt;/sequence>
 *                                               &lt;/choice>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="infLocalDescarrega">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;choice>
 *                                                 &lt;element name="CEP">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                       &lt;pattern value="[0-9]{8}"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;sequence>
 *                                                   &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
 *                                                   &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
 *                                                 &lt;/sequence>
 *                                               &lt;/choice>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="tot">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="qCTe" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;pattern value="[0-9]{1,6}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="qNFe" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;pattern value="[0-9]{1,6}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="qMDFe" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;pattern value="[0-9]{1,6}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="vCarga" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
 *                             &lt;element name="cUnid">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="01"/>
 *                                   &lt;enumeration value="02"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="qCarga" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="lacres" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nLacre">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="60"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="autXML" maxOccurs="10" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;choice>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                             &lt;/choice>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infAdic" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="infAdFisco" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="2000"/>
 *                                   &lt;minLength value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="infCpl" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;minLength value="1"/>
 *                                   &lt;maxLength value="5000"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infRespTec" type="{http://www.portalfiscal.inf.br/mdfe}TRespTec" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/mdfe}TVerMDe" />
 *                 &lt;attribute name="Id" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
 *                       &lt;pattern value="MDFe[0-9]{44}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="infMDFeSupl" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="qrCodMDFe">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;minLength value="50"/>
 *                         &lt;maxLength value="1000"/>
 *                         &lt;pattern value="((HTTPS?|https?)://.*\?chMDFe=[0-9]{44}&amp;tpAmb=[1-2](&amp;sign=[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1})?)"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMDFe", propOrder = {
    "infMDFe",
    "infMDFeSupl",
    "signature"
})
public class TMDFe {

    @XmlElement(required = true)
    protected TMDFe.InfMDFe infMDFe;
    protected TMDFe.InfMDFeSupl infMDFeSupl;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType signature;

    /**
     * Obtém o valor da propriedade infMDFe.
     * 
     * @return
     *     possible object is
     *     {@link TMDFe.InfMDFe }
     *     
     */
    public TMDFe.InfMDFe getInfMDFe() {
        return infMDFe;
    }

    /**
     * Define o valor da propriedade infMDFe.
     * 
     * @param value
     *     allowed object is
     *     {@link TMDFe.InfMDFe }
     *     
     */
    public void setInfMDFe(TMDFe.InfMDFe value) {
        this.infMDFe = value;
    }

    /**
     * Obtém o valor da propriedade infMDFeSupl.
     * 
     * @return
     *     possible object is
     *     {@link TMDFe.InfMDFeSupl }
     *     
     */
    public TMDFe.InfMDFeSupl getInfMDFeSupl() {
        return infMDFeSupl;
    }

    /**
     * Define o valor da propriedade infMDFeSupl.
     * 
     * @param value
     *     allowed object is
     *     {@link TMDFe.InfMDFeSupl }
     *     
     */
    public void setInfMDFeSupl(TMDFe.InfMDFeSupl value) {
        this.infMDFeSupl = value;
    }

    /**
     * Obtém o valor da propriedade signature.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Define o valor da propriedade signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
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
     *         &lt;element name="ide">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/mdfe}TCodUfIBGE"/>
     *                   &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/mdfe}TAmb"/>
     *                   &lt;element name="tpEmit" type="{http://www.portalfiscal.inf.br/mdfe}TEmit"/>
     *                   &lt;element name="tpTransp" type="{http://www.portalfiscal.inf.br/mdfe}TTransp" minOccurs="0"/>
     *                   &lt;element name="mod" type="{http://www.portalfiscal.inf.br/mdfe}TModMD"/>
     *                   &lt;element name="serie" type="{http://www.portalfiscal.inf.br/mdfe}TSerie"/>
     *                   &lt;element name="nMDF" type="{http://www.portalfiscal.inf.br/mdfe}TNF"/>
     *                   &lt;element name="cMDF">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="[0-9]{8}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="cDV">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="[0-9]{1}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="modal" type="{http://www.portalfiscal.inf.br/mdfe}TModalMD"/>
     *                   &lt;element name="dhEmi" type="{http://www.portalfiscal.inf.br/mdfe}TDateTimeUTC"/>
     *                   &lt;element name="tpEmis">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="1"/>
     *                         &lt;enumeration value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="procEmi">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TProcEmi">
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="verProc">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="UFIni" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
     *                   &lt;element name="UFFim" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
     *                   &lt;element name="infMunCarrega" maxOccurs="50">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="cMunCarrega" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
     *                             &lt;element name="xMunCarrega">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                   &lt;minLength value="2"/>
     *                                   &lt;maxLength value="60"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="infPercurso" maxOccurs="25" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="UFPer" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="dhIniViagem" type="{http://www.portalfiscal.inf.br/mdfe}TDateTimeUTC" minOccurs="0"/>
     *                   &lt;element name="indCanalVerde" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="indCarregaPosterior" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="emit">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;choice>
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                   &lt;/choice>
     *                   &lt;element name="IE">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIe">
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="xNome">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="60"/>
     *                         &lt;minLength value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="xFant" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="60"/>
     *                         &lt;minLength value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="enderEmit" type="{http://www.portalfiscal.inf.br/mdfe}TEndeEmi"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infModal">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;any processContents='skip'/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="versaoModal" use="required">
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
     *         &lt;element name="infDoc">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="infMunDescarga" maxOccurs="1000">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="cMunDescarga" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
     *                             &lt;element name="xMunDescarga">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                   &lt;minLength value="2"/>
     *                                   &lt;maxLength value="60"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="infCTe" maxOccurs="1000" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/mdfe}TChCTe"/>
     *                                       &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
     *                                       &lt;element name="indReentrega" minOccurs="0">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;enumeration value="1"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
     *                                       &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="nONU">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                       &lt;pattern value="[0-9]{4}|ND"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="xNomeAE" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="150"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="xClaRisco" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="40"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="grEmb" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="6"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="qTotProd">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="20"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="qVolTipo" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="60"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="infEntregaParcial" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="qtdTotal" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
     *                                                 &lt;element name="qtdParcial" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="infNFe" maxOccurs="1000" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
     *                                       &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
     *                                       &lt;element name="indReentrega" minOccurs="0">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;enumeration value="1"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
     *                                       &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="nONU">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                       &lt;pattern value="[0-9]{4}|ND"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="xNomeAE" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="150"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="xClaRisco" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="40"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="grEmb" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="6"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="qTotProd">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="20"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="qVolTipo" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="60"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="infMDFeTransp" maxOccurs="1000" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="chMDFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
     *                                       &lt;element name="indReentrega" minOccurs="0">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;enumeration value="1"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
     *                                       &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="nONU">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                       &lt;pattern value="[0-9]{4}|ND"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="xNomeAE" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="150"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="xClaRisco" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="40"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="grEmb" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="6"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="qTotProd">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="20"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="qVolTipo" minOccurs="0">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="60"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
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
     *         &lt;element name="seg" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="infResp">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="respSeg">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;whiteSpace value="preserve"/>
     *                                   &lt;minLength value="1"/>
     *                                   &lt;maxLength value="1"/>
     *                                   &lt;enumeration value="1"/>
     *                                   &lt;enumeration value="2"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;choice minOccurs="0">
     *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
     *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                             &lt;/choice>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="infSeg" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="xSeg">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                                   &lt;minLength value="1"/>
     *                                   &lt;maxLength value="30"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="nApol" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="nAver" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="40"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="prodPred" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="tpCarga">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="01"/>
     *                         &lt;enumeration value="02"/>
     *                         &lt;enumeration value="03"/>
     *                         &lt;enumeration value="04"/>
     *                         &lt;enumeration value="05"/>
     *                         &lt;enumeration value="06"/>
     *                         &lt;enumeration value="07"/>
     *                         &lt;enumeration value="08"/>
     *                         &lt;enumeration value="09"/>
     *                         &lt;enumeration value="10"/>
     *                         &lt;enumeration value="11"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="xProd">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="120"/>
     *                         &lt;minLength value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="cEAN" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="SEM GTIN|[0-9]{0}|[0-9]{8}|[0-9]{12,14}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NCM" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="[0-9]{2}|[0-9]{8}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="infLotacao" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="infLocalCarrega">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;choice>
     *                                       &lt;element name="CEP">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;whiteSpace value="preserve"/>
     *                                             &lt;pattern value="[0-9]{8}"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;sequence>
     *                                         &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
     *                                         &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
     *                                       &lt;/sequence>
     *                                     &lt;/choice>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="infLocalDescarrega">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;choice>
     *                                       &lt;element name="CEP">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;whiteSpace value="preserve"/>
     *                                             &lt;pattern value="[0-9]{8}"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;sequence>
     *                                         &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
     *                                         &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
     *                                       &lt;/sequence>
     *                                     &lt;/choice>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
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
     *         &lt;element name="tot">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="qCTe" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;pattern value="[0-9]{1,6}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="qNFe" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;pattern value="[0-9]{1,6}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="qMDFe" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;pattern value="[0-9]{1,6}"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="vCarga" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
     *                   &lt;element name="cUnid">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="01"/>
     *                         &lt;enumeration value="02"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="qCarga" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="lacres" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nLacre">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="60"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="autXML" maxOccurs="10" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;choice>
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                   &lt;/choice>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infAdic" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="infAdFisco" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="2000"/>
     *                         &lt;minLength value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="infCpl" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="5000"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infRespTec" type="{http://www.portalfiscal.inf.br/mdfe}TRespTec" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/mdfe}TVerMDe" />
     *       &lt;attribute name="Id" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
     *             &lt;pattern value="MDFe[0-9]{44}"/>
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
    @XmlType(name = "", propOrder = {
        "ide",
        "emit",
        "infModal",
        "infDoc",
        "seg",
        "prodPred",
        "tot",
        "lacres",
        "autXML",
        "infAdic",
        "infRespTec"
    })
    public static class InfMDFe {

        @XmlElement(required = true)
        protected TMDFe.InfMDFe.Ide ide;
        @XmlElement(required = true)
        protected TMDFe.InfMDFe.Emit emit;
        @XmlElement(required = true)
        protected TMDFe.InfMDFe.InfModal infModal;
        @XmlElement(required = true)
        protected TMDFe.InfMDFe.InfDoc infDoc;
        protected List<TMDFe.InfMDFe.Seg> seg;
        protected TMDFe.InfMDFe.ProdPred prodPred;
        @XmlElement(required = true)
        protected TMDFe.InfMDFe.Tot tot;
        protected List<TMDFe.InfMDFe.Lacres> lacres;
        protected List<TMDFe.InfMDFe.AutXML> autXML;
        protected TMDFe.InfMDFe.InfAdic infAdic;
        protected TRespTec infRespTec;
        @XmlAttribute(name = "versao", required = true)
        protected String versao;
        @XmlAttribute(name = "Id", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;

        /**
         * Obtém o valor da propriedade ide.
         * 
         * @return
         *     possible object is
         *     {@link TMDFe.InfMDFe.Ide }
         *     
         */
        public TMDFe.InfMDFe.Ide getIde() {
            return ide;
        }

        /**
         * Define o valor da propriedade ide.
         * 
         * @param value
         *     allowed object is
         *     {@link TMDFe.InfMDFe.Ide }
         *     
         */
        public void setIde(TMDFe.InfMDFe.Ide value) {
            this.ide = value;
        }

        /**
         * Obtém o valor da propriedade emit.
         * 
         * @return
         *     possible object is
         *     {@link TMDFe.InfMDFe.Emit }
         *     
         */
        public TMDFe.InfMDFe.Emit getEmit() {
            return emit;
        }

        /**
         * Define o valor da propriedade emit.
         * 
         * @param value
         *     allowed object is
         *     {@link TMDFe.InfMDFe.Emit }
         *     
         */
        public void setEmit(TMDFe.InfMDFe.Emit value) {
            this.emit = value;
        }

        /**
         * Obtém o valor da propriedade infModal.
         * 
         * @return
         *     possible object is
         *     {@link TMDFe.InfMDFe.InfModal }
         *     
         */
        public TMDFe.InfMDFe.InfModal getInfModal() {
            return infModal;
        }

        /**
         * Define o valor da propriedade infModal.
         * 
         * @param value
         *     allowed object is
         *     {@link TMDFe.InfMDFe.InfModal }
         *     
         */
        public void setInfModal(TMDFe.InfMDFe.InfModal value) {
            this.infModal = value;
        }

        /**
         * Obtém o valor da propriedade infDoc.
         * 
         * @return
         *     possible object is
         *     {@link TMDFe.InfMDFe.InfDoc }
         *     
         */
        public TMDFe.InfMDFe.InfDoc getInfDoc() {
            return infDoc;
        }

        /**
         * Define o valor da propriedade infDoc.
         * 
         * @param value
         *     allowed object is
         *     {@link TMDFe.InfMDFe.InfDoc }
         *     
         */
        public void setInfDoc(TMDFe.InfMDFe.InfDoc value) {
            this.infDoc = value;
        }

        /**
         * Gets the value of the seg property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the seg property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSeg().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TMDFe.InfMDFe.Seg }
         * 
         * 
         */
        public List<TMDFe.InfMDFe.Seg> getSeg() {
            if (seg == null) {
                seg = new ArrayList<TMDFe.InfMDFe.Seg>();
            }
            return this.seg;
        }

        /**
         * Obtém o valor da propriedade prodPred.
         * 
         * @return
         *     possible object is
         *     {@link TMDFe.InfMDFe.ProdPred }
         *     
         */
        public TMDFe.InfMDFe.ProdPred getProdPred() {
            return prodPred;
        }

        /**
         * Define o valor da propriedade prodPred.
         * 
         * @param value
         *     allowed object is
         *     {@link TMDFe.InfMDFe.ProdPred }
         *     
         */
        public void setProdPred(TMDFe.InfMDFe.ProdPred value) {
            this.prodPred = value;
        }

        /**
         * Obtém o valor da propriedade tot.
         * 
         * @return
         *     possible object is
         *     {@link TMDFe.InfMDFe.Tot }
         *     
         */
        public TMDFe.InfMDFe.Tot getTot() {
            return tot;
        }

        /**
         * Define o valor da propriedade tot.
         * 
         * @param value
         *     allowed object is
         *     {@link TMDFe.InfMDFe.Tot }
         *     
         */
        public void setTot(TMDFe.InfMDFe.Tot value) {
            this.tot = value;
        }

        /**
         * Gets the value of the lacres property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the lacres property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLacres().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TMDFe.InfMDFe.Lacres }
         * 
         * 
         */
        public List<TMDFe.InfMDFe.Lacres> getLacres() {
            if (lacres == null) {
                lacres = new ArrayList<TMDFe.InfMDFe.Lacres>();
            }
            return this.lacres;
        }

        /**
         * Gets the value of the autXML property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the autXML property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAutXML().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TMDFe.InfMDFe.AutXML }
         * 
         * 
         */
        public List<TMDFe.InfMDFe.AutXML> getAutXML() {
            if (autXML == null) {
                autXML = new ArrayList<TMDFe.InfMDFe.AutXML>();
            }
            return this.autXML;
        }

        /**
         * Obtém o valor da propriedade infAdic.
         * 
         * @return
         *     possible object is
         *     {@link TMDFe.InfMDFe.InfAdic }
         *     
         */
        public TMDFe.InfMDFe.InfAdic getInfAdic() {
            return infAdic;
        }

        /**
         * Define o valor da propriedade infAdic.
         * 
         * @param value
         *     allowed object is
         *     {@link TMDFe.InfMDFe.InfAdic }
         *     
         */
        public void setInfAdic(TMDFe.InfMDFe.InfAdic value) {
            this.infAdic = value;
        }

        /**
         * Obtém o valor da propriedade infRespTec.
         * 
         * @return
         *     possible object is
         *     {@link TRespTec }
         *     
         */
        public TRespTec getInfRespTec() {
            return infRespTec;
        }

        /**
         * Define o valor da propriedade infRespTec.
         * 
         * @param value
         *     allowed object is
         *     {@link TRespTec }
         *     
         */
        public void setInfRespTec(TRespTec value) {
            this.infRespTec = value;
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
         * Obtém o valor da propriedade id.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Define o valor da propriedade id.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
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
         *         &lt;choice>
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
         *         &lt;/choice>
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
            "cnpj",
            "cpf"
        })
        public static class AutXML {

            @XmlElement(name = "CNPJ")
            protected String cnpj;
            @XmlElement(name = "CPF")
            protected String cpf;

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
         *         &lt;choice>
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
         *         &lt;/choice>
         *         &lt;element name="IE">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIe">
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="xNome">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="60"/>
         *               &lt;minLength value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="xFant" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="60"/>
         *               &lt;minLength value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="enderEmit" type="{http://www.portalfiscal.inf.br/mdfe}TEndeEmi"/>
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
            "cnpj",
            "cpf",
            "ie",
            "xNome",
            "xFant",
            "enderEmit"
        })
        public static class Emit {

            @XmlElement(name = "CNPJ")
            protected String cnpj;
            @XmlElement(name = "CPF")
            protected String cpf;
            @XmlElement(name = "IE", required = true)
            protected String ie;
            @XmlElement(required = true)
            protected String xNome;
            protected String xFant;
            @XmlElement(required = true)
            protected TEndeEmi enderEmit;

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
             * Obtém o valor da propriedade ie.
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
             * Define o valor da propriedade ie.
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
             * Obtém o valor da propriedade xFant.
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
             * Define o valor da propriedade xFant.
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
             * Obtém o valor da propriedade enderEmit.
             * 
             * @return
             *     possible object is
             *     {@link TEndeEmi }
             *     
             */
            public TEndeEmi getEnderEmit() {
                return enderEmit;
            }

            /**
             * Define o valor da propriedade enderEmit.
             * 
             * @param value
             *     allowed object is
             *     {@link TEndeEmi }
             *     
             */
            public void setEnderEmit(TEndeEmi value) {
                this.enderEmit = value;
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
         *         &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/mdfe}TCodUfIBGE"/>
         *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/mdfe}TAmb"/>
         *         &lt;element name="tpEmit" type="{http://www.portalfiscal.inf.br/mdfe}TEmit"/>
         *         &lt;element name="tpTransp" type="{http://www.portalfiscal.inf.br/mdfe}TTransp" minOccurs="0"/>
         *         &lt;element name="mod" type="{http://www.portalfiscal.inf.br/mdfe}TModMD"/>
         *         &lt;element name="serie" type="{http://www.portalfiscal.inf.br/mdfe}TSerie"/>
         *         &lt;element name="nMDF" type="{http://www.portalfiscal.inf.br/mdfe}TNF"/>
         *         &lt;element name="cMDF">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="[0-9]{8}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="cDV">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="[0-9]{1}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="modal" type="{http://www.portalfiscal.inf.br/mdfe}TModalMD"/>
         *         &lt;element name="dhEmi" type="{http://www.portalfiscal.inf.br/mdfe}TDateTimeUTC"/>
         *         &lt;element name="tpEmis">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="1"/>
         *               &lt;enumeration value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="procEmi">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TProcEmi">
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="verProc">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="UFIni" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
         *         &lt;element name="UFFim" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
         *         &lt;element name="infMunCarrega" maxOccurs="50">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="cMunCarrega" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
         *                   &lt;element name="xMunCarrega">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                         &lt;minLength value="2"/>
         *                         &lt;maxLength value="60"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="infPercurso" maxOccurs="25" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="UFPer" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="dhIniViagem" type="{http://www.portalfiscal.inf.br/mdfe}TDateTimeUTC" minOccurs="0"/>
         *         &lt;element name="indCanalVerde" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="indCarregaPosterior" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="1"/>
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
            "cuf",
            "tpAmb",
            "tpEmit",
            "tpTransp",
            "mod",
            "serie",
            "nmdf",
            "cmdf",
            "cdv",
            "modal",
            "dhEmi",
            "tpEmis",
            "procEmi",
            "verProc",
            "ufIni",
            "ufFim",
            "infMunCarrega",
            "infPercurso",
            "dhIniViagem",
            "indCanalVerde",
            "indCarregaPosterior"
        })
        public static class Ide {

            @XmlElement(name = "cUF", required = true)
            protected String cuf;
            @XmlElement(required = true)
            protected String tpAmb;
            @XmlElement(required = true)
            protected String tpEmit;
            protected String tpTransp;
            @XmlElement(required = true)
            protected String mod;
            @XmlElement(required = true)
            protected String serie;
            @XmlElement(name = "nMDF", required = true)
            protected String nmdf;
            @XmlElement(name = "cMDF", required = true)
            protected String cmdf;
            @XmlElement(name = "cDV", required = true)
            protected String cdv;
            @XmlElement(required = true)
            protected String modal;
            @XmlElement(required = true)
            protected String dhEmi;
            @XmlElement(required = true)
            protected String tpEmis;
            @XmlElement(required = true)
            protected String procEmi;
            @XmlElement(required = true)
            protected String verProc;
            @XmlElement(name = "UFIni", required = true)
            @XmlSchemaType(name = "string")
            protected TUf ufIni;
            @XmlElement(name = "UFFim", required = true)
            @XmlSchemaType(name = "string")
            protected TUf ufFim;
            @XmlElement(required = true)
            protected List<TMDFe.InfMDFe.Ide.InfMunCarrega> infMunCarrega;
            protected List<TMDFe.InfMDFe.Ide.InfPercurso> infPercurso;
            protected String dhIniViagem;
            protected String indCanalVerde;
            protected String indCarregaPosterior;

            /**
             * Obtém o valor da propriedade cuf.
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
             * Define o valor da propriedade cuf.
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
             * Obtém o valor da propriedade tpAmb.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpAmb() {
                return tpAmb;
            }

            /**
             * Define o valor da propriedade tpAmb.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpAmb(String value) {
                this.tpAmb = value;
            }

            /**
             * Obtém o valor da propriedade tpEmit.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpEmit() {
                return tpEmit;
            }

            /**
             * Define o valor da propriedade tpEmit.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpEmit(String value) {
                this.tpEmit = value;
            }

            /**
             * Obtém o valor da propriedade tpTransp.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpTransp() {
                return tpTransp;
            }

            /**
             * Define o valor da propriedade tpTransp.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpTransp(String value) {
                this.tpTransp = value;
            }

            /**
             * Obtém o valor da propriedade mod.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMod() {
                return mod;
            }

            /**
             * Define o valor da propriedade mod.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMod(String value) {
                this.mod = value;
            }

            /**
             * Obtém o valor da propriedade serie.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSerie() {
                return serie;
            }

            /**
             * Define o valor da propriedade serie.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSerie(String value) {
                this.serie = value;
            }

            /**
             * Obtém o valor da propriedade nmdf.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNMDF() {
                return nmdf;
            }

            /**
             * Define o valor da propriedade nmdf.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNMDF(String value) {
                this.nmdf = value;
            }

            /**
             * Obtém o valor da propriedade cmdf.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCMDF() {
                return cmdf;
            }

            /**
             * Define o valor da propriedade cmdf.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCMDF(String value) {
                this.cmdf = value;
            }

            /**
             * Obtém o valor da propriedade cdv.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCDV() {
                return cdv;
            }

            /**
             * Define o valor da propriedade cdv.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCDV(String value) {
                this.cdv = value;
            }

            /**
             * Obtém o valor da propriedade modal.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getModal() {
                return modal;
            }

            /**
             * Define o valor da propriedade modal.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setModal(String value) {
                this.modal = value;
            }

            /**
             * Obtém o valor da propriedade dhEmi.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDhEmi() {
                return dhEmi;
            }

            /**
             * Define o valor da propriedade dhEmi.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDhEmi(String value) {
                this.dhEmi = value;
            }

            /**
             * Obtém o valor da propriedade tpEmis.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpEmis() {
                return tpEmis;
            }

            /**
             * Define o valor da propriedade tpEmis.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpEmis(String value) {
                this.tpEmis = value;
            }

            /**
             * Obtém o valor da propriedade procEmi.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProcEmi() {
                return procEmi;
            }

            /**
             * Define o valor da propriedade procEmi.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProcEmi(String value) {
                this.procEmi = value;
            }

            /**
             * Obtém o valor da propriedade verProc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVerProc() {
                return verProc;
            }

            /**
             * Define o valor da propriedade verProc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVerProc(String value) {
                this.verProc = value;
            }

            /**
             * Obtém o valor da propriedade ufIni.
             * 
             * @return
             *     possible object is
             *     {@link TUf }
             *     
             */
            public TUf getUFIni() {
                return ufIni;
            }

            /**
             * Define o valor da propriedade ufIni.
             * 
             * @param value
             *     allowed object is
             *     {@link TUf }
             *     
             */
            public void setUFIni(TUf value) {
                this.ufIni = value;
            }

            /**
             * Obtém o valor da propriedade ufFim.
             * 
             * @return
             *     possible object is
             *     {@link TUf }
             *     
             */
            public TUf getUFFim() {
                return ufFim;
            }

            /**
             * Define o valor da propriedade ufFim.
             * 
             * @param value
             *     allowed object is
             *     {@link TUf }
             *     
             */
            public void setUFFim(TUf value) {
                this.ufFim = value;
            }

            /**
             * Gets the value of the infMunCarrega property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the infMunCarrega property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getInfMunCarrega().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link TMDFe.InfMDFe.Ide.InfMunCarrega }
             * 
             * 
             */
            public List<TMDFe.InfMDFe.Ide.InfMunCarrega> getInfMunCarrega() {
                if (infMunCarrega == null) {
                    infMunCarrega = new ArrayList<TMDFe.InfMDFe.Ide.InfMunCarrega>();
                }
                return this.infMunCarrega;
            }

            /**
             * Gets the value of the infPercurso property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the infPercurso property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getInfPercurso().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link TMDFe.InfMDFe.Ide.InfPercurso }
             * 
             * 
             */
            public List<TMDFe.InfMDFe.Ide.InfPercurso> getInfPercurso() {
                if (infPercurso == null) {
                    infPercurso = new ArrayList<TMDFe.InfMDFe.Ide.InfPercurso>();
                }
                return this.infPercurso;
            }

            /**
             * Obtém o valor da propriedade dhIniViagem.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDhIniViagem() {
                return dhIniViagem;
            }

            /**
             * Define o valor da propriedade dhIniViagem.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDhIniViagem(String value) {
                this.dhIniViagem = value;
            }

            /**
             * Obtém o valor da propriedade indCanalVerde.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIndCanalVerde() {
                return indCanalVerde;
            }

            /**
             * Define o valor da propriedade indCanalVerde.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIndCanalVerde(String value) {
                this.indCanalVerde = value;
            }

            /**
             * Obtém o valor da propriedade indCarregaPosterior.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIndCarregaPosterior() {
                return indCarregaPosterior;
            }

            /**
             * Define o valor da propriedade indCarregaPosterior.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIndCarregaPosterior(String value) {
                this.indCarregaPosterior = value;
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
             *         &lt;element name="cMunCarrega" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
             *         &lt;element name="xMunCarrega">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *               &lt;minLength value="2"/>
             *               &lt;maxLength value="60"/>
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
                "cMunCarrega",
                "xMunCarrega"
            })
            public static class InfMunCarrega {

                @XmlElement(required = true)
                protected String cMunCarrega;
                @XmlElement(required = true)
                protected String xMunCarrega;

                /**
                 * Obtém o valor da propriedade cMunCarrega.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCMunCarrega() {
                    return cMunCarrega;
                }

                /**
                 * Define o valor da propriedade cMunCarrega.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCMunCarrega(String value) {
                    this.cMunCarrega = value;
                }

                /**
                 * Obtém o valor da propriedade xMunCarrega.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getXMunCarrega() {
                    return xMunCarrega;
                }

                /**
                 * Define o valor da propriedade xMunCarrega.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setXMunCarrega(String value) {
                    this.xMunCarrega = value;
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
             *         &lt;element name="UFPer" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
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
                "ufPer"
            })
            public static class InfPercurso {

                @XmlElement(name = "UFPer", required = true)
                @XmlSchemaType(name = "string")
                protected TUf ufPer;

                /**
                 * Obtém o valor da propriedade ufPer.
                 * 
                 * @return
                 *     possible object is
                 *     {@link TUf }
                 *     
                 */
                public TUf getUFPer() {
                    return ufPer;
                }

                /**
                 * Define o valor da propriedade ufPer.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link TUf }
                 *     
                 */
                public void setUFPer(TUf value) {
                    this.ufPer = value;
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
         *         &lt;element name="infAdFisco" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="2000"/>
         *               &lt;minLength value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="infCpl" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="5000"/>
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
            "infAdFisco",
            "infCpl"
        })
        public static class InfAdic {

            protected String infAdFisco;
            protected String infCpl;

            /**
             * Obtém o valor da propriedade infAdFisco.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInfAdFisco() {
                return infAdFisco;
            }

            /**
             * Define o valor da propriedade infAdFisco.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInfAdFisco(String value) {
                this.infAdFisco = value;
            }

            /**
             * Obtém o valor da propriedade infCpl.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInfCpl() {
                return infCpl;
            }

            /**
             * Define o valor da propriedade infCpl.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInfCpl(String value) {
                this.infCpl = value;
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
         *         &lt;element name="infMunDescarga" maxOccurs="1000">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="cMunDescarga" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
         *                   &lt;element name="xMunDescarga">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                         &lt;minLength value="2"/>
         *                         &lt;maxLength value="60"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="infCTe" maxOccurs="1000" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/mdfe}TChCTe"/>
         *                             &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
         *                             &lt;element name="indReentrega" minOccurs="0">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;enumeration value="1"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
         *                             &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="nONU">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;whiteSpace value="preserve"/>
         *                                             &lt;pattern value="[0-9]{4}|ND"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="xNomeAE" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="150"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="xClaRisco" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="40"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="grEmb" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="6"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="qTotProd">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="20"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="qVolTipo" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="60"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="infEntregaParcial" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="qtdTotal" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
         *                                       &lt;element name="qtdParcial" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="infNFe" maxOccurs="1000" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
         *                             &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
         *                             &lt;element name="indReentrega" minOccurs="0">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;enumeration value="1"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
         *                             &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="nONU">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;whiteSpace value="preserve"/>
         *                                             &lt;pattern value="[0-9]{4}|ND"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="xNomeAE" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="150"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="xClaRisco" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="40"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="grEmb" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="6"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="qTotProd">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="20"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="qVolTipo" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="60"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="infMDFeTransp" maxOccurs="1000" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="chMDFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
         *                             &lt;element name="indReentrega" minOccurs="0">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;enumeration value="1"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
         *                             &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="nONU">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;whiteSpace value="preserve"/>
         *                                             &lt;pattern value="[0-9]{4}|ND"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="xNomeAE" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="150"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="xClaRisco" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="40"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="grEmb" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="6"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="qTotProd">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="20"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="qVolTipo" minOccurs="0">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="60"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
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
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "infMunDescarga"
        })
        public static class InfDoc {

            @XmlElement(required = true)
            protected List<TMDFe.InfMDFe.InfDoc.InfMunDescarga> infMunDescarga;

            /**
             * Gets the value of the infMunDescarga property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the infMunDescarga property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getInfMunDescarga().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga }
             * 
             * 
             */
            public List<TMDFe.InfMDFe.InfDoc.InfMunDescarga> getInfMunDescarga() {
                if (infMunDescarga == null) {
                    infMunDescarga = new ArrayList<TMDFe.InfMDFe.InfDoc.InfMunDescarga>();
                }
                return this.infMunDescarga;
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
             *         &lt;element name="cMunDescarga" type="{http://www.portalfiscal.inf.br/mdfe}TCodMunIBGE"/>
             *         &lt;element name="xMunDescarga">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *               &lt;minLength value="2"/>
             *               &lt;maxLength value="60"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="infCTe" maxOccurs="1000" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/mdfe}TChCTe"/>
             *                   &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
             *                   &lt;element name="indReentrega" minOccurs="0">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;enumeration value="1"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
             *                   &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="nONU">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;whiteSpace value="preserve"/>
             *                                   &lt;pattern value="[0-9]{4}|ND"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="xNomeAE" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="150"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="xClaRisco" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="40"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="grEmb" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="6"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="qTotProd">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="20"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="qVolTipo" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="60"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="infEntregaParcial" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="qtdTotal" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
             *                             &lt;element name="qtdParcial" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
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
             *         &lt;element name="infNFe" maxOccurs="1000" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
             *                   &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
             *                   &lt;element name="indReentrega" minOccurs="0">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;enumeration value="1"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
             *                   &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="nONU">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;whiteSpace value="preserve"/>
             *                                   &lt;pattern value="[0-9]{4}|ND"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="xNomeAE" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="150"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="xClaRisco" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="40"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="grEmb" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="6"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="qTotProd">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="20"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="qVolTipo" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="60"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
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
             *         &lt;element name="infMDFeTransp" maxOccurs="1000" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="chMDFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
             *                   &lt;element name="indReentrega" minOccurs="0">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;enumeration value="1"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
             *                   &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="nONU">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;whiteSpace value="preserve"/>
             *                                   &lt;pattern value="[0-9]{4}|ND"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="xNomeAE" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="150"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="xClaRisco" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="40"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="grEmb" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="6"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="qTotProd">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="20"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="qVolTipo" minOccurs="0">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="60"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
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
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "cMunDescarga",
                "xMunDescarga",
                "infCTe",
                "infNFe",
                "infMDFeTransp"
            })
            public static class InfMunDescarga {

                @XmlElement(required = true)
                protected String cMunDescarga;
                @XmlElement(required = true)
                protected String xMunDescarga;
                protected List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe> infCTe;
                protected List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe> infNFe;
                protected List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp> infMDFeTransp;

                /**
                 * Obtém o valor da propriedade cMunDescarga.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCMunDescarga() {
                    return cMunDescarga;
                }

                /**
                 * Define o valor da propriedade cMunDescarga.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCMunDescarga(String value) {
                    this.cMunDescarga = value;
                }

                /**
                 * Obtém o valor da propriedade xMunDescarga.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getXMunDescarga() {
                    return xMunDescarga;
                }

                /**
                 * Define o valor da propriedade xMunDescarga.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setXMunDescarga(String value) {
                    this.xMunDescarga = value;
                }

                /**
                 * Gets the value of the infCTe property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the infCTe property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getInfCTe().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe }
                 * 
                 * 
                 */
                public List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe> getInfCTe() {
                    if (infCTe == null) {
                        infCTe = new ArrayList<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe>();
                    }
                    return this.infCTe;
                }

                /**
                 * Gets the value of the infNFe property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the infNFe property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getInfNFe().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe }
                 * 
                 * 
                 */
                public List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe> getInfNFe() {
                    if (infNFe == null) {
                        infNFe = new ArrayList<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe>();
                    }
                    return this.infNFe;
                }

                /**
                 * Gets the value of the infMDFeTransp property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the infMDFeTransp property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getInfMDFeTransp().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp }
                 * 
                 * 
                 */
                public List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp> getInfMDFeTransp() {
                    if (infMDFeTransp == null) {
                        infMDFeTransp = new ArrayList<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp>();
                    }
                    return this.infMDFeTransp;
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
                 *         &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/mdfe}TChCTe"/>
                 *         &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
                 *         &lt;element name="indReentrega" minOccurs="0">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;enumeration value="1"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
                 *         &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="nONU">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;whiteSpace value="preserve"/>
                 *                         &lt;pattern value="[0-9]{4}|ND"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="xNomeAE" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="150"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="xClaRisco" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="40"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="grEmb" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="6"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="qTotProd">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="20"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="qVolTipo" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="60"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="infEntregaParcial" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="qtdTotal" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
                 *                   &lt;element name="qtdParcial" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
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
                    "chCTe",
                    "segCodBarra",
                    "indReentrega",
                    "infUnidTransp",
                    "peri",
                    "infEntregaParcial"
                })
                public static class InfCTe {

                    @XmlElement(required = true)
                    protected String chCTe;
                    @XmlElement(name = "SegCodBarra")
                    protected String segCodBarra;
                    protected String indReentrega;
                    protected List<TUnidadeTransp> infUnidTransp;
                    protected List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.Peri> peri;
                    protected TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.InfEntregaParcial infEntregaParcial;

                    /**
                     * Obtém o valor da propriedade chCTe.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getChCTe() {
                        return chCTe;
                    }

                    /**
                     * Define o valor da propriedade chCTe.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setChCTe(String value) {
                        this.chCTe = value;
                    }

                    /**
                     * Obtém o valor da propriedade segCodBarra.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSegCodBarra() {
                        return segCodBarra;
                    }

                    /**
                     * Define o valor da propriedade segCodBarra.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSegCodBarra(String value) {
                        this.segCodBarra = value;
                    }

                    /**
                     * Obtém o valor da propriedade indReentrega.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIndReentrega() {
                        return indReentrega;
                    }

                    /**
                     * Define o valor da propriedade indReentrega.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIndReentrega(String value) {
                        this.indReentrega = value;
                    }

                    /**
                     * Gets the value of the infUnidTransp property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the infUnidTransp property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getInfUnidTransp().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link TUnidadeTransp }
                     * 
                     * 
                     */
                    public List<TUnidadeTransp> getInfUnidTransp() {
                        if (infUnidTransp == null) {
                            infUnidTransp = new ArrayList<TUnidadeTransp>();
                        }
                        return this.infUnidTransp;
                    }

                    /**
                     * Gets the value of the peri property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the peri property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getPeri().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.Peri }
                     * 
                     * 
                     */
                    public List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.Peri> getPeri() {
                        if (peri == null) {
                            peri = new ArrayList<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.Peri>();
                        }
                        return this.peri;
                    }

                    /**
                     * Obtém o valor da propriedade infEntregaParcial.
                     * 
                     * @return
                     *     possible object is
                     *     {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.InfEntregaParcial }
                     *     
                     */
                    public TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.InfEntregaParcial getInfEntregaParcial() {
                        return infEntregaParcial;
                    }

                    /**
                     * Define o valor da propriedade infEntregaParcial.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.InfEntregaParcial }
                     *     
                     */
                    public void setInfEntregaParcial(TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfCTe.InfEntregaParcial value) {
                        this.infEntregaParcial = value;
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
                     *         &lt;element name="qtdTotal" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
                     *         &lt;element name="qtdParcial" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
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
                        "qtdTotal",
                        "qtdParcial"
                    })
                    public static class InfEntregaParcial {

                        @XmlElement(required = true)
                        protected String qtdTotal;
                        @XmlElement(required = true)
                        protected String qtdParcial;

                        /**
                         * Obtém o valor da propriedade qtdTotal.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQtdTotal() {
                            return qtdTotal;
                        }

                        /**
                         * Define o valor da propriedade qtdTotal.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQtdTotal(String value) {
                            this.qtdTotal = value;
                        }

                        /**
                         * Obtém o valor da propriedade qtdParcial.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQtdParcial() {
                            return qtdParcial;
                        }

                        /**
                         * Define o valor da propriedade qtdParcial.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQtdParcial(String value) {
                            this.qtdParcial = value;
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
                     *         &lt;element name="nONU">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;whiteSpace value="preserve"/>
                     *               &lt;pattern value="[0-9]{4}|ND"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="xNomeAE" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="150"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="xClaRisco" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="40"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="grEmb" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="6"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="qTotProd">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="20"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="qVolTipo" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="60"/>
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
                        "nonu",
                        "xNomeAE",
                        "xClaRisco",
                        "grEmb",
                        "qTotProd",
                        "qVolTipo"
                    })
                    public static class Peri {

                        @XmlElement(name = "nONU", required = true)
                        protected String nonu;
                        protected String xNomeAE;
                        protected String xClaRisco;
                        protected String grEmb;
                        @XmlElement(required = true)
                        protected String qTotProd;
                        protected String qVolTipo;

                        /**
                         * Obtém o valor da propriedade nonu.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getNONU() {
                            return nonu;
                        }

                        /**
                         * Define o valor da propriedade nonu.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setNONU(String value) {
                            this.nonu = value;
                        }

                        /**
                         * Obtém o valor da propriedade xNomeAE.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getXNomeAE() {
                            return xNomeAE;
                        }

                        /**
                         * Define o valor da propriedade xNomeAE.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setXNomeAE(String value) {
                            this.xNomeAE = value;
                        }

                        /**
                         * Obtém o valor da propriedade xClaRisco.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getXClaRisco() {
                            return xClaRisco;
                        }

                        /**
                         * Define o valor da propriedade xClaRisco.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setXClaRisco(String value) {
                            this.xClaRisco = value;
                        }

                        /**
                         * Obtém o valor da propriedade grEmb.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getGrEmb() {
                            return grEmb;
                        }

                        /**
                         * Define o valor da propriedade grEmb.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setGrEmb(String value) {
                            this.grEmb = value;
                        }

                        /**
                         * Obtém o valor da propriedade qTotProd.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQTotProd() {
                            return qTotProd;
                        }

                        /**
                         * Define o valor da propriedade qTotProd.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQTotProd(String value) {
                            this.qTotProd = value;
                        }

                        /**
                         * Obtém o valor da propriedade qVolTipo.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQVolTipo() {
                            return qVolTipo;
                        }

                        /**
                         * Define o valor da propriedade qVolTipo.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQVolTipo(String value) {
                            this.qVolTipo = value;
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
                 *         &lt;element name="chMDFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
                 *         &lt;element name="indReentrega" minOccurs="0">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;enumeration value="1"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
                 *         &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="nONU">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;whiteSpace value="preserve"/>
                 *                         &lt;pattern value="[0-9]{4}|ND"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="xNomeAE" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="150"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="xClaRisco" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="40"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="grEmb" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="6"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="qTotProd">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="20"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="qVolTipo" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="60"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
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
                    "chMDFe",
                    "indReentrega",
                    "infUnidTransp",
                    "peri"
                })
                public static class InfMDFeTransp {

                    @XmlElement(required = true)
                    protected String chMDFe;
                    protected String indReentrega;
                    protected List<TUnidadeTransp> infUnidTransp;
                    protected List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp.Peri> peri;

                    /**
                     * Obtém o valor da propriedade chMDFe.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getChMDFe() {
                        return chMDFe;
                    }

                    /**
                     * Define o valor da propriedade chMDFe.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setChMDFe(String value) {
                        this.chMDFe = value;
                    }

                    /**
                     * Obtém o valor da propriedade indReentrega.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIndReentrega() {
                        return indReentrega;
                    }

                    /**
                     * Define o valor da propriedade indReentrega.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIndReentrega(String value) {
                        this.indReentrega = value;
                    }

                    /**
                     * Gets the value of the infUnidTransp property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the infUnidTransp property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getInfUnidTransp().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link TUnidadeTransp }
                     * 
                     * 
                     */
                    public List<TUnidadeTransp> getInfUnidTransp() {
                        if (infUnidTransp == null) {
                            infUnidTransp = new ArrayList<TUnidadeTransp>();
                        }
                        return this.infUnidTransp;
                    }

                    /**
                     * Gets the value of the peri property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the peri property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getPeri().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp.Peri }
                     * 
                     * 
                     */
                    public List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp.Peri> getPeri() {
                        if (peri == null) {
                            peri = new ArrayList<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfMDFeTransp.Peri>();
                        }
                        return this.peri;
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
                     *         &lt;element name="nONU">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;whiteSpace value="preserve"/>
                     *               &lt;pattern value="[0-9]{4}|ND"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="xNomeAE" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="150"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="xClaRisco" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="40"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="grEmb" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="6"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="qTotProd">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="20"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="qVolTipo" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="60"/>
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
                        "nonu",
                        "xNomeAE",
                        "xClaRisco",
                        "grEmb",
                        "qTotProd",
                        "qVolTipo"
                    })
                    public static class Peri {

                        @XmlElement(name = "nONU", required = true)
                        protected String nonu;
                        protected String xNomeAE;
                        protected String xClaRisco;
                        protected String grEmb;
                        @XmlElement(required = true)
                        protected String qTotProd;
                        protected String qVolTipo;

                        /**
                         * Obtém o valor da propriedade nonu.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getNONU() {
                            return nonu;
                        }

                        /**
                         * Define o valor da propriedade nonu.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setNONU(String value) {
                            this.nonu = value;
                        }

                        /**
                         * Obtém o valor da propriedade xNomeAE.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getXNomeAE() {
                            return xNomeAE;
                        }

                        /**
                         * Define o valor da propriedade xNomeAE.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setXNomeAE(String value) {
                            this.xNomeAE = value;
                        }

                        /**
                         * Obtém o valor da propriedade xClaRisco.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getXClaRisco() {
                            return xClaRisco;
                        }

                        /**
                         * Define o valor da propriedade xClaRisco.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setXClaRisco(String value) {
                            this.xClaRisco = value;
                        }

                        /**
                         * Obtém o valor da propriedade grEmb.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getGrEmb() {
                            return grEmb;
                        }

                        /**
                         * Define o valor da propriedade grEmb.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setGrEmb(String value) {
                            this.grEmb = value;
                        }

                        /**
                         * Obtém o valor da propriedade qTotProd.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQTotProd() {
                            return qTotProd;
                        }

                        /**
                         * Define o valor da propriedade qTotProd.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQTotProd(String value) {
                            this.qTotProd = value;
                        }

                        /**
                         * Obtém o valor da propriedade qVolTipo.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQVolTipo() {
                            return qVolTipo;
                        }

                        /**
                         * Define o valor da propriedade qVolTipo.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQVolTipo(String value) {
                            this.qVolTipo = value;
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
                 *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/mdfe}TChNFe"/>
                 *         &lt;element name="SegCodBarra" type="{http://www.portalfiscal.inf.br/mdfe}TSegCodBarra" minOccurs="0"/>
                 *         &lt;element name="indReentrega" minOccurs="0">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;enumeration value="1"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="infUnidTransp" type="{http://www.portalfiscal.inf.br/mdfe}TUnidadeTransp" maxOccurs="unbounded" minOccurs="0"/>
                 *         &lt;element name="peri" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="nONU">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;whiteSpace value="preserve"/>
                 *                         &lt;pattern value="[0-9]{4}|ND"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="xNomeAE" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="150"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="xClaRisco" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="40"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="grEmb" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="6"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="qTotProd">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="20"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="qVolTipo" minOccurs="0">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="60"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
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
                    "chNFe",
                    "segCodBarra",
                    "indReentrega",
                    "infUnidTransp",
                    "peri"
                })
                public static class InfNFe {

                    @XmlElement(required = true)
                    protected String chNFe;
                    @XmlElement(name = "SegCodBarra")
                    protected String segCodBarra;
                    protected String indReentrega;
                    protected List<TUnidadeTransp> infUnidTransp;
                    protected List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe.Peri> peri;

                    /**
                     * Obtém o valor da propriedade chNFe.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getChNFe() {
                        return chNFe;
                    }

                    /**
                     * Define o valor da propriedade chNFe.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setChNFe(String value) {
                        this.chNFe = value;
                    }

                    /**
                     * Obtém o valor da propriedade segCodBarra.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSegCodBarra() {
                        return segCodBarra;
                    }

                    /**
                     * Define o valor da propriedade segCodBarra.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSegCodBarra(String value) {
                        this.segCodBarra = value;
                    }

                    /**
                     * Obtém o valor da propriedade indReentrega.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIndReentrega() {
                        return indReentrega;
                    }

                    /**
                     * Define o valor da propriedade indReentrega.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIndReentrega(String value) {
                        this.indReentrega = value;
                    }

                    /**
                     * Gets the value of the infUnidTransp property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the infUnidTransp property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getInfUnidTransp().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link TUnidadeTransp }
                     * 
                     * 
                     */
                    public List<TUnidadeTransp> getInfUnidTransp() {
                        if (infUnidTransp == null) {
                            infUnidTransp = new ArrayList<TUnidadeTransp>();
                        }
                        return this.infUnidTransp;
                    }

                    /**
                     * Gets the value of the peri property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the peri property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getPeri().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe.Peri }
                     * 
                     * 
                     */
                    public List<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe.Peri> getPeri() {
                        if (peri == null) {
                            peri = new ArrayList<TMDFe.InfMDFe.InfDoc.InfMunDescarga.InfNFe.Peri>();
                        }
                        return this.peri;
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
                     *         &lt;element name="nONU">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;whiteSpace value="preserve"/>
                     *               &lt;pattern value="[0-9]{4}|ND"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="xNomeAE" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="150"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="xClaRisco" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="40"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="grEmb" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="6"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="qTotProd">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="20"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="qVolTipo" minOccurs="0">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="60"/>
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
                        "nonu",
                        "xNomeAE",
                        "xClaRisco",
                        "grEmb",
                        "qTotProd",
                        "qVolTipo"
                    })
                    public static class Peri {

                        @XmlElement(name = "nONU", required = true)
                        protected String nonu;
                        protected String xNomeAE;
                        protected String xClaRisco;
                        protected String grEmb;
                        @XmlElement(required = true)
                        protected String qTotProd;
                        protected String qVolTipo;

                        /**
                         * Obtém o valor da propriedade nonu.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getNONU() {
                            return nonu;
                        }

                        /**
                         * Define o valor da propriedade nonu.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setNONU(String value) {
                            this.nonu = value;
                        }

                        /**
                         * Obtém o valor da propriedade xNomeAE.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getXNomeAE() {
                            return xNomeAE;
                        }

                        /**
                         * Define o valor da propriedade xNomeAE.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setXNomeAE(String value) {
                            this.xNomeAE = value;
                        }

                        /**
                         * Obtém o valor da propriedade xClaRisco.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getXClaRisco() {
                            return xClaRisco;
                        }

                        /**
                         * Define o valor da propriedade xClaRisco.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setXClaRisco(String value) {
                            this.xClaRisco = value;
                        }

                        /**
                         * Obtém o valor da propriedade grEmb.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getGrEmb() {
                            return grEmb;
                        }

                        /**
                         * Define o valor da propriedade grEmb.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setGrEmb(String value) {
                            this.grEmb = value;
                        }

                        /**
                         * Obtém o valor da propriedade qTotProd.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQTotProd() {
                            return qTotProd;
                        }

                        /**
                         * Define o valor da propriedade qTotProd.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQTotProd(String value) {
                            this.qTotProd = value;
                        }

                        /**
                         * Obtém o valor da propriedade qVolTipo.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getQVolTipo() {
                            return qVolTipo;
                        }

                        /**
                         * Define o valor da propriedade qVolTipo.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setQVolTipo(String value) {
                            this.qVolTipo = value;
                        }

                    }

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
         *         &lt;any processContents='skip'/>
         *       &lt;/sequence>
         *       &lt;attribute name="versaoModal" use="required">
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
        @XmlType(name = "", propOrder = {
            "any","rodo"
        })
        public static class InfModal {

            @XmlAnyElement
            protected Element any;
            @XmlAttribute(name = "versaoModal", required = true)
            protected String versaoModal;
            @XmlElement(name = "rodo", required = true)
            protected Rodo rodo;

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
            
            public Rodo getRodo() {
                return rodo;
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
            
            public void setRodo(Rodo value) {
                this.rodo = value;
            }

            /**
             * Obtém o valor da propriedade versaoModal.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVersaoModal() {
                return versaoModal;
            }

            /**
             * Define o valor da propriedade versaoModal.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVersaoModal(String value) {
                this.versaoModal = value;
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
         *         &lt;element name="nLacre">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="60"/>
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
            "nLacre"
        })
        public static class Lacres {

            @XmlElement(required = true)
            protected String nLacre;

            /**
             * Obtém o valor da propriedade nLacre.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNLacre() {
                return nLacre;
            }

            /**
             * Define o valor da propriedade nLacre.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNLacre(String value) {
                this.nLacre = value;
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
         *         &lt;element name="tpCarga">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="01"/>
         *               &lt;enumeration value="02"/>
         *               &lt;enumeration value="03"/>
         *               &lt;enumeration value="04"/>
         *               &lt;enumeration value="05"/>
         *               &lt;enumeration value="06"/>
         *               &lt;enumeration value="07"/>
         *               &lt;enumeration value="08"/>
         *               &lt;enumeration value="09"/>
         *               &lt;enumeration value="10"/>
         *               &lt;enumeration value="11"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="xProd">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="120"/>
         *               &lt;minLength value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="cEAN" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="SEM GTIN|[0-9]{0}|[0-9]{8}|[0-9]{12,14}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NCM" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="[0-9]{2}|[0-9]{8}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="infLotacao" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="infLocalCarrega">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;choice>
         *                             &lt;element name="CEP">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;whiteSpace value="preserve"/>
         *                                   &lt;pattern value="[0-9]{8}"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;sequence>
         *                               &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
         *                               &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
         *                             &lt;/sequence>
         *                           &lt;/choice>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="infLocalDescarrega">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;choice>
         *                             &lt;element name="CEP">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;whiteSpace value="preserve"/>
         *                                   &lt;pattern value="[0-9]{8}"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;sequence>
         *                               &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
         *                               &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
         *                             &lt;/sequence>
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
            "tpCarga",
            "xProd",
            "cean",
            "ncm",
            "infLotacao"
        })
        public static class ProdPred {

            @XmlElement(required = true)
            protected String tpCarga;
            @XmlElement(required = true)
            protected String xProd;
            @XmlElement(name = "cEAN")
            protected String cean;
            @XmlElement(name = "NCM")
            protected String ncm;
            protected TMDFe.InfMDFe.ProdPred.InfLotacao infLotacao;

            /**
             * Obtém o valor da propriedade tpCarga.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpCarga() {
                return tpCarga;
            }

            /**
             * Define o valor da propriedade tpCarga.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpCarga(String value) {
                this.tpCarga = value;
            }

            /**
             * Obtém o valor da propriedade xProd.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXProd() {
                return xProd;
            }

            /**
             * Define o valor da propriedade xProd.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXProd(String value) {
                this.xProd = value;
            }

            /**
             * Obtém o valor da propriedade cean.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCEAN() {
                return cean;
            }

            /**
             * Define o valor da propriedade cean.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCEAN(String value) {
                this.cean = value;
            }

            /**
             * Obtém o valor da propriedade ncm.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNCM() {
                return ncm;
            }

            /**
             * Define o valor da propriedade ncm.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNCM(String value) {
                this.ncm = value;
            }

            /**
             * Obtém o valor da propriedade infLotacao.
             * 
             * @return
             *     possible object is
             *     {@link TMDFe.InfMDFe.ProdPred.InfLotacao }
             *     
             */
            public TMDFe.InfMDFe.ProdPred.InfLotacao getInfLotacao() {
                return infLotacao;
            }

            /**
             * Define o valor da propriedade infLotacao.
             * 
             * @param value
             *     allowed object is
             *     {@link TMDFe.InfMDFe.ProdPred.InfLotacao }
             *     
             */
            public void setInfLotacao(TMDFe.InfMDFe.ProdPred.InfLotacao value) {
                this.infLotacao = value;
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
             *         &lt;element name="infLocalCarrega">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;choice>
             *                   &lt;element name="CEP">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;whiteSpace value="preserve"/>
             *                         &lt;pattern value="[0-9]{8}"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;sequence>
             *                     &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
             *                     &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
             *                   &lt;/sequence>
             *                 &lt;/choice>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="infLocalDescarrega">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;choice>
             *                   &lt;element name="CEP">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;whiteSpace value="preserve"/>
             *                         &lt;pattern value="[0-9]{8}"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;sequence>
             *                     &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
             *                     &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
             *                   &lt;/sequence>
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
                "infLocalCarrega",
                "infLocalDescarrega"
            })
            public static class InfLotacao {

                @XmlElement(required = true)
                protected TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalCarrega infLocalCarrega;
                @XmlElement(required = true)
                protected TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalDescarrega infLocalDescarrega;

                /**
                 * Obtém o valor da propriedade infLocalCarrega.
                 * 
                 * @return
                 *     possible object is
                 *     {@link TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalCarrega }
                 *     
                 */
                public TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalCarrega getInfLocalCarrega() {
                    return infLocalCarrega;
                }

                /**
                 * Define o valor da propriedade infLocalCarrega.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalCarrega }
                 *     
                 */
                public void setInfLocalCarrega(TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalCarrega value) {
                    this.infLocalCarrega = value;
                }

                /**
                 * Obtém o valor da propriedade infLocalDescarrega.
                 * 
                 * @return
                 *     possible object is
                 *     {@link TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalDescarrega }
                 *     
                 */
                public TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalDescarrega getInfLocalDescarrega() {
                    return infLocalDescarrega;
                }

                /**
                 * Define o valor da propriedade infLocalDescarrega.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalDescarrega }
                 *     
                 */
                public void setInfLocalDescarrega(TMDFe.InfMDFe.ProdPred.InfLotacao.InfLocalDescarrega value) {
                    this.infLocalDescarrega = value;
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
                 *         &lt;element name="CEP">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;whiteSpace value="preserve"/>
                 *               &lt;pattern value="[0-9]{8}"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;sequence>
                 *           &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
                 *           &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
                 *         &lt;/sequence>
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
                    "cep",
                    "latitude",
                    "longitude"
                })
                public static class InfLocalCarrega {

                    @XmlElement(name = "CEP")
                    protected String cep;
                    protected String latitude;
                    protected String longitude;

                    /**
                     * Obtém o valor da propriedade cep.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCEP() {
                        return cep;
                    }

                    /**
                     * Define o valor da propriedade cep.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCEP(String value) {
                        this.cep = value;
                    }

                    /**
                     * Obtém o valor da propriedade latitude.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLatitude() {
                        return latitude;
                    }

                    /**
                     * Define o valor da propriedade latitude.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLatitude(String value) {
                        this.latitude = value;
                    }

                    /**
                     * Obtém o valor da propriedade longitude.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLongitude() {
                        return longitude;
                    }

                    /**
                     * Define o valor da propriedade longitude.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLongitude(String value) {
                        this.longitude = value;
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
                 *         &lt;element name="CEP">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;whiteSpace value="preserve"/>
                 *               &lt;pattern value="[0-9]{8}"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;sequence>
                 *           &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/mdfe}TLatitude"/>
                 *           &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/mdfe}TLongitude"/>
                 *         &lt;/sequence>
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
                    "cep",
                    "latitude",
                    "longitude"
                })
                public static class InfLocalDescarrega {

                    @XmlElement(name = "CEP")
                    protected String cep;
                    protected String latitude;
                    protected String longitude;

                    /**
                     * Obtém o valor da propriedade cep.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCEP() {
                        return cep;
                    }

                    /**
                     * Define o valor da propriedade cep.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCEP(String value) {
                        this.cep = value;
                    }

                    /**
                     * Obtém o valor da propriedade latitude.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLatitude() {
                        return latitude;
                    }

                    /**
                     * Define o valor da propriedade latitude.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLatitude(String value) {
                        this.latitude = value;
                    }

                    /**
                     * Obtém o valor da propriedade longitude.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLongitude() {
                        return longitude;
                    }

                    /**
                     * Define o valor da propriedade longitude.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLongitude(String value) {
                        this.longitude = value;
                    }

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
         *         &lt;element name="infResp">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="respSeg">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;whiteSpace value="preserve"/>
         *                         &lt;minLength value="1"/>
         *                         &lt;maxLength value="1"/>
         *                         &lt;enumeration value="1"/>
         *                         &lt;enumeration value="2"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;choice minOccurs="0">
         *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
         *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
         *                   &lt;/choice>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="infSeg" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="xSeg">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *                         &lt;minLength value="1"/>
         *                         &lt;maxLength value="30"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="nApol" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="nAver" maxOccurs="unbounded" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="40"/>
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
            "infResp",
            "infSeg",
            "nApol",
            "nAver"
        })
        public static class Seg {

            @XmlElement(required = true)
            protected TMDFe.InfMDFe.Seg.InfResp infResp;
            protected TMDFe.InfMDFe.Seg.InfSeg infSeg;
            protected String nApol;
            protected List<String> nAver;

            /**
             * Obtém o valor da propriedade infResp.
             * 
             * @return
             *     possible object is
             *     {@link TMDFe.InfMDFe.Seg.InfResp }
             *     
             */
            public TMDFe.InfMDFe.Seg.InfResp getInfResp() {
                return infResp;
            }

            /**
             * Define o valor da propriedade infResp.
             * 
             * @param value
             *     allowed object is
             *     {@link TMDFe.InfMDFe.Seg.InfResp }
             *     
             */
            public void setInfResp(TMDFe.InfMDFe.Seg.InfResp value) {
                this.infResp = value;
            }

            /**
             * Obtém o valor da propriedade infSeg.
             * 
             * @return
             *     possible object is
             *     {@link TMDFe.InfMDFe.Seg.InfSeg }
             *     
             */
            public TMDFe.InfMDFe.Seg.InfSeg getInfSeg() {
                return infSeg;
            }

            /**
             * Define o valor da propriedade infSeg.
             * 
             * @param value
             *     allowed object is
             *     {@link TMDFe.InfMDFe.Seg.InfSeg }
             *     
             */
            public void setInfSeg(TMDFe.InfMDFe.Seg.InfSeg value) {
                this.infSeg = value;
            }

            /**
             * Obtém o valor da propriedade nApol.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNApol() {
                return nApol;
            }

            /**
             * Define o valor da propriedade nApol.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNApol(String value) {
                this.nApol = value;
            }

            /**
             * Gets the value of the nAver property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the nAver property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getNAver().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getNAver() {
                if (nAver == null) {
                    nAver = new ArrayList<String>();
                }
                return this.nAver;
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
             *         &lt;element name="respSeg">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;whiteSpace value="preserve"/>
             *               &lt;minLength value="1"/>
             *               &lt;maxLength value="1"/>
             *               &lt;enumeration value="1"/>
             *               &lt;enumeration value="2"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;choice minOccurs="0">
             *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpj"/>
             *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
             *         &lt;/choice>
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
                "respSeg",
                "cnpj",
                "cpf"
            })
            public static class InfResp {

                @XmlElement(required = true)
                protected String respSeg;
                @XmlElement(name = "CNPJ")
                protected String cnpj;
                @XmlElement(name = "CPF")
                protected String cpf;

                /**
                 * Obtém o valor da propriedade respSeg.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRespSeg() {
                    return respSeg;
                }

                /**
                 * Define o valor da propriedade respSeg.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRespSeg(String value) {
                    this.respSeg = value;
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
             *         &lt;element name="xSeg">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
             *               &lt;minLength value="1"/>
             *               &lt;maxLength value="30"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
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
                "xSeg",
                "cnpj"
            })
            public static class InfSeg {

                @XmlElement(required = true)
                protected String xSeg;
                @XmlElement(name = "CNPJ", required = true)
                protected String cnpj;

                /**
                 * Obtém o valor da propriedade xSeg.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getXSeg() {
                    return xSeg;
                }

                /**
                 * Define o valor da propriedade xSeg.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setXSeg(String value) {
                    this.xSeg = value;
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
         *         &lt;element name="qCTe" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;pattern value="[0-9]{1,6}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="qNFe" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;pattern value="[0-9]{1,6}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="qMDFe" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;pattern value="[0-9]{1,6}"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="vCarga" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
         *         &lt;element name="cUnid">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="01"/>
         *               &lt;enumeration value="02"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="qCarga" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1104"/>
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
            "qcTe",
            "qnFe",
            "qmdFe",
            "vCarga",
            "cUnid",
            "qCarga"
        })
        public static class Tot {

            @XmlElement(name = "qCTe")
            protected String qcTe;
            @XmlElement(name = "qNFe")
            protected String qnFe;
            @XmlElement(name = "qMDFe")
            protected String qmdFe;
            @XmlElement(required = true)
            protected String vCarga;
            @XmlElement(required = true)
            protected String cUnid;
            @XmlElement(required = true)
            protected String qCarga;

            /**
             * Obtém o valor da propriedade qcTe.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQCTe() {
                return qcTe;
            }

            /**
             * Define o valor da propriedade qcTe.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQCTe(String value) {
                this.qcTe = value;
            }

            /**
             * Obtém o valor da propriedade qnFe.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQNFe() {
                return qnFe;
            }

            /**
             * Define o valor da propriedade qnFe.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQNFe(String value) {
                this.qnFe = value;
            }

            /**
             * Obtém o valor da propriedade qmdFe.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQMDFe() {
                return qmdFe;
            }

            /**
             * Define o valor da propriedade qmdFe.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQMDFe(String value) {
                this.qmdFe = value;
            }

            /**
             * Obtém o valor da propriedade vCarga.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVCarga() {
                return vCarga;
            }

            /**
             * Define o valor da propriedade vCarga.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVCarga(String value) {
                this.vCarga = value;
            }

            /**
             * Obtém o valor da propriedade cUnid.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCUnid() {
                return cUnid;
            }

            /**
             * Define o valor da propriedade cUnid.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCUnid(String value) {
                this.cUnid = value;
            }

            /**
             * Obtém o valor da propriedade qCarga.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQCarga() {
                return qCarga;
            }

            /**
             * Define o valor da propriedade qCarga.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQCarga(String value) {
                this.qCarga = value;
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
     *         &lt;element name="qrCodMDFe">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;minLength value="50"/>
     *               &lt;maxLength value="1000"/>
     *               &lt;pattern value="((HTTPS?|https?)://.*\?chMDFe=[0-9]{44}&amp;tpAmb=[1-2](&amp;sign=[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1})?)"/>
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
        "qrCodMDFe"
    })
    public static class InfMDFeSupl {

        @XmlElement(required = true)
        protected String qrCodMDFe;

        /**
         * Obtém o valor da propriedade qrCodMDFe.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQrCodMDFe() {
            return qrCodMDFe;
        }

        /**
         * Define o valor da propriedade qrCodMDFe.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQrCodMDFe(String value) {
            this.qrCodMDFe = value;
        }

    }

}
