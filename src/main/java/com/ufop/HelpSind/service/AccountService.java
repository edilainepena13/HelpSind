package com.ufop.HelpSind.service;

import com.ufop.HelpSind.domain.Account;

import java.math.BigDecimal;

public interface AccountService extends CrudService<Account, Long> {

    public BigDecimal currentBalance();

}
