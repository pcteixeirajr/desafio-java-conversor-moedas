package com.paulojr.cotacao.repository;

import com.paulojr.cotacao.entity.Currency;
import com.paulojr.cotacao.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findTopByMoedaOrigemAndMoedaDestinoOrderByDataTaxaCambioDesc(Currency moedaOrigem, Currency moedaDestino);
}
