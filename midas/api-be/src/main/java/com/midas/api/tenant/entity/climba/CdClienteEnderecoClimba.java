package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class CdClienteEnderecoClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String identificationDescription;
	private String street;
	private String streetNumber;
	private String complement;
	private String district;
	private String city;
	private String state;
	private String zipCode;
	private String reference;

	public CdClienteEnderecoClimba() {
		super();
	}

	public CdClienteEnderecoClimba(String identificationDescription, String street, String streetNumber,
			String complement, String district, String city, String state, String zipCode, String reference) {
		super();
		this.identificationDescription = identificationDescription;
		this.street = street;
		this.streetNumber = streetNumber;
		this.complement = complement;
		this.district = district;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.reference = reference;
	}

	public String getIdentificationDescription() {
		return identificationDescription;
	}

	public void setIdentificationDescription(String identificationDescription) {
		this.identificationDescription = identificationDescription;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public int hashCode() {
		return Objects.hash(zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdClienteEnderecoClimba other = (CdClienteEnderecoClimba) obj;
		return Objects.equals(zipCode, other.zipCode);
	}
}
