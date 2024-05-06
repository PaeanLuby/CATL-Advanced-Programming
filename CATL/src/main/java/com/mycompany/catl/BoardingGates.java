package com.mycompany.catl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class BoardingGates {

    private Airplane[] gates;
    private Lock gateLock;
    private Condition full;
    private Condition first;

    public BoardingGates() {
        this.gates = new Airplane[6];
        this.gateLock = new ReentrantLock(true);
        this.full = gateLock.newCondition();
        this.first = gateLock.newCondition();
    }

    public int enterGateFromParking(Airplane airplane, Airport airport) throws InterruptedException {
        gateLock.lock();
        int excludedGate = 0;
        try {
            int gate = isGatePresent(gates, excludedGate);
            while (!airplane.getAirport(airport).getParking().getAirplanesForBoarding().peek().equals(airplane)) {
                first.await();
            }
            while (gate == -1) {
                full.await();
                gate = isGatePresent(gates, excludedGate);
            }
            gates[gate] = airplane.getAirport(airport).getParking().releaseAirplaneForBoarding(airplane);
            first.signal();
            return gate;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Airplane " + airplane.getIdentifier() + " couldn't be transferred to the boarding gate from parking.");
            return -1;
        } finally {
            gateLock.unlock();
        }
    }

    public int enterGateFromTaxiArea(Airplane airplane, Airport airport) throws InterruptedException {
        gateLock.lock();
        int excludedGate = 1;
        try {
            int gate = isGatePresent(gates, excludedGate);
            while (isGatePresent(gates, excludedGate) == -1) {
                full.await();
                gate = isGatePresent(gates, excludedGate);
            }
            gates[gate] = airplane.getAirport(airport).getTaxiArea().releaseAirplane(airplane);
            return gate;
        } finally {
            gateLock.unlock();
        }
    }

    public Airplane releaseGate(Airplane airplane) throws InterruptedException {
        gateLock.lock();
        try {
            int planeIndex = Arrays.asList(gates).indexOf(airplane);
            gates[planeIndex] = null;
            full.signal();
            return airplane;
        } finally {
            gateLock.unlock();
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
    
    private Airplane[] getGates() {
        return gates;
    }

}
