package br.com.cid.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TransformaStringMD5 {

	public static String md5(String senha) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(senha.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (byte h : hash) {
				sb.append(Integer.toHexString((h & 0xFF) | 0x100).substring(1, 3));
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
