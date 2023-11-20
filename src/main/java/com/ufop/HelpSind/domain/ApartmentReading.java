package com.ufop.HelpSind.domain;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "apartment_reading")
public class ApartmentReading implements Serializable, Comparable<ApartmentReading> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apartment_reading")
    private Long idApartmentReading;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominium")
    private Condominium condominium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apartment")
    private Apartment apartment;

    public ApartmentReading(Long idApartmentReading) {
        this.idApartmentReading = idApartmentReading;
    }

    public ApartmentReading() {
    }

    public ApartmentReading(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public int compareTo(ApartmentReading ap) {
        return this.toString().compareTo(ap.toString());
    }

    public Long getIdApartmentReading() {
        return idApartmentReading;
    }

    public void setIdApartmentReading(Long idApartmentReading) {
        this.idApartmentReading = idApartmentReading;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
