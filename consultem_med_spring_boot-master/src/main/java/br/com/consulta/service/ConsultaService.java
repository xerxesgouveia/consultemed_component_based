package br.com.consulta.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agendamento.StatusAgendamento;
import br.com.consulta.Consulta;
import br.com.consulta.infraestructure.ConsultaRepository;
import br.com.infraestructure.GenericRepository;
import br.com.paciente.Paciente;
import br.com.service.ServicoGenerico;
import br.com.usuario.service.UsuarioService;

@Service
public class ConsultaService extends ServicoGenerico<Consulta, Long> {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public GenericRepository<Consulta, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.consultaRepository;
	}

	@Transactional(readOnly = true)
	public boolean existeConsultaComHoraEData(final LocalDate dataAgendamento, final LocalTime hora,
			final Long idMedico) {
		boolean existeConsulta = this.consultaRepository.existeConsultaComHoraEData(dataAgendamento, hora, idMedico);

		return existeConsulta;
	}

	@Transactional(readOnly = true)
	public Optional<List<Consulta>> filtroListagem(final LocalDate filtro) {

		if (filtro != null) {
			List<Consulta> ConsultasFiltradas = this.consultaRepository.buscarPorDataAgendamento(filtro);
			return Optional.ofNullable(ConsultasFiltradas);
		}
		List<Consulta> consultas = this.consultaRepository.findAll();

		return Optional.ofNullable(consultas);

	}

	@Transactional
	public String salvarConsulta(final Consulta consulta) {

		if (consulta.getId() != null) {
			consulta.getAgendamento().setStatus(StatusAgendamento.REAGENDADO);

		} else {
			consulta.getAgendamento().setStatus(StatusAgendamento.AGENDADO);
		}
		
		final LocalDate dataAgendamento = consulta.getAgendamento().getDataAgendamento();
		final LocalTime hora = consulta.getAgendamento().getHoraAgendamento();
		final Long idMedico = consulta.getMedico().getId();

		final boolean existeConsulta = this.existeConsultaComHoraEData(dataAgendamento, hora, idMedico);

		if (existeConsulta) {
			return "Já existe uma consulta nesta data, no mesmo horario, com este médico";
		}
		
		final String emailFromPaciente = consulta.getAgendamento().getPaciente().getPessoa().getUsuario().getLogin();

		this.usuarioService.sendMail(emailFromPaciente , consulta.getAgendamento());
		this.salvar(consulta);
		return "";
	}
	
	@Transactional
	public String cancelarAgendamento(final Long id) {
		// TODO Auto-generated method stub
		final Consulta consultaFromDB = this.buscarPorId(id);
		
		if (consultaFromDB == null) {
			return "";
		}
		
		consultaFromDB.getAgendamento().setStatus(StatusAgendamento.CANCELADO);
		
		this.salvar(consultaFromDB);
		
		return "Consulta cancelada";
	}
	
	@Transactional(readOnly=true)
	public List<Consulta> filtrarConsultaPorMedicoComData(final Long idMedico){
		return this.consultaRepository.filtrarConsultaPorMedicoComData(idMedico);
	}

}
