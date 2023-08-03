package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CdProdutoClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private List<String> categories;
	private String brandId;
	private String name;
	private String description;
	private String shortDescription;
	private List<CdProdutoVariantesClimba> productVariants;
	private List<CdProdutoVideosClimba> videos;

	public CdProdutoClimba() {
		super();
	}

	public CdProdutoClimba(String id, String status, List<String> categories, String brandId, String name,
			String description, String shortDescription, List<CdProdutoVariantesClimba> productVariants,
			List<CdProdutoVideosClimba> videos) {
		super();
		this.id = id;
		this.status = status;
		this.categories = categories;
		this.brandId = brandId;
		this.name = name;
		this.description = description;
		this.shortDescription = shortDescription;
		this.productVariants = productVariants;
		this.videos = videos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public List<CdProdutoVariantesClimba> getProductVariants() {
		return productVariants;
	}

	public void setProductVariants(List<CdProdutoVariantesClimba> productVariants) {
		this.productVariants = productVariants;
	}

	public List<CdProdutoVideosClimba> getVideos() {
		return videos;
	}

	public void setVideos(List<CdProdutoVideosClimba> videos) {
		this.videos = videos;
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
		CdProdutoClimba other = (CdProdutoClimba) obj;
		return Objects.equals(id, other.id);
	}
}
