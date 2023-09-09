package com.ufop.HelpSind.controller;


import com.ufop.HelpSind.service.ApportFixedExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.ApportFixedExp;
import com.ufop.HelpSind.enums.Gender;
import com.ufop.HelpSind.enums.State;
import javax.validation.Valid;

@Controller
@RequestMapping("trustee/apportFixedExp")
public class ApportFixedExpController {

	@Autowired
	private ApportFixedExpService apportFixedExpService;
	
	@ModelAttribute("ativo")
	public String[] ativo() {
		return new String[] { "condominium", "condos" };
	}
	
	@ModelAttribute("genders")
	public Gender[] genders() {
		return Gender.values();
	}
	
	@ModelAttribute("apartments")
	public List<Apartment> aparments(){
		return null;
	}
	
	@ModelAttribute("state")
	public State[] state() {
		return State.values();
	}
	
	@GetMapping({ "", "/", "/lista"})
	public ModelAndView getPeople(@RequestParam("pagina") Optional<Integer> pagina,
			@RequestParam("size") Optional<Integer> size, ModelMap model) {
		model.addAttribute("apportFixedExp",
				apportFixedExpService.listPage(PageRequest.of(pagina.orElse(1) - 1, size.orElse(20))));
		model.addAttribute("content", "apportFixedExpList");
		return new ModelAndView("layouts/trustee", model);
	}
	@GetMapping("/cadastro")
	public ModelAndView getApportFixedExpCadastro(@ModelAttribute("apportFixedExp") ApportFixedExp apportFixedExp, ModelMap model) {
		model.addAttribute("content", "apportFixedExpRegister");
		return new ModelAndView("layouts/trustee", model);
	}
	@GetMapping("/{idApportFixedExp}/cadastro")
	public ModelAndView getApportFixedExpEditar(@PathVariable("idApportFixedExp") Long idApportFixedExp, ModelMap model) {
		ApportFixedExp apportFixedExp = apportFixedExpService.read(idApportFixedExp);
		
		model.addAttribute("apportFixedExp", apportFixedExp);
		model.addAttribute("content", "apportFixedExpRegister");
		return new ModelAndView("layouts/trustee", model);
	}
	
	@PostMapping(value = "/cadastro")
	public ModelAndView postApportFixedExp(@Valid @ModelAttribute("apportFixedExp") ApportFixedExp apportFixedExp,
			BindingResult validation, ModelMap model) {
		apportFixedExpService.validate(apportFixedExp, validation);
		if (validation.hasErrors()) {
			apportFixedExp.setIdApportFixedExp(null);
			model.addAttribute("content", "apportFixedExpRegister");
			return new ModelAndView("layouts/trustee", model);
		}
		apportFixedExpService.save(apportFixedExp);

		return new ModelAndView("redirect:/trustee/apportFixedExp");
	}
	
	@PutMapping(value = "/cadastro")
	public ModelAndView putApportFixedExp(@Valid @ModelAttribute("pessoa") ApportFixedExp apportFixedExp, BindingResult validation, ModelMap model) {
		apportFixedExpService.validate(apportFixedExp, validation);
		if (validation.hasErrors()) {
			model.addAttribute("content", "apportFixedExpRegister");
			return new ModelAndView("layouts/trustee", model);
		}
		apportFixedExpService.update(apportFixedExp);
		return new ModelAndView("redirect:/trustee/apportFixedExp");
	}
	
	@DeleteMapping("/excluir")
	public ModelAndView deleteApportFixedExpCadastro(@RequestParam("idObj") Long idObj) {
		apportFixedExpService.delete(apportFixedExpService.read(idObj));
		return new ModelAndView("redirect:/trustee/apportFixedExp");
	}
 	
	
}
