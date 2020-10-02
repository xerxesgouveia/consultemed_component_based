package br.com.medico.infraestructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.funcionario.Funcionario;
import br.com.infraestructure.GenericRepository;
import br.com.medico.Medico;

@Repository
public interface MedicoRepository extends GenericRepository<Medico, Long> {
	
	public List<Medico> findByPessoaNomeContaining(String nome);

}
