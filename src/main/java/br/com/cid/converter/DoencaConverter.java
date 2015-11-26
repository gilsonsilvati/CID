package br.com.cid.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;

@FacesConverter(forClass = Doenca.class)
public class DoencaConverter implements Converter {

	@Inject
	private Doencas doencas;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Doenca retorno = null;
		
		if (StringUtils.isNotBlank(value)) {
			retorno = this.doencas.porId(new Long(value));
			
			if (retorno == null) {
				String descricaoErro = "Doença não existe.";
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
			Long id = ((Doenca) value).getId();
			String retorno = (id == null ? null : id.toString());
			 
			 return retorno;
		}
		
		return "";
	}

}
