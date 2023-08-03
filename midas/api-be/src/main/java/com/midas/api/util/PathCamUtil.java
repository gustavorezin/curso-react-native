package com.midas.api.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathCamUtil {
	public static Path cam(String caminho) {
		// Primeiro utilize o Path para localizar o arquivo
		Path path = Paths.get(caminho);
		return path;
	}
}
