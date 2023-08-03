package com.midas.api.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptPassUtil {
	// BCRYPT
	public String cBrypt(String senha) {
		String senhacripto = new BCryptPasswordEncoder().encode(senha);
		return senhacripto;
	}

	// BCRYPT Compara
	public boolean cBryptCompara(String senha1, String senha2) {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		if (b.matches(senha1, senha2) == true) {
			return true;
		}
		return false;
	}

	// SHA1
	public static String cSHA1(String texto) {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));
		sen = hash.toString(16);
		return sen;
	}

	// BASE 64 CRIA
	public String cBase64(String texto) {
		return Base64.getEncoder().encodeToString(texto.getBytes());
	}

	// BASE 64 DECODIFICA
	public String dBase64(String texto) {
		byte[] decodedBytes = Base64.getDecoder().decode(texto);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
}
