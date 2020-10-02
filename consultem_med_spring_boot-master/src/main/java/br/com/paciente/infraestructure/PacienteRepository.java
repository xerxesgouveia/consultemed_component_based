package br.com.paciente.infraestructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.paciente.Paciente;
import br.com.infraestructure.GenericRepository;

/**
 * @author ricardo belo
 *
 */

@Repository
public interface PacienteRepository extends GenericRepository<Paciente, Long> {
	
	public List<Paciente> findByPessoaNomeContaining(String nome);
}
