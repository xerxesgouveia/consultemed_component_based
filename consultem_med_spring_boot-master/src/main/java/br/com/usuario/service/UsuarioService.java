/**
 * 
 */
package br.com.usuario.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agendamento.Agendamento;
import br.com.infraestructure.GenericRepository;
import br.com.service.ServicoGenerico;
import br.com.usuario.Usuario;
import br.com.usuario.infraestructure.UsuarioRepository;

@Service
public class UsuarioService extends ServicoGenerico<Usuario, Long> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @Autowired 
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String emailSistema;

	@Transactional
	public boolean verificarExistenciaLogin(final String login, final String loginAntigo) {
		return this.usuarioRepository.verificarExistenciaLogin(login, loginAntigo); 
	}
	
	@Transactional
	public String prepararParaPersistir(final Usuario usuario) {
		String loginAntigo = "";
		
		if (usuario.getId() != null) {
			
			final Usuario usuarioDB  = this.buscarPorId(usuario.getId());

			loginAntigo = usuarioDB.getLogin();
		}
		
		final boolean existeLogin = this.usuarioRepository.verificarExistenciaLogin(usuario.getLogin(), loginAntigo);
		
		if (existeLogin) {
			return "usuário já existe com este login cadastrado";
		}
		
		return "";
	}
	
	/**
	 * Método responsável poe envio de email a um paciente específico
	 * @param emailPaciente email destinatário
	 * @param agendamento 
	 * @return
	 */
	public String sendMail(final String emailPaciente, final Agendamento agendamento) {
		
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Agendamento consulta");
        message.setText("Sua consulta foi agendada para a data "+ agendamento.getDataAgendamento() + " as : " + agendamento.getHoraAgendamento() );
        message.setSentDate(new Date());
        message.setTo(emailPaciente); 
        message.setFrom(emailSistema);
        
        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
	
	@Override
	public GenericRepository<Usuario, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.usuarioRepository;
	}

}
