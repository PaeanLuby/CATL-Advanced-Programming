package com.mycompany.catl;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Hangar {

    Queue<Airplane> airplanes = new ConcurrentLinkedQueue<Airplane>();
    private Lock hangarLock = new ReentrantLock();
    int position;

    //Constructor
    public Hangar() {
        position = -1;
    }

    public Queue<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(Queue<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    /**
     * It adds an airplane into the hangar and returns its position on the list
     *
     * @param airplane the new airplane
     * @return its position on the list
     */
    public void addAirplane(Airplane airplane, Log log) {
        //int position=-1;           initialize puesto with -1 as an error signal
        this.hangarLock.lock();  //lock to avoid mutual exclusion between threads 
        this.airplanes.offer(airplane);  //adds the airplane to the airplanes list of hangar
        log.write("Successfully added airplane " + airplane.getIdentifier() + " to the hangar.");
        hangarLock.unlock(); //unlock the lock
    }

    /* It takes an airplane from the hangar
    * 
    * @param puesto the position of the airplane ib the list
    * @return the airplane in the position from the list
     */
    public Airplane releaseAirplane(Airplane airplane, Log log) {
        hangarLock.lock();
        boolean removed;                    //initialize an airplane with null as an error signal
        removed = this.airplanes.remove(airplane); //we take out the puesto's (position) airplane from the hangar airplanes list and remove it from the list
        log.write("Successfully removed airplane " + airplane.getIdentifier() + " from the hangar.");
        if (!removed) {                          //signal a possible error
            log.write("Error removing the plane from the hangar.");
        }
        hangarLock.unlock();
        return airplane;                            //return the airplane
    }

    /**
     * It transform the hangar array into a String
     *
     * @return a string of the airplanes in the hangar
     */
    public String toString() {
        StringBuilder allPlanes = new StringBuilder();
        Iterator<Airplane> newIterator = airplanes.iterator(); // Create a new iterator

        while (newIterator.hasNext()) {
            String currPlane = newIterator.next().getIdentifier();
            allPlanes.append(currPlane.concat(", "));
        }
        return allPlanes.toString();
    }

}
