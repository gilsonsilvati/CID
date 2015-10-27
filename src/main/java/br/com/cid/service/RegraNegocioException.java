package br.com.cid.service;

import java.io.Serializable;

public class RegraNegocioException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegraNegocioException(String message) {
		super(message);
	}

}
