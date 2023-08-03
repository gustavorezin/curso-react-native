package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class LcPedidoDadosEntregaEfetuadaClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String deliveryDate;
	private String receiverName;
	private String recipientsRelationship;
	private String deliveryName;
	private boolean recipientPresent;

	public LcPedidoDadosEntregaEfetuadaClimba() {
		super();
	}

	public LcPedidoDadosEntregaEfetuadaClimba(String deliveryDate, String receiverName, String recipientsRelationship,
			String deliveryName, boolean recipientPresent) {
		super();
		this.deliveryDate = deliveryDate;
		this.receiverName = receiverName;
		this.recipientsRelationship = recipientsRelationship;
		this.deliveryName = deliveryName;
		this.recipientPresent = recipientPresent;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getRecipientsRelationship() {
		return recipientsRelationship;
	}

	public void setRecipientsRelationship(String recipientsRelationship) {
		this.recipientsRelationship = recipientsRelationship;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public boolean isRecipientPresent() {
		return recipientPresent;
	}

	public void setRecipientPresent(boolean recipientPresent) {
		this.recipientPresent = recipientPresent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(receiverName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LcPedidoDadosEntregaEfetuadaClimba other = (LcPedidoDadosEntregaEfetuadaClimba) obj;
		return Objects.equals(receiverName, other.receiverName);
	}
}
