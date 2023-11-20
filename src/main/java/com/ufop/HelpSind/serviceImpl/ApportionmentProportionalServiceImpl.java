package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.ApportionmentProportionalDao;
import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.ApportionmentProportional;
import com.ufop.HelpSind.service.ApportionmentProportionalService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@Transactional
public class ApportionmentProportionalServiceImpl implements ApportionmentProportionalService {

    @Autowired
    private ApportionmentProportionalDao apportionmentProportionalDao;

    @Autowired
    private UserService userService;

    @Override
    public void save(ApportionmentProportional apportionmentProportional) {
        apportionmentProportionalDao.save(apportionmentProportional);
    }

    @Override
    public ApportionmentProportional read(Long id) {
        return null;
    }

    @Override
    public List<ApportionmentProportional> list() {
        return apportionmentProportionalDao.findAllByCondominium(userService.logged().getCondominium());
    }

    @Override
    public Page<ApportionmentProportional> listPage(Pageable page) {
        return null;
    }

    @Override
    public void update(ApportionmentProportional entity) {

    }

    @Override
    public void delete(ApportionmentProportional entity) {

    }

    @Override
    public void validate(ApportionmentProportional entity, BindingResult validation) {

    }
}
