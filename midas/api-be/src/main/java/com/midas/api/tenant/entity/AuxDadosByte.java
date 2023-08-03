package com.midas.api.tenant.entity;

import java.io.Serializable;

/*
 * 
 * Classe auxiliar apenas para leitura e exposicao de dados de Byte ao usuario
 * 
 */
public class AuxDadosByte implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte[] arquivo;
	private String nome;
	private String extensao;
	private String filetype;

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
}
