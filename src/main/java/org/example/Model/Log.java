package org.example.Model;


/**
 * This class represents a Log with id and bill.
 */
public record Log(int id, String bill) {
    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", bill='" + bill + '\'' +
                '}';
    }
}