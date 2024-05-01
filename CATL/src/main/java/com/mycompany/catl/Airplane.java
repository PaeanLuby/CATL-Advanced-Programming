/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class Airplane extends Thread {
    private int capacity;
    private int passengers;
    private String identifier;
    private Log log;
    private Airport airportFirst;
    private Airport airportSecond;
    private int boardingGateNumber;
    private GraphicalInterface gf;
    private boolean mAD; //Whether the airway is Mad_Bar
    private boolean landing; //whether the plane is landing

    
    public Airplane(int capacity, String identifier, Log log, Airport airportFirst, Airport airportSecond, GraphicalInterface gf, boolean mAD) {
        this.capacity = capacity;
        this.passengers = 0;
        this.identifier = identifier;
        this.log = log;
        this.airportFirst = airportFirst;
        this.airportSecond = airportSecond;
        this.gf=gf;
        this.mAD = mAD;
        this.landing = false; //all airplanes start as boarding
        System.out.println("Airplane " + identifier + " with capacity " + capacity + " has been created.");
        this.log.write("Airplane " + identifier + " with capacity " + capacity + " has been created.");
    }
    /**
    * Graphical interface output of the hangar
    */
    public void graphicalHangar(Airport airport1, Airport airport2){
        if(this.getCity()=="Madrid"){
                    gf.setMadridHangar(airport1.getHangar().toString());
                }
                else{
                    gf.setBarcelonaHangar(airport2.getHangar().toString());
                }
    }
    /**
    * Graphical interface output of the parking
    */
    public void graphicalParking(Airport airport1, Airport airport2){
        if(this.getCity()=="Madrid"){
                    gf.setMadridParking(airport1.getParking().toString());
                }
                else{
                    gf.setBarcelonaParking(airport2.getParking().toString());
                }
    }
    
    public void run(){
        try {
            lifeCycle(airportFirst, airportSecond);
        } catch (InterruptedException ex) {
            Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void lifeCycle(Airport airport1, Airport airport2) throws InterruptedException {
        //while(true) {
            gf.getGw().look(); //Check the pause/resume bottons
            //Airplane creation in the Hangar
            airport1.getHangar().addAirplane(this);
            this.graphicalHangar(airport1, airport2);
            
            gf.getGw().look(); //Check the pause/resume bottons
            this.log.write("The airplane "+this.identifier+" has been created in the hangar of the airport of: "+this.getCity());
            //Airplane moves to the Parking Area
            gf.getGw().look(); //Check the pause/resume bottons
            airport1.getParking().addAirplane(airport1.getHangar().releaseAirplane(this)); //taking the airplane from the hangar and put in it in Parking Area
            this.graphicalHangar(airport1, airport1);
            this.graphicalParking(airport1, airport2);
            this.log.write("The airplane "+ this.identifier +" leaves the hangar and enters the parking in the airport of: "+this.getCity());
            gf.getGw().look(); //Check the pause/resume bottons
            try {
                airport1.getBoardingGates().enterGate(this, airport1); //enter into free space
            } catch (InterruptedException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            }
            gf.getGw().look(); //Check the pause/resume bottons
            //boarding attempt
            try {
            int remainingAttempts = 3;
            while (capacity >= airport1.getPassengers() && remainingAttempts > 0) {
                remainingAttempts--;
                this.setPassengers(airport1.getPassengers()); //Take available passengers
                long timeWait = (long) Math.random() * 4000 + 1000;
                Thread.sleep(timeWait); //Sleep for random time between 1 and 5 seconds if there aren't enough passengers
                gf.getGw().look(); //Check the pause/resume bottons
                System.out.println("Not yet full. Attempts left: " + remainingAttempts);
                } 

                for (int i = 0; i < passengers; i++) {
                       Thread.sleep((long) Math.random()*200 + 100); //Each passanger's transference to the airplane between 1 and 3 seconds 
                }
                System.out.println("Plane " + identifier + " successfully boarded.");   
                this.log.write("Plane " + identifier + " successfully boarded.");
                airport1.getTaxiArea().enterTaxiArea(airport1.getBoardingGates().releaseGate(this)); //Enter taxi area
                long checkDuration = (long) (1000 + Math.random() * 4000); //Check for period between 1 and 5 second
                int timesChecked = 0;
                while (airport1.getRunways().enterRunway(this) == -1) {
                    System.out.println("Pilot check " + timesChecked + " for airplane " + this.getIdentifier());
                    checkDuration = (long) (1000 + Math.random() * 4000);
                    timesChecked++;
                }
                System.out.println("Airplane " + this.getIdentifier() + " completing final checks.");
                Thread.sleep((long) Math.random() * 200 + 1000); //Final checks between 1 and 3 seconds
                System.out.println("Airplane " + this.getIdentifier() + " is taking off.");
                Thread.sleep((long) Math.random() * 4000 + 1000); //Take off between 1 and 5 seconds
                getAirway(airport1).enterAirway(this);
                Thread.sleep((long) Math.random() * 15000 + 15000); //Flight between 15 and 30 seconds
                //Airport 2
                //Attempt to access runway of the other airport
                int runway = this.getAirport(airport2).getRunways().enterRunway(this);
                while (runway == -1) {
                    runway = this.getAirport(airport2).getRunways().enterRunway(this);
                    Thread.sleep((long) Math.random() * 4000 + 1000); //Detour random time between 1 and 5 seconds
                }
                Thread.sleep((long) Math.random() * 4000 + 1000); //Land for a  random time between 1 and 5 seconds
                
            } catch (InterruptedException e){
            } finally {
            }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void switchAirway() {
        if(mAD) {
            mAD = false;
        }
    }
    
    public Airway getAirway(Airport airport) {
        if(mAD) {
            return airport.getMad_Bar();
        }
        else {
            return airport.getBar_Mad();
        }
    }
    
    public Airport getAirport(Airport airport) {
        return airport;
    }
    
    /**
    * Determine if the airplane is in Madrid or in Barcelona
    * 
    * @return Madrid if the bus has an even identifier, Barcelona if it has an odd identifier
    */
    public String getCity() {
        if (Character.getNumericValue(this.identifier.charAt(6)) % 2 == 0) {
            return "Madrid";
        } else {
            return "Barcelona";
        }
    }


    
    
}
