/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.dto.UtenteDTO;
import org.forit.netflix.exception.NetflixException;

/**
 *
 * @author UTENTE
 */
public class ClientiServlet extends NetflixServlet {

    private final static String THEAD
            = "<thead>"
            + "    <tr>"
            + "        <th class='col-sm-1'>"
            + "            <a href='?action=new' class='btn btn-primary'>Nuovo</a>"
            + "        </th>"
            + "        <th class='col-sm-2'>Nome</th>"
            + "        <th class='col-sm-2'>Cognome</th>"
            + "        <th class='col-sm-2'>Data di Nascita</th>"
            + "        <th class='col-sm-2'>Codice Fiscale</th>"
            + "        <th class='col-sm-3'>Mail</th>"
            + "    </tr>"
            + "</thead>";

    private final static String THEAD_ABBONAMENTI
            = "<thead>"
            + "    <tr>"
            + "        <th class='col-sm-4'>Descrizione</th>"
            + "        <th class='col-sm-2'>Durata</th>"
            + "        <th class='col-sm-2'>Costo</th>"
            + "        <th class='col-sm-2'>Metodo Pagamento</th>"
            + "        <th class='col-sm-2'>Data Sottoscrizione</th>"
            + "    </tr>"
            + "</thead>";

    private final static String THEAD_FILM_NOLEGGIATI
            = "<thead>"
            + "    <tr>"
            + "        <th class='col-sm-2'>Titolo</th>"
            + "        <th class='col-sm-3'>Descrizione</th>"
            + "        <th class='col-sm-1'>Anno</th>"
            + "        <th class='col-sm-1'>Durata</th>"
            + "        <th class='col-sm-2'>Nazione</th>"
            + "        <th class='col-sm-2'>Lingua</th>"
            + "        <th class='col-sm-1'>Data Noleggio</th>"
            + "    </tr>"
            + "</thead>";

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long ID = Long.parseLong(req.getParameter("ID"));
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        LocalDate dataNascita = LocalDate.parse(req.getParameter("dataNascita"));
        String codiceFiscale = req.getParameter("codiceFiscale");
        String mail = req.getParameter("mail");

        try {
            UtenteDTO utente = new UtenteDTO(ID, nome, cognome, dataNascita, codiceFiscale, mail);
            NetflixDAO netflixDAO = new NetflixDAO();
            netflixDAO.updateUtente(utente);

//            this.doGet(req, resp);
            resp.sendRedirect("clienti");
        } catch (NetflixException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            resp.sendRedirect("clienti?ID=" + ID + "&action=edit");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        if (action == null) {
            this.listaUtenti(resp);
        } else {
            switch (action) {
                case "view":
                    String ID = req.getParameter("ID");
                    this.dettaglioCliente(resp, Long.parseLong(ID), true);
                    break;
                case "edit":
                    ID = req.getParameter("ID");
                    this.dettaglioCliente(resp, Long.parseLong(ID), false);
                    break;
                case "new":
                    break;
                default:
                    this.listaUtenti(resp);
            }
        }
    }

    private void listaUtenti(HttpServletResponse resp) throws IOException {
        List<UtenteDTO> utenti;
        String messaggioErrore = null;

        try {
            NetflixDAO netflixDAO = new NetflixDAO();
            utenti = netflixDAO.getListaUtenti();
        } catch (NetflixException ex) {
            utenti = new ArrayList<>();
            messaggioErrore = "Impossibile leggere i dati dal database";
        }

        try (PrintWriter out = resp.getWriter()) {
            this.apriHTML(out, messaggioErrore, "$$clienti$$");
            this.createTabellaUtenti(out, utenti);
            this.chiudiHTML(out);
        }
    }

