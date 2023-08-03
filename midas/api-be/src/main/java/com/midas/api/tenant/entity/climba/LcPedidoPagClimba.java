package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class LcPedidoPagClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String description;
	private String transactionId;
	private String brand;
	private String paymentDate;
	private String paymentType;
	private String nsu;
	private String authorizationCode;
	private String paymentLink;
	private String paymentDueDate;
	private String paymentImage;
	private Integer installments;

	public LcPedidoPagClimba() {
		super();
	}

	public LcPedidoPagClimba(String id, String description, String transactionId, String brand, String paymentDate,
			String paymentType, String nsu, String authorizationCode, String paymentLink, String paymentDueDate,
			String paymentImage, Integer installments) {
		super();
		this.id = id;
		this.description = description;
		this.transactionId = transactionId;
		this.brand = brand;
		this.paymentDate = paymentDate;
		this.paymentType = paymentType;
		this.nsu = nsu;
		this.authorizationCode = authorizationCode;
		this.paymentLink = paymentLink;
		this.paymentDueDate = paymentDueDate;
		this.paymentImage = paymentImage;
		this.installments = installments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getPaymentLink() {
		return paymentLink;
	}

	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}

	public String getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(String paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public String getPaymentImage() {
		return paymentImage;
	}

	public void setPaymentImage(String paymentImage) {
		this.paymentImage = paymentImage;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
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
		LcPedidoPagClimba other = (LcPedidoPagClimba) obj;
		return Objects.equals(id, other.id);
	}
}
