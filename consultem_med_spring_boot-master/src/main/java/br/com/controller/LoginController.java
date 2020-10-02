package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usuario.service.UsuarioService;
import lombok.Data;

@Controller
@Data
@RequestMapping("/login")
public class LoginController {

	private static final String PAGES_LOGIN = "pages/login";
	
	private UsuarioService service;

	@Autowired
	public LoginController(UsuarioService service) {
		this.service = service;
	}
	
	@GetMapping
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView(PAGES_LOGIN);
		return mv;
	}
	
}
