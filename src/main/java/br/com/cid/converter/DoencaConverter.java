package br.com.cid.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.cid.model.Doenca;
import br.com.cid.repository.Doencas;
import br.com.cid.util.Repositorios;

@FacesConverter(forClass = Doenca.class)
public class DoencaConverter implements Converter {

	private Repositorios repositorios = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Doenca retorno = null;
		Doencas doencas = this.repositorios.getDoencas();
		
		if (value != null && !value.equals("")) {
			retorno = doencas.porId(new Long(value));
			
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
			return id == null ? "" : id.toString();
		}
		
		return null;
	}

}
