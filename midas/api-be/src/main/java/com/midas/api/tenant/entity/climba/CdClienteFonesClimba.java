package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class CdClienteFonesClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;// primary
	private String number;

	public CdClienteFonesClimba() {
		super();
	}

	public CdClienteFonesClimba(String type, String number) {
		super();
		this.type = type;
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdClienteFonesClimba other = (CdClienteFonesClimba) obj;
		return Objects.equals(number, other.number);
	}
}
