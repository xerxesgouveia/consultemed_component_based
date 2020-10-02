package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Funcionario;

public interface ContatosRepository extends JpaRepository<Funcionario, Long> {
	
	public List<Funcionario> findByNomeContaining(String nome);
}
