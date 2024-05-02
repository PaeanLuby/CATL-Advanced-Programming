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

    private BlockingQueue<Airplane> airplanes;
    private Lock parkingLock;
    private Condition notFirst;

    public Parking() {
        airplanes = new LinkedBlockingQueue<Airplane>();
        parkingLock = new ReentrantLock(true);
        notFirst = parkingLock.newCondition();
    }

    /**
     * It adds an airplane into the parking
     *
     * @param airplane the new airplane
     */
    public void addAirplane(Airplane airplane) throws InterruptedException {
        this.airplanes.put(airplane); //add the airplane at the end of the list
        System.out.println("Airplane " + airplane.getIdentifier() + " was added to parking.");
        System.out.println("Current airplanes are: " + toString());
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
     */
    public Airplane releaseAirplane(Airplane airplane) throws InterruptedException {
        parkingLock.lock();
        while (airplanes.isEmpty() || !airplanes.peek().equals(airplane)) {
            notFirst.await();
        }
        // Airplane is at the front of the queue and can proceed
        Airplane removedAirplane = airplanes.take(); // Remove the airplane from the queue
        notFirst.signalAll();
        System.out.println("Airplane " + removedAirplane.getIdentifier() + " was removed from parking.");
        System.out.println("Current airplanes in parking are: " + toString());
        parkingLock.unlock();
        return removedAirplane;
    }

    /**
     * It transform the parking array into a String
     *
     * @return a string of the airplanes in the parking
     */
    @Override
    public String toString() {
        StringBuilder allPlanes = new StringBuilder();
        Iterator<Airplane> newIterator = airplanes.iterator(); // Create a new iterator
        while (newIterator.hasNext()) {
            String currPlane = newIterator.next().getIdentifier();
            allPlanes.append(currPlane.concat(" "));
        }
        return allPlanes.toString();
    }
}
