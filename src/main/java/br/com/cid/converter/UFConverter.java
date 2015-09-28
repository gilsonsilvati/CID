package br.com.cid.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.cid.model.UF;
import br.com.cid.repository.UFRepository;

@FacesConverter(forClass = UF.class)
public class UFConverter implements Converter {

	private UFRepository ufs;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UF retorno = null;
		
		if (value != null) {
			retorno = this.ufs.buscaPorId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((UF) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}
	
	

}
