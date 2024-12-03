package com.paulojr.cotacao.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConversionRequestDTO {

    @NotBlank(message = "Moeda de origem é obrigatória")
    private String fromCurrency;

    @NotBlank(message = "Moeda de destino é obrigatória")
    private String toCurrency;

    @NotNull(message = "O valor para conversão é obrigatório")
    @Min(value = 0, message = "O valor deve ser maior que zero")
    private Double amount;

}
