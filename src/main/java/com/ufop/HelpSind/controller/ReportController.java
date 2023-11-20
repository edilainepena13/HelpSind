package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.Report;
import com.ufop.HelpSind.enums.Gender;
import com.ufop.HelpSind.enums.State;
import com.ufop.HelpSind.service.ReportService;
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
@RequestMapping("trustee/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

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

    @ModelAttribute("state")
    public State[] state() {
        return State.values();
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getPeople(@RequestParam("pagina") Optional<Integer> pagina,
                                  @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("report",
                reportService.listPage(PageRequest.of(pagina.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "reportList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getReportCadastro(@ModelAttribute("report") Report report, ModelMap model) {
        model.addAttribute("content", "reportRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/{idReport}/cadastro")
    public ModelAndView getReportEditar(@PathVariable("idReport") Long idReport, ModelMap model) {
        Report report = reportService.read(idReport);

        model.addAttribute("report", report);
        model.addAttribute("content", "reportRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping(value = "/cadastro")
    public ModelAndView postReport(@Valid @ModelAttribute("report") Report report,
                                   BindingResult validation, ModelMap model) {
        reportService.validate(report, validation);
        if (validation.hasErrors()) {
            report.setIdReport(null);
            model.addAttribute("content", "reportRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        reportService.save(report);
        return new ModelAndView("redirect:/trustee/report");
    }

    @PutMapping(value = "/cadastro")
    public ModelAndView putReport(@Valid @ModelAttribute("pessoa") Report report, BindingResult validation, ModelMap model) {
        reportService.validate(report, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "reportRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        reportService.update(report);
        return new ModelAndView("redirect:/trustee/report");
    }

    @DeleteMapping("/excluir")
    public ModelAndView deleteReportCadastro(@RequestParam("idObj") Long idObj) {
        reportService.delete(reportService.read(idObj));
        return new ModelAndView("redirect:/trustee/report");
    }


}