    private void createTabellaUtenti(PrintWriter out, List<UtenteDTO> utenti) {
        out.println("<div class='container-fluid table-responsive'>");
        out.println("<table class='table'>");
        out.println(THEAD);
        out.println("<tbody>");
        utenti.forEach(utente -> {
            out.println("<tr>");
            out.println("  <td>");
            out.println("    <a href='?ID=" + utente.getId() + "&action=view' class='btn btn-default' title='Visualizza Dettaglio'>");
            out.println("      <span class='glyphicon glyphicon-eye-open'></span>");
            out.println("    </a>");
            out.println("    <a href='?ID=" + utente.getId() + "&action=edit' class='btn btn-default' title='Modifica Dati'>");
            out.println("      <span class='glyphicon glyphicon-pencil'></span>");
            out.println("    </a>");
            out.println("  </td>");
            out.println("  <td>" + utente.getNome() + "</td>");
            out.println("  <td>" + utente.getCognome() + "</td>");
            out.println("  <td>" + utente.getDataNascita().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</td>");
            out.println("  <td>" + utente.getCodiceFiscale() + "</td>");
            out.println("  <td>" + utente.getMail() + "</td>");
            out.println("</tr>");
        });
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
    }

    private void dettaglioCliente(HttpServletResponse resp, long ID, boolean disabled) throws IOException {
        UtenteDTO utente = null;
        String messaggioErrore = null;

        try {
            NetflixDAO netflixDAO = new NetflixDAO();
            utente = netflixDAO.getUtente(ID);
        } catch (NetflixException ex) {
            messaggioErrore = "Impossibile leggere i dati dal database";
        }

        try (PrintWriter out = resp.getWriter()) {
            this.apriHTML(out, messaggioErrore, "$$clienti$$");

            if (utente != null) {
                this.creaDettaglioUtente(out, utente, disabled);
            }

            this.chiudiHTML(out);
        }
    }

    private void creaDettaglioUtente(PrintWriter out, UtenteDTO utente, boolean disabled) {
        out.println("<form action='clienti' method='POST' class='container-fluid'>");
        out.println("<div class='panel panel-default'>");
        out.println("<div class='panel-heading'>");
        out.println("<div class='panel-title'>Dettaglio Utente</div>");
        out.println("</div>");
        out.println("<div class='panel-body'>");
        out.println("<input type='hidden' name='ID' value='" + utente.getId() + "'/>");

        this.creaDatiAnagrafici(out, utente, disabled);
        this.creaTabellaAbbonamenti(out, utente);
        this.creaTabellaFilmNoleggiati(out, utente);

        out.println("</div>");
        out.println("<div class='panel-footer text-right'>");
        if (!disabled) {
            out.println("<input type='submit' class='btn btn-primary' value='Salva Modifiche'/>");
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</form>");
    }

    private void creaDatiAnagrafici(PrintWriter out, UtenteDTO utente, boolean disabled) {
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Nome</label>");
        out.println("<input type='text' name='nome' class='form-control' value='" + utente.getNome() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Cognome</label>");
        out.println("<input type='text' name='cognome' class='form-control' value='" + utente.getCognome() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Data di Nascita</label>");
        out.println("<input type='date' name='dataNascita' class='form-control' value='" + utente.getDataNascita() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Codice Fiscale</label>");
        out.println("<input type='text' name='codiceFiscale' class='form-control' value='" + utente.getCodiceFiscale() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-12'>");
        out.println("<label>Mail</label>");
        out.println("<input type='email' name='mail' class='form-control' value='" + utente.getMail() + "'" + (disabled ? " disabled='disabled'" : "") + "/>");
        out.println("</div>");
        out.println("</div>");
    }

    private void creaTabellaAbbonamenti(PrintWriter out, UtenteDTO utente) {
        out.println("<div class='row'>");
        out.println("<div class='col-sm-12'>");
        out.println("<h3>Abbonamenti</h3>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='table-responsive'>");
        out.println("<table class='table'>");
        out.println(THEAD_ABBONAMENTI);
        out.println("<tbody>");
        utente.getAbbonamenti().forEach(abbonamento -> {
            out.println("<tr>");
            out.println("<td>" + abbonamento.getDescrizione() + "</td>");
            out.println("<td>" + abbonamento.getDurata() + " (mesi)</td>");
            out.println("<td>" + abbonamento.getCosto() + " €</td>");
            out.println("<td>" + abbonamento.getMetodoPagamento() + "</td>");
            out.println("<td>" + abbonamento.getDataSottoscrizione().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</td>");
            out.println("</tr>");
        });
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
    }

    private void creaTabellaFilmNoleggiati(PrintWriter out, UtenteDTO utente) {
        out.println("<div class='row'>");
        out.println("<div class='col-sm-12'>");
        out.println("<h3>Film Noleggiati</h3>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='table-responsive'>");
        out.println("<table class='table'>");
        out.println(THEAD_FILM_NOLEGGIATI);
        out.println("<tbody>");
        utente.getFilms().forEach(film -> {
            out.println("<tr>");
            out.println("<td>" + film.getTitolo() + "</td>");
            out.println("<td>" + film.getDescrizione() + "</td>");
            out.println("<td>" + film.getAnno() + "</td>");
            out.println("<td>" + film.getDurata() + " (min)</td>");
            out.println("<td>" + film.getNazione() + "</td>");
            out.println("<td>" + film.getLingua() + "</td>");
            out.println("<td>" + film.getDataNoleggio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</td>");
            out.println("</tr>");
        });
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
    }
}
