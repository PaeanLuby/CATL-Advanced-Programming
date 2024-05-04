/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class BoardingGates {

    private int type;
    private Airplane[] gates;
    private int remainingAttempts;
    private Lock gateLock;
    private Condition full;

    public BoardingGates(int excludedGate) {
        this.type = type; //0 boarding, 1 landing, all others both
        this.gates = new Airplane[6];
        this.gateLock = new ReentrantLock();
        this.full = gateLock.newCondition();
    }

    public int enterGate(Airplane airplane, Airport airport) throws InterruptedException {
        gateLock.lock();
        int excludedGate = airplane.getLanding() ? 1 : 0; // Set excluded gate based on landing status
        try {
            printGates();
            int gate = isGatePresent(gates, excludedGate);
            // Iterate to find the first available gate that is not the excluded gate
            while (gate == -1) {
                System.out.println("Airplane " + airplane.getIdentifier() + " trying to get a gate.");
                full.await();
                gate = isGatePresent(gates, excludedGate);
            }
            
            if (excludedGate == 1) {
                gates[gate] = airport.getTaxiArea().releaseAirplane(airplane);
            } else {
                gates[gate] = airport.getParking().releaseAirplaneForBoarding(airplane);
            }
            System.out.println("Space " + gate + " available in the boarding gate.");
            return gate;
            //If no gate was found, wait
        } finally {
            gateLock.unlock();
        }
    }

    public Airplane releaseGate(Airplane airplane) throws InterruptedException {
        gateLock.lock();
        try {
            int planeIndex = Arrays.asList(gates).indexOf(airplane);
            System.out.println("Airplane " + airplane.getIdentifier() + " waiting to release boarding gate" + planeIndex);
            gates[planeIndex] = null;
            System.out.println("Airplane " + airplane.getIdentifier() + " released boarding gate " + planeIndex);
            full.signalAll();
        } finally {
            gateLock.unlock();
        }
        return airplane;
    }

    private void printGates() {
        for (int i = 0; i < gates.length; i++) {
            System.out.println("Gate " + i + ":");
            if (gates[i] == null) {
                System.out.println("null");
            } else {
                System.out.println(gates[i].getIdentifier());
            }
        }
    }

    private int isGatePresent(Airplane[] gates, int unusableGate) {
        for (int i = 0; i < gates.length; i++) {
            if (i != unusableGate && gates[i] == null) {  // Skip index that is not usable
                return i;
            }
        }
        return -1;
    }
}
