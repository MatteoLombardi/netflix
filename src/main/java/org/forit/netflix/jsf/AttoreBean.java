/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.jsf;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.forit.netflix.dao.NetflixDAO;
import org.forit.netflix.dto.AttoreDTO;
import org.forit.netflix.exception.NetflixException;

/**
 *
 * @author UTENTE
 */
@ManagedBean(name = "aBean")
@RequestScoped
public class AttoreBean {

    public List<AttoreDTO> getAttori() {
        NetflixDAO netflixDAO = new NetflixDAO();

        try {
            return netflixDAO.getListaAttori();
        } catch (NetflixException ex) {
            return new ArrayList<>();
        }
    }
}
