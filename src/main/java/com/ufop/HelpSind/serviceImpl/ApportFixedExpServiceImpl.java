package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.ApportFixedExpDao;
import com.ufop.HelpSind.domain.ApportFixedExp;
import com.ufop.HelpSind.service.ApportFixedExpService;
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

import com.ufop.HelpSind.domain.Condominium;

@Service
@Transactional
public class ApportFixedExpServiceImpl implements ApportFixedExpService{
	
	@Autowired
	private ApportFixedExpDao apportFixedExpDao;
	
	@Autowired
	private UserService userService;

	@Override
	public void save(ApportFixedExp apportFixedExp) {
		if (apportFixedExp.getIdApportFixedExp() == null) {
			apportFixedExp.setCondominium(userService.logged().getCondominium());
			apportFixedExpDao.save(apportFixedExp);
		}
		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ApportFixedExp read(Long id) {
		return apportFixedExpDao.findById(id).get();
	}
	@Override
	public Page<ApportFixedExp> listPage(Pageable page) {
    Condominium condominium = userService.logged().getCondominium();
    
    if (condominium == null) {
        return Page.empty(page);
    }
    
    List<ApportFixedExp> apportFixedExpList = apportFixedExpDao.findAllByCondominiumOrderByName(condominium, page).getContent();

	
    Page<ApportFixedExp> mergedPage = new PageImpl<>(apportFixedExpList, page, apportFixedExpList.size());
    
    return mergedPage;
}

	@Override
	public void update(ApportFixedExp apportFixedExp) {
		apportFixedExpDao.save(apportFixedExp);
	}

	@Override
	public void delete(ApportFixedExp apportFixedExp) {
		apportFixedExpDao.delete(apportFixedExp);		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void validate(ApportFixedExp apportFixedExp, BindingResult validation) {
		
		if(apportFixedExp.getIdApportFixedExp() == null) {
			if (apportFixedExp.getCpf() != null && apportFixedExpDao.existsByCpfAndCondominium(apportFixedExp.getCpf(), userService.logged().getCondominium())) {
				validation.rejectValue("cpf", "Unique");
			}
		} else {
			if (apportFixedExp.getCpf() != null && apportFixedExpDao.existsByCpfAndCondominiumAndIdApportFixedExpNot(apportFixedExp.getCpf(), userService.logged().getCondominium(), apportFixedExp.getIdApportFixedExp())) {
				validation.rejectValue("cpf", "Unique");
			}
		}
	}

	@Override
	public List<ApportFixedExp> list() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'list'");
	}
	
	public List<ApportFixedExp> listApportFixedExp() {
		throw new UnsupportedOperationException("Unimplemented method 'listApportFixedExp'");
	}

}
