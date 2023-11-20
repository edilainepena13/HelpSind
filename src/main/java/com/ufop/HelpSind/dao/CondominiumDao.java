package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Condominium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CondominiumDao extends PagingAndSortingRepository<Condominium, Long>, CrudRepository<Condominium, Long> {

    Boolean existsByCnpj(String cnpj);

    Boolean existsByCnpjAndIdCondominiumNot(String cnpj, Long idCondominium);

}
