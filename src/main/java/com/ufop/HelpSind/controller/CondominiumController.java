package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.enums.State;
import com.ufop.HelpSind.service.CondominiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping({"trustee/condominium"})
public class CondominiumController {

    @Autowired
    private CondominiumService condominiumService;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"inicio", ""};
    }

    @ModelAttribute("states")
    public State[] states() {
        return State.values();
    }

    @GetMapping("/cadastro")
    public ModelAndView getCondominiumRegister(ModelMap model) {
        Condominium condominium = condominiumService.read();
        if (condominium != null) {
            model.addAttribute("condominium", condominium);
        } else {
            model.addAttribute("condominium", new Condominium());
        }
        model.addAttribute("content", "condominiumRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView CondominiumRegister(@Valid @ModelAttribute("condominium") Condominium condominium, BindingResult validation) {
        condominiumService.validate(condominium, validation);
        if (validation.hasErrors()) {
            condominium.setIdCondominium(null);
            return new ModelAndView("layouts/trustee", "content", "condominiumRegister");
        }
        condominiumService.save(condominium);
        return new ModelAndView("redirect:/trustee/condominium/cadastro");
    }

    @PutMapping("/cadastro")
    public ModelAndView putCondominioRegister(@Valid @ModelAttribute("idCondominium") Condominium condominium, BindingResult validation, ModelMap model) {
        condominiumService.validate(condominium, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "condominioRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        condominiumService.update(condominium);
        return new ModelAndView("redirect:/trustee/condominium/cadastro");
    }
}
