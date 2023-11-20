package com.ufop.HelpSind.controller;


import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.ApportionmentProportional;
import com.ufop.HelpSind.domain.Expense;
import com.ufop.HelpSind.enums.ApportionmentType;
import com.ufop.HelpSind.service.ApartmentService;
import com.ufop.HelpSind.service.ApportionmentProportionalService;
import com.ufop.HelpSind.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("trustee/apportionment")
public class ApportionmentController {

    @Autowired
    private ApportionmentProportionalService apportionmentProportionalService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getApportionments(@RequestParam("pagina") Optional<Integer> pagina, @RequestParam("size") Optional<Integer> size, ModelMap model) {
        int totalApartments = apartmentService.list().size();
        double totalPerApartment = 0;
        List<Expense> expenseList = expenseService.findExpensesByType(ApportionmentType.I);
        List<ApportionmentProportional> apportionmentProportional = apportionmentProportionalService.list();
        List<Apartment> apartment = apartmentService.list();
        List<CondominioTotal> condominioTotal = new ArrayList<>();

        for (Expense expense : expenseList) {
            expense.setTotalPerApartment(expense.getTotal().doubleValue() / totalApartments);
            totalPerApartment += (double) (expense.getTotal().doubleValue() / totalApartments);
        }

        for (Apartment apartment1: apartment){
            CondominioTotal condominioTotal1 = new CondominioTotal();
            condominioTotal1.setRateioIgualitario(totalPerApartment);
            condominioTotal1.setApto(String.valueOf(apartment1.getNumber()));

            double consumoProporcional = 0;
            for (ApportionmentProportional apportionmentProportional1 : apportionmentProportional) {
                if (apartment1.getIdApartment() == apportionmentProportional1.getApartment().getIdApartment()){
                    consumoProporcional += apportionmentProportional1.getValue();
                }
            }
            condominioTotal1.setRateioProporcional(consumoProporcional);
            condominioTotal1.setTotalCondominio(consumoProporcional + totalPerApartment);
            condominioTotal1.setFundoReservaObra(70.00);
            condominioTotal1.setTotalGeral(consumoProporcional + totalPerApartment + 70.00);

            condominioTotal.add(condominioTotal1);

        }


        model.addAttribute("apportionment", expenseList);
        model.addAttribute("totalApartment", apartmentService.list().size());
        model.addAttribute("apportionmentProportional", apportionmentProportional);
        model.addAttribute("condominioTotal", condominioTotal);

        model.addAttribute("content", "apportionmentList");
        return new ModelAndView("layouts/trustee", model);
    }

    private static class CondominioTotal {
        private String apto;
        private Double rateioIgualitario;
        private Double rateioProporcional;
        private Double totalCondominio;
        private Double fundoReservaObra;
        private Double totalGeral;

        public CondominioTotal(String apto, Double rateioIgualitario, Double rateioProporcional, Double totalCondominio, Double fundoReservaObra, Double totalGeral) {
            this.apto = apto;
            this.rateioIgualitario = rateioIgualitario;
            this.rateioProporcional = rateioProporcional;
            this.totalCondominio = totalCondominio;
            this.fundoReservaObra = fundoReservaObra;
            this.totalGeral = totalGeral;
        }

        public CondominioTotal() {
        }

        public String getApto() {
            return apto;
        }

        public void setApto(String apto) {
            this.apto = apto;
        }

        public Double getRateioIgualitario() {
            return rateioIgualitario;
        }

        public void setRateioIgualitario(Double rateioIgualitario) {
            this.rateioIgualitario = rateioIgualitario;
        }

        public Double getRateioProporcional() {
            return rateioProporcional;
        }

        public void setRateioProporcional(Double rateioProporcional) {
            this.rateioProporcional = rateioProporcional;
        }

        public Double getTotalCondominio() {
            return totalCondominio;
        }

        public void setTotalCondominio(Double totalCondominio) {
            this.totalCondominio = totalCondominio;
        }

        public Double getFundoReservaObra() {
            return fundoReservaObra;
        }

        public void setFundoReservaObra(Double fundoReservaObra) {
            this.fundoReservaObra = fundoReservaObra;
        }

        public Double getTotalGeral() {
            return totalGeral;
        }

        public void setTotalGeral(Double totalGeral) {
            this.totalGeral = totalGeral;
        }
    }

}
