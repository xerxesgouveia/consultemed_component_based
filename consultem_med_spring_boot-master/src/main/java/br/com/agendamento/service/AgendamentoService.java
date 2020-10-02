package br.com.agendamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agendamento.Agendamento;
import br.com.agendamento.StatusAgendamento;
import br.com.agendamento.infraestructure.AgendamentoRepository;
import br.com.consulta.Consulta;
import br.com.infraestructure.GenericRepository;
import br.com.service.ServicoGenerico;

@Service
public class AgendamentoService extends ServicoGenerico<Agendamento, Long>{
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Override
	public GenericRepository<Agendamento, Long> getRepository() {
		// TODO Auto-generated method stub
		return agendamentoRepository;
	}

	@Transactional
	public String cancelarAgendamento(final Long id) {
		// TODO Auto-generated method stub
		final Agendamento agendamentoFromDB = this.buscarPorId(id);
		
		if (agendamentoFromDB == null) {
			return "";
		}
		
		agendamentoFromDB.setStatus(StatusAgendamento.CANCELADO);
		
		this.salvar(agendamentoFromDB);
		
		return "Agendamento cancelado";
	}

}
