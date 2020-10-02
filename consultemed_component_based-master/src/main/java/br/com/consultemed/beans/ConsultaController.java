
/**
 * 
 */
package br.com.consultemed.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.models.Consulta;
import br.com.consultemed.models.Funcionario;
import br.com.consultemed.services.ConsultaService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Named
@RequestScoped
public class ConsultaController{
	
	@Getter
	@Setter
	private List<Consulta> consultas;

	@Inject
	@Getter
	@Setter
	private Consulta consulta;
	
	@Getter
	@Setter
	private Consulta consultaEditar;
	
	@Inject
	private ConsultaService service;
	
	
	public String editar() {
		this.consulta = this.consultaEditar;
		return "/pages/consultas/addConsultas.xhtml";
	}
	
	public String excluir() throws Exception {
		this.consulta = this.consultaEditar;
		this.service.deletarConsulta(this.consulta.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/consultas/consultas.xhtml?faces-redirect=true";
	}
	
	public String novaConsulta() {
		this.consulta = new Consulta();
		return "/pages/consultas/addConsultas.xhtml?faces-redirect=true";
	}
	
	public String addConsulta() {
		
		this.service.salvarConsulta(this.consulta);
		return "/pages/consultas/consultas.xhtml?faces-redirect=true";
	}
	
	public List<Consulta> listaConsultas(){
		this.consultas = this.service.listaConsulta();
		return consultas;
	}
	
	
}
