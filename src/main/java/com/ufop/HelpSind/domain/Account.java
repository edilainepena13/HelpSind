package com.ufop.HelpSind.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "counts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable, Comparable<Account>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idaccount")
	private Long idAccount;
	
	@Size(min = 1, max = 2)
	@NotBlank
	private String type;
	
	@Size(max = 30)
	private String description;
	
	@Column(name = "initialbalance")
	private BigDecimal initialBalance;
	
	@Column(name = "currentBalance")
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
