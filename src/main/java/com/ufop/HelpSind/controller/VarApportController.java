package com.ufop.HelpSind.controller;


import com.ufop.HelpSind.service.VarApportService;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.VarApport;
import com.ufop.HelpSind.enums.Gender;
import com.ufop.HelpSind.enums.State;
import com.ufop.HelpSind.service.VarApportService;

import javax.validation.Valid;

@Controller
@RequestMapping("trustee/varApport")
public class VarApportController {

	@Autowired
	private VarApportService varApportService;
	
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
		model.addAttribute("varApport",
				varApportService.listPage(PageRequest.of(pagina.orElse(1) - 1, size.orElse(20))));
		model.addAttribute("content", "varApportList");
		return new ModelAndView("layouts/trustee", model);
	}
	@GetMapping("/cadastro")
	public ModelAndView getVarApportCadastro(@ModelAttribute("varApport") VarApport varApport, ModelMap model) {
		model.addAttribute("content", "varApportRegister");
		return new ModelAndView("layouts/trustee", model);
	}
	@GetMapping("/{idVarApport}/cadastro")
	public ModelAndView getVarApportEditar(@PathVariable("idVarApport") Long idVarApport, ModelMap model) {
		VarApport varApport = varApportService.read(idVarApport);
		
		model.addAttribute("varApport", varApport);
		model.addAttribute("content", "varApportRegister");
		return new ModelAndView("layouts/trustee", model);
	}
	
	@PostMapping(value = "/cadastro")
	public ModelAndView postVarApport(@Valid @ModelAttribute("varApport") VarApport varApport,
			BindingResult validation, ModelMap model) {
		varApportService.validate(varApport, validation);
		if (validation.hasErrors()) {
			varApport.setIdVarApport(null);
			model.addAttribute("content", "varApportRegister");
			return new ModelAndView("layouts/trustee", model);
		}
		varApportService.save(varApport);
		return new ModelAndView("redirect:/trustee/varApport");
	}
	
	@PutMapping(value = "/cadastro")
	public ModelAndView putVarApport(@Valid @ModelAttribute("pessoa") VarApport varApport, BindingResult validation, ModelMap model) {
		varApportService.validate(varApport, validation);
		if (validation.hasErrors()) {
			model.addAttribute("content", "varApportRegister");
			return new ModelAndView("layouts/trustee", model);
		}
		varApportService.update(varApport);
		return new ModelAndView("redirect:/trustee/varApport");
	}
	
	@DeleteMapping("/excluir")
	public ModelAndView deleteVarApportCadastro(@RequestParam("idObj") Long idObj) {
		varApportService.delete(varApportService.read(idObj));
		return new ModelAndView("redirect:/trustee/varApport");
	}
 	
	
}
