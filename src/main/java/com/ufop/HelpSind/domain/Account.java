package com.ufop.HelpSind.domain;

import com.ufop.HelpSind.enums.BankAccountType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable, Comparable<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount")
    private Long idAccount;

    @NotNull
    private BankAccountType type;

    @Size(max = 30)
    private String description;

    @Column(name = "initialbalance")
    private BigDecimal initialBalance;

    @Column(name = "currentbalance")
    private BigDecimal currentBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominium")
    private Condominium condominium;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idCount) {
        this.idAccount = idCount;
    }

    public BankAccountType getType() {
        return type;
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }

    @Override
    public int compareTo(Account o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
