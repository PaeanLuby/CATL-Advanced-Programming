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
        int excludedGate = airplane.getLanding() ? 1 : 0; // Set excluded gate based on landing status
        gateLock.lock();
        printGates();
        int gate = -1;
        // Iterate to find the first available gate that is not the excluded gate
        while (!isGatePresent(gates, airplane, excludedGate)) {
            System.out.println("Airplane " + airplane.getIdentifier() + " trying to get a gate.");
            full.await();
        }
        for (int i = 0; i < gates.length; i++) {
            if (gates[i] == null && i != excludedGate) {
                // Found an open gate that's not excluded
                gate = i;
                if (excludedGate == 1) {
                    gates[gate] = airport.getTaxiArea().releaseAirplane(airplane);
                } else {
                    gates[gate] = airport.getParking().releaseAirplaneForBoarding(airplane);
                }
                System.out.println("Space " + gate + " available in the boarding gate.");
                break;
            }
        }
        //If no gate was found, wait
        gateLock.unlock();
        
        return gate;
    }

    public Airplane releaseGate(Airplane airplane) throws InterruptedException {
        gateLock.lock();
        int planeIndex = Arrays.asList(gates).indexOf(airplane);
        System.out.println("Airplane " + airplane.getIdentifier() + " waiting to release boarding gate" + planeIndex);
        gates[planeIndex] = null;
        System.out.println("Airplane " + airplane.getIdentifier() + " released boarding gate " + planeIndex);
        full.signalAll();
        gateLock.unlock();
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
    
    private boolean isGatePresent(Airplane[] gates, Airplane airplane, int unusableGate) {
        for (int i = 0; i < gates.length; i++) {
            if (i != unusableGate && gates[i] == null) {  // Skip index 1 as it is not usable
                return true;
            }
        }
        return false;
    }
}
