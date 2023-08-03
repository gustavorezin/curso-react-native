package com.midas.api.tenant.entity.climba;

import java.io.Serializable;
import java.util.Objects;

public class CdProdutoVideosClimba implements Serializable {
	private static final long serialVersionUID = 1L;
	private String urlVideo;
	private String embed;
	private String type;

	public CdProdutoVideosClimba() {
		super();
	}

	public CdProdutoVideosClimba(String urlVideo, String embed, String type) {
		super();
		this.urlVideo = urlVideo;
		this.embed = embed;
		this.type = type;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(urlVideo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdProdutoVideosClimba other = (CdProdutoVideosClimba) obj;
		return Objects.equals(urlVideo, other.urlVideo);
	}
}
