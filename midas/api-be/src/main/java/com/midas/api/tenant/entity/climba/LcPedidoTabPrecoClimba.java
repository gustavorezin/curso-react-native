package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class LcPedidoTabPrecoClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;

	public LcPedidoTabPrecoClimba() {
		super();
	}

	public LcPedidoTabPrecoClimba(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		LcPedidoTabPrecoClimba other = (LcPedidoTabPrecoClimba) obj;
		return Objects.equals(id, other.id);
	}
}
