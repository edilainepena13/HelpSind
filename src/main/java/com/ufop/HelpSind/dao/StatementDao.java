package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.Statement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatementDao extends PagingAndSortingRepository<Statement, Long>, CrudRepository<Statement, Long> {

    Page<Statement> findAllByCondominiumOrderByControlAccounts(Condominium condominium, Pageable page);

//    Boolean existsByCpfAndCondominium(String cpf, Condominium condominium);

//    Boolean existsByCpfAndCondominiumAndIdStatementNot(String cpf, Condominium condominium, Long idPessoa);


}
