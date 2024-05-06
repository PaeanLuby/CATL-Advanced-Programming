package com.mycompany.catl;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class AirplaneCreator extends Thread {

    private final Log log;
    private final Airport madrid;
    private final Airport barcelona;
    private final GraphicalInterface gf;

    public AirplaneCreator(Log log, Airport madrid, Airport barcelona, GraphicalInterface gf) {
        this.log = log;
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.gf = gf;
    }

    /**
     * Create a random identifier
     *
     * @return two random capital letters and a "-" Ex:XX-
     */
    private String randomLetters() {
        List<String> alphabet = new ArrayList<>(Arrays.asList(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        ));
        int firstLetter = (int) (Math.random() * 26); //Indices 0-25
        int secondtLetter = (int) (Math.random() * 26);
        String randomLetters = alphabet.get(firstLetter) + alphabet.get(secondtLetter) + "-";
        return randomLetters;
    }

    public void run() {
        for (int i = 0; i < 8000; i++) { 
            gf.getGw().look(); //Check the pause/resume bottons
            Airplane airplane;
            String identifier = String.valueOf(i);
            while (identifier.length() != 4) { //If the identifier doesn't have 4 digits
                identifier = "0" + identifier;  //We add 0 until it has 4 digits
            }
            identifier = randomLetters() + identifier; //We add two random letters at the beginning
            int capacity = (int) (Math.random() * 201) + 100; //Capacity between 100 and 300

            if (i % 2 == 0) { //Even identifier for Madrid
                airplane = new Airplane(capacity, identifier, log, madrid, barcelona, gf, true);
            } else { //Odd identifier for Barcelona
                airplane = new Airplane(capacity, identifier, log, barcelona, madrid, gf, false);
            }
            airplane.start(); //Starts airplane thread
 
            try {
                Thread.sleep((long) (Math.random() * 2000 + 1000));    //Random stagger time between 1 and 3 seconds for next airplane
            } catch (InterruptedException ex) {
                //Logger.getLogger(AirplaneCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            gf.getGw().look(); //Check the pause/resume bottons
        }
    }

}
