package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.MaintenancePlan;
import com.ufop.HelpSind.service.MaintenancePlanService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("trustee/maintenancePlan")
public class MaintenancePlanController {

    @Autowired
    private MaintenancePlanService maintenancePlanService;

    @Autowired
    private UserService userService;


    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"finance", "maintenancePlan"};
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getMaintenancePlan(@RequestParam("maintenancePlan") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("maintenancePlan",
                maintenancePlanService.listPage(PageRequest.of(page.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "maintenancePlanList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getMaintenancePlanRegister(@ModelAttribute("maintenancePlan") MaintenancePlan maintenancePlan, ModelMap model) {
        model.addAttribute("content", "maintenancePlanRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView postMaintenancePlanRegister(@Valid @ModelAttribute("maintenancePlan") MaintenancePlan maintenancePlan, BindingResult validation) {
        maintenancePlan.setCondominium(userService.logged().getCondominium());

        if (validation.hasErrors()) {
            maintenancePlan.setIdMaintenancePlan(null);


            LocalDate dataProxima = LocalDate.parse((CharSequence) maintenancePlan.getInitialDate());
            LocalDate novaData = dataProxima.plusDays(maintenancePlan.getDays());

            maintenancePlan.setFinalDate(novaData);

            return new ModelAndView("layouts/trustee", "content", "maintenancePlanRegister");
        }

        maintenancePlanService.save(maintenancePlan);
        return new ModelAndView("redirect:/trustee/maintenancePlan");
    }

    @GetMapping("/{idMaintenancePlan}/cadastro")
    public ModelAndView getMaintenancePlanRegister(@PathVariable("idMaintenancePlan") Long idMaintenancePlan, ModelMap model) {
        model.addAttribute("maintenancePlan", maintenancePlanService.read(idMaintenancePlan));
        model.addAttribute("content", "maintenancePlanRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PutMapping("/cadastro")
    public ModelAndView putMaintenancePlanRegister(@Valid @ModelAttribute("idMaintenancePlan") MaintenancePlan maintenancePlan, BindingResult validation,
                                            ModelMap model) {
        maintenancePlanService.validate(maintenancePlan, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "maintenancePlanRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        maintenancePlanService.update(maintenancePlan);
        return new ModelAndView("redirect:/trustee/maintenancePlan");
    }

    @DeleteMapping("/excluir")
    public ModelAndView deleteMaintenancePlanRegister(@RequestParam("idObj") Long idObj) {
        maintenancePlanService.delete(maintenancePlanService.read(idObj));
        return new ModelAndView("redirect:/trustee/maintenancePlan");
    }

}
