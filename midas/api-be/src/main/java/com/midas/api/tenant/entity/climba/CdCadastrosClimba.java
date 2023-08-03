package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class CdCadastrosClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;// 50
	private String name;// 255
	private String description;
	private String attributeGroupId;// Cor ou tamanho
	private String parentId; // Categorias e grupos
	private String order;

	public CdCadastrosClimba() {
		super();
	}

	public CdCadastrosClimba(String id, String name, String description, String attributeGroupId, String parentId,
			String order) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.attributeGroupId = attributeGroupId;
		this.parentId = parentId;
		this.order = order;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttributeGroupId() {
		return attributeGroupId;
	}

	public void setAttributeGroupId(String attributeGroupId) {
		this.attributeGroupId = attributeGroupId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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
		CdCadastrosClimba other = (CdCadastrosClimba) obj;
		return Objects.equals(id, other.id);
	}
}
