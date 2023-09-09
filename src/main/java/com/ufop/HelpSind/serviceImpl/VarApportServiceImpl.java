package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.ApportFixedExpDao;
import com.ufop.HelpSind.dao.VarApportDao;
import com.ufop.HelpSind.domain.VarApport;
import com.ufop.HelpSind.service.VarApportService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;

import com.ufop.HelpSind.domain.ApportFixedExp;
import com.ufop.HelpSind.domain.Condominium;

@Service
@Transactional
public class VarApportServiceImpl implements VarApportService{
	
	@Autowired
	private VarApportDao varApportDao;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ApportFixedExpDao apportFixedExpDao;

	@Override
	public void save(VarApport varApport) {
		if (varApport.getIdVarApport() == null) {
			varApport.setCondominium(userService.logged().getCondominium());
			varApportDao.save(varApport);
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public VarApport read(Long id) {
		return varApportDao.findById(id).get();
	}
@Override
public Page<VarApport> listPage(Pageable page) {
    Condominium condominium = userService.logged().getCondominium();
    
    if (condominium == null) {
        return Page.empty(page);
    }
    
    List<VarApport> varApportList = varApportDao.findAllByCondominiumOrderByName(condominium, page).getContent();
    
	List<ApportFixedExp> apportFixedExpList = apportFixedExpDao.findAllByCondominiumOrderByName(condominium, page).getContent();

	for (VarApport varApport : varApportList) {
        varApport.setApportFixedExpList(apportFixedExpList);
    }
	
    Page<VarApport> mergedPage = new PageImpl<>(varApportList, page, varApportList.size());
    
    return mergedPage;
}

	@Override
	public void update(VarApport varApport) {
		varApportDao.save(varApport);
	}

	@Override
	public void delete(VarApport varApport) {
		varApportDao.delete(varApport);		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void validate(VarApport varApport, BindingResult validation) {
		
		if(varApport.getIdVarApport() == null) {
			if (varApport.getCpf() != null && varApportDao.existsByCpfAndCondominium(varApport.getCpf(), userService.logged().getCondominium())) {
				validation.rejectValue("cpf", "Unique");
			}
		} else {
			if (varApport.getCpf() != null && varApportDao.existsByCpfAndCondominiumAndIdVarApportNot(varApport.getCpf(), userService.logged().getCondominium(), varApport.getIdVarApport())) {
				validation.rejectValue("cpf", "Unique");
			}
		}
	}

	@Override
	public List<VarApport> list() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'list'");
	}
	
	public List<ApportFixedExp> listApportFixedExp() {
		throw new UnsupportedOperationException("Unimplemented method 'listApportFixedExp'");
	}

}
