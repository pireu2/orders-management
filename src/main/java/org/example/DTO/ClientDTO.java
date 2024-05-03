package org.example.DTO;

/**
 * This class provides the DTO functionality for the Client class.
 */
public class ClientDTO {
    private final String name;
    private final String email;
    private final String address;

    /**
     * Constructs a new ClientDTO.
     * @param name the name of the client
     * @param address the address of the client
     * @param email the email of the client
     */
    public ClientDTO(String name, String address, String email){
        super();
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
