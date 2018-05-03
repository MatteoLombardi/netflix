package org.forit.netflix.rest;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.netflix.dao.NazioneDAO;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.exception.NetflixException;

@Path("/nazioni")
public class NazioneRest {
    
    @Path("/")
    @GET()
    @Produces("application/json")
    public Map<Long, String> getNazioni() {
//        NetflixDAO netflixDAO = new NetflixDAO();
//        try {
//            return netflixDAO.getListaNazioni();
//        } catch (NetflixException ex) {
//            return new HashMap<>();
//        }

        NazioneDAO nazioneDAO = new NazioneDAO();
        return nazioneDAO.getListaNazioni();
    }
    
    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public String getNazione(@PathParam("id") long ID) {
//        return this.getNazioni().get(ID);

        NazioneDAO nazioneDAO = new NazioneDAO();
        return nazioneDAO.getNazione(ID);
    }
}
