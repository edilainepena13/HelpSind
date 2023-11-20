package com.ufop.HelpSind.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufop.HelpSind.enums.ApportionmentType;
import com.ufop.HelpSind.enums.ExpenseType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "expenses")
public class Expense implements Serializable, Comparable<Expense>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idexpense")
	private Long idExpense;
	
	@Column(name = "name")
	private String name;

    @Column(name = "MinimumRate")
    private String MinimumRate;


    @Column(name = "MinimumConsumption")
    private String MinimumConsumption;
    
    @Column(name = "comum_consumption")
    private Double comumConsumption;

    @NotNull
    @Min(1)
    @Column(name = "referenceMonth")
    private Integer referenceMonth;

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    @Column(name = "total_days")
    private Integer totalDays;

    public Integer getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(Integer referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public String getMinimumConsumption() {
        return MinimumConsumption;
    }

    public void setMinimumConsumption(String minimumConsumption) {
        MinimumConsumption = minimumConsumption;
    }
    
    @NotNull
    @Column(name = "apportionment_type")
    @Enumerated(EnumType.STRING)
    private ApportionmentType apportionmentTypeEnum;
    @NotNull
    @Column(name = "expense_type")
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseTypeEnum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idapartment")
    private Apartment apartment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominium")
    private Condominium condominium;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "expense_apartment_reading", joinColumns = @JoinColumn(name = "idexpense"), inverseJoinColumns = @JoinColumn(name = "id_apartment_reading"))
    private Set<ApartmentReading> apartmentReadingSet = new HashSet<>();
    @Transient
    private List<ApartmentReading> apartmentReadingList = new ArrayList<>();
    @NotNull
    @Min(0)
    private BigDecimal total;
    @Transient
    private Boolean child = Boolean.FALSE;
    @Transient
    private List<ApportionmentProportional> apportionmentProportional;
    @Column(name = "initial_reading")
    private Double initialReading;
    @Column(name = "final_reading")
    private Double finalReading;

    @Column(name = "reading_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date readingDate;

    @Transient
    private Double totalPerApartment;

    public Double getTotalPerApartment() {
        return totalPerApartment;
    }

    public void setTotalPerApartment(Double totalPerApartment) {
        this.totalPerApartment = totalPerApartment;
    }

    public Expense() {
    }

    public Expense(String name,
                   ApportionmentType apportionmentTypeEnum, ExpenseType expenseTypeEnum, Apartment apartment, Condominium condominium,
                   BigDecimal total) {
        this.name = name;
        this.apportionmentTypeEnum = apportionmentTypeEnum;
        this.expenseTypeEnum = expenseTypeEnum;
        this.apartment = apartment;
        this.condominium = condominium;
        this.total = total;
    }

    public Expense(Expense expense, Apartment apartment, BigDecimal total) {
        this.name = this.getChildName(expense, apartment);
        this.apportionmentTypeEnum = expense.getApportionmentTypeEnum();
        this.apportionmentTypeEnum = expense.getApportionmentTypeEnum();
        this.apartment = apartment;
        this.condominium = expense.getCondominium();
        this.total = total;
        this.child = true;
    }

    public Double getComumConsumption() {
        return comumConsumption;
    }

    public void setComumConsumption(Double comumConsumption) {
        this.comumConsumption = comumConsumption;
    }

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
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

    public Set<ApartmentReading> getApartmentReadingSet() {
        return apartmentReadingSet;
    }

    public void setApartmentReadingSet(Set<ApartmentReading> apartmentReadingSet) {
        this.apartmentReadingSet = apartmentReadingSet;
    }

    public List<ApportionmentProportional> getApportionmentProportional() {
        return apportionmentProportional;
    }

    public void setApportionmentProportional(List<ApportionmentProportional> apportionmentProportional) {
        this.apportionmentProportional = apportionmentProportional;
    }

    public Long getIdExpense() {
        return idExpense;
    }

	public void setIdExpense(Long idExpense) {
		this.idExpense = idExpense;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getMinimumRate() {
		return MinimumRate;
	}

	public void setMinimumRate(String minimumRate) {
		this.MinimumRate = minimumRate;
	}
	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public Condominium getCondominium() {
		return condominium;
	}

	public void setCondominium(Condominium condominium) {
		this.condominium = condominium;
	}

    public BigDecimal getTotal() {
        return total;
    }

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

    public ApportionmentType getApportionmentTypeEnum() {
        return apportionmentTypeEnum;
    }

    public void setApportionmentTypeEnum(ApportionmentType apportionmentTypeEnum) {
        this.apportionmentTypeEnum = apportionmentTypeEnum;
    }

    public ExpenseType getExpenseTypeEnum() {
        return expenseTypeEnum;
    }

    public void setExpenseTypeEnum(ExpenseType expenseTypeEnum) {
        this.expenseTypeEnum = expenseTypeEnum;
    }

	public void setApartmentReadingSet(Set<ApartmentReading> apartmentReadingSet) {
		this.apartmentReadingSet = apartmentReadingSet;
	}

	public List<ApartmentReading> getApartmentReadingList() {
		return apartmentReadingList;
	}

	public void setApartmentReadingList(List<ApartmentReading> apartmentReadingList) {
		this.apartmentReadingList = apartmentReadingList;
	}

	public Boolean getChild() {
		return child;
	}

	public void setChild(Boolean child) {
		this.child = child;
	}

	@Override
	public int compareTo(Expense o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String getChildName(Expense expense, Apartment apartment){
		String name = expense.getName() + " (Apartamento: %s)";
		return String.format(name, apartment.getNumber());
	}

    @JsonProperty
    public String getApportionmentTypeEnumComplete() {
        return ApportionmentType.P.getSigla().equals(this.apportionmentTypeEnum.getSigla()) ? "Proporcional" : "Igualitário";
    }

    @JsonProperty
    public String getExpenseTypeEnumComplete() {
        return ApportionmentType.P.getSigla().equals(this.expenseTypeEnum.getSigla()) ? "Proporcional" : "Igualitário";
    }

}