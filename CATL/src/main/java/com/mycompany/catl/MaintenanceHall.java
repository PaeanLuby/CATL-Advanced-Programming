/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class MaintenanceHall {

    BlockingQueue airplanes;
    
    public MaintenanceHall() {
        airplanes = new ArrayBlockingQueue<Airplane>(20);
    }
    
    public void enterHall(Airplane airplane, Airport airport) throws InterruptedException {
        while (airplanes.remainingCapacity() == 0) { //Wait until there's spacein the queue to add an element
            wait();
        }
        airplanes.put(airplane.getAirport(airport).getParking().releaseAirplane(airplane)); //Pull first airplane from parking
    }

    public Airplane releaseHall(Airplane airplane) throws InterruptedException {
        synchronized (this) {
            if(airplanes.remove(airplane)) {
                notifyAll();
                return airplane;
            } else {
            return null;
            }
        }
    }
    


}



