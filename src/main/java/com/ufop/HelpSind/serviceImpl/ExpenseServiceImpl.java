package com.ufop.HelpSind.serviceImpl;

import com.ufop.HelpSind.dao.ExpenseDao;
import com.ufop.HelpSind.domain.ApartmentReading;
import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.Expense;
import com.ufop.HelpSind.enums.ApportionmentType;
import com.ufop.HelpSind.service.ApartmentService;
import com.ufop.HelpSind.service.ExpenseService;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @Override
    public void save(Expense expense) {
        if (expense.getIdExpense() == null) {
            expense.setCondominium(userService.logged().getCondominium());
            expenseDao.save(expense);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Expense read(Long id) {
        return expenseDao.findById(id).get();
    }

    @Override
    public List<Expense> list() {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return new ArrayList<>();
        }
        return condominium.getExpense();
    }


    @Override
    public void update(Expense expense) {
        standard(expense);
        expenseDao.save(expense);
    }

    @Override
    public void delete(Expense expense) {
        expenseDao.delete(expense);
    }

    @Override
    public void validate(Expense expense, BindingResult validation) {
        if (expense.getApportionmentTypeEnum().equals(ApportionmentType.P) && expense.getApportionmentProportional()
                .stream()
                .anyMatch(item -> item.getInitialReading() == null || item.getFinalReading() == null)) {
            //TODO revisar esse ponto
            validation.rejectValue("finalReading", "NotNull");
        }
    }

    @Override
    public BigDecimal inadimplencia() {
        Condominium condominium = userService.logged().getCondominium();
        BigDecimal result;

        if (condominium == null) {
            result = BigDecimal.ZERO.setScale(2);
        } else {
            //result = expenseDao.sumValueByCondominiumAndExpirationDateBeforeAndReceivingDateIsNull(condominium, LocalDate.now());
            //descomentar de cima e comentar a debaixo
            result = new BigDecimal("35,2");
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Expense> listarInadimplencia() {
        List<Expense> exepenses = new ArrayList<>();

        return exepenses;
    }

    @Override
    public List<Expense> findExpensesByType(ApportionmentType type) {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return new ArrayList<>();
        }
        return condominium.getExpense().stream().filter(item -> {
            return item.getApportionmentTypeEnum().getSigla().equalsIgnoreCase(type.getSigla());
        }).collect(Collectors.toList());
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void standard(Expense expense) {

        if (expense.getCondominium() == null) {
            expense.setCondominium(userService.logged().getCondominium());
        }

        this.createApartmentReadingSet(expense);
    }


    private void createApartmentReadingSet(Expense expense) {

        if (ApportionmentType.I.getSigla().equalsIgnoreCase(expense.getApportionmentTypeEnum().getSigla())) {
            return;
        }
        if (expense.getApartmentReadingList() == null || expense.getApartmentReadingList().isEmpty()) {
            return;
        }

        for (ApartmentReading apartmentReading : expense.getApartmentReadingList()) {
            apartmentReading.setCondominium(userService.logged().getCondominium());
        }
    }


    @Override
    public Page<Expense> listPage(Pageable page) {
        Condominium condominium = userService.logged().getCondominium();
        if (condominium == null) {
            return Page.empty(page);
        }
        return expenseDao.findAll(page);
    }

}
