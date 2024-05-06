package com.mycompany.catl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class TaxiArea {

    private BlockingQueue<Airplane> airplanes;

    public TaxiArea() {
        airplanes = new LinkedBlockingQueue<Airplane>();
    }

    public void enterTaxiArea(Airplane airplane) {
        airplanes.offer(airplane);
    }

    public BlockingQueue<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(BlockingQueue<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public Airplane releaseAirplane(Airplane airplane) {
        this.airplanes.remove(airplane);
        return airplane; //we remove it and return it
    }

    @Override
    public String toString() {
        StringBuilder allPlanes = new StringBuilder();
        // Create a new iterator
        for (Airplane currPlane : airplanes) {
            // Assuming the object type is Airplane
            String identifier = currPlane.getIdentifier();
            int passengers = currPlane.getPassengers();  // Get the number of passengersString currPlane = boardingIterator.next().getIdentifier();
            allPlanes.append(identifier + "(" + passengers + ")" + ", ");
        }
        return allPlanes.toString();

    }
}
