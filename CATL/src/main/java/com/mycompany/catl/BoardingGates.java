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
            //printGates();
            int gate = isGatePresent(gates, excludedGate);
            while (!airplane.getAirport(airport).getParking().getAirplanesForBoarding().peek().equals(airplane)) {
                first.await();
            }
            while (isGatePresent(gates, excludedGate) == -1) {
                full.await();
                gate = isGatePresent(gates, excludedGate);
            }
            full.signal();
            gates[gate] = airplane.getAirport(airport).getParking().releaseAirplaneForBoarding(airplane);
            first.signal();
            System.out.println("Space " + gate + " available in the boarding gate.");
            return gate;
            //If no gate was found, wait
        } finally {
            gateLock.unlock();
        }
    }

    public int enterGateFromTaxiArea(Airplane airplane, Airport airport, Log log) throws InterruptedException {
        gateLock.lock();
        int excludedGate = 1;
        try {
            //printGates(log);
            int gate = isGatePresent(gates, excludedGate);
            while (isGatePresent(gates, excludedGate) == -1) {
                full.await();
                gate = isGatePresent(gates, excludedGate);
            }
            full.signal();
            gates[gate] = airplane.getAirport(airport).getTaxiArea().releaseAirplane(airplane, log);
            return gate;
            //If no gate was found, wait
        } finally {
            gateLock.unlock();
        }
    }

    public Airplane releaseGate(Airplane airplane, Log log) throws InterruptedException {
        gateLock.lock();
        int planeIndex = Arrays.asList(gates).indexOf(airplane);
        log.write("Airplane " + airplane.getIdentifier() + " waiting to release boarding gate" + planeIndex);
        gates[planeIndex] = null;
        log.write("Airplane " + airplane.getIdentifier() + " released boarding gate " + planeIndex);
        full.signalAll();
        gateLock.unlock();
        return airplane;
    }

//    private void printGates(Log log) {
//        for (int i = 0; i < gates.length; i++) {
//            System.out.println("Gate " + i + ":");
//            if (gates[i] == null) {
//                log.write("null");
//            } else {
//                log.write(gates[i].getIdentifier());
//            }
//        }
//    }
    private int isGatePresent(Airplane[] gates, int unusableGate) {
        for (int i = 0; i < gates.length; i++) {
            if (i != unusableGate && gates[i] == null) {  // Skip index that is not usable
                return i;
            }
        }
        return -1;
    }

}
