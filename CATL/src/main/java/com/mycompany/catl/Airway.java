package com.mycompany.catl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class Airway {

    private Queue<Airplane> airplanes;
    private Lock airwayLock = new ReentrantLock();
    private String name;

    public Airway(String name) {
        airplanes = new ConcurrentLinkedQueue<Airplane>();
        this.name = name;
    }

    public void enterAirway(Airplane airplane) {
        this.airwayLock.lock();          //lock to avoid mutual exclusion between threads
        this.airplanes.offer(airplane); //add the airplane at the end of the list
        System.out.println("Airplane " + airplane.getIdentifier() + " was added to airway " + name);
        System.out.println("Current airplanes in airway are: " + toString());
        airwayLock.unlock();             //unlock the lock
    }

    public Queue<Airplane> getAirplanes() {
        return airplanes;
    }

    public String getAirwayName() {
        return name;
    }

    public void setAirplanes(Queue<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

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
