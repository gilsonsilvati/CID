package br.com.cid.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.cid.model.PermissaoUsuario;
import br.com.cid.repository.Permissoes;

@FacesConverter(forClass = PermissaoUsuario.class)
public class PermissaoUsuarioConverter implements Converter {
	
	@Inject
	private Permissoes permissoes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PermissaoUsuario retorno = null;
		
		if (StringUtils.isNotBlank(value)) {
			retorno = this.permissoes.porId(new Long(value));
			
			if (retorno == null) {
				String descricaoErro = "usuário não existe.";
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
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}
