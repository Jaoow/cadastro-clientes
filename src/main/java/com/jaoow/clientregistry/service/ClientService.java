package com.jaoow.clientregistry.service;

import com.jaoow.clientregistry.dao.ClientDAO;
import com.jaoow.clientregistry.entity.Client;

import java.util.List;

public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public List<Client> listClients() {
        return clientDAO.findAllClients();
    }

    public void saveClient(Client client) {
        clientDAO.saveClient(client);
    }

    public void updateClient(Client client) {
        clientDAO.updateClient(client);
    }

    public void deleteClient(Long id) {
        clientDAO.deleteClientById(id);
    }

    public boolean isEmailAlreadyInUse(String email) {
        return clientDAO.findClientByEmail(email) != null;
    }
}
