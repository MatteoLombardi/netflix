package org.forit.netflix.dao;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.netflix.dto.UtenteDTO;
import org.forit.netflix.entity.UtenteEntity;

public class UtenteDAO {

    public List<UtenteDTO> getListaUtenti() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<UtenteEntity> query = em.createNamedQuery("utente.selectAll", UtenteEntity.class);
        List<UtenteEntity> list = query.getResultList();
        List<UtenteDTO> utenti = list.stream().map(entity -> {
            return new UtenteDTO(entity.getID(), entity.getNome(), entity.getCognome(),
                    entity.getDataNascita(),
                    entity.getCodiceFiscale(), entity.getMail());
        }).collect(Collectors.toList());

        em.close();
        emf.close();
        return utenti;
    }
}
