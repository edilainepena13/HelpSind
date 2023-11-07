package com.ufop.HelpSind.domain;

import java.util.Date;
import java.util.List;

public class ApportionmentProportionalGeneralDataDto {

    private Double value;

    private Double initialReading;

    private Double finalReading;

    private Double commonCondominium;
    private Expense expense;

    private Date readingDate;

    private int days;

    private int currentMonth;

    private List<ApportionmentProportional> apportionmentProportional;

    public ApportionmentProportionalGeneralDataDto() {
    }

    public ApportionmentProportionalGeneralDataDto(Double value, Double initialReading, Double finalReading, Double commonCondominium, Expense expense, Date readingDate, int days, int currentMonth, List<ApportionmentProportional> apportionmentProportional) {
        this.value = value;
        this.initialReading = initialReading;
        this.finalReading = finalReading;
        this.commonCondominium = commonCondominium;
        this.expense = expense;
        this.readingDate = readingDate;
        this.days = days;
        this.currentMonth = currentMonth;
        this.apportionmentProportional = apportionmentProportional;
    }

    public List<ApportionmentProportional> getApportionmentProportional() {
        return apportionmentProportional;
    }

    public void setApportionmentProportional(List<ApportionmentProportional> apportionmentProportional) {
        this.apportionmentProportional = apportionmentProportional;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getInitialReading() {
        return initialReading;
    }

    public void setInitialReading(Double initialReading) {
        this.initialReading = initialReading;
    }

    public Double getFinalReading() {
        return finalReading;
    }

    public void setFinalReading(Double finalReading) {
        this.finalReading = finalReading;
    }

    public Double getCommonCondominium() {
        return commonCondominium;
    }

    public void setCommonCondominium(Double commonCondominium) {
        this.commonCondominium = commonCondominium;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }
}
