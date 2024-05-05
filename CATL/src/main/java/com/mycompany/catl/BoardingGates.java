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
    private Condition first;

    public BoardingGates(int excludedGate) {
        this.type = type; //0 boarding, 1 landing, all others both
        this.gates = new Airplane[6];
        this.gateLock = new ReentrantLock(true);
        this.full = gateLock.newCondition();
        this.first = gateLock.newCondition();
    }

    public int enterGateFromParking(Airplane airplane, Airport airport) throws InterruptedException {
        gateLock.lock();
        int excludedGate = 0;
        try {
            printGates();
            int gate = isGatePresent(gates, excludedGate);
            while (!airplane.getAirport(airport).getParking().getAirplanesForBoarding().peek().equals(airplane)) {
                first.await();
            }
            Airplane removedPlane = airplane.getAirport(airport).getParking().releaseAirplaneForBoarding(airplane);
            first.signal();
            while (isGatePresent(gates, excludedGate) == -1) {
                full.await();
                gate = isGatePresent(gates, excludedGate);
            }
            full.signal();
            gates[gate] = removedPlane;
            System.out.println("Space " + gate + " available in the boarding gate.");
            return gate;
            //If no gate was found, wait
        } finally {
            gateLock.unlock();
        }
    }

    public int enterGateFromTaxiArea(Airplane airplane, Airport airport) throws InterruptedException {
        gateLock.lock();
        int excludedGate = 1;
        try {
            printGates();
            int gate = isGatePresent(gates, excludedGate);
            while (isGatePresent(gates, excludedGate) == -1) {
                full.await();
                gate = isGatePresent(gates, excludedGate);
            }
            full.signal();
            gates[gate] = airplane.getAirport(airport).getTaxiArea().releaseAirplane(airplane);
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
