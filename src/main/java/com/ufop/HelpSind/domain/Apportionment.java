package com.ufop.HelpSind.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
@Table(name = "apportionment")
@Inheritance(strategy = InheritanceType.JOINED)
public class Apportionment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Double value;

    @Column(name = "number_of_apartments")
    private int numberOfApartments;

    @Column(name = "value_for_apartments")
    private Double valueForApartments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expense")
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominium")
    private Condominium condominium;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getNumberOfApartments() {
        return numberOfApartments;
    }

    public void setNumberOfApartments(int numberOfApartments) {
        this.numberOfApartments = numberOfApartments;
    }

    public Double getValueForApartments() {
        return valueForApartments;
    }

    public void setValueForApartments(Double valueForApartments) {
        this.valueForApartments = valueForApartments;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
