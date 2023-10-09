package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.ApportionmentDao;
import com.ufop.HelpSind.domain.Apportionment;
import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.service.ApportionmentService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@Transactional
public class ApportionmentServiceImpl implements ApportionmentService {

    @Autowired
    private ApportionmentDao apportionmentDao;

    @Autowired
    private UserService userService;

    @Override
    public void save(Apportionment entity) {

    }

    @Override
    public Apportionment read(Long id) {
        return null;
    }

    @Override
    public List<Apportionment> list() {
        return null;
    }

    @Override
    public Page<Apportionment> listPage(Pageable page) {
        Condominium condominium = userService.logged().getCondominium();

        if (condominium == null) {
            return Page.empty(page);
        }

        List<Apportionment> apportionmentList = apportionmentDao.findAllByCondominiumOrderByExpense_name(condominium, page).getContent();
        return new PageImpl<>(apportionmentList, page, apportionmentList.size());
    }

    @Override
    public void update(Apportionment entity) {

    }

    @Override
    public void delete(Apportionment entity) {

    }

    @Override
    public void validate(Apportionment entity, BindingResult validation) {

    }

}
