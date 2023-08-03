package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoRetornoDadosList implements Serializable {
	private static final long serialVersionUID = 1L;
	// Erros
	private String _campo;
	private String _erro;
	// Padroes
	private String id;
	private String situacao;
	private boolean ativo;
	// Cedente
	private String cpf_cnpj;
	private String token_cedente;
	private String id_software_house;
	// Conta
	private String codigo_banco;
	private String agencia;
	private String agencia_dv;
	private String conta;
	private String CedenteConta;
	private String conta_dv;
	private String tipo_conta;
	private String cod_beneficiario;
	private String cod_empresa;
	private boolean validacao_ativa;
	private boolean impressao_atualizada;
	private boolean impressao_atualizada_alteracao;
	// Convenio
	private List<TecnoRetornoDadosConvenioList> convenios;
	private String numero_convenio;
	private String descricao_convenio;
	private String carteira;
	private String especie;
	private String id_conta;
	private String padraoCNAB;
	private boolean utiliza_van;
	private boolean reiniciar_diariamente;
	private String numero_remessa;
	// Boletos
	private String IdIntegracao;
	private String CedenteCodigoBanco;
	private String SacadoNome;
	private String SacadoTelefone;
	private String TituloDataEmissao;
	private String TituloDataVencimento;
	private String TituloNossoNumero;
	private String TituloNumeroDocumento;
	private boolean PagamentoRealizado;
	private String TituloValorJuros;// Taxa definida na remessa
	private String TituloValorDesconto;
	private String TituloValorDescontoTaxa;
	private String TituloValorMulta;
	private String TituloValorMultaTaxa; // Taxa definida na remessa
	private String PagamentoValorPago;
	private String PagamentoDataTaxaBancaria;
	private String PagamentoValorAcrescimos;
	private String PagamentoValorTaxaCobranca; // Taxa por boleto banco cobra
	private String TituloValor;
	private String TituloLinhaDigitavel;
	private String TituloCodigoBarras;
	private String TituloNossoNumeroImpressao;
	private String UrlBoleto;
	private String PagamentoData;
	private List<TecnoRetornoBoletoDados> TituloOcorrencias;

	public TecnoRetornoDadosList() {
		super();
	}

	public TecnoRetornoDadosList(String _campo, String _erro, String id, String situacao, boolean ativo,
			String cpf_cnpj, String token_cedente, String id_software_house, String codigo_banco, String agencia,
			String agencia_dv, String conta, String cedenteConta, String conta_dv, String tipo_conta,
			String cod_beneficiario, String cod_empresa, boolean validacao_ativa, boolean impressao_atualizada,
			boolean impressao_atualizada_alteracao, List<TecnoRetornoDadosConvenioList> convenios,
			String numero_convenio, String descricao_convenio, String carteira, String especie, String id_conta,
			String padraoCNAB, boolean utiliza_van, boolean reiniciar_diariamente, String numero_remessa,
			String idIntegracao, String cedenteCodigoBanco, String sacadoNome, String sacadoTelefone,
			String tituloDataEmissao, String tituloDataVencimento, String tituloNossoNumero,
			String tituloNumeroDocumento, boolean pagamentoRealizado, String tituloValorJuros,
			String tituloValorDesconto, String tituloValorDescontoTaxa, String tituloValorMulta,
			String tituloValorMultaTaxa, String pagamentoValorPago, String pagamentoDataTaxaBancaria,
			String pagamentoValorAcrescimos, String pagamentoValorTaxaCobranca, String tituloValor,
			String tituloLinhaDigitavel, String tituloCodigoBarras, String tituloNossoNumeroImpressao, String urlBoleto,
			String pagamentoData, List<TecnoRetornoBoletoDados> tituloOcorrencias) {
		super();
		this._campo = _campo;
		this._erro = _erro;
		this.id = id;
		this.situacao = situacao;
		this.ativo = ativo;
		this.cpf_cnpj = cpf_cnpj;
		this.token_cedente = token_cedente;
		this.id_software_house = id_software_house;
		this.codigo_banco = codigo_banco;
		this.agencia = agencia;
		this.agencia_dv = agencia_dv;
		this.conta = conta;
		CedenteConta = cedenteConta;
		this.conta_dv = conta_dv;
		this.tipo_conta = tipo_conta;
		this.cod_beneficiario = cod_beneficiario;
		this.cod_empresa = cod_empresa;
		this.validacao_ativa = validacao_ativa;
		this.impressao_atualizada = impressao_atualizada;
		this.impressao_atualizada_alteracao = impressao_atualizada_alteracao;
		this.convenios = convenios;
		this.numero_convenio = numero_convenio;
		this.descricao_convenio = descricao_convenio;
		this.carteira = carteira;
		this.especie = especie;
		this.id_conta = id_conta;
		this.padraoCNAB = padraoCNAB;
		this.utiliza_van = utiliza_van;
		this.reiniciar_diariamente = reiniciar_diariamente;
		this.numero_remessa = numero_remessa;
		IdIntegracao = idIntegracao;
		CedenteCodigoBanco = cedenteCodigoBanco;
		SacadoNome = sacadoNome;
		SacadoTelefone = sacadoTelefone;
		TituloDataEmissao = tituloDataEmissao;
		TituloDataVencimento = tituloDataVencimento;
		TituloNossoNumero = tituloNossoNumero;
		TituloNumeroDocumento = tituloNumeroDocumento;
		PagamentoRealizado = pagamentoRealizado;
		TituloValorJuros = tituloValorJuros;
		TituloValorDesconto = tituloValorDesconto;
		TituloValorDescontoTaxa = tituloValorDescontoTaxa;
		TituloValorMulta = tituloValorMulta;
		TituloValorMultaTaxa = tituloValorMultaTaxa;
		PagamentoValorPago = pagamentoValorPago;
		PagamentoDataTaxaBancaria = pagamentoDataTaxaBancaria;
		PagamentoValorAcrescimos = pagamentoValorAcrescimos;
		PagamentoValorTaxaCobranca = pagamentoValorTaxaCobranca;
		TituloValor = tituloValor;
		TituloLinhaDigitavel = tituloLinhaDigitavel;
		TituloCodigoBarras = tituloCodigoBarras;
		TituloNossoNumeroImpressao = tituloNossoNumeroImpressao;
		UrlBoleto = urlBoleto;
		PagamentoData = pagamentoData;
		TituloOcorrencias = tituloOcorrencias;
	}

	public String get_campo() {
		return _campo;
	}

	public void set_campo(String _campo) {
		this._campo = _campo;
	}

	public String get_erro() {
		return _erro;
	}

	public void set_erro(String _erro) {
		this._erro = _erro;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getToken_cedente() {
		return token_cedente;
	}

	public void setToken_cedente(String token_cedente) {
		this.token_cedente = token_cedente;
	}

	public String getId_software_house() {
		return id_software_house;
	}

	public void setId_software_house(String id_software_house) {
		this.id_software_house = id_software_house;
	}

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getAgencia_dv() {
		return agencia_dv;
	}

	public void setAgencia_dv(String agencia_dv) {
		this.agencia_dv = agencia_dv;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCedenteConta() {
		return CedenteConta;
	}

	public void setCedenteConta(String cedenteConta) {
		CedenteConta = cedenteConta;
	}

	public String getConta_dv() {
		return conta_dv;
	}

	public void setConta_dv(String conta_dv) {
		this.conta_dv = conta_dv;
	}

	public String getTipo_conta() {
		return tipo_conta;
	}

	public void setTipo_conta(String tipo_conta) {
		this.tipo_conta = tipo_conta;
	}

	public String getCod_beneficiario() {
		return cod_beneficiario;
	}

	public void setCod_beneficiario(String cod_beneficiario) {
		this.cod_beneficiario = cod_beneficiario;
	}

	public String getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}

	public boolean isValidacao_ativa() {
		return validacao_ativa;
	}

	public void setValidacao_ativa(boolean validacao_ativa) {
		this.validacao_ativa = validacao_ativa;
	}

	public boolean isImpressao_atualizada() {
		return impressao_atualizada;
	}

	public void setImpressao_atualizada(boolean impressao_atualizada) {
		this.impressao_atualizada = impressao_atualizada;
	}

	public boolean isImpressao_atualizada_alteracao() {
		return impressao_atualizada_alteracao;
	}

	public void setImpressao_atualizada_alteracao(boolean impressao_atualizada_alteracao) {
		this.impressao_atualizada_alteracao = impressao_atualizada_alteracao;
	}

	public List<TecnoRetornoDadosConvenioList> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<TecnoRetornoDadosConvenioList> convenios) {
		this.convenios = convenios;
	}

	public String getNumero_convenio() {
		return numero_convenio;
	}

	public void setNumero_convenio(String numero_convenio) {
		this.numero_convenio = numero_convenio;
	}

	public String getDescricao_convenio() {
		return descricao_convenio;
	}

	public void setDescricao_convenio(String descricao_convenio) {
		this.descricao_convenio = descricao_convenio;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getId_conta() {
		return id_conta;
	}

	public void setId_conta(String id_conta) {
		this.id_conta = id_conta;
	}

	public String getPadraoCNAB() {
		return padraoCNAB;
	}

	public void setPadraoCNAB(String padraoCNAB) {
		this.padraoCNAB = padraoCNAB;
	}

	public boolean isUtiliza_van() {
		return utiliza_van;
	}

	public void setUtiliza_van(boolean utiliza_van) {
		this.utiliza_van = utiliza_van;
	}

	public boolean isReiniciar_diariamente() {
		return reiniciar_diariamente;
	}

	public void setReiniciar_diariamente(boolean reiniciar_diariamente) {
		this.reiniciar_diariamente = reiniciar_diariamente;
	}

	public String getNumero_remessa() {
		return numero_remessa;
	}

	public void setNumero_remessa(String numero_remessa) {
		this.numero_remessa = numero_remessa;
	}

	public String getIdIntegracao() {
		return IdIntegracao;
	}

	public void setIdIntegracao(String idIntegracao) {
		IdIntegracao = idIntegracao;
	}

	public String getCedenteCodigoBanco() {
		return CedenteCodigoBanco;
	}

	public void setCedenteCodigoBanco(String cedenteCodigoBanco) {
		CedenteCodigoBanco = cedenteCodigoBanco;
	}

	public String getSacadoNome() {
		return SacadoNome;
	}

	public void setSacadoNome(String sacadoNome) {
		SacadoNome = sacadoNome;
	}

	public String getSacadoTelefone() {
		return SacadoTelefone;
	}

	public void setSacadoTelefone(String sacadoTelefone) {
		SacadoTelefone = sacadoTelefone;
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

	public String getTituloNossoNumero() {
		return TituloNossoNumero;
	}

	public void setTituloNossoNumero(String tituloNossoNumero) {
		TituloNossoNumero = tituloNossoNumero;
	}

	public String getTituloNumeroDocumento() {
		return TituloNumeroDocumento;
	}

	public void setTituloNumeroDocumento(String tituloNumeroDocumento) {
		TituloNumeroDocumento = tituloNumeroDocumento;
	}

	public boolean isPagamentoRealizado() {
		return PagamentoRealizado;
	}

	public void setPagamentoRealizado(boolean pagamentoRealizado) {
		PagamentoRealizado = pagamentoRealizado;
	}

	public String getTituloValorJuros() {
		return TituloValorJuros;
	}

	public void setTituloValorJuros(String tituloValorJuros) {
		TituloValorJuros = tituloValorJuros;
	}

	public String getTituloValorDesconto() {
		return TituloValorDesconto;
	}

	public void setTituloValorDesconto(String tituloValorDesconto) {
		TituloValorDesconto = tituloValorDesconto;
	}

	public String getTituloValorDescontoTaxa() {
		return TituloValorDescontoTaxa;
	}

	public void setTituloValorDescontoTaxa(String tituloValorDescontoTaxa) {
		TituloValorDescontoTaxa = tituloValorDescontoTaxa;
	}

	public String getTituloValorMulta() {
		return TituloValorMulta;
	}

	public void setTituloValorMulta(String tituloValorMulta) {
		TituloValorMulta = tituloValorMulta;
	}

	public String getTituloValorMultaTaxa() {
		return TituloValorMultaTaxa;
	}

	public void setTituloValorMultaTaxa(String tituloValorMultaTaxa) {
		TituloValorMultaTaxa = tituloValorMultaTaxa;
	}

	public String getPagamentoValorPago() {
		return PagamentoValorPago;
	}

	public void setPagamentoValorPago(String pagamentoValorPago) {
		PagamentoValorPago = pagamentoValorPago;
	}

	public String getPagamentoDataTaxaBancaria() {
		return PagamentoDataTaxaBancaria;
	}

	public void setPagamentoDataTaxaBancaria(String pagamentoDataTaxaBancaria) {
		PagamentoDataTaxaBancaria = pagamentoDataTaxaBancaria;
	}

	public String getPagamentoValorAcrescimos() {
		return PagamentoValorAcrescimos;
	}

	public void setPagamentoValorAcrescimos(String pagamentoValorAcrescimos) {
		PagamentoValorAcrescimos = pagamentoValorAcrescimos;
	}

	public String getPagamentoValorTaxaCobranca() {
		return PagamentoValorTaxaCobranca;
	}

	public void setPagamentoValorTaxaCobranca(String pagamentoValorTaxaCobranca) {
		PagamentoValorTaxaCobranca = pagamentoValorTaxaCobranca;
	}

	public String getTituloValor() {
		return TituloValor;
	}

	public void setTituloValor(String tituloValor) {
		TituloValor = tituloValor;
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

	public String getTituloNossoNumeroImpressao() {
		return TituloNossoNumeroImpressao;
	}

	public void setTituloNossoNumeroImpressao(String tituloNossoNumeroImpressao) {
		TituloNossoNumeroImpressao = tituloNossoNumeroImpressao;
	}

	public String getUrlBoleto() {
		return UrlBoleto;
	}

	public void setUrlBoleto(String urlBoleto) {
		UrlBoleto = urlBoleto;
	}

	public String getPagamentoData() {
		return PagamentoData;
	}

	public void setPagamentoData(String pagamentoData) {
		PagamentoData = pagamentoData;
	}

	public List<TecnoRetornoBoletoDados> getTituloOcorrencias() {
		return TituloOcorrencias;
	}

	public void setTituloOcorrencias(List<TecnoRetornoBoletoDados> tituloOcorrencias) {
		TituloOcorrencias = tituloOcorrencias;
	}
}