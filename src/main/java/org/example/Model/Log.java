package org.example.Model;

public class Log {
    private int id;
    private String bill;

    public Log(){
    }
    public Log(int id, String bill){
        super();
        this.id = id;
        this.bill = bill;
    }

    public Log(String bill){
        super();
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", bill='" + bill + '\'' +
                '}';
    }
}
