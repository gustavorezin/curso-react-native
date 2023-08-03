package com.midas.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.midas.api.tenant.entity.AuxDados;

public class ZipUtil {
	// ARQUIVO UNICO
	public static void zipFile(String sourceFile, String cam, String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(cam + filename + ".zip");
			ZipOutputStream zipOut = new ZipOutputStream(fos);
			File fileToZip = new File(sourceFile);
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			zipOut.close();
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// MULTIPLOS ARQUIVOS ZIP
	public static void zipFiles(String nome, String cam, List<AuxDados> arquivos, String formato) throws IOException {
		final FileOutputStream fos = new FileOutputStream(cam + nome);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		for (AuxDados srcFile : arquivos) {
			File fileToZip = new File(srcFile.getCampo2());
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			fis.close();
			// Remove arquivo
			fileToZip.delete();
		}
		zipOut.close();
		fos.close();
	}
}
