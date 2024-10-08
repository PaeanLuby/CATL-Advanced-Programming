package com.mycompany.catl;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Airway {

    private Queue<Airplane> airplanes;
    private final String name;

    public Airway(String name) {
        airplanes = new ConcurrentLinkedQueue<Airplane>();
        this.name = name;
    }

    public void enterAirway(Airplane airplane) {
        this.airplanes.add(airplane); //Add the airplane at the end of the list
    }

    public Airplane releaseAirplane(Airplane airplane, Log log) {
        if (airplanes.remove(airplane)) { //Remove the airplane from whatever position in the list
            log.write("Current airplanes in airway are: " + toString());
            log.write("Airplane " + airplane.getIdentifier() + " was removed from airway " + name);
            return airplane;
        } else {
            System.out.println("Error removing airplane " + airplane.getIdentifier() + " from airway " + name);
            log.write("Error removing airplane " + airplane.getIdentifier() + " from airway " + name);
            return null;
        }
    }

    public Queue<Airplane> getAirplanes() {
        return airplanes;
    }

    public String getAirwayName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder allPlanes = new StringBuilder();
        Iterator<Airplane> airwayIterator = airplanes.iterator(); // Create a new iterator

        while (airwayIterator.hasNext()) {
            Airplane currPlane = airwayIterator.next();  // Assuming the object type is Airplane
            String identifier = currPlane.getIdentifier();
            int passengers = currPlane.getPassengers();  // Get the number of passengersString currPlane = boardingIterator.next().getIdentifier();
            allPlanes.append(identifier + "(" + passengers + ")" + ", ");
        }
        return allPlanes.toString();
    }
}
