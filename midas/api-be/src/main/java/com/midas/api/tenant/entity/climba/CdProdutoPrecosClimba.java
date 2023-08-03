package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class CdProdutoPrecosClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String priceListId;
	private Integer price;
	private Integer priceFrom;
	private Integer cashPrice;

	public CdProdutoPrecosClimba() {
		super();
	}

	public CdProdutoPrecosClimba(String priceListId, Integer price, Integer priceFrom, Integer cashPrice) {
		super();
		this.priceListId = priceListId;
		this.price = price;
		this.priceFrom = priceFrom;
		this.cashPrice = cashPrice;
	}

	public String getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(String priceListId) {
		this.priceListId = priceListId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(Integer priceFrom) {
		this.priceFrom = priceFrom;
	}

	public Integer getCashPrice() {
		return cashPrice;
	}

	public void setCashPrice(Integer cashPrice) {
		this.cashPrice = cashPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(priceListId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdProdutoPrecosClimba other = (CdProdutoPrecosClimba) obj;
		return Objects.equals(priceListId, other.priceListId);
	}
}
