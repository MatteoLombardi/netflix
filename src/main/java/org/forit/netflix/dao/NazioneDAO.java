package org.forit.netflix.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.netflix.entity.NazioneEntity;
import org.forit.netflix.exception.NetflixException;

public class NazioneDAO {

    public Map<Long, String> getListaNazioni() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<NazioneEntity> query = em.createNamedQuery("nazione.selectAll", NazioneEntity.class);
        List<NazioneEntity> list = query.getResultList();
        Map<Long, String> map = list.stream().
                collect(Collectors.toMap(
                        nazione -> nazione.getID(),
                        nazione -> nazione.getDescrizione(),
                        (u, v) -> u,
                        LinkedHashMap::new));

        em.close();
        emf.close();
        return map;
    }

    public String getNazione(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        NazioneEntity nazione = em.find(NazioneEntity.class, ID);
        String descrizione = nazione.getDescrizione();

        em.close();
        emf.close();

        return descrizione;
    }

    public void insertNazione(String descrizione) throws NetflixException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NazioneEntity nazione = new NazioneEntity();
            nazione.setDescrizione(descrizione);
            em.persist(nazione);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new NetflixException();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void updateNazione(long ID, String descrizione) throws NetflixException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            NazioneEntity nazione = em.find(NazioneEntity.class, ID);
            nazione.setDescrizione(descrizione);
            em.merge(nazione);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new NetflixException();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void deleteNazione(long ID) throws NetflixException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            NazioneEntity nazione = em.find(NazioneEntity.class, ID);

            em.remove(nazione);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new NetflixException();
        } finally {
            em.close();
            emf.close();
        }
    }

}
