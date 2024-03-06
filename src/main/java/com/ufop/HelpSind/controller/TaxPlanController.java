package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.TaxPlan;
import com.ufop.HelpSind.service.TaxPlanService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("trustee/taxPlan")
public class TaxPlanController {

    @Autowired
    private TaxPlanService taxPlanService;

    @Autowired
    private UserService userService;


    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"finance", "taxPlan"};
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getTaxPlan(@RequestParam("taxPlan") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("taxPlan",
                taxPlanService.listPage(PageRequest.of(page.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "taxPlanList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getTaxPlanRegister(@ModelAttribute("taxPlan") TaxPlan taxPlan, ModelMap model) {
        model.addAttribute("content", "taxPlanRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView postTaxPlanRegister(@Valid @ModelAttribute("taxPlan") TaxPlan taxPlan, BindingResult validation) {
        taxPlan.setCondominium(userService.logged().getCondominium());

        if (validation.hasErrors()) {
            taxPlan.setIdTaxPlan(null);


            LocalDate dataProxima = LocalDate.parse((CharSequence) taxPlan.getInitialDate());
            LocalDate novaData = dataProxima.plusDays(taxPlan.getDays());

            taxPlan.setFinalDate(novaData);

            return new ModelAndView("layouts/trustee", "content", "taxPlanRegister");
        }

        taxPlanService.save(taxPlan);
        return new ModelAndView("redirect:/trustee/taxPlan");
    }

    @GetMapping("/{idTaxPlan}/cadastro")
    public ModelAndView getTaxPlanRegister(@PathVariable("idTaxPlan") Long idTaxPlan, ModelMap model) {
        model.addAttribute("taxPlan", taxPlanService.read(idTaxPlan));
        model.addAttribute("content", "taxPlanRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PutMapping("/cadastro")
    public ModelAndView putTaxPlanRegister(@Valid @ModelAttribute("idTaxPlan") TaxPlan taxPlan, BindingResult validation,
                                            ModelMap model) {
        taxPlanService.validate(taxPlan, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "taxPlanRegister");
            return new ModelAndView("layout/trustee", model);
        }
        taxPlanService.update(taxPlan);
        return new ModelAndView("redirect:/trustee/taxPlan");
    }

    @DeleteMapping("/delete")
    public ModelAndView deleteTaxPlanRegister(@RequestParam("idObj") Long idObj) {
        taxPlanService.delete(taxPlanService.read(idObj));
        return new ModelAndView();
    }

}
