package br.com.agendamento.infraestructure;

import org.springframework.stereotype.Repository;

import br.com.agendamento.Agendamento;
import br.com.infraestructure.GenericRepository;

@Repository
public interface AgendamentoRepository extends GenericRepository<Agendamento, Long> {
	
	
}
