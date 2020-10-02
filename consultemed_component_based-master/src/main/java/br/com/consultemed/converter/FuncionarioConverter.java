package br.com.consultemed.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.models.Funcionario;
import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.utils.JPAUtils;

@FacesConverter(forClass = Funcionario.class, value = "funcionarioConverter")
public class FuncionarioConverter implements Serializable, Converter {
	
	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();
	
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Funcionario funcionario = (Funcionario) object;
		if (funcionario == null || funcionario.getId() == null)
			return null;
		return String.valueOf(funcionario.getId());
	}

	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.isEmpty())
			return null; 
		Long id = Long.valueOf(string);
		Funcionario funcionario = manager.find(Funcionario.class, id); 
		return funcionario;
	}
}