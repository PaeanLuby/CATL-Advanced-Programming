/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class BoardingGates {

    //private boolean boarding;
    //private boolean landing;
    private int type;
    private Airplane[] gates;
    private int remainingAttempts;

    public BoardingGates(int excludedGate) {
        this.type = type; //0 boarding, 1 landing, all others both
        this.gates = new Airplane[6];
    }

    public int enterGate(Airplane airplane, Airport airport) throws InterruptedException {
        //gateLock.lock();
        int gate = -2;
        int excludedGate = 0; //if boarding excludedGate is 0
        if (airplane.getLanding()) {
            excludedGate = 1; //if landing excludedGate is 1
        }
        gates[excludedGate] = new Airplane(); //fill gate with airplane so that it's not null
        synchronized (this) {
            while (!Arrays.asList(gates).contains(null)) {
                wait();
            }
       
            for (int i = 0; i < 6; i++) {
                if (gates[i] == null) { 
                    gate = i;
                    if (!airplane.getLanding()) {
                        gates[gate] = airplane.getAirport(airport).getParking().releaseAirplane(airplane); //Pull first airplane from parking
                    } else {
                        gates[gate] = airplane.getAirport(airport).getTaxiArea().releaseAirplane(airplane); //Pull first airplane from taxi area
                    }
                    break;
            } 
            }
        }
        System.out.println("Space " + gate + " available in the boarding gate.");
        //gateLock.unlock();
        return gate;
    }

    public Airplane releaseGate(Airplane airplane) throws NullPointerException {
        int planeIndex = Arrays.asList(gates).indexOf(airplane);
        System.out.println(planeIndex);
        synchronized (this) {
            if (planeIndex != -1) {
                gates[planeIndex] = null;
                notifyAll();
                return airplane;
            }
        }
        return null;
    }
}
