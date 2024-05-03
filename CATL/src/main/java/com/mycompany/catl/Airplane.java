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

    public Airport getAirportFirst() {
        return airportFirst;
    }
    private Airport airportSecond;
    private int boardingGateNumber;
    private GraphicalInterface gf;
    private boolean mAD; //Whether the airway is Mad_Bar
    private boolean landing; //whether the plane is landing
    private int numberFlights;
    private boolean actualAirport; //true when it is in Madrid, false in Barcelona

    public Airplane() {
        //Default constructor
    }

    public Airplane(int capacity, String identifier, Log log, Airport airportFirst, Airport airportSecond, GraphicalInterface gf, boolean mAD) {
        this.capacity = capacity;
        this.passengers = 0;
        this.identifier = identifier;
        this.log = log;
        this.airportFirst = airportFirst;
        this.airportSecond = airportSecond;
        this.gf = gf;
        this.numberFlights = 0;
        this.mAD = mAD;
        this.landing = false; //all airplanes start as boarding
        if (Character.getNumericValue(this.identifier.charAt(6)) % 2 == 0) {
            this.actualAirport = true;
        } else {
            this.actualAirport = false;
        }
        System.out.println("Airplane " + identifier + " with capacity " + capacity + " has been created.");
        this.log.write("Airplane " + identifier + " with capacity " + capacity + " has been created.");

    }

    /**
     * Graphical interface output of the hangar
     */
    public void graphicalHangar(boolean actualAirport, Airport airport) {
        if (actualAirport) {
            gf.setMadridHangar(airport.getHangar().toString());
        } else {
            gf.setBarcelonaHangar(airport.getHangar().toString());
        }
    }

    /**
     * Graphical interface output of the parking
     */
    public void graphicalParking(boolean actualAirport, Airport airport) {
        if (actualAirport) {
            gf.setMadridParking(airport.getParking().toString());
        } else {
            gf.setBarcelonaParking(airport.getParking().toString());
        }
    }
    
    public void graphicalBoardingGate(int gate,boolean actualAirport, Airport airport){
        if (actualAirport) {
            if(gate==0){
                gf.setMadridGate1(this.identifier);
            }else if (gate==1){
                gf.setMadridGate2(this.identifier);
            }else if (gate==2){
                gf.setMadridGate3(this.identifier);
            }else if (gate==3){
                gf.setMadridGate4(this.identifier);
            }else if (gate==4){
                gf.setMadridGate5(this.identifier);
            }else if (gate==5){
                gf.setMadridGate6(this.identifier);
            }else{
                System.out.println("ERROR in boarding gates graphicalBoardingGate");
            }
        } else {
            if(gate == 0) {
                gf.setBarcelonaGate1(this.identifier);
            } else if (gate == 1) {
                gf.setBarcelonaGate2(this.identifier);
            } else if (gate == 2) {
                gf.setBarcelonaGate3(this.identifier);
            } else if (gate == 3) {
                gf.setBarcelonaGate4(this.identifier);
            } else if (gate == 4) {
                gf.setBarcelonaGate5(this.identifier);
            } else if (gate == 5) {
                gf.setBarcelonaGate6(this.identifier);
            } else {
                System.out.println("ERROR in boarding gates graphicalBoardingGate");
            }
        }
    }


    public void run() {
        //while(true) {
        try {
            lifeCycle(airportFirst, airportSecond);
            lifeCycle(airportSecond, airportFirst);
        } catch (InterruptedException ex) {
            Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}
    }

    public void lifeCycle(Airport airport1, Airport airport2) throws InterruptedException {
        numberFlights++; //Increment number of flights
        //while(true) {
        gf.getGw().look(); //Check the pause/resume bottons
        //Airplane creation in the Hangar
        airport1.getHangar().addAirplane(this);
        this.graphicalHangar(this.actualAirport, airport1);

        gf.getGw().look(); //Check the pause/resume bottons
        this.log.write("The airplane " + this.identifier + " has been created in the hangar of the airport of: " + this.getCity());
        //Airplane moves to the Parking Area
        gf.getGw().look(); //Check the pause/resume bottons
        airport1.getParking().addAirplane(airport1.getHangar().releaseAirplane(this)); //taking the airplane from the hangar and put in it in Parking Area
        this.graphicalHangar(this.actualAirport, airport1);
        this.graphicalParking(this.actualAirport, airport1);
        this.log.write("The airplane " + this.identifier + " leaves the hangar and enters the parking in the airport of: " + this.getCity());
        gf.getGw().look(); //Check the pause/resume bottons

        int gate=-1;
        try {
            gate=airport1.getBoardingGates().enterGate(this, airport1); //enter into free space
            this.graphicalBoardingGate(gate, this.actualAirport, airport1);

        } catch (InterruptedException ex) {
            Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
        }

        gf.getGw().look(); //Check the pause/resume bottons
        //boarding attempt
            int remainingAttempts = 3;
            while (capacity >= airport1.getPassengers() && remainingAttempts > 0) { //while plane hasnt reached capacity 
                remainingAttempts--;
                this.setPassengers(airport1.getPassengers()); //Take available passengers
                long timeWait = (long) Math.random() * 4 + 1;
                Thread.sleep(timeWait); //Sleep for random time between 1 and 5 seconds if there aren't enough passengers
                gf.getGw().look(); //Check the pause/resume bottons
                System.out.println("Not yet full. Attempts left: " + remainingAttempts);
            }
            System.out.println("Airplane " + identifier + " is ready to start boarding.");
            for (int i = 0; i < passengers; i++) {
                Thread.sleep((long) Math.random() * 2000 + 1000); //Each passanger's transference to the airplane between 1 and 3 seconds 
            }
            System.out.println("Airplane " + identifier + " successfully boarded.");
            this.log.write("Airplane " + identifier + " successfully boarded.");
            airport1.getTaxiArea().enterTaxiArea(airport1.getBoardingGates().releaseGate(this)); //Enter taxi area
            System.out.println("Pilot check for airplane " + this.getIdentifier());
            Thread.sleep((long) (1000 + Math.random() * 4)); //Check for period between 1 and 5 seconds
            airport1.getRunways().enterRunway(airport1.getTaxiArea().releaseAirplane(this)); //
            System.out.println("Airplane " + this.getIdentifier() + " completing final checks.");
            Thread.sleep((long) Math.random() * 2 + 1); //Final checks between 1 and 3 seconds
            System.out.println("Airplane " + this.getIdentifier() + " is taking off.");
            Thread.sleep((long) Math.random() * 4 + 1); //Take off between 1 and 5 seconds
            getAirway(airport1).enterAirway(airport1.getRunways().releaseRunway(this)); //Enter airway and remove it from the runway
            Thread.sleep((long) Math.random() * 15 + 15); //Flight between 15 and 30 seconds
            //Airport 2
            //Attempt to access runway of the other airport
            this.setLanding(true); //Set landing to true
            System.out.println("Airplane " + this.getIdentifier() + " requested runway for landing.");
            //Take airplane from airway and put into runway
            int runway = airport2.getRunways().enterRunway(getAirway(airport2).releaseAirplane(this));
            while (runway == -1) {
                Thread.sleep((long) Math.random() * 4000 + 1000); //Detour random time between 1 and 5 seconds
                System.out.println("Airplane " + this.getIdentifier() + " taking a detour.");
                runway = airport2.getRunways().enterRunway(this);
            }
            System.out.println("Airplane " + this.getIdentifier() + " entered into runway " + runway);
            Thread.sleep((long) Math.random() * 4000 + 1000); //Land for a  random time between 1 and 5 seconds
            airport2.getTaxiArea().enterTaxiArea(airport2.getRunways().releaseRunway(this)); //Leave runway and directly access taxi area
            
            System.out.println("Airplane " + this.getIdentifier() + " flying between boarding gate and taxi area.");
            Thread.sleep((long) Math.random() * 2000 + 3000); //Cover distance between runway and boarding gate
            System.out.println("Airplane " + identifier + " is ready to start debarking.");
            airport2.getBoardingGates().enterGate(this, airport2);
            for (int i = 0; i < passengers; i++) {
                Thread.sleep((long) Math.random() * 4 + 1); //Each passanger's transference from the airplane between 1 and 5 seconds 
            } 
            airport2.getParking().addAirplane(airport2.getBoardingGates().releaseGate(this));
            System.out.println("Pilot check for airplane " + this.getIdentifier());
            Thread.sleep((long) (1 + Math.random() * 4)); //Check for period between 1 and 5 seconds

            System.out.println("Airplane " + this.getIdentifier() + " going through maintenance hall door.");
            Thread.sleep(1000); //Airplane takes 1 second to go through maintanence hall door
            this.getAirport(airport2).getMaintenanceHall().enterHall(this, airport2);
            //Check if airplane needs to be sent to the maintenance hall for deep or light inspection
            if (numberFlights % 15 == 0) { //if it's been 15 flight since last tune up'
                System.out.println("Airplane " + this.getIdentifier() + " going in for a deep inspection.");
                Thread.sleep((long) Math.random() * 5 + 5); //Inspection takes random time between 5 and 10 seconds
            } else {
                System.out.println("Airplane " + this.getIdentifier() + " going in for a quick inspection.");
                Thread.sleep((long) Math.random() * 1 + 4); //Inspection takes random time between 1 and 5 seconds
            }
            //Airplane decides to rest in hangar or continue life cycle
            int choice = 1 + (int) (Math.random() * 2); //50% chance
            if (choice == 1) { //if choice 1, rest in hangar 
                Thread.sleep((long) Math.random() * 15 + 15);
            } //else, continue with airports reversed
            airport2.getHangar().releaseAirplane(this); //take airplane from hangar
            this.switchAirway(); //switch airway to opposite
            this.setLanding(false);
            System.out.println("Airplane " + this.getIdentifier() + " has finished it's lap from " + airport1 + " to " + airport2);
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
        if (mAD) {
            mAD = false;
        }
    }

    public Airway getAirway(Airport airport) {
        if (mAD) {
            return airport.getMad_Bar();
        } else {
            return airport.getBar_Mad();
        }
    }

    public Airport getAirport(Airport airport) {
        return airport;
    }

    /**
     * Determine if the airplane is in Madrid or in Barcelona
     *
     * @return Madrid if the bus has an even identifier, Barcelona if it has an
     * odd identifier
     */
    public String getCity() {
        if (Character.getNumericValue(this.identifier.charAt(6)) % 2 == 0) {
            return "Madrid";
        } else {
            return "Barcelona";
        }
    }
    
    public boolean getLanding() {
        return landing;
    }
    
    public void setLanding(boolean landing) {
        this.landing = landing;
    }

}
