package com.paulojr.cotacao.controller;

import com.paulojr.cotacao.dto.ConversionRequestDTO;
import com.paulojr.cotacao.dto.ConversionResponseDTO;
import com.paulojr.cotacao.entity.ExchangeRate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Endpoint para consultar a taxa de câmbio atual entre Ouro Real e Tibar.
     *
     * @return Taxa de câmbio atual
     */
    @Operation(summary = "Consultar taxa de câmbio atual", description = "Retorna a taxa de câmbio atual entre Ouro Real e Tibar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Taxa de câmbio obtida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar taxa de câmbio")
    })
    @GetMapping("/exchange-rate")
    public ResponseEntity<Double> getExchangeRate() {
        double exchangeRate = currencyService.getCurrentExchangeRate();
        return ResponseEntity.ok(exchangeRate);
    }

    /**
     * Endpoint para realizar a conversão de moeda entre Ouro Real e Tibar.
     *
     * @param request Dados da conversão, como valor e moeda de origem.
     * @return Resposta da conversão com o valor convertido.
     */
    @Operation(summary = "Realizar conversão de moeda", description = "Converte um valor de Ouro Real para Tibar ou vice-versa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conversão realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a conversão"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao realizar conversão")
    })
    @PostMapping("/convert")
    public ResponseEntity<ConversionResponseDTO> convertCurrency(
            @Parameter(description = "Dados para conversão de moeda")
            @RequestBody ConversionRequestDTO request) {
        ConversionResponseDTO response = currencyService.convertCurrency(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para criar uma nova taxa de câmbio entre moedas.
     */
    @Operation(summary = "Criar nova taxa de câmbio", description = "Cria uma nova taxa de câmbio entre duas moedas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Taxa de câmbio criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação de taxa"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao criar taxa")
    })
    @PostMapping("/exchange-rate")
    public ResponseEntity<ExchangeRate> createExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        ExchangeRate createdTaxa = currencyService.createExchangeRate(exchangeRate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaxa);
    }

    /**
     * Endpoint para consultar todas as taxas de câmbio.
     */
    @Operation(summary = "Listar todas as taxas de câmbio", description = "Retorna uma lista de todas as taxas de câmbio registradas.")
    @GetMapping("/exchange-rate/all")
    public ResponseEntity<List<ExchangeRate>> getAllExchangeRates() {
        List<ExchangeRate> exchangeRates = currencyService.getAllExchangeRates();
        return ResponseEntity.ok(exchangeRates);
    }

    /**
     * Endpoint para atualizar uma taxa de câmbio existente.
     */
    @Operation(summary = "Atualizar taxa de câmbio", description = "Atualiza uma taxa de câmbio existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Taxa de câmbio atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Taxa de câmbio não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar taxa")
    })
    @PutMapping("/exchange-rate/{id}")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@PathVariable Long id, @RequestBody ExchangeRate updatedTaxa) {
        Optional<ExchangeRate> updatedExchangeRate = currencyService.updateExchangeRate(id, updatedTaxa);
        return updatedExchangeRate
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para excluir uma taxa de câmbio.
     */
    @Operation(summary = "Excluir taxa de câmbio", description = "Exclui uma taxa de câmbio pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Taxa de câmbio excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Taxa de câmbio não encontrada")
    })
    @DeleteMapping("/exchange-rate/{id}")
    public ResponseEntity<Void> deleteExchangeRate(@PathVariable Long id) {
        boolean deleted = currencyService.deleteExchangeRate(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
