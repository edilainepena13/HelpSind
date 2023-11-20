package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExpenseDao extends PagingAndSortingRepository<Expense, Long>, CrudRepository<Expense, Long> {


}
