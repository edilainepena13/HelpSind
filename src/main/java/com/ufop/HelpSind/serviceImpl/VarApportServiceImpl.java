package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.VarApportDao;
import com.ufop.HelpSind.domain.VarApport;
import com.ufop.HelpSind.service.VarApportService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@Transactional
public class VarApportServiceImpl implements VarApportService {
	
	@Autowired
	private VarApportDao varApportDao;
	
	@Autowired
	private UserService userService;

	@Override
	public void save(VarApport entity) {

	}

	@Override
	public VarApport read(String id) {
		return varApportDao.findById(id).orElse(null);
	}

	@Override
	public List<VarApport> list() {
		return varApportDao.findAllByIdCondominiumOrderByExpenseExpirationDateDesc(userService.logged().getCondominium().getIdCondominium());
	}

	@Override
	public Page<VarApport> listPage(Pageable page) {
		return varApportDao.findAllByIdCondominiumOrderByExpenseExpirationDateDesc(userService.logged().getCondominium().getIdCondominium(), page);
	}

	@Override
	public void update(VarApport entity) {

	}

	@Override
	public void delete(VarApport entity) {

	}

	@Override
	public void validate(VarApport entity, BindingResult validation) {

	}
}
