package com.midas.api.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;

import com.midas.api.constant.MidasConfig;
import com.midas.api.controller.HomeController;
import com.midas.api.mt.config.DBContextHolder;

public class SQLExecUtil {
	// Executa qualquer SQL atraves do seu caminho de arquivo - usa conexao manual
	public static void executeSqlScript(File inputFile, String banco, MidasConfig pr) throws FileNotFoundException {
		ConnExtraUtil con = new ConnExtraUtil();
		Connection conn = con.getConexao(banco, pr);
		ScriptRunnerUtil runner = new ScriptRunnerUtil(conn);
		runner.runScript(new BufferedReader(new FileReader(inputFile)));
	}

	// Executa qualquer SQL atraves do seu caminho de arquivo
	public static void executarSqlPadrao(String caminho, MidasConfig mc) throws FileNotFoundException {
		ClassLoader classLoader = HomeController.class.getClassLoader();
		URL resource = classLoader.getResource(caminho);
		File file = new File(resource.getFile());
		SQLExecUtil.executeSqlScript(file, DBContextHolder.getCurrentDb(), mc);
	}
}