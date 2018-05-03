package org.forit.netflix.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "nazione")
@NamedQueries({
    @NamedQuery(
            name = "nazione.selectAll",
            query = "select n from NazioneEntity n ORDER BY n.descrizione")
})
public class NazioneEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long ID = -1;

    @Column(name = "DESCRIZIONE", unique = false, nullable = true)
    private String descrizione;

    public NazioneEntity() {
    }

    public NazioneEntity(long ID, String descrizione) {
        this.ID = ID;
        this.descrizione = descrizione;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 53 * hash + Objects.hashCode(this.descrizione);
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
        final NazioneEntity other = (NazioneEntity) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NazioneEntity{" + "ID=" + ID + ", descrizione=" + descrizione + '}';
    }

}
