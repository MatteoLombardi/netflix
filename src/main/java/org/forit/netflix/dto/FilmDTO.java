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
public class FilmDTO {

    private long ID;
    private String titolo, descrizione;
    private int anno;
    private int durata; // in minuti
    private String nazione;
    private String lingua;
    private LocalDate dataNoleggio;
    private List<String> generi = new ArrayList<>();

    public FilmDTO() {
    }

    public FilmDTO(long ID, String titolo, String descrizione, int anno, int durata, String nazione, String lingua, LocalDate dataNoleggio) {
        this.ID = ID;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.anno = anno;
        this.durata = durata;
        this.nazione = nazione;
        this.lingua = lingua;
        this.dataNoleggio = dataNoleggio;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public LocalDate getDataNoleggio() {
        return dataNoleggio;
    }

    public void setDataNoleggio(LocalDate dataNoleggio) {
        this.dataNoleggio = dataNoleggio;
    }

    public List<String> getGeneri() {
        return generi;
    }

    public void setGeneri(List<String> generi) {
        this.generi = generi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 13 * hash + Objects.hashCode(this.titolo);
        hash = 13 * hash + Objects.hashCode(this.descrizione);
        hash = 13 * hash + this.anno;
        hash = 13 * hash + this.durata;
        hash = 13 * hash + Objects.hashCode(this.nazione);
        hash = 13 * hash + Objects.hashCode(this.lingua);
        hash = 13 * hash + Objects.hashCode(this.dataNoleggio);
        hash = 13 * hash + Objects.hashCode(this.generi);
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
        final FilmDTO other = (FilmDTO) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.anno != other.anno) {
            return false;
        }
        if (this.durata != other.durata) {
            return false;
        }
        if (!Objects.equals(this.titolo, other.titolo)) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.nazione, other.nazione)) {
            return false;
        }
        if (!Objects.equals(this.lingua, other.lingua)) {
            return false;
        }
        if (!Objects.equals(this.dataNoleggio, other.dataNoleggio)) {
            return false;
        }
        if (!Objects.equals(this.generi, other.generi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilmDTO{" + "ID=" + ID + ", titolo=" + titolo + ", descrizione=" + descrizione + ", anno=" + anno + ", durata=" + durata + ", nazione=" + nazione + ", lingua=" + lingua + ", dataNoleggio=" + dataNoleggio + ", generi=" + generi + '}';
    }
}
