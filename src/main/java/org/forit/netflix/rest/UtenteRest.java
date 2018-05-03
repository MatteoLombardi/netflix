package org.forit.netflix.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.dao.UtenteDAO;
import org.forit.netflix.dto.UtenteDTO;
import org.forit.netflix.exception.NetflixException;

@Path("/utenti")
public class UtenteRest {

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<UtenteDTO> getUtenti() {

        UtenteDAO utenteDAO = new UtenteDAO();
        return utenteDAO.getListaUtenti();

    }

//    @Path("/{id}")
//    @GET()
//    @Produces("application/json")
//    public AttoreDTO getAttore(@PathParam("id") long ID) {
//        try {
//            NetflixDAO netflixDAO = new NetflixDAO();
//            return netflixDAO.getAttore(ID);
//        } catch (NetflixException ex) {
//            return null;
//        }
//    }
//    @Path("/")
//    @POST()
//    @Consumes("application/json")
//    @Produces("application/json")
//    public boolean postAttore(AttoreDTO attore) {
//        try {
//            NetflixDAO netflixDAO = new NetflixDAO();
//            netflixDAO.insertAttore(attore.getNome(), attore.getCognome(), attore.getDataNascita(), 1);
//            return true;
//        } catch (NetflixException ex) {
//            return false;
//        }
//    }
}
