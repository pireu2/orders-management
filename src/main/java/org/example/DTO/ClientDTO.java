package org.example.DTO;

public class ClientDTO {
    private final String name;
    private final String email;
    private final String address;

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
