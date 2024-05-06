package com.mycompany.catl;

import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class BusCreator extends Thread {

    private final Log log;
    private final Airport madrid;
    private final Airport barcelona;
    private final GraphicalInterface gf;

    public BusCreator(Log log, Airport madrid, Airport barcelona, Lock madridPassengersLock, Lock barcelonaPassengersLock, GraphicalInterface gf) {
        this.log = log;
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.gf = gf;
    }

    public void run() {
        for (int i = 0; i < 4000; i++) { //4000
            gf.getGw().look(); //Check the pause/resume bottons
            Bus bus;
            String identifier = String.valueOf(i);
            while (identifier.length() != 4) { //If the identifier doesn't have 4 digits
                identifier = "0" + identifier;  //We add 0 until it has 4 digits
            }
            identifier = "B-" + identifier;    //We add B-
            if (i % 2 == 0) {                   //Even identifier for Madrid
                bus = new Bus(identifier, log, madrid, gf);
            } else {                         //Odd identifier for Barcelona
                bus = new Bus(identifier, log, barcelona, gf);
            }
            bus.start();

            long sleepTime = (long) (Math.random() * 500 + 500);  //random between 0,5 and 1
            try {
                sleep(sleepTime);    //sleeps between 0,5 and 1 second between each bus
            } catch (InterruptedException ex) {
                Logger.getLogger(BusCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            gf.getGw().look(); //Check the pause/resume bottons
        }
    }
}
