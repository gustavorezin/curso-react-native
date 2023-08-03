package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TecnoBoleto implements Serializable {
	private static final long serialVersionUID = 1L;
	// Sacado
	private String SacadoCPFCNPJ;
	private String SacadoNome;
	private String SacadoEnderecoLogradouro;
	private String SacadoEnderecoNumero;
	private String SacadoEnderecoBairro;
	private String SacadoEnderecoCep;
	private String SacadoEnderecoCidade;
	private String SacadoEnderecoComplemento;
	private String SacadoEnderecoPais;
	private String SacadoEnderecoUf;
	private String SacadoEmail;
	private String SacadoTelefone;
	private String SacadoCelular;
	// Conta-convenio
	private String CedenteContaCodigoBanco;
	private String CedenteContaNumero;
	private String CedenteContaNumeroDV;
	private String CedenteConvenioNumero;
	private String TituloVariacaoCarteira;
	// Campos basicos
	private String TituloNossoNumero;
	private String TituloValor;
	private String TituloNumeroDocumento;
	private String TituloDataEmissao;
	private String TituloDataVencimento;
	private String TituloAceite;
	private String TituloDocEspecie;
	private String TituloLocalPagamento;
	// Desconto
	private String TituloCodDesconto;
	private String TituloDataDesconto;
	private String TituloValorDescontoTaxa;
	// Juros
	private String TituloCodigoJuros;
	private String TituloDataJuros;
	private String TituloValorJuros;
	// Multa
	private String TituloCodigoMulta;
	private String TituloDataMulta;
	private String TituloValorMultaTaxa;
	// Protesto auto
	private String TituloCodProtesto;
	private String TituloPrazoProtesto;
	// Baixa auto
	private String TituloCodBaixaDevolucao;
	private String TituloPrazoBaixa;
	// Mensagens - pode ir ate 09
	private String TituloMensagem01;
	private String TituloMensagem02;
	private String TituloMensagem03;
	private String TituloMensagem04;
	private String TituloMensagem05;
	private String TituloMensagem06;
	private String TituloMensagem07;
	private String TituloMensagem08;
	private String TituloMensagem09;
	// Avalista
	private String TituloSacadorAvalista;
	private String TituloSacadorAvalistaEndereco;
	private String TituloSacadorAvalistaCidade;
	private String TituloSacadorAvalistaCEP;
	private String TituloSacadorAvalistaUF;
	private String TituloInscricaoSacadorAvalista;
	// Outros
	private String UrlBoleto;
	private String PagamentoValorTaxaCobranca;
	private String situacao;
	private String PagamentoData;
	private boolean PagamentoRealizado;
	private String PagamentoValorCredito;
	private String PagamentoValorPago;// Valor com juros e descontos
	private String PagamentoValorAcrescimos;// Juros e multas
	private String PagamentoValorDesconto;
	private String PagamentoDataTaxaBancaria;// Dia que a taxa sera debitada cedente
	private String TituloLinhaDigitavel;
	private String TituloCodigoBarras;
	private String TituloParcela;
	private String IdIntegracao;
	private List<TecnoRetornoBoletoDados> TituloOcorrencias = new ArrayList<TecnoRetornoBoletoDados>();

	public TecnoBoleto() {
	}

	public TecnoBoleto(String sacadoCPFCNPJ, String sacadoNome, String sacadoEnderecoLogradouro,
			String sacadoEnderecoNumero, String sacadoEnderecoBairro, String sacadoEnderecoCep,
			String sacadoEnderecoCidade, String sacadoEnderecoComplemento, String sacadoEnderecoPais,
			String sacadoEnderecoUf, String sacadoEmail, String sacadoTelefone, String sacadoCelular,
			String cedenteContaCodigoBanco, String cedenteContaNumero, String cedenteContaNumeroDV,
			String cedenteConvenioNumero, String tituloVariacaoCarteira, String tituloNossoNumero, String tituloValor,
			String tituloNumeroDocumento, String tituloDataEmissao, String tituloDataVencimento, String tituloAceite,
			String tituloDocEspecie, String tituloLocalPagamento, String tituloCodDesconto, String tituloDataDesconto,
			String tituloValorDescontoTaxa, String tituloCodigoJuros, String tituloDataJuros, String tituloValorJuros,
			String tituloCodigoMulta, String tituloDataMulta, String tituloValorMultaTaxa, String tituloCodProtesto,
			String tituloPrazoProtesto, String tituloCodBaixaDevolucao, String tituloPrazoBaixa,
			String tituloMensagem01, String tituloMensagem02, String tituloMensagem03, String tituloMensagem04,
			String tituloMensagem05, String tituloMensagem06, String tituloMensagem07, String tituloMensagem08,
			String tituloMensagem09, String tituloSacadorAvalista, String tituloSacadorAvalistaEndereco,
			String tituloSacadorAvalistaCidade, String tituloSacadorAvalistaCEP, String tituloSacadorAvalistaUF,
			String tituloInscricaoSacadorAvalista, String urlBoleto, String pagamentoValorTaxaCobranca, String situacao,
			String pagamentoData, boolean pagamentoRealizado, String pagamentoValorCredito, String pagamentoValorPago,
			String pagamentoValorAcrescimos, String pagamentoValorDesconto, String pagamentoDataTaxaBancaria,
			String tituloLinhaDigitavel, String tituloCodigoBarras, String tituloParcela, String idIntegracao,
			List<TecnoRetornoBoletoDados> tituloOcorrencias) {
		super();
		SacadoCPFCNPJ = sacadoCPFCNPJ;
		SacadoNome = sacadoNome;
		SacadoEnderecoLogradouro = sacadoEnderecoLogradouro;
		SacadoEnderecoNumero = sacadoEnderecoNumero;
		SacadoEnderecoBairro = sacadoEnderecoBairro;
		SacadoEnderecoCep = sacadoEnderecoCep;
		SacadoEnderecoCidade = sacadoEnderecoCidade;
		SacadoEnderecoComplemento = sacadoEnderecoComplemento;
		SacadoEnderecoPais = sacadoEnderecoPais;
		SacadoEnderecoUf = sacadoEnderecoUf;
		SacadoEmail = sacadoEmail;
		SacadoTelefone = sacadoTelefone;
		SacadoCelular = sacadoCelular;
		CedenteContaCodigoBanco = cedenteContaCodigoBanco;
		CedenteContaNumero = cedenteContaNumero;
		CedenteContaNumeroDV = cedenteContaNumeroDV;
		CedenteConvenioNumero = cedenteConvenioNumero;
		TituloVariacaoCarteira = tituloVariacaoCarteira;
		TituloNossoNumero = tituloNossoNumero;
		TituloValor = tituloValor;
		TituloNumeroDocumento = tituloNumeroDocumento;
		TituloDataEmissao = tituloDataEmissao;
		TituloDataVencimento = tituloDataVencimento;
		TituloAceite = tituloAceite;
		TituloDocEspecie = tituloDocEspecie;
		TituloLocalPagamento = tituloLocalPagamento;
		TituloCodDesconto = tituloCodDesconto;
		TituloDataDesconto = tituloDataDesconto;
		TituloValorDescontoTaxa = tituloValorDescontoTaxa;
		TituloCodigoJuros = tituloCodigoJuros;
		TituloDataJuros = tituloDataJuros;
		TituloValorJuros = tituloValorJuros;
		TituloCodigoMulta = tituloCodigoMulta;
		TituloDataMulta = tituloDataMulta;
		TituloValorMultaTaxa = tituloValorMultaTaxa;
		TituloCodProtesto = tituloCodProtesto;
		TituloPrazoProtesto = tituloPrazoProtesto;
		TituloCodBaixaDevolucao = tituloCodBaixaDevolucao;
		TituloPrazoBaixa = tituloPrazoBaixa;
		TituloMensagem01 = tituloMensagem01;
		TituloMensagem02 = tituloMensagem02;
		TituloMensagem03 = tituloMensagem03;
		TituloMensagem04 = tituloMensagem04;
		TituloMensagem05 = tituloMensagem05;
		TituloMensagem06 = tituloMensagem06;
		TituloMensagem07 = tituloMensagem07;
		TituloMensagem08 = tituloMensagem08;
		TituloMensagem09 = tituloMensagem09;
		TituloSacadorAvalista = tituloSacadorAvalista;
		TituloSacadorAvalistaEndereco = tituloSacadorAvalistaEndereco;
		TituloSacadorAvalistaCidade = tituloSacadorAvalistaCidade;
		TituloSacadorAvalistaCEP = tituloSacadorAvalistaCEP;
		TituloSacadorAvalistaUF = tituloSacadorAvalistaUF;
		TituloInscricaoSacadorAvalista = tituloInscricaoSacadorAvalista;
		UrlBoleto = urlBoleto;
		PagamentoValorTaxaCobranca = pagamentoValorTaxaCobranca;
		this.situacao = situacao;
		PagamentoData = pagamentoData;
		PagamentoRealizado = pagamentoRealizado;
		PagamentoValorCredito = pagamentoValorCredito;
		PagamentoValorPago = pagamentoValorPago;
		PagamentoValorAcrescimos = pagamentoValorAcrescimos;
		PagamentoValorDesconto = pagamentoValorDesconto;
		PagamentoDataTaxaBancaria = pagamentoDataTaxaBancaria;
		TituloLinhaDigitavel = tituloLinhaDigitavel;
		TituloCodigoBarras = tituloCodigoBarras;
		TituloParcela = tituloParcela;
		IdIntegracao = idIntegracao;
		TituloOcorrencias = tituloOcorrencias;
	}

	public String getSacadoCPFCNPJ() {
		return SacadoCPFCNPJ;
	}

	public void setSacadoCPFCNPJ(String sacadoCPFCNPJ) {
		SacadoCPFCNPJ = sacadoCPFCNPJ;
	}

	public String getSacadoNome() {
		return SacadoNome;
	}

	public void setSacadoNome(String sacadoNome) {
		SacadoNome = sacadoNome;
	}

	public String getSacadoEnderecoLogradouro() {
		return SacadoEnderecoLogradouro;
	}

	public void setSacadoEnderecoLogradouro(String sacadoEnderecoLogradouro) {
		SacadoEnderecoLogradouro = sacadoEnderecoLogradouro;
	}

	public String getSacadoEnderecoNumero() {
		return SacadoEnderecoNumero;
	}

	public void setSacadoEnderecoNumero(String sacadoEnderecoNumero) {
		SacadoEnderecoNumero = sacadoEnderecoNumero;
	}

	public String getSacadoEnderecoBairro() {
		return SacadoEnderecoBairro;
	}

	public void setSacadoEnderecoBairro(String sacadoEnderecoBairro) {
		SacadoEnderecoBairro = sacadoEnderecoBairro;
	}

	public String getSacadoEnderecoCep() {
		return SacadoEnderecoCep;
	}

	public void setSacadoEnderecoCep(String sacadoEnderecoCep) {
		SacadoEnderecoCep = sacadoEnderecoCep;
	}

	public String getSacadoEnderecoCidade() {
		return SacadoEnderecoCidade;
	}

	public void setSacadoEnderecoCidade(String sacadoEnderecoCidade) {
		SacadoEnderecoCidade = sacadoEnderecoCidade;
	}

	public String getSacadoEnderecoComplemento() {
		return SacadoEnderecoComplemento;
	}

	public void setSacadoEnderecoComplemento(String sacadoEnderecoComplemento) {
		SacadoEnderecoComplemento = sacadoEnderecoComplemento;
	}

	public String getSacadoEnderecoPais() {
		return SacadoEnderecoPais;
	}

	public void setSacadoEnderecoPais(String sacadoEnderecoPais) {
		SacadoEnderecoPais = sacadoEnderecoPais;
	}

	public String getSacadoEnderecoUf() {
		return SacadoEnderecoUf;
	}

	public void setSacadoEnderecoUf(String sacadoEnderecoUf) {
		SacadoEnderecoUf = sacadoEnderecoUf;
	}

	public String getSacadoEmail() {
		return SacadoEmail;
	}

	public void setSacadoEmail(String sacadoEmail) {
		SacadoEmail = sacadoEmail;
	}

	public String getSacadoTelefone() {
		return SacadoTelefone;
	}

	public void setSacadoTelefone(String sacadoTelefone) {
		SacadoTelefone = sacadoTelefone;
	}

	public String getSacadoCelular() {
		return SacadoCelular;
	}

	public void setSacadoCelular(String sacadoCelular) {
		SacadoCelular = sacadoCelular;
	}

	public String getCedenteContaCodigoBanco() {
		return CedenteContaCodigoBanco;
	}

	public void setCedenteContaCodigoBanco(String cedenteContaCodigoBanco) {
		CedenteContaCodigoBanco = cedenteContaCodigoBanco;
	}

	public String getCedenteContaNumero() {
		return CedenteContaNumero;
	}

	public void setCedenteContaNumero(String cedenteContaNumero) {
		CedenteContaNumero = cedenteContaNumero;
	}

	public String getCedenteContaNumeroDV() {
		return CedenteContaNumeroDV;
	}

	public void setCedenteContaNumeroDV(String cedenteContaNumeroDV) {
		CedenteContaNumeroDV = cedenteContaNumeroDV;
	}

	public String getCedenteConvenioNumero() {
		return CedenteConvenioNumero;
	}

	public void setCedenteConvenioNumero(String cedenteConvenioNumero) {
		CedenteConvenioNumero = cedenteConvenioNumero;
	}

	public String getTituloVariacaoCarteira() {
		return TituloVariacaoCarteira;
	}

	public void setTituloVariacaoCarteira(String tituloVariacaoCarteira) {
		TituloVariacaoCarteira = tituloVariacaoCarteira;
	}

	public String getTituloNossoNumero() {
		return TituloNossoNumero;
	}

	public void setTituloNossoNumero(String tituloNossoNumero) {
		TituloNossoNumero = tituloNossoNumero;
	}

	public String getTituloValor() {
		return TituloValor;
	}

	public void setTituloValor(String tituloValor) {
		TituloValor = tituloValor;
	}

	public String getTituloNumeroDocumento() {
		return TituloNumeroDocumento;
	}

	public void setTituloNumeroDocumento(String tituloNumeroDocumento) {
		TituloNumeroDocumento = tituloNumeroDocumento;
	}

	public String getTituloDataEmissao() {
		return TituloDataEmissao;
	}

	public void setTituloDataEmissao(String tituloDataEmissao) {
		TituloDataEmissao = tituloDataEmissao;
	}

	public String getTituloDataVencimento() {
		return TituloDataVencimento;
	}

	public void setTituloDataVencimento(String tituloDataVencimento) {
		TituloDataVencimento = tituloDataVencimento;
	}

	public String getTituloAceite() {
		return TituloAceite;
	}

	public void setTituloAceite(String tituloAceite) {
		TituloAceite = tituloAceite;
	}

	public String getTituloDocEspecie() {
		return TituloDocEspecie;
	}

	public void setTituloDocEspecie(String tituloDocEspecie) {
		TituloDocEspecie = tituloDocEspecie;
	}

	public String getTituloLocalPagamento() {
		return TituloLocalPagamento;
	}

	public void setTituloLocalPagamento(String tituloLocalPagamento) {
		TituloLocalPagamento = tituloLocalPagamento;
	}

	public String getTituloCodDesconto() {
		return TituloCodDesconto;
	}

	public void setTituloCodDesconto(String tituloCodDesconto) {
		TituloCodDesconto = tituloCodDesconto;
	}

	public String getTituloDataDesconto() {
		return TituloDataDesconto;
	}

	public void setTituloDataDesconto(String tituloDataDesconto) {
		TituloDataDesconto = tituloDataDesconto;
	}

	public String getTituloValorDescontoTaxa() {
		return TituloValorDescontoTaxa;
	}

	public void setTituloValorDescontoTaxa(String tituloValorDescontoTaxa) {
		TituloValorDescontoTaxa = tituloValorDescontoTaxa;
	}

	public String getTituloCodigoJuros() {
		return TituloCodigoJuros;
	}

	public void setTituloCodigoJuros(String tituloCodigoJuros) {
		TituloCodigoJuros = tituloCodigoJuros;
	}

	public String getTituloDataJuros() {
		return TituloDataJuros;
	}

	public void setTituloDataJuros(String tituloDataJuros) {
		TituloDataJuros = tituloDataJuros;
	}

	public String getTituloValorJuros() {
		return TituloValorJuros;
	}

	public void setTituloValorJuros(String tituloValorJuros) {
		TituloValorJuros = tituloValorJuros;
	}

	public String getTituloCodigoMulta() {
		return TituloCodigoMulta;
	}

	public void setTituloCodigoMulta(String tituloCodigoMulta) {
		TituloCodigoMulta = tituloCodigoMulta;
	}

	public String getTituloDataMulta() {
		return TituloDataMulta;
	}

	public void setTituloDataMulta(String tituloDataMulta) {
		TituloDataMulta = tituloDataMulta;
	}

	public String getTituloValorMultaTaxa() {
		return TituloValorMultaTaxa;
	}

	public void setTituloValorMultaTaxa(String tituloValorMultaTaxa) {
		TituloValorMultaTaxa = tituloValorMultaTaxa;
	}

	public String getTituloCodProtesto() {
		return TituloCodProtesto;
	}

	public void setTituloCodProtesto(String tituloCodProtesto) {
		TituloCodProtesto = tituloCodProtesto;
	}

	public String getTituloPrazoProtesto() {
		return TituloPrazoProtesto;
	}

	public void setTituloPrazoProtesto(String tituloPrazoProtesto) {
		TituloPrazoProtesto = tituloPrazoProtesto;
	}

	public String getTituloCodBaixaDevolucao() {
		return TituloCodBaixaDevolucao;
	}

	public void setTituloCodBaixaDevolucao(String tituloCodBaixaDevolucao) {
		TituloCodBaixaDevolucao = tituloCodBaixaDevolucao;
	}

	public String getTituloPrazoBaixa() {
		return TituloPrazoBaixa;
	}

	public void setTituloPrazoBaixa(String tituloPrazoBaixa) {
		TituloPrazoBaixa = tituloPrazoBaixa;
	}

	public String getTituloMensagem01() {
		return TituloMensagem01;
	}

	public void setTituloMensagem01(String tituloMensagem01) {
		TituloMensagem01 = tituloMensagem01;
	}

	public String getTituloMensagem02() {
		return TituloMensagem02;
	}

	public void setTituloMensagem02(String tituloMensagem02) {
		TituloMensagem02 = tituloMensagem02;
	}

	public String getTituloMensagem03() {
		return TituloMensagem03;
	}

	public void setTituloMensagem03(String tituloMensagem03) {
		TituloMensagem03 = tituloMensagem03;
	}

	public String getTituloMensagem04() {
		return TituloMensagem04;
	}

	public void setTituloMensagem04(String tituloMensagem04) {
		TituloMensagem04 = tituloMensagem04;
	}

	public String getTituloMensagem05() {
		return TituloMensagem05;
	}

	public void setTituloMensagem05(String tituloMensagem05) {
		TituloMensagem05 = tituloMensagem05;
	}

	public String getTituloMensagem06() {
		return TituloMensagem06;
	}

	public void setTituloMensagem06(String tituloMensagem06) {
		TituloMensagem06 = tituloMensagem06;
	}

	public String getTituloMensagem07() {
		return TituloMensagem07;
	}

	public void setTituloMensagem07(String tituloMensagem07) {
		TituloMensagem07 = tituloMensagem07;
	}

	public String getTituloMensagem08() {
		return TituloMensagem08;
	}

	public void setTituloMensagem08(String tituloMensagem08) {
		TituloMensagem08 = tituloMensagem08;
	}

	public String getTituloMensagem09() {
		return TituloMensagem09;
	}

	public void setTituloMensagem09(String tituloMensagem09) {
		TituloMensagem09 = tituloMensagem09;
	}

	public String getTituloSacadorAvalista() {
		return TituloSacadorAvalista;
	}

	public void setTituloSacadorAvalista(String tituloSacadorAvalista) {
		TituloSacadorAvalista = tituloSacadorAvalista;
	}

	public String getTituloSacadorAvalistaEndereco() {
		return TituloSacadorAvalistaEndereco;
	}

	public void setTituloSacadorAvalistaEndereco(String tituloSacadorAvalistaEndereco) {
		TituloSacadorAvalistaEndereco = tituloSacadorAvalistaEndereco;
	}

	public String getTituloSacadorAvalistaCidade() {
		return TituloSacadorAvalistaCidade;
	}

	public void setTituloSacadorAvalistaCidade(String tituloSacadorAvalistaCidade) {
		TituloSacadorAvalistaCidade = tituloSacadorAvalistaCidade;
	}

	public String getTituloSacadorAvalistaCEP() {
		return TituloSacadorAvalistaCEP;
	}

	public void setTituloSacadorAvalistaCEP(String tituloSacadorAvalistaCEP) {
		TituloSacadorAvalistaCEP = tituloSacadorAvalistaCEP;
	}

	public String getTituloSacadorAvalistaUF() {
		return TituloSacadorAvalistaUF;
	}

	public void setTituloSacadorAvalistaUF(String tituloSacadorAvalistaUF) {
		TituloSacadorAvalistaUF = tituloSacadorAvalistaUF;
	}

	public String getTituloInscricaoSacadorAvalista() {
		return TituloInscricaoSacadorAvalista;
	}

	public void setTituloInscricaoSacadorAvalista(String tituloInscricaoSacadorAvalista) {
		TituloInscricaoSacadorAvalista = tituloInscricaoSacadorAvalista;
	}

	public String getUrlBoleto() {
		return UrlBoleto;
	}

	public void setUrlBoleto(String urlBoleto) {
		UrlBoleto = urlBoleto;
	}

	public String getPagamentoValorTaxaCobranca() {
		return PagamentoValorTaxaCobranca;
	}

	public void setPagamentoValorTaxaCobranca(String pagamentoValorTaxaCobranca) {
		PagamentoValorTaxaCobranca = pagamentoValorTaxaCobranca;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getPagamentoData() {
		return PagamentoData;
	}

	public void setPagamentoData(String pagamentoData) {
		PagamentoData = pagamentoData;
	}

	public boolean isPagamentoRealizado() {
		return PagamentoRealizado;
	}

	public void setPagamentoRealizado(boolean pagamentoRealizado) {
		PagamentoRealizado = pagamentoRealizado;
	}

	public String getPagamentoValorCredito() {
		return PagamentoValorCredito;
	}

	public void setPagamentoValorCredito(String pagamentoValorCredito) {
		PagamentoValorCredito = pagamentoValorCredito;
	}

	public String getPagamentoValorPago() {
		return PagamentoValorPago;
	}

	public void setPagamentoValorPago(String pagamentoValorPago) {
		PagamentoValorPago = pagamentoValorPago;
	}

	public String getPagamentoValorAcrescimos() {
		return PagamentoValorAcrescimos;
	}

	public void setPagamentoValorAcrescimos(String pagamentoValorAcrescimos) {
		PagamentoValorAcrescimos = pagamentoValorAcrescimos;
	}

	public String getPagamentoValorDesconto() {
		return PagamentoValorDesconto;
	}

	public void setPagamentoValorDesconto(String pagamentoValorDesconto) {
		PagamentoValorDesconto = pagamentoValorDesconto;
	}

	public String getPagamentoDataTaxaBancaria() {
		return PagamentoDataTaxaBancaria;
	}

	public void setPagamentoDataTaxaBancaria(String pagamentoDataTaxaBancaria) {
		PagamentoDataTaxaBancaria = pagamentoDataTaxaBancaria;
	}

	public String getTituloLinhaDigitavel() {
		return TituloLinhaDigitavel;
	}

	public void setTituloLinhaDigitavel(String tituloLinhaDigitavel) {
		TituloLinhaDigitavel = tituloLinhaDigitavel;
	}

	public String getTituloCodigoBarras() {
		return TituloCodigoBarras;
	}

	public void setTituloCodigoBarras(String tituloCodigoBarras) {
		TituloCodigoBarras = tituloCodigoBarras;
	}

	public String getTituloParcela() {
		return TituloParcela;
	}

	public void setTituloParcela(String tituloParcela) {
		TituloParcela = tituloParcela;
	}

	public String getIdIntegracao() {
		return IdIntegracao;
	}

	public void setIdIntegracao(String idIntegracao) {
		IdIntegracao = idIntegracao;
	}

	public List<TecnoRetornoBoletoDados> getTituloOcorrencias() {
		return TituloOcorrencias;
	}

	public void setTituloOcorrencias(List<TecnoRetornoBoletoDados> tituloOcorrencias) {
		TituloOcorrencias = tituloOcorrencias;
	}
}
