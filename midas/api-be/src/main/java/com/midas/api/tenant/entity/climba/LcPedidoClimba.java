package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class LcPedidoClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String sourceId;
	private String sourceDescription;
	private String integrationId;
	private String orderGroupId;
	private CdClienteClimba customer;
	private String orderDate;
	private String expirationDate;
	private BigDecimal discountValue;
	private BigDecimal shippingValue;
	private BigDecimal additionValue;
	private BigDecimal totalValue;
	private Integer totalWeight;
	private LcPedidoEntregaClimba shipping;
	private Integer estimatedDispatchTime;
	private Integer estimatedDeliveryTime;
	private String status;
	private LcPedidoPagClimba payment;
	private List<LcPedidoPagClimba> payments;
	private Integer installmentQuantity;
	private String customerComment;
	private String orderNote;
	private List<LcPedidoItemsClimba> items;
	private LcPedidoTabPrecoClimba priceList;
	private LcPedidoEnderecoEntregaClimba receiverAddress;
	private List<LcPedidoDadosEntregaClimba> orderTrackings;
	private List<String> tags;
	private BigDecimal externalMarketplaceComission;
	private LcPedidoDadosEntregaEfetuadaClimba orderDelivery;
	private boolean orderExportedToErp;
	private Integer totalHeight;
	private Integer totalWidth;
	private Integer totalLength;
	private Integer totalCubage;
	private String updateAt;
	private String reviewUrl;
	private String couponCode;

	public LcPedidoClimba() {
		super();
	}

	public LcPedidoClimba(String id, String sourceId, String sourceDescription, String integrationId,
			String orderGroupId, CdClienteClimba customer, String orderDate, String expirationDate,
			BigDecimal discountValue, BigDecimal shippingValue, BigDecimal additionValue, BigDecimal totalValue,
			Integer totalWeight, LcPedidoEntregaClimba shipping, Integer estimatedDispatchTime,
			Integer estimatedDeliveryTime, String status, LcPedidoPagClimba payment, List<LcPedidoPagClimba> payments,
			Integer installmentQuantity, String customerComment, String orderNote, List<LcPedidoItemsClimba> items,
			LcPedidoTabPrecoClimba priceList, LcPedidoEnderecoEntregaClimba receiverAddress,
			List<LcPedidoDadosEntregaClimba> orderTrackings, List<String> tags, BigDecimal externalMarketplaceComission,
			LcPedidoDadosEntregaEfetuadaClimba orderDelivery, boolean orderExportedToErp, Integer totalHeight,
			Integer totalWidth, Integer totalLength, Integer totalCubage, String updateAt, String reviewUrl,
			String couponCode) {
		super();
		this.id = id;
		this.sourceId = sourceId;
		this.sourceDescription = sourceDescription;
		this.integrationId = integrationId;
		this.orderGroupId = orderGroupId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.expirationDate = expirationDate;
		this.discountValue = discountValue;
		this.shippingValue = shippingValue;
		this.additionValue = additionValue;
		this.totalValue = totalValue;
		this.totalWeight = totalWeight;
		this.shipping = shipping;
		this.estimatedDispatchTime = estimatedDispatchTime;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
		this.status = status;
		this.payment = payment;
		this.payments = payments;
		this.installmentQuantity = installmentQuantity;
		this.customerComment = customerComment;
		this.orderNote = orderNote;
		this.items = items;
		this.priceList = priceList;
		this.receiverAddress = receiverAddress;
		this.orderTrackings = orderTrackings;
		this.tags = tags;
		this.externalMarketplaceComission = externalMarketplaceComission;
		this.orderDelivery = orderDelivery;
		this.orderExportedToErp = orderExportedToErp;
		this.totalHeight = totalHeight;
		this.totalWidth = totalWidth;
		this.totalLength = totalLength;
		this.totalCubage = totalCubage;
		this.updateAt = updateAt;
		this.reviewUrl = reviewUrl;
		this.couponCode = couponCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceDescription() {
		return sourceDescription;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}

	public String getOrderGroupId() {
		return orderGroupId;
	}

	public void setOrderGroupId(String orderGroupId) {
		this.orderGroupId = orderGroupId;
	}

	public CdClienteClimba getCustomer() {
		return customer;
	}

	public void setCustomer(CdClienteClimba customer) {
		this.customer = customer;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public BigDecimal getShippingValue() {
		return shippingValue;
	}

	public void setShippingValue(BigDecimal shippingValue) {
		this.shippingValue = shippingValue;
	}

	public BigDecimal getAdditionValue() {
		return additionValue;
	}

	public void setAdditionValue(BigDecimal additionValue) {
		this.additionValue = additionValue;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public Integer getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	public LcPedidoEntregaClimba getShipping() {
		return shipping;
	}

	public void setShipping(LcPedidoEntregaClimba shipping) {
		this.shipping = shipping;
	}

	public Integer getEstimatedDispatchTime() {
		return estimatedDispatchTime;
	}

	public void setEstimatedDispatchTime(Integer estimatedDispatchTime) {
		this.estimatedDispatchTime = estimatedDispatchTime;
	}

	public Integer getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LcPedidoPagClimba getPayment() {
		return payment;
	}

	public void setPayment(LcPedidoPagClimba payment) {
		this.payment = payment;
	}

	public List<LcPedidoPagClimba> getPayments() {
		return payments;
	}

	public void setPayments(List<LcPedidoPagClimba> payments) {
		this.payments = payments;
	}

	public Integer getInstallmentQuantity() {
		return installmentQuantity;
	}

	public void setInstallmentQuantity(Integer installmentQuantity) {
		this.installmentQuantity = installmentQuantity;
	}

	public String getCustomerComment() {
		return customerComment;
	}

	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public List<LcPedidoItemsClimba> getItems() {
		return items;
	}

	public void setItems(List<LcPedidoItemsClimba> items) {
		this.items = items;
	}

	public LcPedidoTabPrecoClimba getPriceList() {
		return priceList;
	}

	public void setPriceList(LcPedidoTabPrecoClimba priceList) {
		this.priceList = priceList;
	}

	public LcPedidoEnderecoEntregaClimba getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(LcPedidoEnderecoEntregaClimba receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public List<LcPedidoDadosEntregaClimba> getOrderTrackings() {
		return orderTrackings;
	}

	public void setOrderTrackings(List<LcPedidoDadosEntregaClimba> orderTrackings) {
		this.orderTrackings = orderTrackings;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public BigDecimal getExternalMarketplaceComission() {
		return externalMarketplaceComission;
	}

	public void setExternalMarketplaceComission(BigDecimal externalMarketplaceComission) {
		this.externalMarketplaceComission = externalMarketplaceComission;
	}

	public LcPedidoDadosEntregaEfetuadaClimba getOrderDelivery() {
		return orderDelivery;
	}

	public void setOrderDelivery(LcPedidoDadosEntregaEfetuadaClimba orderDelivery) {
		this.orderDelivery = orderDelivery;
	}

	public boolean isOrderExportedToErp() {
		return orderExportedToErp;
	}

	public void setOrderExportedToErp(boolean orderExportedToErp) {
		this.orderExportedToErp = orderExportedToErp;
	}

	public Integer getTotalHeight() {
		return totalHeight;
	}

	public void setTotalHeight(Integer totalHeight) {
		this.totalHeight = totalHeight;
	}

	public Integer getTotalWidth() {
		return totalWidth;
	}

	public void setTotalWidth(Integer totalWidth) {
		this.totalWidth = totalWidth;
	}

	public Integer getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}

	public Integer getTotalCubage() {
		return totalCubage;
	}

	public void setTotalCubage(Integer totalCubage) {
		this.totalCubage = totalCubage;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public String getReviewUrl() {
		return reviewUrl;
	}

	public void setReviewUrl(String reviewUrl) {
		this.reviewUrl = reviewUrl;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
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
		LcPedidoClimba other = (LcPedidoClimba) obj;
		return Objects.equals(id, other.id);
	}
}
