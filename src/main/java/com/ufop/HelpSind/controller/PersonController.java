package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.Person;
import com.ufop.HelpSind.enums.Gender;
import com.ufop.HelpSind.enums.State;
import com.ufop.HelpSind.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("trustee/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"condominium", "condos"};
    }

    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @ModelAttribute("apartments")
    public List<Apartment> aparments() {
        return null;
    }

    @ModelAttribute("states")
    public State[] states() {
        return State.values();
    }

    @ModelAttribute("state")
    public State[] state() {
        return State.values();
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getPeople(@RequestParam("pagina") Optional<Integer> pagina,
                                  @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("person",
                personService.listPage(PageRequest.of(pagina.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "personList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getPersonCadastro(@ModelAttribute("person") Person person, ModelMap model) {
        model.addAttribute("content", "personRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/{idPerson}/cadastro")
    public ModelAndView getPessoaEditar(@PathVariable("idPerson") Long idPerson, ModelMap model) {
        Person person = personService.read(idPerson);

        model.addAttribute("person", person);
        model.addAttribute("content", "personRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping(value = "/cadastro")
    public ModelAndView postPerson(@Valid @ModelAttribute("person") Person person,
                                   BindingResult validation, ModelMap model) {
        personService.validate(person, validation);
        if (validation.hasErrors()) {
            person.setIdPerson(null);
            model.addAttribute("content", "personRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        personService.save(person);
        return new ModelAndView("redirect:/trustee/person");
    }

    @PutMapping(value = "/cadastro")
    public ModelAndView putPerson(@Valid @ModelAttribute("pessoa") Person person, BindingResult validation, ModelMap model) {
        personService.validate(person, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "personRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        personService.update(person);
        return new ModelAndView("redirect:/trustee/person");
    }

    @DeleteMapping("/excluir")
    public ModelAndView deletePessoaCadastro(@RequestParam("idObj") Long idObj) {
        personService.delete(personService.read(idObj));
        return new ModelAndView("redirect:/trustee/person");
    }


}
