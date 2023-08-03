package com.midas.api.constant;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class MidasConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	// DB
	public final String DbName = "midas";
	public final String Url = "jdbc:mysql://localhost:3306/";
	public final String Username = "root";
	public final String Password = "unisul.123";
	public final String Driverclass = "com.mysql.cj.jdbc.Driver";
	// public final String MysqlDump =
	// "/usr/local/mysql-8.0.27-macos11-arm64/bin/mysqldump";
	// public final String BackupFd = "/Volumes/Dados/";
	public final String MysqlDump = "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump";
	public final String BackupFd = "C:/Backups/";
	public final String TempFd = "C:/Backups/temps/";
	// OUTROS
	public final String MidasApresenta = "Midas Software LTDA";
	public final String MidasSite = "www.midassi.com";
	public final String MidasVersao = "8.0";
	public final String MidasCNPJ = "33330605000108";
	public final String MidasFone = "4834662331";
	// EMAIL
	public final String NrEmail = "suporte@midassi.com";
	public final String NrLogin = "suporte@midassi.com";
	public final String NrSenha = "W#ab@uni.745";
	public final String NrSmtp = "email-ssl.com.br";
	public final String NrRequeraut = "true";
	public final String NrSslsmtp = "false";
	public final String NrPortastmp = "587";
	public final String NrTipo = "OUTROS";
	// FISCAL
	public final Integer tempobuscaman = 60; // Tempo busca manifesto
	public final String xusocartacorrecao = "A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.";
	// INTEGRACAO TENOSPEDD - BOLETOS
	public final String tokenTecno = "2ffc34c3d9267202960263bf66b98005";
	public final String linkTecnoHom = "https://homologacao.plugboleto.com.br/api/v1/";
	public final String linkTecnoProd = "https://plugboleto.com.br/api/v1/";
}
