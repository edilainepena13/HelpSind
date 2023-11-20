package com.ufop.HelpSind.dao;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.Condominium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ApartmentDao extends PagingAndSortingRepository<Apartment, Long>, CrudRepository<Apartment, Long> {

    Boolean existsByNumber(Integer number);

    Boolean existsByNumberAndIdApartmentNot(Integer number, Long idApartment);

    List<Apartment> findAllByCondominiumOrderByNumber(Condominium condominium);

    Page<Apartment> findAllByCondominiumOrderByNumber(Condominium condominium, Pageable pagina);

	List<Apartment> findByNumber(int apartmentNumber);
}
