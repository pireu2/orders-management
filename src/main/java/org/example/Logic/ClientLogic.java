package org.example.Logic;

import org.example.DAO.ClientDAO;
import org.example.DTO.ClientDTO;
import org.example.Model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientLogic {
    public static void createClient(ClientDTO clientDTO){
        Client client = new Client(clientDTO.getName(), clientDTO.getEmail(), clientDTO.getAddress());
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insert(client);
    }

    public static void deleteClient(String name){
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findByName(name);
        clientDAO.delete(client.getId());
    }

    public static List<ClientDTO> getAllClients(){
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clients = clientDAO.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        for(Client client : clients){
            ClientDTO clientDTO = new ClientDTO(client.getName(), client.getEmail(), client.getAddress());
            clientDTOS.add(clientDTO);
        }
        return clientDTOS;
    }
    public static List<String> getAllClientsName(){
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clients = clientDAO.findAll();
        List<String> clientNames = new ArrayList<>();
        for(Client client : clients){
            clientNames.add(client.getName());
        }
        return clientNames;
    }

}
