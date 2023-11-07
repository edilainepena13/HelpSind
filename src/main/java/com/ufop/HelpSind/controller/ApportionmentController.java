package com.ufop.HelpSind.controller;


import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.ApportionmentProportional;
import com.ufop.HelpSind.domain.ApportionmentProportionalGeneralDataDto;
import com.ufop.HelpSind.enums.ExpenseType;
import com.ufop.HelpSind.service.ApartmentService;
import com.ufop.HelpSind.service.ApportionmentService;
import com.ufop.HelpSind.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("trustee/apportionment")
public class ApportionmentController {

    @Autowired
    private ApportionmentService apportionmentService;
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getApportionments(@RequestParam("pagina") Optional<Integer> pagina, @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("apportionment", apportionmentService.listPage(PageRequest.of(pagina.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "apportionmentList");
        return new ModelAndView("layouts/trustee", model);
    }


    @GetMapping("/cadastro")
    public ModelAndView getApportionmentsRegister(ModelMap model) {

        List<Apartment> apartments = apartmentService.list();
        List<ApportionmentProportional> apportionmentProportionals = new ArrayList<>();
        ApportionmentProportionalGeneralDataDto apportionmentProportionalGeneralDataDto = new ApportionmentProportionalGeneralDataDto();
        apportionmentProportionalGeneralDataDto.setDays(30);

        for (Apartment apartment : apartments) {
            ApportionmentProportional apportionmentProportional = new ApportionmentProportional();
            apportionmentProportional.setApartment(apartment);
            apportionmentProportionals.add(apportionmentProportional);
        }
        apportionmentProportionalGeneralDataDto.setApportionmentProportional(apportionmentProportionals);

        model.addAttribute("expenses", expenseService.findExpensesByType(ExpenseType.P));
        model.addAttribute("apportionmentProportionalGeneralDataDto", apportionmentProportionalGeneralDataDto);

        model.addAttribute("content", "apportionmentRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView postApportionmentsRegister(@Valid @ModelAttribute("apportionmentProportionalGeneralDataDto") ApportionmentProportionalGeneralDataDto apportionment, BindingResult validation) {
        //expenseService.validate(expense, validation);
        //if (validation.hasErrors()) {
        //apportionment.setId(null);
        //return new ModelAndView("layouts/trustee", "content", "apportionmentRegister");
        //}
//        apportionmentService.save(apportionment);

        System.out.println(apportionment);
        return new ModelAndView("redirect:/trustee/apportionment");
    }

}
