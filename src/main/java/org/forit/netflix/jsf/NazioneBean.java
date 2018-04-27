package org.forit.netflix.jsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.exception.NetflixException;

@ManagedBean(name = "nBean")
@ViewScoped
public class NazioneBean {

    private Map<Long, String> nazioni = new HashMap<>();

    @PostConstruct
    private void init() {
        this.loadNazioni();
    }

    public Set<Long> getIdNazioni() {
        return nazioni.keySet();
    }

    public String getDescrizioneById(long ID) {
        return nazioni.get(ID);
    }

    public long getIdByDescrizione(String descrizione) {
        return nazioni.entrySet().stream().
                filter(entry -> entry.getValue().equals(descrizione)).
                findAny().get().getKey();
    }

    private void loadNazioni() {
        try {
            NetflixDAO netflixDAO = new NetflixDAO();
            nazioni = netflixDAO.getListaNazioni();
        } catch (NetflixException ex) {
            System.out.println("Errore: " + ex.getMessage());
            nazioni = new HashMap<>();
        }
    }
}
