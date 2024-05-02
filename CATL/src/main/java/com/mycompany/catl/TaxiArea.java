package com.mycompany.catl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class TaxiArea {

    private ConcurrentLinkedQueue<Airplane> airplanes;
    private Lock taxiLock = new ReentrantLock();

    public TaxiArea() {
        airplanes = new ConcurrentLinkedQueue<Airplane>();
    }

    public void enterTaxiArea(Airplane airplane) {
        airplanes.offer(airplane);
        System.out.println("Airplane " + airplane.getIdentifier() + " is in the taxi area.");
        System.out.println("Current airplanes in taxi area are:" + toString());
    }

    public ConcurrentLinkedQueue<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(ConcurrentLinkedQueue<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public Airplane releaseAirplane(Airplane airplane) {
        if (this.airplanes.remove(airplane)) {
            System.out.println("Successfully removed airplane " + airplane.getIdentifier() + " from parking.");
        } else {
            System.out.println("Error removing airplane from parking.");
        }
        return airplane; //we remove it and return it
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
