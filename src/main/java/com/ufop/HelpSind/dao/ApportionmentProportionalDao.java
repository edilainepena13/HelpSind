package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.ApportionmentProportional;
import com.ufop.HelpSind.domain.Condominium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApportionmentProportionalDao extends PagingAndSortingRepository<ApportionmentProportional, Long>, CrudRepository<ApportionmentProportional, Long> {
    List<ApportionmentProportional> findAllByCondominium(Condominium condominium);

}
