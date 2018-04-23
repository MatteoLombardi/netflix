/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author UTENTE
 */
public class UtenteDTO {

    private long id;
    private String nome, cognome;
    private LocalDate dataNascita;
    private String codiceFiscale;
    private String mail;

    private List<AbbonamentoDTO> abbonamenti = new ArrayList<>();
    private List<FilmDTO> films = new ArrayList<>();

    public UtenteDTO() {
    }

    public UtenteDTO(long id, String nome, String cognome, LocalDate dataNascita, String codiceFiscale, String mail) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<AbbonamentoDTO> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(List<AbbonamentoDTO> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    public List<FilmDTO> getFilms() {
        return films;
    }

    public void setFilms(List<FilmDTO> films) {
        this.films = films;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.cognome);
        hash = 89 * hash + Objects.hashCode(this.dataNascita);
        hash = 89 * hash + Objects.hashCode(this.codiceFiscale);
        hash = 89 * hash + Objects.hashCode(this.mail);
        hash = 89 * hash + Objects.hashCode(this.abbonamenti);
        hash = 89 * hash + Objects.hashCode(this.films);
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
        final UtenteDTO other = (UtenteDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.codiceFiscale, other.codiceFiscale)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.dataNascita, other.dataNascita)) {
            return false;
        }
        if (!Objects.equals(this.abbonamenti, other.abbonamenti)) {
            return false;
        }
        if (!Objects.equals(this.films, other.films)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UtenteDTO{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + ", codiceFiscale=" + codiceFiscale + ", mail=" + mail + ", abbonamenti=" + abbonamenti + ", films=" + films + '}';
    }
}
