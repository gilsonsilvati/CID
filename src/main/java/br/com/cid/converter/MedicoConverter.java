package br.com.cid.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.cid.model.Medico;
import br.com.cid.repository.Medicos;
import br.com.cid.util.Repositorios;

@FacesConverter(forClass = Medico.class)
public class MedicoConverter implements Converter {

	private Repositorios repositorios = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Medico retorno = null;
		Medicos medicos = this.repositorios.getMedicos();
		
		if (value != null && !value.equals("")) {
			retorno = medicos.porId(new Long(value));
			
			if (retorno == null) {
				String descricaoErro = "Médico não existe.";
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
			Long id = ((Medico) value).getId();
			return id == null ? "" : id.toString();
		}
		
		return null;
	}

}
