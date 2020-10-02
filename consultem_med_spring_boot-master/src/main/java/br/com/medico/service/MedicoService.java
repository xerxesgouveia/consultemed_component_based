/**
 * 
 */
package br.com.medico.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import br.com.consulta.Consulta;
import br.com.consulta.service.ConsultaService;
import br.com.infraestructure.GenericRepository;
import br.com.medico.Medico;
import br.com.medico.infraestructure.MedicoRepository;
import br.com.service.ServicoGenerico;
import br.com.usuario.service.UsuarioService;

@Service
public class MedicoService extends ServicoGenerico<Medico, Long> {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public GenericRepository<Medico, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.medicoRepository;
	}
	
	@Transactional(readOnly = true)
	public Optional<List<Medico>> filtroListagem(final String filtro) {

		if (!StringUtils.isEmpty(filtro)) {
			List<Medico> medicosFiltrados = this.medicoRepository.findByPessoaNomeContaining(filtro);
			return Optional.ofNullable(medicosFiltrados);
		}
		List<Medico>  medicos = this.medicoRepository.findAll();
		
		return Optional.ofNullable(medicos);

	}

	@Transactional
	public String salvarMedico(Medico medico) {
		String mensagemUsuario = this.usuarioService.prepararParaPersistir(medico.getPessoa().getUsuario());
		
		if (mensagemUsuario.equals("")) {
			this.medicoRepository.save(medico);
			return "";
		}
		
		return mensagemUsuario;
	}
	
}
