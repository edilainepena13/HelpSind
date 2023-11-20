package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.ApartmentReading;
import com.ufop.HelpSind.domain.ApportionmentProportional;
import com.ufop.HelpSind.domain.Expense;
import com.ufop.HelpSind.enums.ApportionmentType;
import com.ufop.HelpSind.service.ApartmentService;
import com.ufop.HelpSind.service.ApportionmentProportionalService;
import com.ufop.HelpSind.service.ExpenseService;
import com.ufop.HelpSind.serviceImpl.ApportionmentProportionalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("trustee/expense")
public class ExpenseController {

    @Autowired
    private ApportionmentProportionalService apportionmentProportionalService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ApartmentService apartmentService;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"finance", "expenses"};
    }

    @ModelAttribute("apartment")
    public List<Apartment> apartment() {
        return apartmentService.list();
    }

    @ModelAttribute("apportionmentTypes")
    public List<com.ufop.HelpSind.enums.ApportionmentType> apportionmentTypes() {
        return Arrays.asList(com.ufop.HelpSind.enums.ApportionmentType.values());
    }

    @ModelAttribute("expenseTypes")
    public List<com.ufop.HelpSind.enums.ExpenseType> expenseTypes() {
        return Arrays.asList(com.ufop.HelpSind.enums.ExpenseType.values());
    }

    @ModelAttribute("apartmentReadingList")
    public List<ApartmentReading> apartmentReadingList() {
        List<Apartment> apartmentList = apartmentService.list();
        var apartmentReadingList = new ArrayList<ApartmentReading>();
        for (Apartment apartment : apartmentList) {
            apartmentReadingList.add(new ApartmentReading(apartment));
        }
        return apartmentReadingList;
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getExpenses(@RequestParam("expense") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                                    ModelMap model) {
        model.addAttribute("expense", expenseService.listPage(PageRequest.of(page.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "expenseList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getExpensesRegister(ModelMap model) {

        Expense expense = new Expense();
        expense.setApartmentReadingList(apartmentReadingList());

        model.addAttribute("expense", expense);

        model.addAttribute("content", "expenseRegister");
        return new ModelAndView("layouts/trustee", model);

    }

    @GetMapping("/{idExpense}/cadastro")
    public ModelAndView getExpensesRegister(@PathVariable("idExpense") Long idExpense, ModelMap model) {
        model.addAttribute("expense", expenseService.read(idExpense));
        model.addAttribute("content", "expenseRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView postExpensesRegister(@Valid @ModelAttribute("expense") Expense expense, BindingResult validation) {
        int totalApartments = apartmentService.list().size();

        expenseService.validate(expense, validation);
        if (validation.hasErrors()) {
            expense.setIdExpense(null);
            return new ModelAndView("layouts/trustee", "content", "expenseRegister");
        }
        expenseService.save(expense);
        return new ModelAndView("redirect:/trustee/expense");
    }

    @PutMapping("/cadastro")
    public ModelAndView putExpensesRegister(@Valid @ModelAttribute("idExpense") Expense expense, BindingResult validation,
                                            ModelMap model) {
        expenseService.validate(expense, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "expenseRegister");
            return new ModelAndView("layout/trustee", model);
        }
        expenseService.update(expense);
        return new ModelAndView("redirect:/trustee/expense");
    }

    @DeleteMapping("/delete")
    public ModelAndView deleteExpensesRegister(@RequestParam("idObj") Long idObj) {
        expenseService.delete(expenseService.read(idObj));
        return new ModelAndView();
    }
}
