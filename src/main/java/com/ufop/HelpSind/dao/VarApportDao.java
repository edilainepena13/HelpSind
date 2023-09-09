package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.VarApport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;

import com.ufop.HelpSind.domain.Condominium;

public interface VarApportDao extends PagingAndSortingRepository<VarApport, Long>, CrudRepository<VarApport, Long>{
	
	Page<VarApport> findAllByCondominiumOrderByName(Condominium condominium, Pageable page);
	
	Boolean existsByCpfAndCondominium(String cpf, Condominium condominium);

	Boolean existsByCpfAndCondominiumAndIdVarApportNot(String cpf, Condominium condominium, Long idPessoa);


}
