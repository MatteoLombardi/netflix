package org.forit.netflix.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "persona_x_abbonamento")
public class PXAEntity implements Serializable {

    @EmbeddedId
    private PXAEntityID ID;

    @ManyToOne
    @MapsId(value = "idPersona")
    private PersonaEntity persona;

    @ManyToOne
    @MapsId(value = "idAbbonamento")
    private AbbonamentoEntity abbonamento;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    @Column(name = "data_sottoscrizione")
    private LocalDate dataSottoscrizione;

    public PXAEntity() {
    }

    public PXAEntity(PXAEntityID ID, PersonaEntity persona, AbbonamentoEntity abbonamento, String metodoPagamento, LocalDate dataSottoscrizione) {
        this.ID = ID;
        this.persona = persona;
        this.abbonamento = abbonamento;
        this.metodoPagamento = metodoPagamento;
        this.dataSottoscrizione = dataSottoscrizione;
    }

    public PXAEntityID getID() {
        return ID;
    }

    public void setID(PXAEntityID ID) {
        this.ID = ID;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public AbbonamentoEntity getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(AbbonamentoEntity abbonamento) {
        this.abbonamento = abbonamento;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDate getDataSottoscrizione() {
        return dataSottoscrizione;
    }

    public void setDataSottoscrizione(LocalDate dataSottoscrizione) {
        this.dataSottoscrizione = dataSottoscrizione;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ID);
        hash = 53 * hash + Objects.hashCode(this.persona);
        hash = 53 * hash + Objects.hashCode(this.abbonamento);
        hash = 53 * hash + Objects.hashCode(this.metodoPagamento);
        hash = 53 * hash + Objects.hashCode(this.dataSottoscrizione);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PXAEntity other = (PXAEntity) obj;
        if (!Objects.equals(this.metodoPagamento, other.metodoPagamento)) {
            return false;
        }
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.abbonamento, other.abbonamento)) {
            return false;
        }
        if (!Objects.equals(this.dataSottoscrizione, other.dataSottoscrizione)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PXAEntity{" + "ID=" + ID + ", persona=" + persona + ", abbonamento=" + abbonamento + ", metodoPagamento=" + metodoPagamento + ", dataSottoscrizione=" + dataSottoscrizione + '}';
    }

}
