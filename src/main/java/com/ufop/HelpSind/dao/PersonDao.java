package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonDao extends PagingAndSortingRepository<Person, Long>, CrudRepository<Person, Long> {

    Page<Person> findAllByCondominiumOrderByName(Condominium condominium, Pageable page);

    Boolean existsByCpfAndCondominium(String cpf, Condominium condominium);

    Boolean existsByCpfAndCondominiumAndIdPersonNot(String cpf, Condominium condominium, Long idPessoa);


}
