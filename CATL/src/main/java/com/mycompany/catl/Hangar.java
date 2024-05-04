/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class Hangar {

    List<Airplane> airplanes = new LinkedList<Airplane>();
    private Lock hangarLock = new ReentrantLock();
    int position;

    //Constructor
    public Hangar() {
        position = -1;
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    /**
     * It adds an airplane into the hangar and returns its position on the list
     *
     * @param airplane the new airplane
     * @return its position on the list
     */
    public void addAirplane(Airplane airplane) {
        hangarLock.lock();
        //int position=-1;           initialize puesto with -1 as an error signal
        this.airplanes.add(airplane);  //adds the airplane to the airplanes list of hangar
        System.out.println("Successfully added airplane " + airplane.getIdentifier() + " to the hangar.");
        hangarLock.unlock();
    }

    /* It takes an airplane from the hangar
    * 
    * @param puesto the position of the airplane ib the list
    * @return the airplane in the position from the list
     */
    public Airplane releaseAirplane(Airplane airplane) {
        hangarLock.lock();
        boolean removed;                    //initialize an airplane with null as an error signal
        removed = this.airplanes.remove(airplane); //we take out the puesto's (position) airplane from the hangar airplanes list and remove it from the list 
        System.out.println("Successfully removed airplane " + airplane.getIdentifier() + " from the hangar.");
        if (!removed) {                          //signal a possible error
            System.out.println("Error removing the plane from the hangar.");
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
            allPlanes.append(currPlane.concat(" "));
        }
        return allPlanes.toString();
    }
    
    
}
