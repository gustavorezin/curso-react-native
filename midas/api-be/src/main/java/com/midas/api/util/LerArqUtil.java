package com.midas.api.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.midas.api.mt.entity.Tenant;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class LerArqUtil {
	// Caminho - destino
	public static String ler(String caminho) throws IOException {
		String ret = "";
		Path p = PathCamUtil.cam(caminho);
		List<String> linhasArquivo = Files.readAllLines(p);
		for (String linha : linhasArquivo) {
			ret += linha;
		}
		return ret;
	}

	// Cria conteudo em um arquivo
	public static File criaConteudoFile(String nome, String conteudo, String formato) throws IOException {
		File temp = File.createTempFile(nome, "." + formato);
		FileWriter fw = new FileWriter(temp);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(conteudo);
		bw.close();
		return temp;
	}

	// Cria PDF arquivo
	public static File criaFilePDF(String nome, JasperPrint conteudo, String formato) throws Exception {
		byte[] pdf = JasperExportManager.exportReportToPdf(conteudo);
		File temp = File.createTempFile(nome, "." + formato);
		OutputStream os = new FileOutputStream(temp);
		os.write(pdf);
		os.close();
		return temp;
	}

	// Transforma Multipart do input file em File
	public static File multipartToFile(MultipartFile multipart, String fileName)
			throws IllegalStateException, IOException {
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipart.transferTo(convFile);
		return convFile;
	}

	// Cria pastas padroes se nao houver
	public static void criaPastaPadrao(Tenant tn) throws IOException {
		// Backups
		Path pathBk = Paths.get(tn.getBackupfd());
		Files.createDirectories(pathBk);
		// Arq. temporarios
		Path pathTmp = Paths.get(tn.getTempfd());
		Files.createDirectories(pathTmp);
	}

	// CONVERTE CLASSE PARA JSON
	public static String setJsonString(Object content) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(content);
			return jsonInString;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	// LER ARQUIVO INPUT STREAM
	public static String retIS(InputStream inputStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		for (int length; (length = inputStream.read(buffer)) != -1;) {
			result.write(buffer, 0, length);
		}
		// StandardCharsets.UTF_8.name() > JDK 7
		return result.toString("UTF-8");
	}
}
