/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author THINKPAD
 */

public class Parking {
    private List<Airplane> airplanes;
    private Lock parkingLock;

    public Parking(List<Airplane> airplanes, Lock parkingLock) {
        this.airplanes = airplanes;
        this.parkingLock = parkingLock;
    }

    

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }
    
    /**
    * It adds an airplane into the parking 
    * 
    * @param airplane the new airplane
    */
    public void addAirplane(Airplane airplane){
        this.parkingLock.lock();          //lock to avoid mutual exclusion between threads 
        try{
            this.airplanes.add(airplane); //add the airplane at the end of the list
        }catch(Exception e) {}
        finally{
        parkingLock.unlock();             //unlock the lock
        }
    }
    
    /**
    * It takes the airplane from the parking area with FIFO strategy
    * 
    * @param airplane the airplane that we want to take out from the parking
    * @return the airplane that we take out
    */
    public Airplane takeAirplane(Airplane airplane){ //We don't need to lock it because with the FIFO strategy it is our airplane or it isn't 
        while(this.airplanes.getFirst()!=airplane){}//While the first airplane is not our plane we don't do anything
        if(this.airplanes.getFirst()==airplane){ //If the first airplane is our airplane
            return this.airplanes.removeFirst(); //we remove it and return it
        }
        else{                                    //for a possible error
            System.out.println("Error sacando avión del parking!");
            return null;
        }
    }
}
