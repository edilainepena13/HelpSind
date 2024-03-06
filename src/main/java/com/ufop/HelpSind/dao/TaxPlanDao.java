package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.TaxPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaxPlanDao extends PagingAndSortingRepository<TaxPlan, Long>, CrudRepository<TaxPlan, Long> {


    Page<TaxPlan> findAllByCondominiumOrderByName(Condominium condominium, Pageable page);

}
