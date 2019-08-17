
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

import br.com.consultemed.models.Funcionario;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.services.FuncionarioService;
import br.com.consultemed.services.PacienteService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Named
@RequestScoped
public class FuncionarioController{
	
	@Getter
	@Setter
	private List<Funcionario> funcionarios;

	@Inject
	@Getter
	@Setter
	private Funcionario funcionario;
	
	@Getter
	@Setter
	private Funcionario funcionarioEditar;
	
	@Inject
	private FuncionarioService service;
	
	
	public String editar() {
		this.funcionario = this.funcionarioEditar;
		return "/pages/funcionarios/addFuncionarios.xhtml";
	}
	
	public String excluir() throws Exception {
		this.funcionario = this.funcionarioEditar;
		this.service.deletarFuncionario(this.funcionario.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return "/pages/funcionarios/funcionarios.xhtml?faces-redirect=true";
	}
	
	public String novoFuncionario() {
		this.funcionario = new Funcionario();
		return "/pages/funcionarios/addFuncionarios.xhtml?faces-redirect=true";
	}
	
	public String addFuncionario() {
		if(this.existeFuncionarioComEmail(this.funcionario.getEmail())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "E-mail já existe!"));
			return null;
		}
		
		this.service.salvarFuncionario(this.funcionario);
		return "/pages/funcionarios/funcionarios.xhtml?faces-redirect=true";
	}
	
	public List<Funcionario> listaFuncionarios(){
		this.funcionarios = this.service.listaFuncionario();
		return funcionarios;
	}
	
	public boolean existeFuncionarioComEmail(String email) {
		return this.service.getFuncionarioByEmail(email);
	}
}
