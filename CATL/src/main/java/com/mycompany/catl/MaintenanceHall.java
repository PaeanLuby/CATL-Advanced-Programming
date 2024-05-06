package com.mycompany.catl;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class MaintenanceHall {

    BlockingQueue airplanes;
    Lock enterDoor;
    Condition first;

    public MaintenanceHall() {
        airplanes = new ArrayBlockingQueue<Airplane>(20);
        enterDoor = new ReentrantLock(); //Only one plane can enter through the door at a time
        first = enterDoor.newCondition();
    }

    public void enterHallDoor(Airplane airplane, Airport airport, Log log) throws InterruptedException {
        enterDoor.lock();
        try {
            while (!airplane.getAirport(airport).getParking().getAirplanesForMaintenance().peek().equals(airplane)) {
                log.write("Airplane " + airplane.getIdentifier() + " waiting to enter hall of airport " + airport);
                first.await();
            }
            airplanes.put(airplane.getAirport(airport).getParking().releaseAirplaneForMaintenance(airplane));
            log.write("Airplane " + airplane.getIdentifier() + " entered hall of airport " + airport);
            first.signalAll();
            Thread.sleep(1000);
        } finally {
            enterDoor.unlock();
        }
    }

    public Airplane releaseHall(Airplane airplane) throws InterruptedException {
        enterDoor.lock();
        try {
            if (airplanes.remove(airplane)) {
                System.out.println("Airplane " + airplane.getIdentifier() + " successfully exiting the maintenance hall.");
                Thread.sleep(1000);
                return airplane;
            } else {
                System.err.println("Error removing airplane " + airplane.getIdentifier() + " from the maintenance hall.");
                return null;
            }
        } finally {
            enterDoor.unlock();
        }
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

    public BlockingQueue getAirplanes() {
        return airplanes;
    }

}
