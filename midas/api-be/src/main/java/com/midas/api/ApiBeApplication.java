package com.midas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiBeApplication.class, args);
	}
}
/*
 * 
 * INSTRUCOES
 * 
 * - Para ANGULAR
 * 
 * 1 - Utilizar este comando dentro da pasta Front-End: ng build --configuration
 * production 2 - Na pasta Dist criada, certificar que o nome e o mesmo, neste
 * caso: angular 3 - na pasta Dist, campo base, deixar apenas "." 4 - base do
 * Servidor exemplo: return 'http://192.168.15.40:8070/api';
 * 
 * 
 * - Para Rest Api - Application
 * 
 * 1 - Botao direto projeto 2 - Maven Clean 3 - Maven Install
 * 
 * Obs.: Projeto original com java 11, alterado para 8 Se nao fizer deploy
 * automatico, entrar pelo tomcat, selecionar arquivo War e apos, deploy
 * 
 * 
 */