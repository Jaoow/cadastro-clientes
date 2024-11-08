package com.jaoow.clientregistry.bean;

import com.jaoow.clientregistry.entity.Client;
import com.jaoow.clientregistry.model.Address;
import com.jaoow.clientregistry.service.ClientService;
import com.jaoow.clientregistry.service.ViaCEPService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ManagedBean(name = "clientBean")
@RequestScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Client selectedClient = new Client();
    private List<Client> clientList = new ArrayList<>();

    private ViaCEPService viaCEPService;
    private ClientService clientService;

    public ClienteBean() {
        this.viaCEPService = new ViaCEPService();
        this.clientService = new ClientService();
    }

    public void fillAddressByCEP() {
        if (selectedClient.getZipCode() != null && !selectedClient.getZipCode().isEmpty()) {
            Address address = viaCEPService.searchAddressByCEP(selectedClient.getZipCode());

            if (address == null) {
                addMessage(FacesMessage.SEVERITY_WARN, "CEP não encontrado!", null);
                return;
            }

            selectedClient.setAddress(address.getLogradouro());
            selectedClient.setDistrict(address.getBairro());
            selectedClient.setCity(address.getLocalidade());
            selectedClient.setState(address.getUf());
        }
    }

    public List<Client> listClients() {
        this.clientList = clientService.listClients();
        return clientList;
    }

    public void saveClient() {
        if (clientService.isEmailAlreadyInUse(selectedClient.getEmail())) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar cliente!", "E-mail já cadastrado!");
            return;
        }

        try {
            clientService.saveClient(selectedClient);
            addMessage(FacesMessage.SEVERITY_INFO, "Cliente salvo com sucesso!", "");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar cliente!", e.getMessage());
        }
    }

    public void updateClient() {
        try {
            clientService.updateClient(selectedClient);
            addMessage(FacesMessage.SEVERITY_INFO, "Cliente atualizado com sucesso!", "");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar cliente!", e.getMessage());
        }
    }

    public void deleteClient() {
        try {
            clientService.deleteClient(selectedClient.getId());
            addMessage(FacesMessage.SEVERITY_WARN, "Cliente deletado com sucesso!", "");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar cliente!", e.getMessage());
        }
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
