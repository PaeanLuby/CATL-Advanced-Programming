/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author THINKPAD
 */
public class Parking {

    private BlockingQueue<Airplane> airplanesForBoarding;
    private BlockingQueue<Airplane> airplanesForMaintenance;
    private Lock parkingLock;
    private Condition firstBoarding;
    private Condition firstMaintenance;

    public Parking() {
        airplanesForBoarding = new LinkedBlockingQueue<Airplane>();
        airplanesForMaintenance = new LinkedBlockingQueue<Airplane>();
        parkingLock = new ReentrantLock();
        firstBoarding = parkingLock.newCondition();
        firstMaintenance = parkingLock.newCondition();
    }

    /**
     * It adds an airplane into the parking
     *
     * @param airplane the new airplane
     * @throws java.lang.InterruptedException
     */
    public void addAirplane(Airplane airplane) throws InterruptedException {
        parkingLock.lock();
        try {
            if (!airplane.getLanding()) { //if airplane boarding send to boarding queue
                this.airplanesForBoarding.put(airplane); //add the airplane at the end of the list
            } else {
                this.airplanesForMaintenance.put(airplane); //add the airplane at the end of the list
            }
            
            System.out.println("Airplane " + airplane.getIdentifier() + " was added to parking.");
        } finally {
            parkingLock.unlock();
        }
    }

    /**
     * It takes the airplane from the parking area with FIFO strategy
     *
     * @param airplane the airplane that we want to take out from the parking
     * @return the airplane that we take out
     */
    /**
     * It removes and returns the airplane from the front of the parking area if
     * it matches the requested airplane. This ensures the FIFO strategy is
     * maintained.
     *
     * @param airplane the airplane that we want to take out from the parking
     * @return the airplane that was removed if it was at the front, otherwise
     * null
     * @throws java.lang.InterruptedException
     */
    public Airplane releaseAirplaneForBoarding(Airplane airplane) throws InterruptedException {
        parkingLock.lock();
        try {
            Airplane removedAirplane;
            while ((airplanesForBoarding.isEmpty() || !airplanesForBoarding.peek().equals(airplane))) {
                System.out.println("Airplane " + airplane.getIdentifier() + " waiting. First airplane is " + airplanesForBoarding.peek().getIdentifier());
                firstBoarding.await();
            }
            
            // Airplane is at the front of the queue and can proceed
            removedAirplane = airplanesForBoarding.take(); // Remove the airplane from the queue
            System.out.println("Airplane " + removedAirplane.getIdentifier() + " was removed from parking.");
            firstBoarding.signalAll();
            System.out.println("Current airplanes in parking are: " + toString());
            return removedAirplane;  
        } finally {
            parkingLock.unlock();
        }
        
    }
    
    public Airplane releaseAirplaneForMaintenance(Airplane airplane) throws InterruptedException {
        parkingLock.lock();
        try {
            Airplane removedAirplane = null;
            while ((airplanesForMaintenance.isEmpty() || !airplanesForMaintenance.peek().equals(airplane))) {
                System.out.println("Airplane " + airplane.getIdentifier() + " waiting. First airplane is " + airplanesForMaintenance.peek().getIdentifier());
                firstMaintenance.await();
            }
            
            // Airplane is at the front of the queue and can proceed
            removedAirplane = airplanesForMaintenance.take(); // Remove the airplane from the queue
            System.out.println("Airplane " + removedAirplane.getIdentifier() + " was removed from parking.");
            firstMaintenance.signalAll();
            System.out.println("Current airplanes in parking are: " + toString());
            return removedAirplane; 
        } finally {
            parkingLock.unlock();
        }
        
    }

    /**
     * It transform the parking array into a String
     *
     * @return a string of the airplanes in the parking
     */
    @Override
    public String toString() {
        StringBuilder allPlanes = new StringBuilder();
        Iterator<Airplane> boardingIterator = airplanesForBoarding.iterator(); // Create a new iterator
        Iterator<Airplane> maintenanceIterator = airplanesForMaintenance.iterator(); // Create a new iterator
        while (boardingIterator.hasNext()) {
            String currPlane = boardingIterator.next().getIdentifier();
            allPlanes.append(currPlane.concat(" "));
        } 
        while (maintenanceIterator.hasNext()) {
            String currPlane = maintenanceIterator.next().getIdentifier();
            allPlanes.append(currPlane.concat(" "));
        } 
        return allPlanes.toString();
    }
}
