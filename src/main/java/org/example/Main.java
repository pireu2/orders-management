package org.example;


import org.example.Gui.View;


/**
 * This class contains the main method which starts the application.
 */
public class Main {

    /**
     * The main method which starts the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        View view = new View("Orders Management System");
        view.setVisible(true);
    }
}