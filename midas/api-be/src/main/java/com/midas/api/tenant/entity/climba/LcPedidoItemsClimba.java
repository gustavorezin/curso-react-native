package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class LcPedidoItemsClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private List<LcPedidoItemsAttrClimba> orderItemVariants;
	private Integer sequence;
	private String productId;
	private String sku;
	private Integer quantity;
	private BigDecimal sellingPrice;
	private boolean giftPaperEnabled;
	private BigDecimal giftPaperValue;
	private String giftPaperMessage;
	private BigDecimal discountValue;
	private BigDecimal price;
	private String length;
	private String width;
	private boolean freebie;
	private String freebieMessage;
	private String typeSalesUnit;
	private BigDecimal externalMarketplaceComission;
	private boolean purchaseByFootage;
	private String manufacturerCode;

	public LcPedidoItemsClimba() {
		super();
	}

	public LcPedidoItemsClimba(String id, String name, List<LcPedidoItemsAttrClimba> orderItemVariants,
			Integer sequence, String productId, String sku, Integer quantity, BigDecimal sellingPrice,
			boolean giftPaperEnabled, BigDecimal giftPaperValue, String giftPaperMessage, BigDecimal discountValue,
			BigDecimal price, String length, String width, boolean freebie, String freebieMessage, String typeSalesUnit,
			BigDecimal externalMarketplaceComission, boolean purchaseByFootage, String manufacturerCode) {
		super();
		this.id = id;
		this.name = name;
		this.orderItemVariants = orderItemVariants;
		this.sequence = sequence;
		this.productId = productId;
		this.sku = sku;
		this.quantity = quantity;
		this.sellingPrice = sellingPrice;
		this.giftPaperEnabled = giftPaperEnabled;
		this.giftPaperValue = giftPaperValue;
		this.giftPaperMessage = giftPaperMessage;
		this.discountValue = discountValue;
		this.price = price;
		this.length = length;
		this.width = width;
		this.freebie = freebie;
		this.freebieMessage = freebieMessage;
		this.typeSalesUnit = typeSalesUnit;
		this.externalMarketplaceComission = externalMarketplaceComission;
		this.purchaseByFootage = purchaseByFootage;
		this.manufacturerCode = manufacturerCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LcPedidoItemsAttrClimba> getOrderItemVariants() {
		return orderItemVariants;
	}

	public void setOrderItemVariants(List<LcPedidoItemsAttrClimba> orderItemVariants) {
		this.orderItemVariants = orderItemVariants;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public boolean isGiftPaperEnabled() {
		return giftPaperEnabled;
	}

	public void setGiftPaperEnabled(boolean giftPaperEnabled) {
		this.giftPaperEnabled = giftPaperEnabled;
	}

	public BigDecimal getGiftPaperValue() {
		return giftPaperValue;
	}

	public void setGiftPaperValue(BigDecimal giftPaperValue) {
		this.giftPaperValue = giftPaperValue;
	}

	public String getGiftPaperMessage() {
		return giftPaperMessage;
	}

	public void setGiftPaperMessage(String giftPaperMessage) {
		this.giftPaperMessage = giftPaperMessage;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public boolean isFreebie() {
		return freebie;
	}

	public void setFreebie(boolean freebie) {
		this.freebie = freebie;
	}

	public String getFreebieMessage() {
		return freebieMessage;
	}

	public void setFreebieMessage(String freebieMessage) {
		this.freebieMessage = freebieMessage;
	}

	public String getTypeSalesUnit() {
		return typeSalesUnit;
	}

	public void setTypeSalesUnit(String typeSalesUnit) {
		this.typeSalesUnit = typeSalesUnit;
	}

	public BigDecimal getExternalMarketplaceComission() {
		return externalMarketplaceComission;
	}

	public void setExternalMarketplaceComission(BigDecimal externalMarketplaceComission) {
		this.externalMarketplaceComission = externalMarketplaceComission;
	}

	public boolean isPurchaseByFootage() {
		return purchaseByFootage;
	}

	public void setPurchaseByFootage(boolean purchaseByFootage) {
		this.purchaseByFootage = purchaseByFootage;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
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
		LcPedidoItemsClimba other = (LcPedidoItemsClimba) obj;
		return Objects.equals(id, other.id);
	}
}
