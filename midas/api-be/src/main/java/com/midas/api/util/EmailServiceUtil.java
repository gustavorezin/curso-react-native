package com.midas.api.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Service;

import com.midas.api.tenant.entity.AuxDados;
import com.midas.api.tenant.entity.AuxDadosByte;
import com.midas.api.tenant.entity.AuxEmail;

@Service
public class EmailServiceUtil {
	/*
	 * 
	 * Destinatarios podem ser:
	 * 
	 * + "vendas@midassi.com,daniel_redivo@hotmail.com,suporte@midassi.com"
	 * 
	 */
	public void EnviaHTML(String destinatarios, String assunto, String conteudo, List<AuxDados> listaAnexo,
			List<AuxDadosByte> listaAnexoBytes, AuxEmail rem) throws UnsupportedEncodingException, MessagingException {
		// DADOS DO EMAIL ENVIO
		String remetente = rem.getRemetente();
		String email = rem.getEmail();
		String senha = rem.getSenha();
		String smtp = rem.getSmtp();
		String requeraut = rem.getRequeraut();
		String sslsmtp = rem.getSslsmtp();
		String portastmp = rem.getPortastmp();
		String tipo = rem.getTipo();
		Properties props = new Properties();
		// SERVIDOR REQUER AUTENTICACAO ?
		String autentica = "";
		if (requeraut.equals("true") || requeraut.equals("S")) {
			autentica = "true";
		} else {
			autentica = "false";
		}
		String sslSmtp = "";
		if (sslsmtp.equals("true") || sslsmtp.equals("S")) {
			sslSmtp = "true";
		} else {
			sslSmtp = "false";
		}
		// HOTMAIL - PROVEDOR PROPRIO
		if (tipo.equals("HOTMAIL") || tipo.equals("OUTROS")) {
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", smtp);
			props.put("mail.smtp.socketFactory.port", portastmp);
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.starttls.enable", sslSmtp);
			props.put("mail.smtp.auth", autentica);
			props.put("mail.smtp.port", portastmp);
		}
		// GMAIL
		if (tipo.equals("GMAIL")) {
			props.put("mail.smtp.host", smtp);
			props.put("mail.smtp.port", portastmp);
			props.put("mail.smtp.ssl.enable", sslSmtp);
			props.put("mail.smtp.auth", autentica);
		}
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, senha);
			}
		});
		Address[] enviaTodos = InternetAddress.parse(destinatarios);
		// CRIANDO MENSAGEM MIME
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email, remetente));
		msg.setRecipients(Message.RecipientType.TO, enviaTodos);
		msg.setSubject(assunto);
		msg.setSentDate(new Date());
		// MONTA MSG HTML
		MimeMultipart multipart = new MimeMultipart("related");
		// ANEXOS SE HOUVER
		if (listaAnexo != null) {
			for (AuxDados a : listaAnexo) {
				MimeBodyPart anexo = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(a.getCampo2());
				anexo.setFileName(a.getCampo1());
				anexo.setDataHandler(new DataHandler(fds));
				// ANEXANDO
				multipart.addBodyPart(anexo);
			}
		}
		// ANEXOS BYTES SE HOUVER
		if (listaAnexoBytes != null) {
			for (AuxDadosByte a : listaAnexoBytes) {
				MimeBodyPart anexo = new MimeBodyPart();
				anexo.setFileName(a.getNome() + "." + a.getExtensao());
				ByteArrayDataSource ds = new ByteArrayDataSource(a.getArquivo(), a.getFiletype());
				anexo.setDataHandler(new DataHandler(ds));
				multipart.addBodyPart(anexo);
			}
		}
		// HTML
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(conteudo, "text/html; charset=utf-8");
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);
		// ENVIA EMAIL
		Transport.send(msg);
	}
}
