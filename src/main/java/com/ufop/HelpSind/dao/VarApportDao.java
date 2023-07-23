package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.VarApport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarApportDao extends PagingAndSortingRepository<VarApport, String>{

	Page<VarApport> findAllByIdCondominiumOrderByExpenseExpirationDateDesc(Long condominium, Pageable pagina);
	List<VarApport> findAllByIdCondominiumOrderByExpenseExpirationDateDesc(Long condominium);
}
