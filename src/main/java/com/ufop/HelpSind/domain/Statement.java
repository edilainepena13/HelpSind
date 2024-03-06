package com.ufop.HelpSind.domain;

import com.ufop.HelpSind.enums.State;
//import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "statement")
@Inheritance(strategy = InheritanceType.JOINED)
public class Statement implements Serializable, Comparable<Statement> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstatement")
    private Long idStatement;

    @Column(name = "control_accounts")
    private String controlAccounts;

    @Column(name = "funds")
    private String funds;

    @Column(name = "total_paid")
    private String totalPaid;

    public Long getIdStatement() {
        return idStatement;
    }

    public void setIdStatement(Long idStatement) {
        this.idStatement = idStatement;
    }

    public String getControlAccounts() {
        return controlAccounts;
    }

    public void setControlAccounts(String controlAccounts) {
        this.controlAccounts = controlAccounts;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(String totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(String totalOutput) {
        this.totalOutput = totalOutput;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }

    @Column(name = "total_expenses")
    private String totalExpenses;

    @Column(name = "expense")
    private String expense;

    @Column(name = "total_output")
    private String totalOutput;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominium")
    private Condominium condominium;



    @Override
    public int compareTo(Statement o) {
        return this.toString().compareTo(o.toString());
    }

}
