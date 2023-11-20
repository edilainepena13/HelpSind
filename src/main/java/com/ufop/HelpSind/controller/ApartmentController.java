package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.service.ApartmentService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("trustee/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;


    @Autowired
    private UserService userService;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"condominium", "apartments"};
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getApartments(@RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("apartments",
                apartmentService.listPage(PageRequest.of(page.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "apartmentList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getApartmentsRegister(@ModelAttribute("apartments") Apartment apartment) {
        return new ModelAndView("layouts/trustee", "content", "apartmentRegister");
    }

    @GetMapping("/{idApartment}/cadastro")
    public ModelAndView getApartmentsUpdate(@PathVariable("idApartment") Long idApartment, ModelMap model) {
        model.addAttribute("apartments", apartmentService.read(idApartment));
        model.addAttribute("content", "apartmentRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView postApartmentsRegister(@Valid @ModelAttribute("apartments") Apartment apartment, BindingResult validation) {
        apartment.setCondominium(userService.logged().getCondominium());
        apartmentService.validate(apartment, validation);
        if (validation.hasErrors()) {
            apartment.setIdApartment(null);
            return new ModelAndView("layouts/trustee", "content", "apartmentRegister");
        }
        apartmentService.save(apartment);
        return new ModelAndView("redirect:/trustee/apartments");
    }

    @PutMapping("/cadastro")
    public ModelAndView putApartmentsRegister(@Valid @ModelAttribute("apartments") Apartment apartments, BindingResult validation,
                                              ModelMap model) {
        apartments.setCondominium(userService.logged().getCondominium());
        apartmentService.validate(apartments, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "apartmentRegister");
            return new ModelAndView("layout/trustee", model);
        }
        apartmentService.update(apartments);
        return new ModelAndView("redirect:/trustee/apartments");
    }

    @DeleteMapping("/excluir")
    public ModelAndView deleteApartmentsRegister(@RequestParam("idObj") Long idObj) {
        apartmentService.delete(apartmentService.read(idObj));
        return new ModelAndView("redirect:/trustee/apartments");
    }

}