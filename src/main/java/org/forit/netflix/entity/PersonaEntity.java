package org.forit.netflix.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long ID = -1;

    @Column(name = "NOME", unique = false, nullable = false)
    private String nome;

    @Column(name = "COGNOME", unique = false, nullable = false)
    private String cognome;

    @Column(name = "DATA_DI_NASCITA", unique = false, nullable = false)
    private LocalDate dataNascita;

    public PersonaEntity() {
    }

    public PersonaEntity(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.cognome);
        hash = 23 * hash + Objects.hashCode(this.dataNascita);
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
        final PersonaEntity other = (PersonaEntity) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.dataNascita, other.dataNascita)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonaEntity{" + "ID=" + ID + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + '}';
    }

}
