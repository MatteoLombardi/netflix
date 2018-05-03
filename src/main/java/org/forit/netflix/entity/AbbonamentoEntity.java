package org.forit.netflix.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abbonamento")
public class AbbonamentoEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long ID = -1;

    @Column(name = "DESCRIZIONE", unique = false, nullable = false)
    private String descrizione;

    @Column(name = "DURATA", unique = false, nullable = false)
    private int durata;

    @Column(name = "COSTO", unique = false, nullable = false, precision = 5, scale = 2)
    private BigDecimal costo;

    public AbbonamentoEntity() {
    }

    public AbbonamentoEntity(String descrizione, int durata, BigDecimal costo) {
        this.descrizione = descrizione;
        this.durata = durata;
        this.costo = costo;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 59 * hash + Objects.hashCode(this.descrizione);
        hash = 59 * hash + this.durata;
        hash = 59 * hash + Objects.hashCode(this.costo);
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
        final AbbonamentoEntity other = (AbbonamentoEntity) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.durata != other.durata) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.costo, other.costo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbbonamentoEntity{" + "ID=" + ID + ", descrizione=" + descrizione + ", durata=" + durata + ", costo=" + costo + '}';
    }
}
