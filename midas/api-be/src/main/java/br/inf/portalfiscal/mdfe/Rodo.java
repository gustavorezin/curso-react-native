//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2021.05.04 às 04:02:06 PM BRT 
//


package br.inf.portalfiscal.mdfe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="infANTT" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC" minOccurs="0"/>
 *                   &lt;element name="infCIOT" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CIOT">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TCIOT">
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;choice>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                             &lt;/choice>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="valePed" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="disp" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CNPJForn">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TCnpj">
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;choice minOccurs="0">
 *                                         &lt;element name="CNPJPg" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                                         &lt;element name="CPFPg" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                                       &lt;/choice>
 *                                       &lt;element name="nCompra" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;pattern value="[0-9]{1,20}"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="vValePed" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
 *                                       &lt;element name="tpValePed" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;whiteSpace value="preserve"/>
 *                                             &lt;enumeration value="01"/>
 *                                             &lt;enumeration value="02"/>
 *                                             &lt;enumeration value="03"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="categCombVeic" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="02"/>
 *                                   &lt;enumeration value="04"/>
 *                                   &lt;enumeration value="06"/>
 *                                   &lt;enumeration value="07"/>
 *                                   &lt;enumeration value="08"/>
 *                                   &lt;enumeration value="10"/>
 *                                   &lt;enumeration value="11"/>
 *                                   &lt;enumeration value="12"/>
 *                                   &lt;enumeration value="13"/>
 *                                   &lt;enumeration value="14"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infContratante" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="xNome" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;choice>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                               &lt;element name="idEstrangeiro">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                     &lt;whiteSpace value="preserve"/>
 *                                     &lt;minLength value="2"/>
 *                                     &lt;maxLength value="20"/>
 *                                     &lt;pattern value="([!-ÿ]{0}|[!-ÿ]{5,20})?"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                             &lt;/choice>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="infPag" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="xNome" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;choice>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                               &lt;element name="idEstrangeiro">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                     &lt;whiteSpace value="preserve"/>
 *                                     &lt;minLength value="2"/>
 *                                     &lt;maxLength value="20"/>
 *                                     &lt;pattern value="([!-ÿ]{0}|[!-ÿ]{5,20})?"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                             &lt;/choice>
 *                             &lt;element name="Comp" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="tpComp">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;whiteSpace value="preserve"/>
 *                                             &lt;enumeration value="01"/>
 *                                             &lt;enumeration value="02"/>
 *                                             &lt;enumeration value="03"/>
 *                                             &lt;enumeration value="99"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="vComp" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
 *                                       &lt;element name="xComp" minOccurs="0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                             &lt;maxLength value="60"/>
 *                                             &lt;minLength value="2"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="vContrato" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
 *                             &lt;element name="indAltoDesemp" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="indPag">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="0"/>
 *                                   &lt;enumeration value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="vAdiant" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302" minOccurs="0"/>
 *                             &lt;element name="infPrazo" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="nParcela">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;whiteSpace value="preserve"/>
 *                                             &lt;pattern value="[0-9]{3}"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="dVenc" type="{http://www.portalfiscal.inf.br/mdfe}TData"/>
 *                                       &lt;element name="vParcela" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302Opc"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="infBanc">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;choice>
 *                                       &lt;sequence>
 *                                         &lt;element name="codBanco">
 *                                           &lt;simpleType>
 *                                             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                               &lt;minLength value="3"/>
 *                                               &lt;maxLength value="5"/>
 *                                             &lt;/restriction>
 *                                           &lt;/simpleType>
 *                                         &lt;/element>
 *                                         &lt;element name="codAgencia">
 *                                           &lt;simpleType>
 *                                             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                               &lt;minLength value="1"/>
 *                                               &lt;maxLength value="10"/>
 *                                             &lt;/restriction>
 *                                           &lt;/simpleType>
 *                                         &lt;/element>
 *                                       &lt;/sequence>
 *                                       &lt;element name="CNPJIPEF" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                                       &lt;element name="PIX">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                             &lt;maxLength value="60"/>
 *                                             &lt;minLength value="2"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
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
 *         &lt;element name="veicTracao">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cInt" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="10"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="placa">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TPlaca">
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="RENAVAM" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                         &lt;minLength value="9"/>
 *                         &lt;maxLength value="11"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tara">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="capKG" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="capM3" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="0|[1-9]{1}[0-9]{0,2}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="prop" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;choice>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                             &lt;/choice>
 *                             &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC"/>
 *                             &lt;element name="xNome">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;sequence minOccurs="0">
 *                               &lt;element name="IE">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIeDest">
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
 *                             &lt;/sequence>
 *                             &lt;element name="tpProp">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="0"/>
 *                                   &lt;enumeration value="1"/>
 *                                   &lt;enumeration value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="condutor" maxOccurs="10">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="xNome">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="tpRod">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;enumeration value="01"/>
 *                         &lt;enumeration value="02"/>
 *                         &lt;enumeration value="03"/>
 *                         &lt;enumeration value="04"/>
 *                         &lt;enumeration value="05"/>
 *                         &lt;enumeration value="06"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tpCar">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;enumeration value="00"/>
 *                         &lt;enumeration value="01"/>
 *                         &lt;enumeration value="02"/>
 *                         &lt;enumeration value="03"/>
 *                         &lt;enumeration value="04"/>
 *                         &lt;enumeration value="05"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="veicReboque" maxOccurs="3" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cInt" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="10"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="placa">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TPlaca">
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="RENAVAM" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                         &lt;minLength value="9"/>
 *                         &lt;maxLength value="11"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tara">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="capKG">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="capM3" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;pattern value="0|[1-9]{1}[0-9]{0,2}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="prop" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;choice>
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
 *                             &lt;/choice>
 *                             &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC"/>
 *                             &lt;element name="xNome">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                                   &lt;maxLength value="60"/>
 *                                   &lt;minLength value="1"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;sequence minOccurs="0">
 *                               &lt;element name="IE">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIeDest">
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
 *                             &lt;/sequence>
 *                             &lt;element name="tpProp">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;whiteSpace value="preserve"/>
 *                                   &lt;enumeration value="0"/>
 *                                   &lt;enumeration value="1"/>
 *                                   &lt;enumeration value="2"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="tpCar">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;whiteSpace value="preserve"/>
 *                         &lt;enumeration value="00"/>
 *                         &lt;enumeration value="01"/>
 *                         &lt;enumeration value="02"/>
 *                         &lt;enumeration value="03"/>
 *                         &lt;enumeration value="04"/>
 *                         &lt;enumeration value="05"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="codAgPorto" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="lacRodo" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nLacre">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="20"/>
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
    "infANTT",
    "veicTracao",
    "veicReboque",
    "codAgPorto",
    "lacRodo"
})
@XmlRootElement(name = "rodo")
public class Rodo {

