package com.ufop.HelpSind.service;

import com.ufop.HelpSind.domain.Expense;
import com.ufop.HelpSind.enums.ApportionmentType;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService extends CrudService<Expense, Long> {


    public BigDecimal inadimplencia();


    public List<Expense> listarInadimplencia();

    public List<Expense> findExpensesByType(ApportionmentType type);

}
