package org.forit.netflix.jsf;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.dto.AttoreDTO;
import org.forit.netflix.exception.NetflixException;

@ManagedBean(name = "aBean")
@ViewScoped
public class AttoreBean {

    private List<AttoreDTO> attori = new ArrayList<>();
    private AttoreDTO attore = new AttoreDTO(-1, "", "", LocalDate.now(), "");
    private long idNazione = -1;
    private boolean disabled;

    @ManagedProperty(value = "#{nBean}")
    private NazioneBean nazioneBean;

    @PostConstruct
    public void init() {
        this.loadAttori();
    }

    public List<AttoreDTO> getAttori() {
        return attori;
    }

    public void setNazioneBean(NazioneBean nazioneBean) {
        this.nazioneBean = nazioneBean;
    }

    public NazioneBean getNazioneBean() {
        return nazioneBean;
    }

    public void setIdNazione(long idNazione) {
        this.idNazione = idNazione;
    }

    public long getIdNazione() {
        return idNazione;
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
            idNazione = -1;
        } else {
            try {
                NetflixDAO netflixDAO = new NetflixDAO();
                attore = netflixDAO.getAttore(ID);
                idNazione = nazioneBean.getIdByDescrizione(attore.getNazione());
            } catch (NetflixException ex) {
                attore = new AttoreDTO(-1, "", "", LocalDate.now(), "");
                idNazione = -1;
            }
        }
    }

    public void saveAttore() {
        try {
            NetflixDAO netflixDAO = new NetflixDAO();
            if (attore.getID() == -1) {
                netflixDAO.insertAttore(attore.getNome(), attore.getCognome(), attore.getDataNascita(), idNazione);
            } else {
                netflixDAO.updateAttore(attore.getID(), attore.getNome(), attore.getCognome(), attore.getDataNascita(), idNazione);
            }
            this.loadAttori();
        } catch (NetflixException ex) {
        }
    }

    public boolean getDisabled() {
        return disabled;
    }
}
