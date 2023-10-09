package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Apportionment;
import com.ufop.HelpSind.domain.Condominium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApportionmentDao extends PagingAndSortingRepository<Apportionment, Long>, CrudRepository<Apportionment, Long> {

    Page<Apportionment> findAllByCondominiumOrderByExpense_name(Condominium condominium, Pageable page);
}
