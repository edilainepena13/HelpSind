package com.ufop.HelpSind.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
@Table(name = "apportionment")
@Inheritance(strategy = InheritanceType.JOINED)
public class TotalCondominium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_apartment")
    private Double idApartment;

    @Column(name = "equal_apportionment")
    private int equalApportionment;

    @Column(name = "proportional_apportionment")
    private Double proportionalApportionment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(Double idApartment) {
        this.idApartment = idApartment;
    }

    public int getEqualApportionment() {
        return equalApportionment;
    }

    public void setEqualApportionment(int equalApportionment) {
        this.equalApportionment = equalApportionment;
    }

    public Double getProportionalApportionment() {
        return proportionalApportionment;
    }

    public void setProportionalApportionment(Double proportionalApportionment) {
        this.proportionalApportionment = proportionalApportionment;
    }

    public Double getCondominiumTotal() {
        return condominiumTotal;
    }

    public void setCondominiumTotal(Double condominiumTotal) {
        this.condominiumTotal = condominiumTotal;
    }

    public Double getFunds() {
        return funds;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }

    @Column(name = "condominium_total")
    private Double condominiumTotal;

    @Column(name = "funds")
    private Double funds;

    @Column(name = "total")
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominium")
    private Condominium condominium;

}
