package org.forit.netflix.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author UTENTE
 */
@Embeddable
public class AbbonamentoPerUtenteEntity implements Serializable {

    @Column(name = "METODO_PAGAMENTO", unique = false, nullable = false)
    private String metodoPagamento;

    @Column(name = "DATA_SOTTOSCRIZIONE", unique = false, nullable = false)
    private LocalDate dataSottoscrizione;

    @ManyToOne
    @JoinColumn(name = "ID_ABBONAMENTO")
    private AbbonamentoEntity abbonamentoEntity;

    public AbbonamentoPerUtenteEntity() {
    }

    public AbbonamentoPerUtenteEntity(String metodoPagamento, LocalDate dataSottoscrizione) {
        this.metodoPagamento = metodoPagamento;
        this.dataSottoscrizione = dataSottoscrizione;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDate getDataSottoscrizione() {
        return dataSottoscrizione;
    }

    public void setDataSottoscrizione(LocalDate dataSottoscrizione) {
        this.dataSottoscrizione = dataSottoscrizione;
    }

    public AbbonamentoEntity getAbbonamentoEntity() {
        return abbonamentoEntity;
    }

    public void setAbbonamentoEntity(AbbonamentoEntity abbonamentoEntity) {
        this.abbonamentoEntity = abbonamentoEntity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.metodoPagamento);
        hash = 89 * hash + Objects.hashCode(this.dataSottoscrizione);
        hash = 89 * hash + Objects.hashCode(this.abbonamentoEntity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbbonamentoPerUtenteEntity other = (AbbonamentoPerUtenteEntity) obj;
        if (!Objects.equals(this.metodoPagamento, other.metodoPagamento)) {
            return false;
        }
        if (!Objects.equals(this.dataSottoscrizione, other.dataSottoscrizione)) {
            return false;
        }
        if (!Objects.equals(this.abbonamentoEntity, other.abbonamentoEntity)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbbonamentoPerUtenteEntity{" + "metodoPagamento=" + metodoPagamento + ", dataSottoscrizione=" + dataSottoscrizione + ", abbonamentoEntity=" + abbonamentoEntity + '}';
    }
}