package org.forit.netflix.dao;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.netflix.dto.AbbonamentoDTO;
import org.forit.netflix.dto.UtenteDTO;
import org.forit.netflix.entity.UtenteEntity;

/**
 *
 * @author UTENTE
 */
public class UtenteDAO {

    public List<UtenteDTO> getListaUtenti() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<UtenteEntity> query = em.createNamedQuery("utente.selectAll", UtenteEntity.class);
        List<UtenteEntity> list = query.getResultList();
        List<UtenteDTO> utenti = list.stream().map(entity -> {
            UtenteDTO utente = new UtenteDTO(entity.getID(), entity.getNome(), entity.getCognome(),
                    entity.getDataNascita(),
                    entity.getCodiceFiscale(), entity.getMail());

            List<AbbonamentoDTO> abbonamenti = entity.getAbbonamenti().stream().map(abbEntity -> {
                return new AbbonamentoDTO(
                        abbEntity.getAbbonamentoEntity().getID(),
                        abbEntity.getAbbonamentoEntity().getDescrizione(),
                        abbEntity.getAbbonamentoEntity().getDurata(), abbEntity.getAbbonamentoEntity().getCosto(),
                        abbEntity.getMetodoPagamento(), abbEntity.getDataSottoscrizione());
            }).collect(Collectors.toList());

            utente.setAbbonamenti(abbonamenti);
            return utente;
        }).collect(Collectors.toList());

        em.close();
        emf.close();

        return utenti;
    }

    public UtenteDTO getUtente(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("netflix_pu");
        EntityManager em = emf.createEntityManager();

        UtenteEntity entity = em.find(UtenteEntity.class, ID);
        UtenteDTO utente = new UtenteDTO(entity.getID(), entity.getNome(), entity.getCognome(),
                entity.getDataNascita(),
                entity.getCodiceFiscale(), entity.getMail());

        List<AbbonamentoDTO> abbonamenti = entity.getAbbonamenti().stream().map(abbEntity -> {
            return new AbbonamentoDTO(
                    abbEntity.getAbbonamentoEntity().getID(),
                    abbEntity.getAbbonamentoEntity().getDescrizione(),
                    abbEntity.getAbbonamentoEntity().getDurata(), abbEntity.getAbbonamentoEntity().getCosto(),
                    abbEntity.getMetodoPagamento(), abbEntity.getDataSottoscrizione());
        }).collect(Collectors.toList());

        utente.setAbbonamenti(abbonamenti);

        em.close();
        emf.close();

        return utente;
    }
}