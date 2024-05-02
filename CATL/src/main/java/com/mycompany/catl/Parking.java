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


/**
 *
 * @author THINKPAD
 */

public class Parking {
    private ConcurrentLinkedQueue<Airplane> airplanes;
    private Lock parkingLock;

    public Parking (){
        parkingLock = new ReentrantLock();
        airplanes = new ConcurrentLinkedQueue<Airplane>();
    }

    /**
    * It adds an airplane into the parking 
    * 
    * @param airplane the new airplane
    */
    public void addAirplane(Airplane airplane){
        this.parkingLock.lock();          //lock to avoid mutual exclusion between threads
        try{
            this.airplanes.offer(airplane); //add the airplane at the end of the list
            System.out.println("Airplane " + airplane.getIdentifier() + " was added to parking.");
            System.out.println("Current airplanes are: " + toString());
        } catch(Exception e) {
        } finally{
            parkingLock.unlock();             //unlock the lock
        } 
    }
    
    
    /**
    * It takes the airplane from the parking area with FIFO strategy
    * 
    * @param airplane the airplane that we want to take out from the parking
    * @return the airplane that we take out
    */
    /**
* It removes and returns the airplane from the front of the parking area if it matches the requested airplane.
* This ensures the FIFO strategy is maintained.
*
* @param airplane the airplane that we want to take out from the parking
* @return the airplane that was removed if it was at the front, otherwise null
*/
    public Airplane releaseAirplane(Airplane airplane){ 
        parkingLock.lock();
        try {
            if (!airplanes.isEmpty() && airplanes.peek().equals(airplane)) {
                Airplane removedAirplane = airplanes.poll(); // Removes and returns the head of the queue
                System.out.println("Airplane " + removedAirplane.getIdentifier() + " was removed from parking.");
                System.out.println("Current airplanes are: " + toString());
                return removedAirplane;
            } else {
                System.out.println("Error: Only the first airplane in the queue can be removed. " + airplane.getIdentifier() + " is not at the front.");
                return null;
            }
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
    public String toString(){
        StringBuilder allPlanes = new StringBuilder();
        Iterator<Airplane> newIterator = airplanes.iterator(); // Create a new iterator

        while(newIterator.hasNext()) {  
        String currPlane = newIterator.next().getIdentifier(); 
        allPlanes.append(currPlane.concat(" "));
    }
    return allPlanes.toString();
    }
}
