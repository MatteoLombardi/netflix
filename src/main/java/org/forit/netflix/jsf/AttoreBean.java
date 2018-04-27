package org.forit.netflix.jsf;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.dto.AttoreDTO;
import org.forit.netflix.exception.NetflixException;

@ManagedBean(name = "aBean")
@ViewScoped
public class AttoreBean {

    private List<AttoreDTO> attori = new ArrayList<>();
    private AttoreDTO attore = new AttoreDTO();
    private boolean disabled;

    @PostConstruct
    public void init() {
        this.loadAttori();
    }

    public List<AttoreDTO> getAttori() {
        return attori;
    }

    public AttoreDTO getAttore() {
        return attore;
    }

    private void loadAttori() {
        NetflixDAO netflixDAO = new NetflixDAO();
        try {
            attori = netflixDAO.getListaAttori();
        } catch (NetflixException ex) {
            attori = new ArrayList<>();
        }
    }

    public void loadAttore(long ID, boolean disabled) {
        
        this.disabled = disabled;
        if (ID == -1) {
            attore = new AttoreDTO(-1, "", "", LocalDate.now(), "");
        } else {
            try {
                NetflixDAO netflixDAO = new NetflixDAO();
                attore = netflixDAO.getAttore(ID);
                //attore=
            } catch (NetflixException ex) {
                attore = new AttoreDTO(-1, "", "", LocalDate.now(), "");
            }
        }
    }

    public void saveAttore() {
        try {
            NetflixDAO netflixDAO = new NetflixDAO();
            if (attore.getID() == -1) {
                netflixDAO.insertAttore(attore.getNome(), attore.getCognome(), attore.getDataNascita(), 1);
            } else {
                netflixDAO.updateAttore(attore.getID(), attore.getNome(), attore.getCognome(), attore.getDataNascita(), 1);
            }
            this.loadAttori();
        } catch (NetflixException ex) {
        }
    }
    public boolean getDisabled(){
        return disabled;
    }
}
