package com.ufop.HelpSind.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CrudService<C, T> {

    public void save(C entity);

    public C read(T id);

    public List<C> list();

    public Page<C> listPage(Pageable page);

    public void update(C entity);

    public void delete(C entity);

    public void validate(C entity, BindingResult validation);
}
