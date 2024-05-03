package org.example.DTO;

/**
 * This class provides the DTO functionality for the Log class.
 */
public class LogDTO {

    private final String bill;

    /**
     * Constructs a new LogDTO.
     * @param bill the bill of the log
     */
    public LogDTO(String bill){
        super();
        this.bill = bill;
    }


    public String getBill() {
        return bill;
    }

}
