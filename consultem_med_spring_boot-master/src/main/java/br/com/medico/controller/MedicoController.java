package br.com.medico.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.consulta.Consulta;
import br.com.consulta.service.ConsultaService;
import br.com.medico.Medico;
import br.com.medico.dto.MedicoFiltroDTO;
import br.com.medico.service.MedicoService;

@Controller
@RequestMapping("/medico")
public class MedicoController {
	private static final String PAGES_NOVO_MEDICO = "pages/medico/novo_medico";

	private static final String PAGES_MEDICO_LISTAGEM = "pages/medico/medicos";
	
	private static final String PAGES_MEDICO_LISTAGEM_FILTRO = "pages/medico/medico_filtro";


	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private ConsultaService consultaService;

	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") MedicoFiltroDTO filtro) {
		ModelAndView mv = new ModelAndView(PAGES_MEDICO_LISTAGEM);
		Optional<List<Medico>> medicos = this.medicoService.filtroListagem(filtro.getNome());
		mv.addObject("medicos",medicos.get());

		return mv; 
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medico");
		this.medicoService.remover(id);
		attributes.addFlashAttribute("removido", "Medico removido com sucesso!");
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		final Medico medico = this.medicoService.buscarPorId(id);
		return novo(medico);
	}

	@GetMapping("/novo")
	public ModelAndView novo(Medico medico) {
		ModelAndView mv = new ModelAndView(PAGES_NOVO_MEDICO);
		mv.addObject("medico", medico);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid Medico medico, BindingResult result, Model model,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medico");

		if (result.hasErrors()) {
			return novo(medico);
		}
		//Caso exista um usuário com mesmo login, uma mensagem é enviada para a view
    	final String mensagemusuario=  this.medicoService.salvarMedico(medico);
    	
    	if (mensagemusuario.equals("")) {
    		attributes.addFlashAttribute("mensagem", "Medico salvo com sucesso");
    		return mv;
		}
		attributes.addFlashAttribute("mensagemErro", mensagemusuario);

		return new ModelAndView("redirect:/medico/novo");
	}
	@GetMapping("/filtro/{id}")
	public ModelAndView filtrarConsultaPorMedicoComData(@PathVariable final Long id){
		ModelAndView mv = new ModelAndView(PAGES_MEDICO_LISTAGEM_FILTRO);
		List<Consulta> consultasFiltradas = this.consultaService.filtrarConsultaPorMedicoComData(id);
		mv.addObject("consultas", consultasFiltradas);
		return mv;
	}
}
