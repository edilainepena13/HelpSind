package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.ApportFixedExp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;

import com.ufop.HelpSind.domain.Condominium;

public interface ApportFixedExpDao extends PagingAndSortingRepository<ApportFixedExp, Long>, CrudRepository<ApportFixedExp, Long>{
	
	Page<ApportFixedExp> findAllByCondominiumOrderByName(Condominium condominium, Pageable page);
	
	Boolean existsByCpfAndCondominium(String cpf, Condominium condominium);

	Boolean existsByCpfAndCondominiumAndIdApportFixedExpNot(String cpf, Condominium condominium, Long idPessoa);


}
