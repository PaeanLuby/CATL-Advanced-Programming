/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
//        if (Character.getNumericValue(this.identifier.charAt(6)) % 2 == 0) {
//            this.actualAirport = true;
//        } else {
//            this.actualAirport = false;
//        }
        System.out.println("Airplane " + identifier + " with capacity " + capacity + " has been created.");
        this.log.write("Airplane " + identifier + " with capacity " + capacity + " has been created.");

    }

    /**
     * Graphical interface output of the hangar
     * 
     * @param actualAirport True if it is in Madrid, false if it is in Barcelona
     * @param airport it is the actual airport
     */
    public void graphicalHangar(Airport airport) {
        if (airport.toString().equals("MAD")) {
            gf.setMadridHangar(airport.getHangar().toString());
        } else {
            gf.setBarcelonaHangar(airport.getHangar().toString());
        }
    }

    /**
     * Graphical interface output of the parking
     * 
     * @param actualAirport True if it is in Madrid, false if it is in Barcelona
     * @param airport it is the actual airport
     */
    public void graphicalParking(Airport airport) {
        if (airport.toString().equals("MAD")) {
            gf.setMadridParking(airport.getParking().toString());
        } else {
            gf.setBarcelonaParking(airport.getParking().toString());
        }
    }
    /**
     * Graphical interface output of the diferent runways
     * 
     * @param gate it is the gate where the airplane has entered
     * @param actualAirport True if it is in Madrid, false if it is in Barcelona
     * @param airport it is the actual airport
     * @param delete if it is true, it cleans the runway because the airplane has left if it is false it introduces the airplane in the boarding gate
     */
    public void graphicalBoardingGate(int gate, Airport airport, boolean delete){
        if(delete){
            if (airport.toString().equals("MAD")) {
                switch (gate) {
                    case 0 -> gf.setMadridGate1("");
                    case 1 -> gf.setMadridGate2("");
                    case 2 -> gf.setMadridGate3("");
                    case 3 -> gf.setMadridGate4("");
                    case 4 -> gf.setMadridGate5("");
                    case 5 -> gf.setMadridGate6("");
                    default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                }
            } else {
                switch (gate) {
                    case 0 -> gf.setBarcelonaGate1("");
                    case 1 -> gf.setBarcelonaGate2("");
                    case 2 -> gf.setBarcelonaGate3("");
                    case 3 -> gf.setBarcelonaGate4("");
                    case 4 -> gf.setBarcelonaGate5("");
                    case 5 -> gf.setBarcelonaGate6("");
                    default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                }
            }

        }
        else{
            if (airport.toString().equals("MAD")) {
                switch (gate) {
                    case 0 -> gf.setMadridGate1(this.identifier);
                    case 1 -> gf.setMadridGate2(this.identifier);
                    case 2 -> gf.setMadridGate3(this.identifier);
                    case 3 -> gf.setMadridGate4(this.identifier);
                    case 4 -> gf.setMadridGate5(this.identifier);
                    case 5 -> gf.setMadridGate6(this.identifier);
                    default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                }
            } else {
                switch (gate) {
                    case 0 -> gf.setBarcelonaGate1(this.identifier);
                    case 1 -> gf.setBarcelonaGate2(this.identifier);
                    case 2 -> gf.setBarcelonaGate3(this.identifier);
                    case 3 -> gf.setBarcelonaGate4(this.identifier);
                    case 4 -> gf.setBarcelonaGate5(this.identifier);
                    case 5 -> gf.setBarcelonaGate6(this.identifier);
                    default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                }
            }
        }    
    }
    
     /**
     * Graphical interface output of the taxiArea
     * 
     * @param actualAirport True if it is in Madrid, false if it is in Barcelona
     * @param airport it is the actual airport
     */
    public void graphicalTaxiArea(Airport airport) {
        if (airport.toString().equals("MAD")) {
            gf.setMadridTaxiArea(airport.getTaxiArea().toString());
        } else {
            gf.setBarcelonaTaxiArea(airport.getTaxiArea().toString());
        }
    }
    
        /**
         * Graphical interface output of the different runways
         * 
         * @param runway it is the gate where the airplane has entered
         * @param mAD True if it is in Madrid, false if it is in Barcelona
         * @param airport it is the actual airport
         * @param delete if it is true, it cleans the runway because the airplane has left if it is false it introduces the airplane in the boarding gate
         */
    public void graphicalRunway(int runway, Airport airport, boolean delete) {
        // Arrays holding the method names for runways based on airport
        String[] madridRunways = {"setMadridRunway1", "setMadridRunway2", "setMadridRunway3", "setMadridRunway4"};
        String[] barcelonaRunways = {"setBarcelonaRunway1", "setBarcelonaRunway2", "setBarcelonaRunway3", "setBarcelonaRunway4"};

        // Determine which set of runways to use
        String[] runways = airport.toString().equals("MAD") ? madridRunways : barcelonaRunways;

        // Determine the identifier to set based on the delete flag
        String identifierToSet = delete ? "" : this.identifier;

        // Set the appropriate runway identifier or an empty string if runway is valid
        if (runway >= 0 && runway < 4) {
            try {
                // Use reflection to invoke the correct method
                Method method = gf.getClass().getMethod(runways[runway], String.class);
                try {
                    method.invoke(gf, identifierToSet);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException e) {
                System.out.println("ERROR in accessing runway method: " + e.getMessage());
            }
        } else {
            System.out.println("ERROR in runway graphicalRunway: invalid runway index.");
        }
    }

        /**
         * Graphical interface output of the taxiArea
         * 
         * @param actualAirport True if it is in Madrid, false if it is in Barcelona
         * @param airport it is the actual airport
         */
        public void graphicalAirway(Airport airport) {
            if (airport.toString().equals("MAD")) {
                gf.setAirwayMadridBarcelona(airport.getMad_Bar().toString());
            } else {
                gf.setAirwayBarcelonaMadrid(airport.getBar_Mad().toString());
            }
        }
    
    /**
     * Graphical interface output of the maintenance hall
     * 
     * @param actualAirport True if it is in Madrid, false if it is in Barcelona
     * @param airport it is the actual airport
     */
    public void graphicalMaintenanceHall(Airport airport) {
        if (airport.toString().equals("MAD")) {
            gf.setMadridMaintenanceHall(airport.getMaintenanceHall().toString());
        } else {
            gf.setBarcelonaMaintenanceHall(airport.getMaintenanceHall().toString());
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
        
    /*
    * ================ ENTER HANGAR OF STARTING AIRPORT =================
    */
        if (numberFlights == 1) { //if flight is first flight
        //Add airplane to hangar.
        airport1.getHangar().addAirplane(this);
        this.graphicalHangar(airport1); //Update hangar GUI. Airplane shpould display in hangar.
        gf.getGw().look(); //Check the pause/resume bottons.
        this.log.write("The airplane " + this.identifier + " has been created in the hangar of the airport of: " + this.getCity());
        gf.getGw().look(); //Check the pause/resume bottons
        }
                
    /*
    * ================ ENTER PARKING OF STARTING AIRPORT =================
    */
        airport1.getParking().addAirplane(airport1.getHangar().releaseAirplane(this)); //Take the airplane from the hangar and put in it in parking.
        this.graphicalHangar(airport1);
        this.graphicalParking(airport1);
        this.log.write("The airplane " + this.identifier + " leaves the hangar and enters the parking in the airport of: " + this.getCity());
        gf.getGw().look(); //Check the pause/resume bottons
                
    /*
    * ================ ENTER BOARDING GATE OF STARTING AIRPORT =================
    */
        int gate= -1; //Starts with no gate.
        gate=airport1.getBoardingGates().enterGate(this, airport1); //Enter into free boarding gate from parking.
        this.graphicalParking(airport1);
        this.graphicalBoardingGate(gate, airport1,false);
        gf.getGw().look(); //Check the pause/resume bottons
        
    /*
    * ================ ATTEMPT TO BOARD AT STARTING AIRPORT =================
    */
        int remainingAttempts = 3;
        while (capacity >= airport1.getPassengers() && remainingAttempts > 0) { //while plane hasnt reached capacity 
            remainingAttempts--;
            this.setPassengers(airport1.getPassengers()); //Take available passengers
            long timeWait = (long) Math.random() * 4000 + 1000;
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
            
    /*
    * ================ ENTER TAXI AREA OF STARTING AIRPORT =================
    */
        airport1.getTaxiArea().enterTaxiArea(airport1.getBoardingGates().releaseGate(this)); //Enter taxi area
        this.graphicalBoardingGate(gate, airport1,true);
        this.graphicalTaxiArea(airport1);
        System.out.println("Pilot check for airplane " + this.getIdentifier());
        Thread.sleep((long) (1000 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
        //RUNWAYS
        int rw=airport1.getRunways().enterRunway(airport1.getTaxiArea().releaseAirplane(this));
        this.graphicalTaxiArea(airport1);
        this.graphicalRunway(rw, airport1,false);
        System.out.println("Airplane " + this.getIdentifier() + " completing final checks.");
        Thread.sleep((long) Math.random() * 2000 + 1000); //Final checks between 1 and 3 seconds
        System.out.println("Airplane " + this.getIdentifier() + " is taking off.");
        Thread.sleep((long) Math.random() * 4000 + 1000); //Take off between 1 and 5 seconds
            
    /*
    * ================ ENTER RUNWAY OF STARTING AIRPORT =================
    */
        getAirway(airport1).enterAirway(airport1.getRunways().releaseRunway(this)); //Enter airway and remove it from the runway
        this.graphicalRunway(rw, airport1, true);
        this.graphicalAirway(airport1); 
        Thread.sleep((long) Math.random() * 1500 + 1500); //Flight between 15 and 30 seconds
         
    /*
    * ================  REQUEST RUNWAY OF DESTINATION AIRPORT =================
    */
        //Attempt to access runway of the other airport
        System.out.println("Airplane " + this.getIdentifier() + " requested runway for landing.");
     
    /*
    * ================ ENTER RUNWAY OF DESTINATION AIRPORT =================
    */
        this.setLanding(true); //Set landing to true
        int runway = airport2.getRunways().enterRunway(this);
        this.graphicalRunway(runway, airport2, false); 
        if (runway != -1) {
            getAirway(airport1).releaseAirplane(this);
            this.graphicalAirway(airport1);
        }
        this.graphicalRunway(runway, airport2, false);
        while (runway == -1) {
            Thread.sleep((long) Math.random() * 4000 + 1000); //Detour random time between 1 and 5 seconds
            System.out.println("Airplane " + this.getIdentifier() + " taking a detour.");
            runway = airport2.getRunways().enterRunway(this);
            this.graphicalRunway(runway, airport2, false);
        }
        getAirway(airport1).releaseAirplane(this);
        this.graphicalAirway(airport1);
        System.out.println("Airplane " + this.getIdentifier() + " entered into runway " + runway);
        switchAirway();
                
    /*
    * ================ LAND AT DESTINATION AIRPORT =================
    */
        Thread.sleep((long) Math.random() * 4000 + 1000); //Land for a  random time between 1 and 5 seconds
            
    /*
    * ================ ENTER TAXI AREA OF DESTINATION AIRPORT =================
    */
        airport2.getTaxiArea().enterTaxiArea(airport2.getRunways().releaseRunway(this)); //Leave runway and directly access taxi area
        this.graphicalRunway(runway, airport2, true);
        this.graphicalTaxiArea(airport2);
            
    /*
    * ================ ENTER BOARDING GATE OF DESTINATION AIRPORT =================
    */      
    
        gate=airport2.getBoardingGates().enterGate(this, airport2);
        System.out.println("Airplane " + this.getIdentifier() + " flying between taxi area and taxi boarding gate.");
        Thread.sleep((long) Math.random() * 2000 + 3000); 
        this.graphicalTaxiArea(airport2);
        System.out.println("Airplane " + identifier + " is ready to start debarking.");
        this.graphicalBoardingGate(gate, airport2, false);
    /*
    * ================ BEGIN DISEMBARKING =================
    */             
        for (int i = 0; i < passengers; i++) {
            Thread.sleep((long) Math.random() * 4000 + 1000); //Each passanger's transference from the airplane between 1 and 5 seconds 
        } 
            Thread.sleep((long) Math.random() * 4000 + 1000); //Each passenger's transference from the airplane between 1 and 5 seconds 
                
    /*
    * ================ COMPLETE CHECKS IN PARKING AREA =================
    */     
        airport2.getParking().addAirplane(airport2.getBoardingGates().releaseGate(this));
        this.graphicalBoardingGate(gate, airport2, true);
        this.graphicalParking(airport2);
        System.out.println("Pilot check for airplane " + this.getIdentifier());
        Thread.sleep((long) (1 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
    /*
    * ================ GO TO THE MAINTENANCE HALL FOR INSPECTION =================
    */  
            System.out.println("Airplane " + this.getIdentifier() + " going through maintenance hall door.");
            Thread.sleep(1000); //Airplane takes 1 second to go through maintanence hall door
            this.getAirport(airport2).getMaintenanceHall().enterHall(this, airport2);
            this.graphicalParking(airport2);
            this.graphicalMaintenanceHall(airport2);
            
            //Check if airplane needs to be sent to the maintenance hall for deep or light inspection
            if (numberFlights % 15 == 0) { //if it's been 15 flight since last tune up'
                System.out.println("Airplane " + this.getIdentifier() + " going in for a deep inspection.");
                Thread.sleep((long) Math.random() * 500 + 500); //Inspection takes random time between 5 and 10 seconds
            } else {
                System.out.println("Airplane " + this.getIdentifier() + " going in for a quick inspection.");
                Thread.sleep((long) Math.random() * 1000 + 4000); //Inspection takes random time between 1 and 5 seconds
            }
            
            //Airplane decides to rest in hangar or continue life cycle
            int choice = 1 + (int) (Math.random() * 2000); //50% chance
            if (choice == 1) { //if choice 1, rest in hangar 
                airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this));
                this.graphicalHangar(airport2);
                this.graphicalMaintenanceHall(airport2);
                Thread.sleep((long) Math.random() * 1500 + 1500);
                this.graphicalHangar(airport2);
                
            } else {
                airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this));
                this.graphicalMaintenanceHall(airport2);
                this.graphicalHangar(airport2);
            }
            
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

