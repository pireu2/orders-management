package org.example.Logic;

import org.example.DAO.ClientDAO;
import org.example.DTO.ClientDTO;
import org.example.Model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the logic for the Client operations.
 */
public class ClientLogic {

    /**
     * Creates a new client.
     *
     * @param clientDTO the client to create
     */
    public static void createClient(ClientDTO clientDTO){
        Client client = new Client(clientDTO.getName(), clientDTO.getAddress(), clientDTO.getEmail());
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insert(client);
    }

    /**
     * Deletes a client.
     *
     * @param name the name of the client to delete
     */
    public static void deleteClient(String name){
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findByName(name);
        clientDAO.delete(client.getId());
    }

    /**
     * Edits a client.
     *
     * @param name the name of the client to edit
     * @param clientDTO the new client data
     */
    public static void editClient(String name, ClientDTO clientDTO){
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findByName(name);
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setAddress(clientDTO.getAddress());
        clientDAO.update(client, client.getId());
    }

    /**
     * Retrieves all clients.
     *
     * @return a list of all clients
     */
    public static List<ClientDTO> getAllClients(){
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clients = clientDAO.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        for(Client client : clients){
            ClientDTO clientDTO = new ClientDTO(client.getName(), client.getAddress(), client.getEmail());
            clientDTOS.add(clientDTO);
        }
        return clientDTOS;
    }

    /**
     * Retrieves all client names.
     *
     * @return a list of all client names
     */
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
