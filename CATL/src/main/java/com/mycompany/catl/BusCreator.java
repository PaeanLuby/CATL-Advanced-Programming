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
    private Log log;
    private Airport madrid;
    private Lock madridPassengersLock;
    private Airport barcelona;
    private Lock barcelonaPassengersLock;
    private GraphicalInterface gf;

    public BusCreator(Log log, Airport madrid, Airport barcelona, Lock madridPassengersLock, Lock barcelonaPassengersLock,GraphicalInterface gf) {
        this.log = log;
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.madridPassengersLock = madridPassengersLock;
        this.barcelonaPassengersLock = barcelonaPassengersLock;
        this.gf=gf;
    }

    
    public void run(){
        for(int i=0; i<4000; i++){ //4000
            gf.getGw().look(); //Check the pause/resume bottons
            Bus bus;
            String identifier = String.valueOf(i);
            while (identifier.length()!=4){ //If the identifier doesn't have 4 digits
                identifier="0"+identifier;  //We add 0 until it has 4 digits
            }
            identifier="B-"+identifier;    //We add B-
            if (i%2==0){                   //Even identifier for Madrid
                bus= new Bus(identifier,log,madrid,madridPassengersLock,gf);
            }
            else{                         //Odd identifier for Barcelona
                bus= new Bus(identifier,log,barcelona,barcelonaPassengersLock,gf);
            }
            bus.start();
            
            long sleepTime = (long)(Math.random() * 500 + 500);  //random between 0,5 and 1
            try {
                sleep(sleepTime);    //sleeps between 0,5 and 1 second between each bus
            } catch (InterruptedException ex) {
                //Logger.getLogger(BusCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            gf.getGw().look(); //Check the pause/resume bottons
        }
    }
}
