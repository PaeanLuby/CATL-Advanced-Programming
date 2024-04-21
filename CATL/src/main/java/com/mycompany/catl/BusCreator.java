/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.io.BufferedWriter;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class BusCreator extends Thread{
    private Lock textLock;
    private Airport madrid;
    private Airport barcelona;

    public BusCreator(Lock textLock, Airport madrid, Airport barcelona) {
        this.textLock = textLock;
        this.madrid = madrid;
        this.barcelona = barcelona;
    }
    
    public void run(){
        for(int i=0; i<4000; i++){ //4000
            Bus bus;
            String identifier = String.valueOf(i);
            while (identifier.length()!=4){ //If the identifier doesn't have 4 digits
                identifier="0"+identifier;  //We add 0 until it has 4 digits
            }
            identifier="B-"+identifier;    //We add B-
            if (i%2==0){                   //Even identifier for Madrid
                bus= new Bus(identifier,textLock,madrid);
            }
            else{                         //Odd identifier for Barcelona
                bus= new Bus(identifier,textLock,barcelona);
            }
            bus.start();
            
            long sleepTime = (long)(Math.random() * 500 + 500);  //random between 0,5 and 1
            try {
                sleep(sleepTime);    //sleeps between 0,5 and 1 second between each bus
            } catch (InterruptedException ex) {
                Logger.getLogger(BusCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
