package com.midas.api.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.TenantRepository;

@Service
public class DataBaseBackupUtil {
	@Autowired
	private TenantRepository tenantRp;
	private static final Logger log = LoggerFactory.getLogger(DataBaseBackupUtil.class);
	private static final CryptPassUtil cryptPass = new CryptPassUtil();

	public void backupApp(String database, String forcarBkp) throws Exception {
		Tenant tn = tenantRp.findByDbname(database);
		// VERIFICA SE JA FEITO
		if (tenantRp.getBackupUlt(tn.getId()) == 0 || forcarBkp.equals("S")) {
			// sql file
			Path sqlFile = Paths.get(tn.getBackupfd() + tn.getDbname() + ".sql");
			// stdErr
			ByteArrayOutputStream stdErr = new ByteArrayOutputStream();
			// stdOut
			OutputStream stdOut = new BufferedOutputStream(
					Files.newOutputStream(sqlFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
			try {
				// Watchdog. Tempo de execucao de 3 minutos
				ExecuteWatchdog watchdog = new ExecuteWatchdog(TimeUnit.MINUTES.toMillis(3));
				DefaultExecutor defaultExecutor = new DefaultExecutor();
				defaultExecutor.setWatchdog(watchdog);
				defaultExecutor.setStreamHandler(new PumpStreamHandler(stdOut, stdErr));
				CommandLine commandLine = new CommandLine(tn.getMdump());
				commandLine.addArgument("-u" + cryptPass.dBase64(tn.getUsername()));
				commandLine.addArgument("-p" + cryptPass.dBase64(tn.getPassword()));
				commandLine.addArgument("--default-character-set=utf8");
				commandLine.addArgument("--skip-triggers");
				commandLine.addArgument(tn.getDbname());
				log.info("Exportando banco SQL para: " + tn.getDbname());
				// Synchronous execution. Blocking until the execution of the child process is
				// complete.
				int exitCode = defaultExecutor.execute(commandLine);
				// Zipando
				ZipUtil.zipFile(sqlFile.toString(), tn.getBackupfd(), tn.getDbname());
				// Atualiza informacao
				tenantRp.updateBkpUlt(tn.getId());
				if (defaultExecutor.isFailure(exitCode) && watchdog.killedProcess()) {
					System.out.println("Erro: Tempo limite excedido!");
				}
				log.info("Dados de banco SQL completo: exitCode={}, sqlFile={}", exitCode, sqlFile.toString());
			} catch (Exception e) {
				log.error("Exception: SQL erro : {}", e.getMessage());
				log.error("Erro: STD: {}{}", System.lineSeparator(), stdErr.toString());
			}
		}
	}
}
