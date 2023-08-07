package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.Report;

public interface ReportDao extends PagingAndSortingRepository<Report, Long>, CrudRepository<Report, Long>{
	
	Page<Report> findAllByCondominiumOrderByName(Condominium condominium, Pageable page);
	
	Boolean existsByCpfAndCondominium(String cpf, Condominium condominium);

	Boolean existsByCpfAndCondominiumAndIdReportNot(String cpf, Condominium condominium, Long idPessoa);


}
