package com.mycompany.catl;

import java.util.Queue;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Parking {

    private BlockingQueue<Airplane> airplanesForBoarding;
    private BlockingQueue<Airplane> airplanesForMaintenance;

    public Parking() {
        airplanesForBoarding = new LinkedBlockingQueue<Airplane>();
        airplanesForMaintenance = new LinkedBlockingQueue<Airplane>();
        //firstMaintenance = parkingLock.newCondition();
    }

    /**
     * It adds an airplane into the parking
     *
     * @param airplane the new airplane
     * @throws java.lang.InterruptedException
     */
    public void addAirplane(Airplane airplane) throws InterruptedException {
        if (!airplane.getLanding()) { //if airplane boarding send to boarding queue
            this.airplanesForBoarding.offer(airplane); //add the airplane at the end of the list
        } else {
            this.airplanesForMaintenance.offer(airplane); //add the airplane at the end of the list
        }

        System.out.println("Airplane " + airplane.getIdentifier() + " was added to parking.");
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
        Airplane removedAirplane;
        // Airplane is at the front of the queue and can proceed
        removedAirplane = airplanesForBoarding.poll(); // Remove the airplane from the queue
        System.out.println("Airplane " + removedAirplane.getIdentifier() + " was removed from parking.");
        System.out.println("Current airplanes in parking are: " + toString());
        return removedAirplane;
    }

    public Airplane releaseAirplaneForMaintenance(Airplane airplane) throws InterruptedException {
        System.out.println("Airplane " + airplane.getIdentifier() + " waiting. First airplane waiting for maintenance is " + airplanesForMaintenance.peek().getIdentifier());
        Airplane removedAirplane;
        // Airplane is at the front of the queue and can proceed
        removedAirplane = airplanesForMaintenance.poll(); // Remove the airplane from the queue
        System.out.println("Airplane " + removedAirplane.getIdentifier() + " was removed from parking.");
        System.out.println("Current airplanes in parking are: " + toString());
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
        Iterator<Airplane> boardingIterator = airplanesForBoarding.iterator(); // Create a new iterator
        Iterator<Airplane> maintenanceIterator = airplanesForMaintenance.iterator(); // Create a new iterator
        while (boardingIterator.hasNext()) {
            Airplane currPlane = boardingIterator.next();  // Assuming the object type is Airplane
            String identifier = currPlane.getIdentifier();
            int passengers = currPlane.getPassengers();  // Get the number of passengersString currPlane = boardingIterator.next().getIdentifier();
            allPlanes.append(identifier + "(" + passengers + ")" + ", ");
        }
        while (maintenanceIterator.hasNext()) {
            String currPlane = maintenanceIterator.next().getIdentifier();
            allPlanes.append(currPlane.concat(" "));
        }
        return allPlanes.toString();
    }

    public Queue<Airplane> getAirplanesForBoarding() {
        return airplanesForBoarding;
    }

    public Queue<Airplane> getAirplanesForMaintenance() {
        return airplanesForMaintenance;
    }

}
