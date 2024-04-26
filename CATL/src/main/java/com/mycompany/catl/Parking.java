/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */

public class Parking {
    Queue<Airplane> airplanes = new ConcurrentLinkedQueue<Airplane>();
    private Lock parkingLock=new ReentrantLock();

    public Queue<Airplane> getAirplanes() {
        return airplanes;
    }
    
    /**
    * It adds an airplane into the parking 
    * 
    * @param airplane the new airplane
    */
    public boolean addAirplane(Airplane airplane){
        this.parkingLock.lock();          //lock to avoid mutual exclusion between threads
        boolean added = false;
        try{
            added = this.airplanes.offer(airplane); //add the airplane at the end of the list
            System.out.println("Airplane " + airplane.getIdentifier() + " was added to parking: " + added);
            System.out.println("Current airplanes are: ");
            for(Airplane plane : airplanes) {
                System.out.println(airplane.getIdentifier());
            }
        } catch(Exception e) {
        } finally{
            parkingLock.unlock();             //unlock the lock
        } return added;
    }
    
    
    /**
    * It takes the airplane from the parking area with FIFO strategy
    * 
    * @param airplane the airplane that we want to take out from the parking
    * @return the airplane that we take out
    */
    public Airplane releaseAirplane(Airplane airplane){ //We don't need to lock it because with the FIFO strategy it is our airplane or it isn't 
            if(this.airplanes.peek()==airplane){ //If the first airplane is our airplane
                System.out.println("Successfully removed airplane " + airplane.getIdentifier() + " from parking.");
                return this.airplanes.poll(); //we remove it and return it
            } else {
                System.out.println("Error getting plane " + airplane.getIdentifier() + " from parking!");
                return null;
            }  
    }
    /**
    * It transform the parking array into a String
    * 
    * @return a string of the airplanes in the parking
    */
    public String parkingToString(){
        String answer="";
      //  System.out.println("----------------"+airplanes.size()+"-------------------");
        for(int i=0;i<airplanes.size();i++){
            answer=answer+airplanes.get(i).getIdentifier()+", ";
        }
        return answer;
    }
}
