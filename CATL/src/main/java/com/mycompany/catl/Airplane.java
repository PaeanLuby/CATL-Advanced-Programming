/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

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
    private Airport airport;
    private int boardingGateNumber;
    private GraphicalInterface gf;
    
    public Airplane(int capacity, String identifier, Log log, Airport airport,GraphicalInterface gf) {
        this.capacity = capacity;
        this.passengers = 0;
        this.identifier = identifier;
        this.log = log;
        this.airport = airport;
        this.gf=gf;
        this.boardingGateNumber = -1;
        System.out.println("Airplane " + identifier + " with capacity " + capacity + " has been created.");
        this.log.write("Airplane " + identifier + " with capacity " + capacity + " has been created.");
    }
    /**
    * Graphical interface output of the hangar
    */
    public void graphicalHangar(){
        if(this.getCity()=="Madrid"){
                    gf.setMadridHangar(airport.getHangar().hangarToString());
                }
                else{
                    gf.setBarcelonaHangar(airport.getHangar().hangarToString());
                }
    }
    /**
    * Graphical interface output of the parking
    */
    public void graphicalParking(){
        if(this.getCity()=="Madrid"){
                    gf.setMadridParking(airport.getParking().parkingToString());
                }
                else{
                    gf.setBarcelonaParking(airport.getParking().parkingToString());
                }
    }
    
    public void run(){
        while(true) {
            gf.getGw().look(); //Check the pause/resume bottons
            //Airplane creation in the Hangar
            if (!airport.getHangar().addAirplane(this)){System.out.println("ERROR inserting the plane in the hangar");} //possible error detection
            this.graphicalHangar();
            gf.getGw().look(); //Check the pause/resume bottons
            this.log.write("The airplane "+this.identifier+" has been created in the hangar of the airport of: "+this.getCity());
            //Airplane moves to the Parking Area
            gf.getGw().look(); //Check the pause/resume bottons
            airport.getParking().addAirplane(airport.getHangar().releaseAirplane(this)); //taking the airplane from the hangar and put in it in Parking Area
            this.graphicalHangar();
            this.graphicalParking();
            this.log.write("The airplane "+this.identifier+" leaves the hangar and enters the parking in the airport of: "+this.getCity());
            gf.getGw().look(); //Check the pause/resume bottons
            try {
                airport.getBoardingGates().enterGate(this); //enter into free space
            } catch (InterruptedException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            }
            gf.getGw().look(); //Check the pause/resume bottons
            //boarding attempt
            try {
            int remainingAttempts = 3;
            while (capacity >= airport.getPassengers() && remainingAttempts > 0) {
                remainingAttempts--;
                this.setPassengers(airport.getPassengers()); //Take available passengers
                long timeWait = (long) Math.random() * 4000 + 1000;
                Thread.sleep(timeWait); //Sleep for random time between 1 and 5 seconds if there aren't enough passengers
                gf.getGw().look(); //Check the pause/resume bottons
                System.out.println("Not yet full. Attempts left: " + remainingAttempts);
               // this.log.write("Not yet full. Attempts left: " + remainingAttempts);
                    //return false; //Boarding in progress
                } 

                for (int i = 0; i < passengers; i++) {
                       Thread.sleep((long) Math.random()*2 + 1); //Each passanger's transference to the airplane between 1 and 3 seconds 
                }
                System.out.println("Plane " + identifier + " successfully boarded.");   
                this.log.write("Plane " + identifier + " successfully boarded.");
                airport.getBoardingGates().releaseGate(this);
                long checkDuration = (long) (1 + Math.random() * 4); //Check for period between 1 and 5 seconds
                airport.getTaxiArea().enterTaxiArea(this); //Enter taxi area
                int timesChecked = 0;
                while (airport.getRunways().enterRunway(this) == -1) {
                    airport.getTaxiArea().pilotChecks(timesChecked, this);
                    checkDuration = (long) (1 + Math.random() * 4);
                    timesChecked++;
                }
                    //return true; //Boarding successful
            } catch (InterruptedException e){
            } finally {
            }
        }
        

        //
        //airport.getBoardingGate(index).startBoarding(this); //capacity initially not reached 
       // airport.getBoardingGate().addAirplane(airport.getParking().takeAirplane(this));
       
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
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
