package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.StatementDao;
import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.Statement;
import com.ufop.HelpSind.service.StatementService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@Transactional
public class StatementServiceImpl implements StatementService {

    @Autowired
    private StatementDao statementDao;

    @Autowired
    private UserService userService;

    @Override
    public void save(Statement statement) {
        if (statement.getIdStatement() == null) {
            statement.setCondominium(userService.logged().getCondominium());
            statementDao.save(statement);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Statement read(Long id) {
        return statementDao.findById(id).get();
    }

    /*
        @Override
        @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
        public List<Statement> list() {
            Condominium condominium = userService.logged().getCondominium();
            if (condominium == null) {
                return new ArrayList<Statement>();
            }
            return condominium.getPeople();
        }
     */
    @Override
    public Page<Statement> listPage(Pageable page) {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return Page.empty(page);
        }
        return statementDao.findAllByCondominiumOrderByControlAccounts(condominium, page);
    }

    @Override
    public void update(Statement statement) {
        statementDao.save(statement);
    }

    @Override
    public void delete(Statement statement) {
        statementDao.delete(statement);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void validate(Statement statement, BindingResult validation) {


    }

    @Override
    public List<Statement> list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }


}
