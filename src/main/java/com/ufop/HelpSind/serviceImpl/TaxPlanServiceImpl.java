package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.TaxPlanDao;
import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.TaxPlan;
import com.ufop.HelpSind.service.TaxPlanService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaxPlanServiceImpl implements TaxPlanService {

    @Autowired
    private TaxPlanDao taxPlanDao;

    @Autowired
    private UserService userService;

    @Override
    public void save(TaxPlan entity) {
        if (entity.getIdTaxPlan() == null) {
            taxPlanDao.save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public TaxPlan read(Long id) {
        return taxPlanDao.findById(id).get();
    }

    @Override
    public List<TaxPlan> list() {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return new ArrayList<>();
        }
        return condominium.getTaxPlan();
    }

    @Override
    public Page<TaxPlan> listPage(Pageable page) {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return Page.empty(page);
        }
        return taxPlanDao.findAllByCondominiumOrderByName(condominium, page);
    }

    @Override
    public void update(TaxPlan entity) {

    }

    @Override
    public void delete(TaxPlan entity) {
        taxPlanDao.delete(entity);
    }

    @Override
    public void validate(TaxPlan entity, BindingResult validation) {

    }


}
