package org.forit.netflix.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PXAEntityID implements Serializable {

    @Column(name = "ID_PERSONA")
    private long idPersona = -1;

    @Column(name = "ID_ABBONAMENTO")
    private long idAbbonamento = -1;

    public PXAEntityID() {
    }

    public PXAEntityID(long idPersona, long idAbbonamento) {
        this.idPersona = idPersona;
        this.idAbbonamento = idAbbonamento;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public long getIdAbbonamento() {
        return idAbbonamento;
    }

    public void setIdAbbonamento(long idAbbonamento) {
        this.idAbbonamento = idAbbonamento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.idPersona ^ (this.idPersona >>> 32));
        hash = 59 * hash + (int) (this.idAbbonamento ^ (this.idAbbonamento >>> 32));
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
        final PXAEntityID other = (PXAEntityID) obj;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idAbbonamento != other.idAbbonamento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PXAEntityID{" + "idPersona=" + idPersona + ", idAbbonamento=" + idAbbonamento + '}';
    }

}
