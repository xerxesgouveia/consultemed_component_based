package br.com.funcionario.infraestructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.funcionario.Funcionario;
import br.com.infraestructure.GenericRepository;

@Repository
public interface FuncionarioRepository extends GenericRepository<Funcionario, Long> {
	
	public List<Funcionario> findByPessoaNomeContaining(String nome);
}
