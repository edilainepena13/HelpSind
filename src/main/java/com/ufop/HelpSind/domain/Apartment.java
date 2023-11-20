package com.ufop.HelpSind.domain;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idperson")
    private Person person;
//
//	@OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
//	private List<Expense> expenses = new ArrayList<>();
    private String typePerson;

    public Apartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    public Apartment() {

    }

    public String getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(String typePerson) {
        this.typePerson = typePerson;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int compareTo(Apartment ap) {
        return this.toString().compareTo(ap.toString());
    }

}
