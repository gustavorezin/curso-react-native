//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.31 at 02:36:13 PM BRT 
//

package br.inf.portalfiscal.nfe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.nfe.xsd.pl6.retConsCad package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactoryConsCad {

	private final static QName _RetConsCad_QNAME = new QName(
			"http://www.portalfiscal.inf.br/nfe", "retConsCad");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: org.nfe.xsd.pl6.retConsCad
	 * 
	 */
	public ObjectFactoryConsCad() {
	}

	/**
	 * Create an instance of {@link TRetConsCad.InfCons }
	 * 
	 */
	public TRetConsCad.InfCons createTRetConsCadInfCons() {
		return new TRetConsCad.InfCons();
	}

	/**
	 * Create an instance of {@link TConsCad }
	 * 
	 */
	public TConsCad createTConsCad() {
		return new TConsCad();
	}

	/**
	 * Create an instance of {@link TEndereco }
	 * 
	 */
	public TEndereco createTEndereco() {
		return new TEndereco();
	}

	/**
	 * Create an instance of {@link TRetConsCad.InfCons.InfCad }
	 * 
	 */
	public TRetConsCad.InfCons.InfCad createTRetConsCadInfConsInfCad() {
		return new TRetConsCad.InfCons.InfCad();
	}

	/**
	 * Create an instance of {@link TConsCad.InfCons }
	 * 
	 */
	public TConsCad.InfCons createTConsCadInfCons() {
		return new TConsCad.InfCons();
	}

	/**
	 * Create an instance of {@link TRetConsCad }
	 * 
	 */
	public TRetConsCad createTRetConsCad() {
		return new TRetConsCad();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TRetConsCad }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "retConsCad")
	public JAXBElement<TRetConsCad> createRetConsCad(TRetConsCad value) {
		return new JAXBElement<TRetConsCad>(_RetConsCad_QNAME,
				TRetConsCad.class, null, value);
	}

}