package org.example.Model;

/**
 * This class represents a Log with id and bill.
 */
public class Log {
    private int id;
    private String bill;

    /**
     * Default constructor.
     */
    public Log(){
    }

    /**
     * Constructor with all parameters.
     *
     * @param id the id of the log
     * @param bill the bill of the log
     */
    public Log(int id, String bill){
        super();
        this.id = id;
        this.bill = bill;
    }

    /**
     * Constructor without id.
     *
     * @param bill the bill of the log
     */
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
