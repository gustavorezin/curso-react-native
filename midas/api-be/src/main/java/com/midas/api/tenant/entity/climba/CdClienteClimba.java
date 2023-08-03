package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CdClienteClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String marketplaceId;
	private String name;
	private String tradeName;
	private String contactName;
	private String gender;
	private String birthDate;
	private List<CdClienteDocsClimba> documents;
	private List<CdClienteFonesClimba> phones;
	private String email;
	private CdClienteEnderecoClimba shippingAddress;
	private String priceListId;

	public CdClienteClimba() {
		super();
	}

	public CdClienteClimba(String id, String marketplaceId, String name, String tradeName, String contactName,
			String gender, String birthDate, List<CdClienteDocsClimba> documents, List<CdClienteFonesClimba> phones,
			String email, CdClienteEnderecoClimba shippingAddress, String priceListId) {
		super();
		this.id = id;
		this.marketplaceId = marketplaceId;
		this.name = name;
		this.tradeName = tradeName;
		this.contactName = contactName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.documents = documents;
		this.phones = phones;
		this.email = email;
		this.shippingAddress = shippingAddress;
		this.priceListId = priceListId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarketplaceId() {
		return marketplaceId;
	}

	public void setMarketplaceId(String marketplaceId) {
		this.marketplaceId = marketplaceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<CdClienteDocsClimba> getDocuments() {
		return documents;
	}

	public void setDocuments(List<CdClienteDocsClimba> documents) {
		this.documents = documents;
	}

	public List<CdClienteFonesClimba> getPhones() {
		return phones;
	}

	public void setPhones(List<CdClienteFonesClimba> phones) {
		this.phones = phones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CdClienteEnderecoClimba getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(CdClienteEnderecoClimba shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(String priceListId) {
		this.priceListId = priceListId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdClienteClimba other = (CdClienteClimba) obj;
		return Objects.equals(id, other.id);
	}
}
