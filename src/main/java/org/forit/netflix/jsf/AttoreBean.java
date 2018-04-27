package org.forit.netflix.jsf;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.dto.AttoreDTO;
import org.forit.netflix.exception.NetflixException;

@ManagedBean(name = "aBean")
@RequestScoped
public class AttoreBean {

    private List<AttoreDTO> attori = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        this.loadAttori();
    }

    public List<AttoreDTO> getAttori() {
        return attori;
    }
    
    public void loadAttori(){
        NetflixDAO netflixDAO = new NetflixDAO();
        try{
            attori= netflixDAO.getListaAttori();
        }catch(NetflixException ex){
            attori = new ArrayList<>();
        }
    }
}

