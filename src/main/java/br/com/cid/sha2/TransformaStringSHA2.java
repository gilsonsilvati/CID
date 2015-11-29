package br.com.cid.sha2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TransformaStringSHA2 {

	public static String sha2(String senha) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-256"); // 256 bits (64 caractéres)
			byte[] hash = md.digest(senha.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (byte h : hash) {
				sb.append(String.format("%02X", 0xFF & h));
			}
			
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Erro: Motivo: " + e.getMessage());
			e.printStackTrace();
			return null;
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("Erro: Motivo: " + e.getMessage());
			e.printStackTrace();
			return null;
			
		}
		
	}

}
