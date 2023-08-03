package com.midas.api.util;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.midas.api.tenant.entity.ReceitaWSCNPJ;

public class CPFConsUtil {
	public static String[] getCaptcha() throws Exception {
		String[] retorno = new String[2];
		/* Iremos criar uma conexão com a receita federal */
		Connection conn = Jsoup.connect(
				"https://servicos.receita.fazenda.gov.br/Servicos/CPF/ConsultaSituacao/ConsultaPublicaSonoro.asp")
				.validateTLSCertificates(false).followRedirects(true).timeout(1000).method(Connection.Method.GET);
		Connection.Response r = conn.execute();
		Document doc = Jsoup.parse(r.body());
		Element el = doc.getElementById("imgCaptcha");
		String base = el.attr("src");
		String bas = base.subSequence(base.indexOf(",") + 1, base.length()).toString();
		String[] it = r.cookies().toString().substring(1, (r.cookies().toString().length() - 1)).split(",");
		Map<String, String> cookies = new HashMap<>();
		for (int i = 0; i < it.length; i++) {
			String[] res = it[i].split("=");
			cookies.put(res[0], res[1]);
		}
		retorno[0] = bas;
		retorno[1] = cookies.toString();
		return retorno;
	}

	public static ReceitaWSCNPJ consulta(String cpf, String nasc, String captcha, String cookiesresp) throws Exception {
		Map<String, String> cookies = new HashMap<String, String>();
		String[] it = cookiesresp.substring(1, (cookiesresp.length() - 1)).split(",");
		for (int i = 0; i < it.length; i++) {
			String[] res = it[i].split("=");
			cookies.put(res[0], res[1]);
		}
		Document d = Jsoup.connect(
				"https://servicos.receita.fazenda.gov.br/servicos/cpf/consultasituacao/ConsultaPublicaExibir.asp")
				.data("idCheckedReCaptcha", "false").data("txtCPF", cpf).data("txtDataNascimento", nasc)
				/* O captcha deve ser respondido */
				.data("txtTexto_captcha_serpro_gov_br", captcha).data("enviar", "Consultar")
				.validateTLSCertificates(false).cookies(cookies).post();
		Document resposta = Jsoup.parse(d.body().toString());
		Elements els = resposta.getElementsByClass("clConteudoDados");
		ReceitaWSCNPJ obj = new ReceitaWSCNPJ();
		obj.setCnpj(els.get(0).text());
		obj.setNome(els.get(1).text());
		obj.setStatus(els.get(3).text());
		obj.setAbertura(els.get(4).text());
		return obj;
	}
}
