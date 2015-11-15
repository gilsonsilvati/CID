package br.com.cid.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.repository.Permissoes;
import br.com.cid.util.Repositorios;

@FacesConverter(forClass = PermissaoUsuario.class)
public class PermissaoUsuarioConverter implements Converter {
	
	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PermissaoUsuario retorno = null;
		Permissoes permissoes = this.repositorios.getPermissoes();
		
		if (value != null && !value.equals("")) {
			retorno = permissoes.porId(new Long(value));
			
			if (retorno == null) {
				String descricaoErro = "Usuário não existe.";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						descricaoErro, descricaoErro);
				throw new ConverterException(message);
			}
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((PermissaoUsuario) value).getId();
			return id == null ? "" : id.toString();
		}
		
		return null;
	}

}
