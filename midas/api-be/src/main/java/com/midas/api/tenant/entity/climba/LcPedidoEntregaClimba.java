package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class LcPedidoEntregaClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String description;
	private String identification;
	private String invoiceNumber;
	private String invoiceAccessKey;

	public LcPedidoEntregaClimba() {
		super();
	}

	public LcPedidoEntregaClimba(String id, String description, String identification, String invoiceNumber,
			String invoiceAccessKey) {
		super();
		this.id = id;
		this.description = description;
		this.identification = identification;
		this.invoiceNumber = invoiceNumber;
		this.invoiceAccessKey = invoiceAccessKey;
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

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceAccessKey() {
		return invoiceAccessKey;
	}

	public void setInvoiceAccessKey(String invoiceAccessKey) {
		this.invoiceAccessKey = invoiceAccessKey;
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
		LcPedidoEntregaClimba other = (LcPedidoEntregaClimba) obj;
		return Objects.equals(id, other.id);
	}
}
