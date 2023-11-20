package com.ufop.HelpSind.domain;

import com.ufop.HelpSind.enums.State;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "report")
@Inheritance(strategy = InheritanceType.JOINED)
public class Report implements Serializable, Comparable<Report> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreport")
    private Long idReport;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @Email
    @Size(max = 100)
    private String email;

    @CPF
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @Size(max = 15)
    private String phone;

    @Size(max = 15)
    private String cellphone;

    @Size(max = 100)
    private String address;

    @Size(max = 6)
    @Column(name = "addressnumber")
    private String addressNumber;

    @Size(max = 30)
    @Column(name = "addresscomplement")
    private String addressComplement;

    @Size(max = 30)
    private String neighborhood;

    @Size(max = 30)
    private String city;

    @Enumerated(EnumType.STRING)
    private State state;

    @Size(max = 8)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominium")
    private Condominium condominium;

    public Long getIdReport() {
        return idReport;
    }

    public void setIdReport(Long idReport) {
        this.idReport = idReport;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
    public int compareTo(Report o) {
        return this.toString().compareTo(o.toString());
    }

}
