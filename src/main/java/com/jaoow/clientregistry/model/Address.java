package com.jaoow.clientregistry.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    private String cep;
    private String logradouro; // Rua
    private String bairro;
    private String localidade; // Cidade
    private String uf; // Estado
}
