package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CdProdutoVariantesClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sku;
	private String manufacturerCode;
	private Integer quantity;
	private String typeSalesUnit;
	private String description;
	private Integer grossWeight;
	private Integer netWeight;
	private Integer height;
	private Integer width;
	private Integer length;
	private String barCode;
	private List<CdProdutoPrecosClimba> prices;
	private List<CdProdutoAttrClimba> attributes;

	public CdProdutoVariantesClimba() {
		super();
	}

	public CdProdutoVariantesClimba(String sku, String manufacturerCode, Integer quantity, String typeSalesUnit,
			String description, Integer grossWeight, Integer netWeight, Integer height, Integer width, Integer length,
			String barCode, List<CdProdutoPrecosClimba> prices, List<CdProdutoAttrClimba> attributes) {
		super();
		this.sku = sku;
		this.manufacturerCode = manufacturerCode;
		this.quantity = quantity;
		this.typeSalesUnit = typeSalesUnit;
		this.description = description;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.height = height;
		this.width = width;
		this.length = length;
		this.barCode = barCode;
		this.prices = prices;
		this.attributes = attributes;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTypeSalesUnit() {
		return typeSalesUnit;
	}

	public void setTypeSalesUnit(String typeSalesUnit) {
		this.typeSalesUnit = typeSalesUnit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Integer grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Integer getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Integer netWeight) {
		this.netWeight = netWeight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public List<CdProdutoPrecosClimba> getPrices() {
		return prices;
	}

	public void setPrices(List<CdProdutoPrecosClimba> prices) {
		this.prices = prices;
	}

	public List<CdProdutoAttrClimba> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<CdProdutoAttrClimba> attributes) {
		this.attributes = attributes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdProdutoVariantesClimba other = (CdProdutoVariantesClimba) obj;
		return Objects.equals(barCode, other.barCode);
	}
}
