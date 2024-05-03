package org.example.Model;

/**
 * This class represents a Client with id, name, email, and address.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;

    /**
     * Default constructor.
     */
    public Client(){
    }

    /**
     * Constructor with all parameters.
     *
     * @param id the id of the client
     * @param name the name of the client
     * @param address the address of the client
     * @param email the email of the client
     */
    public Client(int id, String name,  String address, String email){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Constructor without id.
     *
     * @param name the name of the client
     * @param address the address of the client
     * @param email the email of the client
     */
    public Client(String name,  String address, String email){
        super();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
