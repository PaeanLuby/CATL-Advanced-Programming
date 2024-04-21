/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.io.BufferedWriter;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class AirplaneCreator extends Thread {
    private Lock textLock;
    private Airport madrid;
    private Airport barcelona;
    private BufferedWriter writerBuffer;

    public AirplaneCreator(Lock textLock, Airport madrid, Airport barcelona, BufferedWriter writerBuffer) {
        this.textLock = textLock;
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.writerBuffer = writerBuffer;
    }
    /**
    * Create a random identifier
    * 
    * @return two random capital letters and a "-"  Ex:XX-
    */
    public String randomLetters(){
        List<String> alphabet = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        ));
        int firstLetter = (int)(Math.random() * 25);
        int secondtLetter = (int)(Math.random() * 25);
        String randomLetters=alphabet.get(firstLetter)+alphabet.get(secondtLetter)+"-";
        return randomLetters;
    }
    
    public void run(){
        for(int i=0; i<8000; i++){ //8000
            Airplane airplane;
            String identifier = String.valueOf(i);
            while (identifier.length()!=4){ //If the identifier doesn't have 4 digits
                identifier="0"+identifier;  //We add 0 until it has 4 digits
            }
            identifier=randomLetters()+identifier;    //We add two random letters at the beginning
            int capacity = (int)(Math.random() * 200)+100;
            
            if (i%2==0){                   //Even identifier for Madrid
                airplane= new Airplane(capacity,identifier,textLock,madrid,writerBuffer);
            }
            else{                         //Odd identifier for Barcelona
                airplane= new Airplane(capacity,identifier,textLock,barcelona,writerBuffer);
            }
            airplane.start();
            
            long sleepTime = (long)(Math.random() * 2 + 1);  //random between 1 and 3. Temporarily modified for testing
            try {
                sleep(sleepTime);    //sleeps between 1 and 3 second between each bus
            } catch (InterruptedException ex) {
                Logger.getLogger(BusCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
