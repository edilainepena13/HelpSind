package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.MaintenancePlanDao;
import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.MaintenancePlan;
import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.service.MaintenancePlanService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaintenancePlanServiceImpl implements MaintenancePlanService {

    @Autowired
    private MaintenancePlanDao maintenancePlanDao;

    @Autowired
    private UserService userService;

    @Override
    public void save(MaintenancePlan entity) {
        if (entity.getIdMaintenancePlan() == null) {
            maintenancePlanDao.save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MaintenancePlan read(Long id) {
        return maintenancePlanDao.findById(id).get();
    }

    @Override
    public List<MaintenancePlan> list() {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return new ArrayList<>();
        }
        return condominium.getMaintenancePlan();
    }

    @Override
    public Page<MaintenancePlan> listPage(Pageable page) {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return Page.empty(page);
        }
        return maintenancePlanDao.findAllByCondominiumOrderByName(condominium, page);
    }

    @Override
    public void update(MaintenancePlan entity) {

    }

    @Override
    public void delete(MaintenancePlan entity) {
        maintenancePlanDao.delete(entity);
    }

    @Override
    public void validate(MaintenancePlan entity, BindingResult validation) {

    }


}
