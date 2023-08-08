package com.ufop.HelpSind.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.ufop.HelpSind.domain.Expense;

public interface ExpenseDao extends PagingAndSortingRepository<Expense, Long>, CrudRepository<Expense, Long> {
	


}
