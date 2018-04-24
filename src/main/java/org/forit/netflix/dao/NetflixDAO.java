/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.forit.netflix.dto.AbbonamentoDTO;
import org.forit.netflix.dto.AttoreDTO;
import org.forit.netflix.dto.FilmDTO;
import org.forit.netflix.dto.UtenteDTO;
import org.forit.netflix.exception.NetflixException;

/**
 *
 * @author UTENTE
 */
public class NetflixDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/netflix?user=forit&password=12345&useSSL=false";

    private static final String LISTA_UTENTI
            = "SELECT p.*, u.CODICE_FISCALE, u.MAIL "
            + "FROM persona p, utente u "
            + "WHERE p.ID = u.ID "
            + "ORDER BY p.cognome, p.nome";

    private static final String UTENTE
            = "SELECT p.*, u.CODICE_FISCALE, u.MAIL "
            + "FROM persona p, utente u "
            + "WHERE p.ID=? AND p.ID = u.ID";

    private static final String ABBONAMENTI_X_PERSONA
            = "SELECT a.*, pxa.METODO_PAGAMENTO, pxa.DATA_SOTTOSCRIZIONE "
            + "FROM abbonamento a, persona_x_abbonamento pxa "
            + "WHERE id_persona = ? AND a.id = pxa.ID_ABBONAMENTO";

    private static final String FILMS_X_PERSONA
            = "SELECT f.*, n.DESCRIZIONE nazione, l.DESCRIZIONE lingua, pxf.DATA_NOLEGGIO "
            + "FROM film f, persona_x_film pxf, nazione n, lingua l "
            + "WHERE pxf.id_persona = ? AND pxf.ID_FILM = f.ID and f.ID_NAZIONE = n.id and l.id=f.ID_LINGUA";

    private static final String LISTA_ATTORI
            = "SELECT p.*, n.DESCRIZIONE "
            + "FROM persona p, attore a, nazione n "
            + "WHERE p.ID = a.ID AND a.ID_NAZIONE = n.ID "
            + "ORDER BY p.cognome, p.nome";

    private static final String INSERISCI_PERSONA
            = "INSERT INTO PERSONA (NOME, COGNOME, DATA_NASCITA) "
            + "VALUES (?,?,?)";

    private static final String INSERISCI_ATTORE = "INSERT INTO ATTORE (ID, ID_NAZIONE) VALUES (?,?)";

    private static final String ADD_PREMIO_TO_ENTITA
            = "INSERT INTO ENTITA_X_PREMIO (ID_ENTITA, ID_PREMIO, ANNO) "
            + "VALUES (?,?,?)";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
        }
    }

    public List<UtenteDTO> getListaUtenti() throws NetflixException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(LISTA_UTENTI)) {

            List<UtenteDTO> listaUtenti = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cognome = rs.getString("COGNOME");
                LocalDate dataNascita = rs.getDate("DATA_NASCITA").toLocalDate();
                String codiceFiscale = rs.getString("CODICE_FISCALE");
                String mail = rs.getString("MAIL");

                UtenteDTO utente = new UtenteDTO(id, nome, cognome, dataNascita, codiceFiscale, mail);
                listaUtenti.add(utente);
            }
            return listaUtenti;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new NetflixException(ex);
        }
    }

    public UtenteDTO getUtente(long ID) throws NetflixException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps1 = conn.prepareStatement(UTENTE);
                PreparedStatement ps2 = conn.prepareStatement(ABBONAMENTI_X_PERSONA);
                PreparedStatement ps3 = conn.prepareStatement(FILMS_X_PERSONA)) {

            ps1.setLong(1, ID);
            ResultSet rs = ps1.executeQuery();
            rs.next();

            String nome = rs.getString("NOME");
            String cognome = rs.getString("COGNOME");
            LocalDate dataNascita = rs.getDate("DATA_NASCITA").toLocalDate();
            String codiceFiscale = rs.getString("CODICE_FISCALE");
            String mail = rs.getString("MAIL");

            UtenteDTO utente = new UtenteDTO(ID, nome, cognome, dataNascita, codiceFiscale, mail);

            ps2.setLong(1, ID);
            rs = ps2.executeQuery();
            while (rs.next()) {
                long idAbbonamento = rs.getLong("ID");
                String descrizioneAbbonamento = rs.getString("DESCRIZIONE");
                int durataAbbonamento = rs.getInt("DURATA");
                BigDecimal costoAbbonamento = rs.getBigDecimal("COSTO");
                String metodoPagamentoAbbonamento = rs.getString("METODO_PAGAMENTO");
                LocalDate dataSottoscrizioneAbbonamento = rs.getDate("DATA_SOTTOSCRIZIONE").toLocalDate();

                AbbonamentoDTO abbonamento = new AbbonamentoDTO(idAbbonamento, descrizioneAbbonamento, durataAbbonamento, costoAbbonamento, metodoPagamentoAbbonamento, dataSottoscrizioneAbbonamento);
                utente.getAbbonamenti().add(abbonamento);
            }

            ps3.setLong(1, ID);
            rs = ps3.executeQuery();
            while (rs.next()) {
                long idFilm = rs.getLong("ID");
                String titoloFilm = rs.getString("TITOLO");
                String descrizioneFilm = rs.getString("DESCRIZIONE");
                int annoFilm = rs.getInt("ANNO");
                int durataFilm = rs.getInt("DURATA");
                String nazioneFilm = rs.getString("NAZIONE");
                String linguaFilm = rs.getString("LINGUA");
                LocalDate dataNoleggioFilm = rs.getDate("DATA_NOLEGGIO").toLocalDate();

                FilmDTO film = new FilmDTO(idFilm, titoloFilm, descrizioneFilm, annoFilm, durataFilm, nazioneFilm, linguaFilm, dataNoleggioFilm);
                utente.getFilms().add(film);
            }

            return utente;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new NetflixException(ex);
        }
    }

    public List<AttoreDTO> getListaAttori() throws NetflixException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(LISTA_ATTORI)) {

            List<AttoreDTO> listaAttori = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cognome = rs.getString("COGNOME");
                LocalDate dataNascita = rs.getDate("DATA_NASCITA").toLocalDate();
                String nazione = rs.getString("DESCRIZIONE");

                AttoreDTO attore = new AttoreDTO(id, nome, cognome, dataNascita, nazione);
                listaAttori.add(attore);
            }
            return listaAttori;
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new NetflixException(ex);
        }
    }

    public void insertAttore(String nome, String cognome, LocalDate dataNascita, long idNazione) throws NetflixException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = conn.prepareStatement(INSERISCI_PERSONA, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement ps2 = conn.prepareStatement(INSERISCI_ATTORE)) {

                ps1.setString(1, nome);
                ps1.setString(2, cognome);
                ps1.setDate(3, Date.valueOf(dataNascita));
                ps1.executeUpdate();

                ResultSet generatedKey = ps1.getGeneratedKeys();
                generatedKey.next();
                long ID = generatedKey.getLong(1);

                ps2.setLong(1, ID);
                ps2.setLong(2, idNazione);
                ps2.executeUpdate();

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new NetflixException(ex);
        }
    }

    public void addPremioToEntita(long idEntita, long idPremio, int anno) throws NetflixException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(ADD_PREMIO_TO_ENTITA)) {

            ps.setLong(1, idEntita);
            ps.setLong(2, idPremio);
            ps.setInt(3, anno);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new NetflixException(ex);
        }
    }
}