    protected Rodo.InfANTT infANTT;
    @XmlElement(required = true)
    protected Rodo.VeicTracao veicTracao;
    protected List<Rodo.VeicReboque> veicReboque;
    protected String codAgPorto;
    protected List<Rodo.LacRodo> lacRodo;

    /**
     * Obtém o valor da propriedade infANTT.
     * 
     * @return
     *     possible object is
     *     {@link Rodo.InfANTT }
     *     
     */
    public Rodo.InfANTT getInfANTT() {
        return infANTT;
    }

    /**
     * Define o valor da propriedade infANTT.
     * 
     * @param value
     *     allowed object is
     *     {@link Rodo.InfANTT }
     *     
     */
    public void setInfANTT(Rodo.InfANTT value) {
        this.infANTT = value;
    }

    /**
     * Obtém o valor da propriedade veicTracao.
     * 
     * @return
     *     possible object is
     *     {@link Rodo.VeicTracao }
     *     
     */
    public Rodo.VeicTracao getVeicTracao() {
        return veicTracao;
    }

    /**
     * Define o valor da propriedade veicTracao.
     * 
     * @param value
     *     allowed object is
     *     {@link Rodo.VeicTracao }
     *     
     */
    public void setVeicTracao(Rodo.VeicTracao value) {
        this.veicTracao = value;
    }

    /**
     * Gets the value of the veicReboque property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the veicReboque property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVeicReboque().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rodo.VeicReboque }
     * 
     * 
     */
    public List<Rodo.VeicReboque> getVeicReboque() {
        if (veicReboque == null) {
            veicReboque = new ArrayList<Rodo.VeicReboque>();
        }
        return this.veicReboque;
    }

