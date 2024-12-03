package com.paulojr.cotacao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    /**
     * Método para buscar uma moeda pelo nome.
     *
     * @param NameCurrency Nome da moeda a ser buscada.
     * @return Um Optional contendo a moeda correspondente ou vazio se não existir.
     */
    Optional<Currency> findByNameCurrency(String NameCurrency);

    /**
     * Método para verificar se uma moeda existe pelo nome.
     *
     * @param NameCurrency Nome da moeda a ser verificada.
     * @return Verdadeiro se a moeda existir, falso caso contrário.
     */
    boolean existsByNameCurrency(String NameCurrency);
}
