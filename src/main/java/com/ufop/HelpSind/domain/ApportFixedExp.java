package com.ufop.HelpSind.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="apportFixedExp")
@Inheritance(strategy = InheritanceType.JOINED)
public class ApportFixedExp implements Serializable, Comparable<ApportFixedExp>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idapportFixedExp")
	private Long idApportFixedExp;
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String name;

	@Email
	@Size(max = 100)
	private String email;
	
	@CPF
	@NotBlank
	@Size(min=11, max=11)
	private String cpf;

	@Size(max = 15)
	private String phone;

	@Size(max = 15)
	private String cellphone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominium")
	private Condominium condominium;

	public Long getIdApportFixedExp() {
		return idApportFixedExp;
	}

	public void setIdApportFixedExp(Long idApportFixedExp) {
		this.idApportFixedExp = idApportFixedExp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Condominium getCondominium() {
		return condominium;
	}

	public void setCondominium(Condominium condominium) {
		this.condominium = condominium;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int compareTo(ApportFixedExp o) {
		return this.toString().compareTo(o.toString());
	}

}