    /**
     * Obtém o valor da propriedade codAgPorto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAgPorto() {
        return codAgPorto;
    }

    /**
     * Define o valor da propriedade codAgPorto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAgPorto(String value) {
        this.codAgPorto = value;
    }

    /**
     * Gets the value of the lacRodo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lacRodo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLacRodo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rodo.LacRodo }
     * 
     * 
     */
    public List<Rodo.LacRodo> getLacRodo() {
        if (lacRodo == null) {
            lacRodo = new ArrayList<Rodo.LacRodo>();
        }
        return this.lacRodo;
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
     *         &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC" minOccurs="0"/>
     *         &lt;element name="infCIOT" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CIOT">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TCIOT">
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;choice>
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
     *                   &lt;/choice>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="valePed" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="disp" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CNPJForn">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TCnpj">
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;choice minOccurs="0">
     *                               &lt;element name="CNPJPg" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
     *                               &lt;element name="CPFPg" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                             &lt;/choice>
     *                             &lt;element name="nCompra" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;pattern value="[0-9]{1,20}"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="vValePed" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
     *                             &lt;element name="tpValePed" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;whiteSpace value="preserve"/>
     *                                   &lt;enumeration value="01"/>
     *                                   &lt;enumeration value="02"/>
     *                                   &lt;enumeration value="03"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="categCombVeic" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="02"/>
     *                         &lt;enumeration value="04"/>
     *                         &lt;enumeration value="06"/>
     *                         &lt;enumeration value="07"/>
     *                         &lt;enumeration value="08"/>
     *                         &lt;enumeration value="10"/>
     *                         &lt;enumeration value="11"/>
     *                         &lt;enumeration value="12"/>
     *                         &lt;enumeration value="13"/>
     *                         &lt;enumeration value="14"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infContratante" maxOccurs="unbounded" minOccurs="0">
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
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="infPag" maxOccurs="unbounded" minOccurs="0">
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
     *                   &lt;element name="indAltoDesemp" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
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
        "rntrc",
        "infCIOT",
        "valePed",
        "infContratante",
        "infPag"
    })
    public static class InfANTT {

        @XmlElement(name = "RNTRC")
        protected String rntrc;
        protected List<Rodo.InfANTT.InfCIOT> infCIOT;
        protected Rodo.InfANTT.ValePed valePed;
        protected List<Rodo.InfANTT.InfContratante> infContratante;
        protected List<Rodo.InfANTT.InfPag> infPag;

        /**
         * Obtém o valor da propriedade rntrc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRNTRC() {
            return rntrc;
        }

        /**
         * Define o valor da propriedade rntrc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRNTRC(String value) {
            this.rntrc = value;
        }

        /**
         * Gets the value of the infCIOT property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infCIOT property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfCIOT().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Rodo.InfANTT.InfCIOT }
         * 
         * 
         */
        public List<Rodo.InfANTT.InfCIOT> getInfCIOT() {
            if (infCIOT == null) {
                infCIOT = new ArrayList<Rodo.InfANTT.InfCIOT>();
            }
            return this.infCIOT;
        }

        /**
         * Obtém o valor da propriedade valePed.
         * 
         * @return
         *     possible object is
         *     {@link Rodo.InfANTT.ValePed }
         *     
         */
        public Rodo.InfANTT.ValePed getValePed() {
            return valePed;
        }

        /**
         * Define o valor da propriedade valePed.
         * 
         * @param value
         *     allowed object is
         *     {@link Rodo.InfANTT.ValePed }
         *     
         */
        public void setValePed(Rodo.InfANTT.ValePed value) {
            this.valePed = value;
        }

        /**
         * Gets the value of the infContratante property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infContratante property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfContratante().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Rodo.InfANTT.InfContratante }
         * 
         * 
         */
        public List<Rodo.InfANTT.InfContratante> getInfContratante() {
            if (infContratante == null) {
                infContratante = new ArrayList<Rodo.InfANTT.InfContratante>();
            }
            return this.infContratante;
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
         * {@link Rodo.InfANTT.InfPag }
         * 
         * 
         */
        public List<Rodo.InfANTT.InfPag> getInfPag() {
            if (infPag == null) {
                infPag = new ArrayList<Rodo.InfANTT.InfPag>();
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
         *         &lt;element name="CIOT">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TCIOT">
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;choice>
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
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
            "ciot",
            "cpf",
            "cnpj"
        })
        public static class InfCIOT {

            @XmlElement(name = "CIOT", required = true)
            protected String ciot;
            @XmlElement(name = "CPF")
            protected String cpf;
            @XmlElement(name = "CNPJ")
            protected String cnpj;

            /**
             * Obtém o valor da propriedade ciot.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCIOT() {
                return ciot;
            }

            /**
             * Define o valor da propriedade ciot.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCIOT(String value) {
                this.ciot = value;
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
            "idEstrangeiro"
        })
        public static class InfContratante {

            protected String xNome;
            @XmlElement(name = "CPF")
            protected String cpf;
            @XmlElement(name = "CNPJ")
            protected String cnpj;
            protected String idEstrangeiro;

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
         *         &lt;element name="indAltoDesemp" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
            "indAltoDesemp",
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
            protected List<Rodo.InfANTT.InfPag.Comp> comp;
            @XmlElement(required = true)
            protected String vContrato;
            protected String indAltoDesemp;
            @XmlElement(required = true)
            protected String indPag;
            protected String vAdiant;
            protected List<Rodo.InfANTT.InfPag.InfPrazo> infPrazo;
            @XmlElement(required = true)
            protected Rodo.InfANTT.InfPag.InfBanc infBanc;

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
             * {@link Rodo.InfANTT.InfPag.Comp }
             * 
             * 
             */
            public List<Rodo.InfANTT.InfPag.Comp> getComp() {
                if (comp == null) {
                    comp = new ArrayList<Rodo.InfANTT.InfPag.Comp>();
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
             * Obtém o valor da propriedade indAltoDesemp.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIndAltoDesemp() {
                return indAltoDesemp;
            }

            /**
             * Define o valor da propriedade indAltoDesemp.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIndAltoDesemp(String value) {
                this.indAltoDesemp = value;
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
             * {@link Rodo.InfANTT.InfPag.InfPrazo }
             * 
             * 
             */
            public List<Rodo.InfANTT.InfPag.InfPrazo> getInfPrazo() {
                if (infPrazo == null) {
                    infPrazo = new ArrayList<Rodo.InfANTT.InfPag.InfPrazo>();
                }
                return this.infPrazo;
            }

            /**
             * Obtém o valor da propriedade infBanc.
             * 
             * @return
             *     possible object is
             *     {@link Rodo.InfANTT.InfPag.InfBanc }
             *     
             */
            public Rodo.InfANTT.InfPag.InfBanc getInfBanc() {
                return infBanc;
            }

            /**
             * Define o valor da propriedade infBanc.
             * 
             * @param value
             *     allowed object is
             *     {@link Rodo.InfANTT.InfPag.InfBanc }
             *     
             */
            public void setInfBanc(Rodo.InfANTT.InfPag.InfBanc value) {
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
         *         &lt;element name="disp" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="CNPJForn">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TCnpj">
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;choice minOccurs="0">
         *                     &lt;element name="CNPJPg" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
         *                     &lt;element name="CPFPg" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
         *                   &lt;/choice>
         *                   &lt;element name="nCompra" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;pattern value="[0-9]{1,20}"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="vValePed" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
         *                   &lt;element name="tpValePed" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;whiteSpace value="preserve"/>
         *                         &lt;enumeration value="01"/>
         *                         &lt;enumeration value="02"/>
         *                         &lt;enumeration value="03"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="categCombVeic" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="02"/>
         *               &lt;enumeration value="04"/>
         *               &lt;enumeration value="06"/>
         *               &lt;enumeration value="07"/>
         *               &lt;enumeration value="08"/>
         *               &lt;enumeration value="10"/>
         *               &lt;enumeration value="11"/>
         *               &lt;enumeration value="12"/>
         *               &lt;enumeration value="13"/>
         *               &lt;enumeration value="14"/>
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
            "disp",
            "categCombVeic"
        })
        public static class ValePed {

            @XmlElement(required = true)
            protected List<Rodo.InfANTT.ValePed.Disp> disp;
            protected String categCombVeic;

            /**
             * Gets the value of the disp property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the disp property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getDisp().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Rodo.InfANTT.ValePed.Disp }
             * 
             * 
             */
            public List<Rodo.InfANTT.ValePed.Disp> getDisp() {
                if (disp == null) {
                    disp = new ArrayList<Rodo.InfANTT.ValePed.Disp>();
                }
                return this.disp;
            }

            /**
             * Obtém o valor da propriedade categCombVeic.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCategCombVeic() {
                return categCombVeic;
            }

            /**
             * Define o valor da propriedade categCombVeic.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCategCombVeic(String value) {
                this.categCombVeic = value;
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
             *         &lt;element name="CNPJForn">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TCnpj">
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;choice minOccurs="0">
             *           &lt;element name="CNPJPg" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
             *           &lt;element name="CPFPg" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
             *         &lt;/choice>
             *         &lt;element name="nCompra" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;pattern value="[0-9]{1,20}"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="vValePed" type="{http://www.portalfiscal.inf.br/mdfe}TDec_1302"/>
             *         &lt;element name="tpValePed" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;whiteSpace value="preserve"/>
             *               &lt;enumeration value="01"/>
             *               &lt;enumeration value="02"/>
             *               &lt;enumeration value="03"/>
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
                "cnpjForn",
                "cnpjPg",
                "cpfPg",
                "nCompra",
                "vValePed",
                "tpValePed"
            })
            public static class Disp {

                @XmlElement(name = "CNPJForn", required = true)
                protected String cnpjForn;
                @XmlElement(name = "CNPJPg")
                protected String cnpjPg;
                @XmlElement(name = "CPFPg")
                protected String cpfPg;
                protected String nCompra;
                @XmlElement(required = true)
                protected String vValePed;
                protected String tpValePed;

                /**
                 * Obtém o valor da propriedade cnpjForn.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCNPJForn() {
                    return cnpjForn;
                }

                /**
                 * Define o valor da propriedade cnpjForn.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCNPJForn(String value) {
                    this.cnpjForn = value;
                }

                /**
                 * Obtém o valor da propriedade cnpjPg.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCNPJPg() {
                    return cnpjPg;
                }

                /**
                 * Define o valor da propriedade cnpjPg.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCNPJPg(String value) {
                    this.cnpjPg = value;
                }

                /**
                 * Obtém o valor da propriedade cpfPg.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCPFPg() {
                    return cpfPg;
                }

                /**
                 * Define o valor da propriedade cpfPg.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCPFPg(String value) {
                    this.cpfPg = value;
                }

                /**
                 * Obtém o valor da propriedade nCompra.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getNCompra() {
                    return nCompra;
                }

                /**
                 * Define o valor da propriedade nCompra.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setNCompra(String value) {
                    this.nCompra = value;
                }

                /**
                 * Obtém o valor da propriedade vValePed.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getVValePed() {
                    return vValePed;
                }

                /**
                 * Define o valor da propriedade vValePed.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setVValePed(String value) {
                    this.vValePed = value;
                }

                /**
                 * Obtém o valor da propriedade tpValePed.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTpValePed() {
                    return tpValePed;
                }

                /**
                 * Define o valor da propriedade tpValePed.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTpValePed(String value) {
                    this.tpValePed = value;
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
     *         &lt;element name="nLacre">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="20"/>
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
    public static class LacRodo {

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
     *         &lt;element name="cInt" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="10"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="placa">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TPlaca">
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="RENAVAM" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *               &lt;minLength value="9"/>
     *               &lt;maxLength value="11"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tara">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="capKG">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="capM3" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="0|[1-9]{1}[0-9]{0,2}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="prop" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;choice>
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
     *                   &lt;/choice>
     *                   &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC"/>
     *                   &lt;element name="xNome">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="60"/>
     *                         &lt;minLength value="1"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;sequence minOccurs="0">
     *                     &lt;element name="IE">
     *                       &lt;simpleType>
     *                         &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIeDest">
     *                         &lt;/restriction>
     *                       &lt;/simpleType>
     *                     &lt;/element>
     *                     &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
     *                   &lt;/sequence>
     *                   &lt;element name="tpProp">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="0"/>
     *                         &lt;enumeration value="1"/>
     *                         &lt;enumeration value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="tpCar">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;enumeration value="00"/>
     *               &lt;enumeration value="01"/>
     *               &lt;enumeration value="02"/>
     *               &lt;enumeration value="03"/>
     *               &lt;enumeration value="04"/>
     *               &lt;enumeration value="05"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf" minOccurs="0"/>
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
        "cInt",
        "placa",
        "renavam",
        "tara",
        "capKG",
        "capM3",
        "prop",
        "tpCar",
        "uf"
    })
    public static class VeicReboque {

        protected String cInt;
        @XmlElement(required = true)
        protected String placa;
        @XmlElement(name = "RENAVAM")
        protected String renavam;
        @XmlElement(required = true)
        protected String tara;
        @XmlElement(required = true)
        protected String capKG;
        protected String capM3;
        protected Rodo.VeicReboque.Prop prop;
        @XmlElement(required = true)
        protected String tpCar;
        @XmlElement(name = "UF")
        @XmlSchemaType(name = "string")
        protected TUf uf;

        /**
         * Obtém o valor da propriedade cInt.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCInt() {
            return cInt;
        }

        /**
         * Define o valor da propriedade cInt.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCInt(String value) {
            this.cInt = value;
        }

        /**
         * Obtém o valor da propriedade placa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPlaca() {
            return placa;
        }

        /**
         * Define o valor da propriedade placa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPlaca(String value) {
            this.placa = value;
        }

        /**
         * Obtém o valor da propriedade renavam.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRENAVAM() {
            return renavam;
        }

        /**
         * Define o valor da propriedade renavam.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRENAVAM(String value) {
            this.renavam = value;
        }

        /**
         * Obtém o valor da propriedade tara.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTara() {
            return tara;
        }

        /**
         * Define o valor da propriedade tara.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTara(String value) {
            this.tara = value;
        }

        /**
         * Obtém o valor da propriedade capKG.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCapKG() {
            return capKG;
        }

        /**
         * Define o valor da propriedade capKG.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCapKG(String value) {
            this.capKG = value;
        }

        /**
         * Obtém o valor da propriedade capM3.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCapM3() {
            return capM3;
        }

        /**
         * Define o valor da propriedade capM3.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCapM3(String value) {
            this.capM3 = value;
        }

        /**
         * Obtém o valor da propriedade prop.
         * 
         * @return
         *     possible object is
         *     {@link Rodo.VeicReboque.Prop }
         *     
         */
        public Rodo.VeicReboque.Prop getProp() {
            return prop;
        }

        /**
         * Define o valor da propriedade prop.
         * 
         * @param value
         *     allowed object is
         *     {@link Rodo.VeicReboque.Prop }
         *     
         */
        public void setProp(Rodo.VeicReboque.Prop value) {
            this.prop = value;
        }

        /**
         * Obtém o valor da propriedade tpCar.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTpCar() {
            return tpCar;
        }

        /**
         * Define o valor da propriedade tpCar.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTpCar(String value) {
            this.tpCar = value;
        }

        /**
         * Obtém o valor da propriedade uf.
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
         * Define o valor da propriedade uf.
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
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
         *         &lt;/choice>
         *         &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC"/>
         *         &lt;element name="xNome">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="60"/>
         *               &lt;minLength value="1"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;sequence minOccurs="0">
         *           &lt;element name="IE">
         *             &lt;simpleType>
         *               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIeDest">
         *               &lt;/restriction>
         *             &lt;/simpleType>
         *           &lt;/element>
         *           &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
         *         &lt;/sequence>
         *         &lt;element name="tpProp">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="0"/>
         *               &lt;enumeration value="1"/>
         *               &lt;enumeration value="2"/>
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
            "cpf",
            "cnpj",
            "rntrc",
            "xNome",
            "ie",
            "uf",
            "tpProp"
        })
        public static class Prop {

            @XmlElement(name = "CPF")
            protected String cpf;
            @XmlElement(name = "CNPJ")
            protected String cnpj;
            @XmlElement(name = "RNTRC", required = true)
            protected String rntrc;
            @XmlElement(required = true)
            protected String xNome;
            @XmlElement(name = "IE")
            protected String ie;
            @XmlElement(name = "UF")
            @XmlSchemaType(name = "string")
            protected TUf uf;
            @XmlElement(required = true)
            protected String tpProp;

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
             * Obtém o valor da propriedade rntrc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRNTRC() {
                return rntrc;
            }

            /**
             * Define o valor da propriedade rntrc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRNTRC(String value) {
                this.rntrc = value;
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
             * Obtém o valor da propriedade uf.
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
             * Define o valor da propriedade uf.
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
             * Obtém o valor da propriedade tpProp.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpProp() {
                return tpProp;
            }

            /**
             * Define o valor da propriedade tpProp.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpProp(String value) {
                this.tpProp = value;
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
     *         &lt;element name="cInt" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="10"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="placa">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TPlaca">
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="RENAVAM" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *               &lt;minLength value="9"/>
     *               &lt;maxLength value="11"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tara">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="capKG" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="0|[1-9]{1}[0-9]{0,5}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="capM3" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;pattern value="0|[1-9]{1}[0-9]{0,2}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="prop" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;choice>
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
     *                   &lt;/choice>
     *                   &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC"/>
     *                   &lt;element name="xNome">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="60"/>
     *                         &lt;minLength value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;sequence minOccurs="0">
     *                     &lt;element name="IE">
     *                       &lt;simpleType>
     *                         &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIeDest">
     *                         &lt;/restriction>
     *                       &lt;/simpleType>
     *                     &lt;/element>
     *                     &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
     *                   &lt;/sequence>
     *                   &lt;element name="tpProp">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;whiteSpace value="preserve"/>
     *                         &lt;enumeration value="0"/>
     *                         &lt;enumeration value="1"/>
     *                         &lt;enumeration value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="condutor" maxOccurs="10">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="xNome">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
     *                         &lt;maxLength value="60"/>
     *                         &lt;minLength value="2"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="tpRod">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;enumeration value="01"/>
     *               &lt;enumeration value="02"/>
     *               &lt;enumeration value="03"/>
     *               &lt;enumeration value="04"/>
     *               &lt;enumeration value="05"/>
     *               &lt;enumeration value="06"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tpCar">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;whiteSpace value="preserve"/>
     *               &lt;enumeration value="00"/>
     *               &lt;enumeration value="01"/>
     *               &lt;enumeration value="02"/>
     *               &lt;enumeration value="03"/>
     *               &lt;enumeration value="04"/>
     *               &lt;enumeration value="05"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf" minOccurs="0"/>
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
        "cInt",
        "placa",
        "renavam",
        "tara",
        "capKG",
        "capM3",
        "prop",
        "condutor",
        "tpRod",
        "tpCar",
        "uf"
    })
    public static class VeicTracao {

        protected String cInt;
        @XmlElement(required = true)
        protected String placa;
        @XmlElement(name = "RENAVAM")
        protected String renavam;
        @XmlElement(required = true)
        protected String tara;
        protected String capKG;
        protected String capM3;
        protected Rodo.VeicTracao.Prop prop;
        @XmlElement(required = true)
        protected List<Rodo.VeicTracao.Condutor> condutor;
        @XmlElement(required = true)
        protected String tpRod;
        @XmlElement(required = true)
        protected String tpCar;
        @XmlElement(name = "UF")
        @XmlSchemaType(name = "string")
        protected TUf uf;

        /**
         * Obtém o valor da propriedade cInt.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCInt() {
            return cInt;
        }

        /**
         * Define o valor da propriedade cInt.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCInt(String value) {
            this.cInt = value;
        }

        /**
         * Obtém o valor da propriedade placa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPlaca() {
            return placa;
        }

        /**
         * Define o valor da propriedade placa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPlaca(String value) {
            this.placa = value;
        }

        /**
         * Obtém o valor da propriedade renavam.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRENAVAM() {
            return renavam;
        }

        /**
         * Define o valor da propriedade renavam.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRENAVAM(String value) {
            this.renavam = value;
        }

        /**
         * Obtém o valor da propriedade tara.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTara() {
            return tara;
        }

        /**
         * Define o valor da propriedade tara.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTara(String value) {
            this.tara = value;
        }

        /**
         * Obtém o valor da propriedade capKG.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCapKG() {
            return capKG;
        }

        /**
         * Define o valor da propriedade capKG.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCapKG(String value) {
            this.capKG = value;
        }

        /**
         * Obtém o valor da propriedade capM3.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCapM3() {
            return capM3;
        }

        /**
         * Define o valor da propriedade capM3.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCapM3(String value) {
            this.capM3 = value;
        }

        /**
         * Obtém o valor da propriedade prop.
         * 
         * @return
         *     possible object is
         *     {@link Rodo.VeicTracao.Prop }
         *     
         */
        public Rodo.VeicTracao.Prop getProp() {
            return prop;
        }

        /**
         * Define o valor da propriedade prop.
         * 
         * @param value
         *     allowed object is
         *     {@link Rodo.VeicTracao.Prop }
         *     
         */
        public void setProp(Rodo.VeicTracao.Prop value) {
            this.prop = value;
        }

        /**
         * Gets the value of the condutor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the condutor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCondutor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Rodo.VeicTracao.Condutor }
         * 
         * 
         */
        public List<Rodo.VeicTracao.Condutor> getCondutor() {
            if (condutor == null) {
                condutor = new ArrayList<Rodo.VeicTracao.Condutor>();
            }
            return this.condutor;
        }

        /**
         * Obtém o valor da propriedade tpRod.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTpRod() {
            return tpRod;
        }

        /**
         * Define o valor da propriedade tpRod.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTpRod(String value) {
            this.tpRod = value;
        }

        /**
         * Obtém o valor da propriedade tpCar.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTpCar() {
            return tpCar;
        }

        /**
         * Define o valor da propriedade tpCar.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTpCar(String value) {
            this.tpCar = value;
        }

        /**
         * Obtém o valor da propriedade uf.
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
         * Define o valor da propriedade uf.
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
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="xNome">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="60"/>
         *               &lt;minLength value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
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
            "cpf"
        })
        public static class Condutor {

            @XmlElement(required = true)
            protected String xNome;
            @XmlElement(name = "CPF", required = true)
            protected String cpf;

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
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/mdfe}TCpf"/>
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/mdfe}TCnpjOpc"/>
         *         &lt;/choice>
         *         &lt;element name="RNTRC" type="{http://www.portalfiscal.inf.br/mdfe}TRNTRC"/>
         *         &lt;element name="xNome">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TString">
         *               &lt;maxLength value="60"/>
         *               &lt;minLength value="2"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;sequence minOccurs="0">
         *           &lt;element name="IE">
         *             &lt;simpleType>
         *               &lt;restriction base="{http://www.portalfiscal.inf.br/mdfe}TIeDest">
         *               &lt;/restriction>
         *             &lt;/simpleType>
         *           &lt;/element>
         *           &lt;element name="UF" type="{http://www.portalfiscal.inf.br/mdfe}TUf"/>
         *         &lt;/sequence>
         *         &lt;element name="tpProp">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;whiteSpace value="preserve"/>
         *               &lt;enumeration value="0"/>
         *               &lt;enumeration value="1"/>
         *               &lt;enumeration value="2"/>
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
            "cpf",
            "cnpj",
            "rntrc",
            "xNome",
            "ie",
            "uf",
            "tpProp"
        })
        public static class Prop {

            @XmlElement(name = "CPF")
            protected String cpf;
            @XmlElement(name = "CNPJ")
            protected String cnpj;
            @XmlElement(name = "RNTRC", required = true)
            protected String rntrc;
            @XmlElement(required = true)
            protected String xNome;
            @XmlElement(name = "IE")
            protected String ie;
            @XmlElement(name = "UF")
            @XmlSchemaType(name = "string")
            protected TUf uf;
            @XmlElement(required = true)
            protected String tpProp;

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
             * Obtém o valor da propriedade rntrc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRNTRC() {
                return rntrc;
            }

            /**
             * Define o valor da propriedade rntrc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRNTRC(String value) {
                this.rntrc = value;
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
             * Obtém o valor da propriedade uf.
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
             * Define o valor da propriedade uf.
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
             * Obtém o valor da propriedade tpProp.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpProp() {
                return tpProp;
            }

            /**
             * Define o valor da propriedade tpProp.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpProp(String value) {
                this.tpProp = value;
            }

        }

    }

}
