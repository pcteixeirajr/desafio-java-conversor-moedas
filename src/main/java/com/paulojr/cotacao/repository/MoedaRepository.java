package com.paulojr.cotacao.repository;

import com.paulojr.cotacao.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoedaRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByNomeMoeda(String nomeMoeda);
}
