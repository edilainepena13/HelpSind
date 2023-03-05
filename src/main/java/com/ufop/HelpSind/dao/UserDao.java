package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long>{
	
	User findOneByCpf (String cpf);
	
	Boolean existsUserByCpf (String cpf);
	
	Boolean existsUserByCpfAndId (String cpf, Long id);
	
	User findOneByNome(String name);
}
