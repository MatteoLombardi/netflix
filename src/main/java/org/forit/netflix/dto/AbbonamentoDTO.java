/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author UTENTE
 */
public class AbbonamentoDTO {

    private long ID;
    private String descrizione;
    private int durata; // in mesi
    private BigDecimal costo;
    private String metodoPagamento;
    private LocalDate dataSottoscrizione;

    public AbbonamentoDTO() {
    }

    public AbbonamentoDTO(long ID, String descrizione, int durata, BigDecimal costo, String metodoPagamento, LocalDate dataSottoscrizione) {
        this.ID = ID;
        this.descrizione = descrizione;
        this.durata = durata;
        this.costo = costo;
        this.metodoPagamento = metodoPagamento;
        this.dataSottoscrizione = dataSottoscrizione;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
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
        int hash = 7;
        hash = 31 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 31 * hash + Objects.hashCode(this.descrizione);
        hash = 31 * hash + this.durata;
        hash = 31 * hash + Objects.hashCode(this.costo);
        hash = 31 * hash + Objects.hashCode(this.metodoPagamento);
        hash = 31 * hash + Objects.hashCode(this.dataSottoscrizione);
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
        final AbbonamentoDTO other = (AbbonamentoDTO) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.durata != other.durata) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.metodoPagamento, other.metodoPagamento)) {
            return false;
        }
        if (!Objects.equals(this.costo, other.costo)) {
            return false;
        }
        if (!Objects.equals(this.dataSottoscrizione, other.dataSottoscrizione)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbbonamentoDTO{" + "ID=" + ID + ", descrizione=" + descrizione + ", durata=" + durata + ", costo=" + costo + ", metodoPagamento=" + metodoPagamento + ", dataSottoscrizione=" + dataSottoscrizione + '}';
    }
}
