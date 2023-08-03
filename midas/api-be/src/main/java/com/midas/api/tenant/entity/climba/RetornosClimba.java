package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.List;

public class RetornosClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	private Integer total;
	private Integer perPage;
	private Integer currentPage;
	private Integer lastPage;
	private String prevPageUrl;
	private String nextPageUrl;
	private Integer from;
	private Integer to;
	private List<LcPedidoClimba> data;

	public RetornosClimba() {
	}

	public RetornosClimba(String code, String description, Integer total, Integer perPage, Integer currentPage,
			Integer lastPage, String prevPageUrl, String nextPageUrl, Integer from, Integer to,
			List<LcPedidoClimba> data) {
		super();
		this.code = code;
		this.description = description;
		this.total = total;
		this.perPage = perPage;
		this.currentPage = currentPage;
		this.lastPage = lastPage;
		this.prevPageUrl = prevPageUrl;
		this.nextPageUrl = nextPageUrl;
		this.from = from;
		this.to = to;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPerPage() {
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public String getPrevPageUrl() {
		return prevPageUrl;
	}

	public void setPrevPageUrl(String prevPageUrl) {
		this.prevPageUrl = prevPageUrl;
	}

	public String getNextPageUrl() {
		return nextPageUrl;
	}

	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public List<LcPedidoClimba> getData() {
		return data;
	}

	public void setData(List<LcPedidoClimba> data) {
		this.data = data;
	}
}
