package com.midas.api.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DataUtil {
	// VERIFICA DATA DA SEMANA
	public static int isSOuD(GregorianCalendar gc) {
		int diaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
		// 1 Domingo
		// 7 Sabado
		return diaSemana;
	}

	// FORMATO PADRAO BRASIL
	public static String dataPadraoBr(Date data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return formato.format(calendar.getTime());
	}

	// FORMATO PADRAO BRASIL
	public static String dataPadraoBrContra(Date data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return formato.format(calendar.getTime());
	}

	// FORMATO PADRAO BRASIL SEM BARRAS
	public static String dataPadraoBrSemBarra(Date data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return formato.format(calendar.getTime());
	}

	// FORMATO PADRAO SQL DATA HORA
	public static String dataPadraoSQLDataHora(Date data, Date hora) throws ParseException {
		// PEGANDO TIMEZONE DA APLICACAO
		Date d = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("Z");
		String localidade = formato.format(d).toString();
		StringBuilder formaHoraZona = new StringBuilder(localidade);
		formaHoraZona.insert(localidade.length() - 2, ':');
		return data + "T" + hora + formaHoraZona.toString();
	}

	// FORMATO PADRAO SQL
	public static String dataPadraoSQL(Date data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return formato.format(calendar.getTime());
	}

	// FORMATO PADRAO SQL
	public static String horaPadraoSQL(Date data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return formato.format(calendar.getTime());
	}

	// STRING PARA DATE UTIL
	public static Date dUtil(String minhadata) throws ParseException {
		Date novadata = new Date();
		if (minhadata != null) {
			novadata = new SimpleDateFormat("yyyy-MM-dd").parse(minhadata);
		}
		return novadata;
	}

	// STRING PARA DATE UTIL - HORA
	public static Date hUtil(String minhahora) throws ParseException {
		Date novahora = new Date();
		if (minhahora != null) {
			novahora = new SimpleDateFormat("HH:mm:ss").parse(minhahora);
		}
		return novahora;
	}

	// DATA BANCO DE DADOS
	public static String dataBancoAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");
		return formatarDate.format(data);
	}

	// MES ATUAL
	public static String mesAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("MM");
		return formatarDate.format(data);
	}

	// ANO ATUAL
	public static String anoAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy");
		return formatarDate.format(data);
	}

	// ANO E MES
	public static String anoMesAAMM(Date data) {
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyMM");
		return formatarDate.format(data);
	}

	// ANO E MES ATUAL
	public static String anoMesAtualAAMM() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyMM");
		return formatarDate.format(data);
	}

	// DATA BANCO DE DADOS RETORNO STRING TIME
	public static String dataBancoTimeAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("HH:mm:ss");
		return formatarDate.format(data);
	}

	// ADD REM MESES
	public static Date addRemMeses(Date data, int qtd, String addrem) {
		Date ret = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		if (addrem.equals("A")) {
			gc.add(Calendar.MONTH, qtd);
			ret = gc.getTime();
		}
		if (addrem.equals("R")) {
			gc.add(Calendar.MONTH, -qtd);
			ret = gc.getTime();
		}
		return ret;
	}

	// ADD REM DIAS
	public static Date addRemDias(Date data, int qtd, String addrem) {
		Date ret = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		if (addrem.equals("A")) {
			gc.add(Calendar.DAY_OF_MONTH, qtd);
			ret = gc.getTime();
		}
		if (addrem.equals("R")) {
			gc.add(Calendar.DAY_OF_MONTH, -qtd);
			ret = gc.getTime();
		}
		return ret;
	}

	// ADD REM HORA
	public static Date addRemHora(Date hora, int qtd, String addrem) {
		Date ret = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(hora);
		if (addrem.equals("A")) {
			gc.add(Calendar.HOUR, qtd);
			ret = gc.getTime();
		}
		if (addrem.equals("R")) {
			gc.add(Calendar.HOUR, -qtd);
			ret = gc.getTime();
		}
		return ret;
	}

	// ADD REM HORA DATE
	public static Date addRemMin(Date hora, int qtd, String addrem) {
		Date ret = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(hora);
		if (addrem.equals("A")) {
			gc.add(Calendar.MINUTE, qtd);
			ret = gc.getTime();
		}
		if (addrem.equals("R")) {
			gc.add(Calendar.MINUTE, -qtd);
			ret = gc.getTime();
		}
		return ret;
	}

	// ADD REM HORA TIME
	public static Time addRemMinTime(Time hora, int qtd, String addrem) {
		LocalTime localtime = hora.toLocalTime();
		Time ret = hora;
		if (addrem.equals("A")) {
			localtime = localtime.plusMinutes(qtd);
		}
		if (addrem.equals("R")) {
			localtime = localtime.minusMinutes(qtd);
		}
		ret = Time.valueOf(localtime);
		return ret;
	}

	// FUNC. DATA COM TIME ZONE
	public static String dtTim(String dt, String tm) {
		String retorno = "";
		// PEGANDO TIMEZONE DA APLICACAO
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("Z");
		String localidade = sd.format(d).toString();
		StringBuilder formaHoraZona = new StringBuilder(localidade);
		formaHoraZona.insert(localidade.length() - 2, ':');
		retorno = dt + "T" + tm + formaHoraZona.toString();
		return retorno;
	}

	// ULTIMO DIA DO MES
	public static String ultimoDiaMes(Date data) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date ultimoDiaMesData = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(ultimoDiaMesData);
	}

	// FUNC. XML GREGORIAN CALENDAR
	public static XMLGregorianCalendar dTimG(String data) throws DatatypeConfigurationException {
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(data);
		return xmlGregorianCalendar;
	}

	// CONVERTE DATA PADRA BR PARA SQL
	public static Date brToSql(String minhadata) throws ParseException {
		Date novadata = new Date();
		if (minhadata != null) {
			minhadata = minhadata.replace("/", "");
			minhadata = minhadata.replace("00:00:00", "");
			minhadata = minhadata.trim();
			// Inverte
			String ano = minhadata.substring(4, 8);
			String mes = minhadata.substring(2, 4);
			String dia = minhadata.substring(0, 2);
			String novoajuste = ano + "-" + mes + "-" + dia;
			novadata = new SimpleDateFormat("yyyy-MM-dd").parse(novoajuste);
		}
		return novadata;
	}

	// CONVERTE DATA PADRA BR PARA SQL
	public static String brToSqlString(String minhadata) throws ParseException {
		String novoajuste = minhadata;
		if (minhadata != null) {
			minhadata = minhadata.replace("/", "");
			minhadata = minhadata.replace("00:00:00", "");
			minhadata = minhadata.trim();
			// Inverte
			String ano = minhadata.substring(4, 8);
			String mes = minhadata.substring(2, 4);
			String dia = minhadata.substring(0, 2);
			novoajuste = ano + "-" + mes + "-" + dia;
		}
		return novoajuste;
	}

	// COMPARAR DUAS DATAS
	public static boolean comparaDataAtualMenor(Date dataCompara) throws Exception {
		boolean retorno = false;
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse(dataCompara + "");
		Date d2 = sdformat.parse(new java.sql.Date(System.currentTimeMillis()) + "");
		if (d1.compareTo(d2) < 0) {
			retorno = true;
		}
		return retorno;
	}
}
