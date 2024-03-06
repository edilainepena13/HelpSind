package com.ufop.HelpSind.domain;

import com.ufop.HelpSind.enums.State;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "apartments")
public class Apartment implements Serializable, Comparable<Apartment> {
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

    public String getCellphoneTenant() {
        return cellphoneTenant;
    }

    public void setCellphoneTenant(String cellphoneTenant) {
        this.cellphoneTenant = cellphoneTenant;
    }

    public String getCellphoneOwner() {
        return cellphoneOwner;
    }

    public void setCellphoneOwner(String cellphoneOwner) {
        this.cellphoneOwner = cellphoneOwner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpfTenant() {
        return cpfTenant;
    }

    public void setCpfTenant(String cpfTenant) {
        this.cpfTenant = cpfTenant;
    }

    public String getCpfOwner() {
        return cpfOwner;
    }

    public void setCpfOwner(String cpfOwner) {
        this.cpfOwner = cpfOwner;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapartment")
    private Long idApartment;

    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominium")
    private Condominium condominium;

    @Size(max = 15)
    private String cellphoneTenant;

    @Size(max = 15)
    private String cellphoneOwner;

    @Size(max = 30)
    @Column(name = "address")
    private String address;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cpfTenant;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cpfOwner;

    @NotBlank
    @Size(min = 1, max = 6)
    @Column(name = "addressnumber")
    private String addressNumber;

    @NotBlank
    @Size(min = 1, max = 30)
    private String neighborhood;

    @NotBlank
    @Size(min = 1, max = 30)
    private String city;

    @NotBlank
    @Size(min = 9, max = 9)
    private String cep;

    @NotNull
    @Enumerated(EnumType.STRING)
    private State state;

    @Size(min = 1, max = 50)
    @Column(name = "name_tenant")
    private String nameTenant;

    @Size(min = 1, max = 50)
    @Column(name = "name_owner")
    private String nameOwner;

    public Apartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    public Apartment() {

    }

    @Override
    public int compareTo(Apartment ap) {
        return this.toString().compareTo(ap.toString());
    }

}
