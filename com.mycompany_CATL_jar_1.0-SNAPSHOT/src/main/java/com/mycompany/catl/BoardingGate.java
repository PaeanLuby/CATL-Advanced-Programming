/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class BoardingGate {
    private Airplane airplane;
    private boolean boarding;
    private boolean landing;
    private int type;
    private Lock access;
    private int remainingAttempts;
    
    public BoardingGate(Airplane airplane, int type) {
        this.airplane = airplane;
//        this.boarding = boarding;
//        this.landing = landing;
        this.type = type; //0 boarding, 1 landing, 2 both
        this.access = new ReentrantLock();
        this.remainingAttempts = 3;
    }
    
    public void board(boolean capacityReached) throws InterruptedException {
        access.lock();
        try {
            if (!capacityReached && remainingAttempts > 0) {
                airplane.setPassengers(airplane.getAirport().getPassengers()); //Take available passengers
                long timeWait = (long) Math.random() * 4000 + 1000;
                Thread.sleep(timeWait); //Sleep for random time between 1 and 5 seconds if there aren't enough passengers
                remainingAttempts--;
                //return false; //Boarding in progress
            } else {
                for (int i =  0; i < airplane.getPassengers(); i++) {
                   Thread.sleep((long) Math.random()*2000 + 1000); //Each passanger's transference to the airplane between 1 and 3 seconds 
                   remainingAttempts = 3;
                }
                //return true; //Boarding successful
            }
        } catch (InterruptedException e){
            
        } finally {
            access.unlock();
        }
        
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public boolean isBoarding() {
        return boarding;
    }

    public void setBoarding(boolean boarding) {
        this.boarding = boarding;
    }

    public boolean isLanding() {
        return landing;
    }

    public void setLanding(boolean landing) {
        this.landing = landing;
    }

    
    
}
