package com.jaoow.clientregistry.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaoow.clientregistry.model.Address;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViaCEPService {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/";
    private static final Logger logger = Logger.getLogger(ViaCEPService.class.getName());

    public Address searchAddressByCEP(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            return null;
        }

        try {
            HttpURLConnection connection = createConnection(cep);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(reader, Address.class);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao buscar o endereço para o CEP " + cep, e);
        }

        return null;
    }

    private HttpURLConnection createConnection(String cep) throws Exception {
        URL url = new URL(VIA_CEP_URL + cep + "/json/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }
}
