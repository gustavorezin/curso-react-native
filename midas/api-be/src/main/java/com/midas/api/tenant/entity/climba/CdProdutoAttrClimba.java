package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class CdProdutoAttrClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String attributeGroupId;

	public CdProdutoAttrClimba() {
		super();
	}

	public CdProdutoAttrClimba(String id, String name, String attributeGroupId) {
		super();
		this.id = id;
		this.name = name;
		this.attributeGroupId = attributeGroupId;
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

	public String getAttributeGroupId() {
		return attributeGroupId;
	}

	public void setAttributeGroupId(String attributeGroupId) {
		this.attributeGroupId = attributeGroupId;
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
		CdProdutoAttrClimba other = (CdProdutoAttrClimba) obj;
		return Objects.equals(id, other.id);
	}
}
