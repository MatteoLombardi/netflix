package org.forit.netflix.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 *
 * @author UTENTE
 */
@Entity
@Table(name = "persona")
@SecondaryTable(name = "utente", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID"))
@org.hibernate.annotations.Table(appliesTo = "utente", optional = false)
@NamedQueries({
    @NamedQuery(
            name = "utente.selectAll",
            query = "SELECT u FROM UtenteEntity u ORDER BY u.cognome, u.nome"
    )
})
public class UtenteEntity extends PersonaEntity implements Serializable {

    @Column(table = "utente", name = "CODICE_FISCALE", unique = false, nullable = false)
    private String codiceFiscale;

    @Column(table = "utente", name = "MAIL", unique = false, nullable = false)
    private String mail;

    @ElementCollection()
    @CollectionTable(name = "persona_x_abbonamento", joinColumns = @JoinColumn(name = "id_persona"))
    private List<AbbonamentoPerUtenteEntity> abbonamenti = new ArrayList<>();

    public UtenteEntity() {
    }

    public UtenteEntity(String codiceFiscale, String mail, String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
        this.codiceFiscale = codiceFiscale;
        this.mail = mail;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<AbbonamentoPerUtenteEntity> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(List<AbbonamentoPerUtenteEntity> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + super.hashCode();
        hash = 53 * hash + Objects.hashCode(this.codiceFiscale);
        hash = 53 * hash + Objects.hashCode(this.mail);
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
        if (!super.equals(obj)) {
            return false;
        }
        final UtenteEntity other = (UtenteEntity) obj;
        if (!Objects.equals(this.codiceFiscale, other.codiceFiscale)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UtenteEntity{" + "codiceFiscale=" + codiceFiscale + ", mail=" + mail + ", " + super.toString() + "}";
    }
}
