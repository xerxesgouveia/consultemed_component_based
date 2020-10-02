/**
 * 
 */
package br.com.funcionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import br.com.funcionario.Funcionario;
import br.com.funcionario.infraestructure.FuncionarioRepository;
import br.com.infraestructure.GenericRepository;
import br.com.service.ServicoGenerico;
import br.com.usuario.service.UsuarioService;

@Service
public class FuncionarioService extends ServicoGenerico<Funcionario, Long> {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public GenericRepository<Funcionario, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.funcionarioRepository;
	}

	@Transactional(readOnly = true)
	public Optional<List<Funcionario>> filtroListagem(final String filtro) {

		if (!StringUtils.isEmpty(filtro)) {
			List<Funcionario> funcionariosFiltrados = this.funcionarioRepository.findByPessoaNomeContaining(filtro);
			return Optional.ofNullable(funcionariosFiltrados);
		}
		List<Funcionario>  funcionarios = this.funcionarioRepository.findAll();
		
		return Optional.ofNullable(funcionarios);

	}

	@Transactional
	public String salvarFuncionario(Funcionario funcionario) {
		String mensagemUsuario = this.usuarioService.prepararParaPersistir(funcionario.getPessoa().getUsuario());
		
		if (mensagemUsuario.equals("")) {
			this.funcionarioRepository.save(funcionario);
			return "";
		}
		
		return mensagemUsuario;
	}
}
