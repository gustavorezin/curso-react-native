//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2019.10.04 às 11:26:14 AM BRT 
//


package br.inf.portalfiscal.cte;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de TUF_sem_EX.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TUF_sem_EX">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;whiteSpace value="preserve"/>
 *     &lt;enumeration value="AC"/>
 *     &lt;enumeration value="AL"/>
 *     &lt;enumeration value="AM"/>
 *     &lt;enumeration value="AP"/>
 *     &lt;enumeration value="BA"/>
 *     &lt;enumeration value="CE"/>
 *     &lt;enumeration value="DF"/>
 *     &lt;enumeration value="ES"/>
 *     &lt;enumeration value="GO"/>
 *     &lt;enumeration value="MA"/>
 *     &lt;enumeration value="MG"/>
 *     &lt;enumeration value="MS"/>
 *     &lt;enumeration value="MT"/>
 *     &lt;enumeration value="PA"/>
 *     &lt;enumeration value="PB"/>
 *     &lt;enumeration value="PE"/>
 *     &lt;enumeration value="PI"/>
 *     &lt;enumeration value="PR"/>
 *     &lt;enumeration value="RJ"/>
 *     &lt;enumeration value="RN"/>
 *     &lt;enumeration value="RO"/>
 *     &lt;enumeration value="RR"/>
 *     &lt;enumeration value="RS"/>
 *     &lt;enumeration value="SC"/>
 *     &lt;enumeration value="SE"/>
 *     &lt;enumeration value="SP"/>
 *     &lt;enumeration value="TO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TUF_sem_EX")
@XmlEnum
public enum TUFSemEX {

    AC,
    AL,
    AM,
    AP,
    BA,
    CE,
    DF,
    ES,
    GO,
    MA,
    MG,
    MS,
    MT,
    PA,
    PB,
    PE,
    PI,
    PR,
    RJ,
    RN,
    RO,
    RR,
    RS,
    SC,
    SE,
    SP,
    TO;

    public String value() {
        return name();
    }

    public static TUFSemEX fromValue(String v) {
        return valueOf(v);
    }

}
