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

        this.listaUtenti(resp);
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
}
