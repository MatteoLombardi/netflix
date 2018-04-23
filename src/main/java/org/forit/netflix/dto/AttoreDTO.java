/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author UTENTE
 */
public class AttoreDTO {

    private long ID;
    private String nome, cognome;
    private LocalDate dataNascita;
    private String nazione;

    public AttoreDTO() {
    }

    public AttoreDTO(long ID, String nome, String cognome, LocalDate dataNascita, String nazione) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.nazione = nazione;
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

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 31 * hash + Objects.hashCode(this.nome);
        hash = 31 * hash + Objects.hashCode(this.cognome);
        hash = 31 * hash + Objects.hashCode(this.dataNascita);
        hash = 31 * hash + Objects.hashCode(this.nazione);
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
        final AttoreDTO other = (AttoreDTO) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.nazione, other.nazione)) {
            return false;
        }
        if (!Objects.equals(this.dataNascita, other.dataNascita)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AttoreDTO{" + "ID=" + ID + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + ", nazione=" + nazione + '}';
    }       
}
