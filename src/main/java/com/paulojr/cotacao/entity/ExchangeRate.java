package com.paulojr.cotacao.entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade que representa a tabela de Taxa de Câmbio no banco de dados.
 */
@Entity
@Table(name = "taxa_de_cambio")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taxa_cambio")
    private Long idTaxaCambio;

    @ManyToOne
    @JoinColumn(name = "id_moeda_origem", nullable = false)
    private Currency moedaOrigem;

    @ManyToOne
    @JoinColumn(name = "id_moeda_destino", nullable = false)
    private Currency moedaDestino;

    @Column(name = "taxa_cambio", nullable = false)
    private Float taxaCambio;

    @Column(name = "data_taxa_cambio", nullable = false)
    private LocalDateTime dataTaxaCambio;

    // Construtores

    public ExchangeRate() {
    }

    public ExchangeRate(Currency moedaOrigem, Currency moedaDestino, Float taxaCambio, LocalDateTime dataTaxaCambio) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.taxaCambio = taxaCambio;
        this.dataTaxaCambio = dataTaxaCambio;
    }

    // Getters e Setters

    public Long getIdTaxaCambio() {
        return idTaxaCambio;
    }

    public void setIdTaxaCambio(Long idTaxaCambio) {
        this.idTaxaCambio = idTaxaCambio;
    }

    public Currency getMoedaOrigem() {
        return moedaOrigem;
    }

    public void setMoedaOrigem(Currency moedaOrigem) {
        this.moedaOrigem = moedaOrigem;
    }

    public Currency getMoedaDestino() {
        return moedaDestino;
    }

    public void setMoedaDestino(Currency moedaDestino) {
        this.moedaDestino = moedaDestino;
    }

    public Float getTaxaCambio() {
        return taxaCambio;
    }

    public void setTaxaCambio(Float taxaCambio) {
        this.taxaCambio = taxaCambio;
    }

    public LocalDateTime getDataTaxaCambio() {
        return dataTaxaCambio;
    }

    public void setDataTaxaCambio(LocalDateTime dataTaxaCambio) {
        this.dataTaxaCambio = dataTaxaCambio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Objects.equals(idTaxaCambio, that.idTaxaCambio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTaxaCambio);
    }

    @Override
    public String toString() {
        return "TaxaDeCambio{" +
                "idTaxaCambio=" + idTaxaCambio +
                ", moedaOrigem=" + moedaOrigem.getName() +
                ", moedaDestino=" + moedaDestino.getName() +
                ", taxaCambio=" + taxaCambio +
                ", dataTaxaCambio=" + dataTaxaCambio +
                '}';
    }
}

