package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class LcPedidoDadosEntregaClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer logisticOperatorId;
	private String number;
	private String trackingUrl;
	private String shipmentDate;
	private String status;
	private String labelUrl;

	public LcPedidoDadosEntregaClimba() {
		super();
	}

	public LcPedidoDadosEntregaClimba(Integer logisticOperatorId, String number, String trackingUrl,
			String shipmentDate, String status, String labelUrl) {
		super();
		this.logisticOperatorId = logisticOperatorId;
		this.number = number;
		this.trackingUrl = trackingUrl;
		this.shipmentDate = shipmentDate;
		this.status = status;
		this.labelUrl = labelUrl;
	}

	public Integer getLogisticOperatorId() {
		return logisticOperatorId;
	}

	public void setLogisticOperatorId(Integer logisticOperatorId) {
		this.logisticOperatorId = logisticOperatorId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTrackingUrl() {
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}

	public String getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLabelUrl() {
		return labelUrl;
	}

	public void setLabelUrl(String labelUrl) {
		this.labelUrl = labelUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(logisticOperatorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LcPedidoDadosEntregaClimba other = (LcPedidoDadosEntregaClimba) obj;
		return Objects.equals(logisticOperatorId, other.logisticOperatorId);
	}
}
