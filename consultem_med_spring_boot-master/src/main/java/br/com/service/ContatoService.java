/**
 * 
 */
package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Funcionario;
import br.com.model.dto.ContatoPesquisaDTO;
import br.com.repository.ContatosRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Service
public class ContatoService {

	@Autowired
	private ContatosRepository repository;
	
	@Transactional(readOnly=true)
	public List<Funcionario> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Funcionario contato) {
		this.repository.save(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Funcionario getById(Long id) {
		return this.repository.findOne(id);
	}
	
	public List<Funcionario> filtrar(ContatoPesquisaDTO contato) {
		String nome = contato.getNome() == null ? "%" : contato.getNome()+"%";
		return repository.findByNomeContaining(nome);
	}


	@Transactional
	public boolean ativarDesativar(Long id) {
		
		
		boolean ativou = false;
		
		Funcionario contato = this.repository.getOne(id);
		if(contato.isAtivo()) {
			contato.setAtivo(false);
			return ativou;
		}else {
			contato.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarContato(Funcionario contato) {
		if (contato.isAtivo()) {
			ativaDesativaUsuario(contato);
		} else {
			ativaDesativaUsuario(contato);
		}
		return false;
	}


	
	@Transactional
	private void ativaDesativaUsuario(Funcionario contato) {

		if (contato.isAtivo()) {
			contato.setAtivo(false);
		} else {
			contato.setAtivo(true);
		}

		this.repository.saveAndFlush(contato);
	}

}
