/**
 * 
 */
package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Consulta;
import br.com.consultemed.models.Funcionario;
import br.com.consultemed.repository.repositories.ConsultaRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class ConsultaService {

	@Inject
	private ConsultaRepository dao;
	
	public List<Consulta> listaConsulta(){
		return this.dao.listaConsulta();
	}
	
	public void salvarConsulta(Consulta consulta) {
		this.dao.salvarConsulta(consulta);
	}
	
	public void deletarConsulta(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
}
