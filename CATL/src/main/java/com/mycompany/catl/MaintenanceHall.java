/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
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


    public void enterHallDoor(Airplane airplane, Airport airport) throws InterruptedException {
        System.out.println("Airplane " + airplane.getIdentifier() + " wants to enter hall.");
        enterDoor.lock();
        while(!airplane.getAirport(airport).getParking().getAirplanesForMaintenance().peek().equals(airplane)) {
            first.await();
        }
        airplanes.put(airplane.getAirport(airport).getParking().releaseAirplaneForMaintenance(airplane));
        first.signalAll();
        Thread.sleep(1000);
        enterDoor.unlock();
        System.out.println("Airplane " + airplane.getIdentifier() + " successfully entered into maintenance hall.");
            //Pull first airplane from parking
    }

    public Airplane releaseHall(Airplane airplane) throws InterruptedException {
        if (airplanes.remove(airplane)) {
            System.out.println("Airplane " + airplane.getIdentifier() + " successfully exiting the maintenance hall.");
            return airplane;
        } else {
            return null;
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

