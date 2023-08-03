package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.List;

public class LcPedidoItemsAttrClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<CdProdutoAttrClimba> attributes;

	public LcPedidoItemsAttrClimba() {
		super();
	}

	public LcPedidoItemsAttrClimba(List<CdProdutoAttrClimba> attributes) {
		super();
		this.attributes = attributes;
	}

	public List<CdProdutoAttrClimba> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<CdProdutoAttrClimba> attributes) {
		this.attributes = attributes;
	}
}
