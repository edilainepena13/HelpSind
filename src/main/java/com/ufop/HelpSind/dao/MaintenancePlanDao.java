package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.MaintenancePlan;
import com.ufop.HelpSind.domain.Condominium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MaintenancePlanDao extends PagingAndSortingRepository<MaintenancePlan, Long>, CrudRepository<MaintenancePlan, Long> {


    Page<MaintenancePlan> findAllByCondominiumOrderByName(Condominium condominium, Pageable page);

}
