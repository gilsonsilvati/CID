package br.com.cid.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesMessageUtil {
	
	public static String getMensagemI18n(String chave) {
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = context.getApplication().getResourceBundle(context, "msg").getString(chave);
		return msg;
	}
	
	public static void adicionarMensagem(Severity tipo, String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(tipo, msg, msg));
	}

}
