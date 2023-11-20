package com.ufop.HelpSind.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "apartments")
public class Apartment implements Serializable, Comparable<Apartment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapartment")
    private Long idApartment;

    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominium")
    private Condominium condominium;

    public String getNameTenant() {
        return nameTenant;
    }

    public void setNameTenant(String nameTenant) {
        this.nameTenant = nameTenant;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    @Size(min = 1, max = 50)
    @Column(name = "name_tenant")
    private String nameTenant;

    @Size(min = 1, max = 50)
    @Column(name = "name_owner")
    private String nameOwner;

    //
//	@OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
//	private List<Expense> expenses = new ArrayList<>();
    public Apartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    public Apartment() {

    }

    public Long getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }


    @Override
    public int compareTo(Apartment ap) {
        return this.toString().compareTo(ap.toString());
    }

}
