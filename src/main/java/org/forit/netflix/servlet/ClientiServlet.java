/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
            = "<thead>\n"
            + "                                <tr>\n"
            + "                                    <th class='col-sm-4'>Descrizione</th>\n"
            + "                                    <th class='col-sm-2'>Durata</th>\n"
            + "                                    <th class='col-sm-2'>Costo</th>\n"
            + "                                    <th class='col-sm-2'>Metodo Pagamento</th>\n"
            + "                                    <th class='col-sm-2'>Data Sottoscrizione</th>\n"
            + "                                </tr>\n"
            + "                            </thead>";

    private final static String THEAD_FILM_NOLEGGIATI
            = "<thead>\n"
            + "                                <tr>\n"
            + "                                    <th class='col-sm-2'>Titolo</th>\n"
            + "                                    <th class='col-sm-3'>Descrizione</th>\n"
            + "                                    <th class='col-sm-1'>Anno</th>\n"
            + "                                    <th class='col-sm-1'>Durata</th>\n"
            + "                                    <th class='col-sm-2'>Nazione</th>\n"
            + "                                    <th class='col-sm-2'>Lingua</th>\n"
            + "                                    <th class='col-sm-1'>Data Noleggio</th>\n"
            + "                                </tr>\n"
            + "                            </thead>";

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                    this.dettaglioCliente(resp, Long.parseLong(ID));
                    break;
                case "edit":
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

    private void dettaglioCliente(HttpServletResponse resp, long ID) throws IOException {
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
                this.creaDettaglioUtente(out, utente);
            }

            this.chiudiHTML(out);
        }
    }

    private void creaDettaglioUtente(PrintWriter out, UtenteDTO utente) {
        out.println("<form class='container-fluid'>");
        out.println("<div class='panel panel-default'>");
        out.println("<div class='panel-heading'>");
        out.println("<div class='panel-title'>Dettaglio Utente</div>");
        out.println("</div>");
        out.println("<div class='panel-body'>");

        this.creaDatiAnagrafici(out, utente);
        this.creaTabellaAbbonamenti(out, utente);
        this.creaTabellaFilmNoleggiati(out, utente);

        out.println("</div>");
        out.println("<div class='panel-footer text-right'>");
        out.println("</div>");
        out.println("</div>");
        out.println("</form>");
    }

    private void creaDatiAnagrafici(PrintWriter out, UtenteDTO utente) {
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Nome</label>");
        out.println("<input type='text' name='nome' class='form-control' disabled='disabled'/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Cognome</label>");
        out.println("<input type='text' name='cognome' class='form-control' disabled='disabled'/>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Data di Nascita</label>");
        out.println("<input type='date' name='dataNascita' class='form-control' disabled='disabled'/>");
        out.println("</div>");
        out.println("<div class='col-sm-6'>");
        out.println("<label>Codice Fiscale</label>");
        out.println("<input type='text' name='codiceFiscale' class='form-control' disabled='disabled'/>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-12'>");
        out.println("<label>Mail</label>");
        out.println("<input type='email' name='mail' class='form-control' disabled='disabled'/>");
        out.println("</div>");
        out.println("</div>");
    }

    private void creaTabellaAbbonamenti(PrintWriter out, UtenteDTO utente) {

    }

    private void creaTabellaFilmNoleggiati(PrintWriter out, UtenteDTO utente) {

    }
}
