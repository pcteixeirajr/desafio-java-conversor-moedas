package com.paulojr.cotacao.dto;

import lombok.Data;

@Data
public class ConversionResponseDTO {

    private double originalAmount;   // Valor original da conversão
    private double convertedAmount;  // Valor após a conversão
    private String fromCurrency;     // Moeda de origem
    private String toCurrency;       // Moeda de destino

}
