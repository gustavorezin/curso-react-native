package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoConta implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ContaCodigoBanco;
	private String ContaAgencia;
	private String ContaAgenciaDV;
	private String ContaNumero;
	private String ContaNumeroDV;
	private String ContaTipo;
	private String ContaCodigoBeneficiario;
	private String ContaCodigoBancoCorrespondente;
	private String ContaCodigoEmpresa;
	private boolean ContaValidacaoAtiva;
	private boolean ContaImpressaoAtualizada;
	private boolean ContaImpressaoAtualizadaAlteracao;
	private boolean Ativo;

	public TecnoConta() {
	}

	public TecnoConta(String contaCodigoBanco, String contaAgencia, String contaAgenciaDV, String contaNumero,
			String contaNumeroDV, String contaTipo, String contaCodigoBeneficiario,
			String contaCodigoBancoCorrespondente, String contaCodigoEmpresa, boolean contaValidacaoAtiva,
			boolean contaImpressaoAtualizada, boolean contaImpressaoAtualizadaAlteracao, boolean ativo) {
		super();
		ContaCodigoBanco = contaCodigoBanco;
		ContaAgencia = contaAgencia;
		ContaAgenciaDV = contaAgenciaDV;
		ContaNumero = contaNumero;
		ContaNumeroDV = contaNumeroDV;
		ContaTipo = contaTipo;
		ContaCodigoBeneficiario = contaCodigoBeneficiario;
		ContaCodigoBancoCorrespondente = contaCodigoBancoCorrespondente;
		ContaCodigoEmpresa = contaCodigoEmpresa;
		ContaValidacaoAtiva = contaValidacaoAtiva;
		ContaImpressaoAtualizada = contaImpressaoAtualizada;
		ContaImpressaoAtualizadaAlteracao = contaImpressaoAtualizadaAlteracao;
		Ativo = ativo;
	}

	public String getContaCodigoBanco() {
		return ContaCodigoBanco;
	}

	public void setContaCodigoBanco(String contaCodigoBanco) {
		ContaCodigoBanco = contaCodigoBanco;
	}

	public String getContaAgencia() {
		return ContaAgencia;
	}

	public void setContaAgencia(String contaAgencia) {
		ContaAgencia = contaAgencia;
	}

	public String getContaAgenciaDV() {
		return ContaAgenciaDV;
	}

	public void setContaAgenciaDV(String contaAgenciaDV) {
		ContaAgenciaDV = contaAgenciaDV;
	}

	public String getContaNumero() {
		return ContaNumero;
	}

	public void setContaNumero(String contaNumero) {
		ContaNumero = contaNumero;
	}

	public String getContaNumeroDV() {
		return ContaNumeroDV;
	}

	public void setContaNumeroDV(String contaNumeroDV) {
		ContaNumeroDV = contaNumeroDV;
	}

	public String getContaTipo() {
		return ContaTipo;
	}

	public void setContaTipo(String contaTipo) {
		ContaTipo = contaTipo;
	}

	public String getContaCodigoBeneficiario() {
		return ContaCodigoBeneficiario;
	}

	public void setContaCodigoBeneficiario(String contaCodigoBeneficiario) {
		ContaCodigoBeneficiario = contaCodigoBeneficiario;
	}

	public String getContaCodigoBancoCorrespondente() {
		return ContaCodigoBancoCorrespondente;
	}

	public void setContaCodigoBancoCorrespondente(String contaCodigoBancoCorrespondente) {
		ContaCodigoBancoCorrespondente = contaCodigoBancoCorrespondente;
	}

	public String getContaCodigoEmpresa() {
		return ContaCodigoEmpresa;
	}

	public void setContaCodigoEmpresa(String contaCodigoEmpresa) {
		ContaCodigoEmpresa = contaCodigoEmpresa;
	}

	public boolean isContaValidacaoAtiva() {
		return ContaValidacaoAtiva;
	}

	public void setContaValidacaoAtiva(boolean contaValidacaoAtiva) {
		ContaValidacaoAtiva = contaValidacaoAtiva;
	}

	public boolean isContaImpressaoAtualizada() {
		return ContaImpressaoAtualizada;
	}

	public void setContaImpressaoAtualizada(boolean contaImpressaoAtualizada) {
		ContaImpressaoAtualizada = contaImpressaoAtualizada;
	}

	public boolean isContaImpressaoAtualizadaAlteracao() {
		return ContaImpressaoAtualizadaAlteracao;
	}

	public void setContaImpressaoAtualizadaAlteracao(boolean contaImpressaoAtualizadaAlteracao) {
		ContaImpressaoAtualizadaAlteracao = contaImpressaoAtualizadaAlteracao;
	}

	public boolean isAtivo() {
		return Ativo;
	}

	public void setAtivo(boolean ativo) {
		Ativo = ativo;
	}
}
