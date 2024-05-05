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
import java.rmi.RemoteException;
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
     * @param airport it is the actual airport
     * @param delete if it is true, it cleans the runway because the airplane has left if it is false it introduces the airplane in the boarding gate
     * @param boarding true when boarding, false when disembarking
     */
    public void graphicalBoardingGate(int gate, Airport airport, boolean delete,boolean boarding){
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
            if (boarding){
                if (airport.toString().equals("MAD")) {
                    switch (gate) {
                        case 0 -> gf.setMadridGate1("Boarding: "+this.identifier);
                        case 1 -> gf.setMadridGate2("Boarding: "+this.identifier);
                        case 2 -> gf.setMadridGate3("Boarding: "+this.identifier);
                        case 3 -> gf.setMadridGate4("Boarding: "+this.identifier);
                        case 4 -> gf.setMadridGate5("Boarding: "+this.identifier);
                        case 5 -> gf.setMadridGate6("Boarding: "+this.identifier);
                        default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                    }
                } else {
                    switch (gate) {
                        case 0 -> gf.setBarcelonaGate1("Boarding: "+this.identifier);
                        case 1 -> gf.setBarcelonaGate2("Boarding: "+this.identifier);
                        case 2 -> gf.setBarcelonaGate3("Boarding: "+this.identifier);
                        case 3 -> gf.setBarcelonaGate4("Boarding: "+this.identifier);
                        case 4 -> gf.setBarcelonaGate5("Boarding: "+this.identifier);
                        case 5 -> gf.setBarcelonaGate6("Boarding: "+this.identifier);
                        default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                    }
                }
            } else{
                if (airport.toString().equals("MAD")) {
                    switch (gate) {
                        case 0 -> gf.setMadridGate1("Disembark: "+this.identifier);
                        case 1 -> gf.setMadridGate2("Disembark: "+this.identifier);
                        case 2 -> gf.setMadridGate3("Disembark: "+this.identifier);
                        case 3 -> gf.setMadridGate4("Disembark: "+this.identifier);
                        case 4 -> gf.setMadridGate5("Disembark: "+this.identifier);
                        case 5 -> gf.setMadridGate6("Disembark: "+this.identifier);
                        default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                    }
                } else {
                    switch (gate) {
                        case 0 -> gf.setBarcelonaGate1("Disembark: "+this.identifier);
                        case 1 -> gf.setBarcelonaGate2("Disembark: "+this.identifier);
                        case 2 -> gf.setBarcelonaGate3("Disembark: "+this.identifier);
                        case 3 -> gf.setBarcelonaGate4("Disembark: "+this.identifier);
                        case 4 -> gf.setBarcelonaGate5("Disembark: "+this.identifier);
                        case 5 -> gf.setBarcelonaGate6("Disembark: "+this.identifier);
                        default -> System.out.println("ERROR in boarding gates graphicalBoardingGate");
                    }
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
            if(!airport.getTaxiArea().getAirplanes().isEmpty()){
                gf.setMadridTaxiArea(airport.getTaxiArea().toString()+" ("+this.passengers+")");
            }else{
                gf.setMadridTaxiArea("");
            }
        } else {
            if(!airport.getTaxiArea().getAirplanes().isEmpty()){
                gf.setBarcelonaTaxiArea(airport.getTaxiArea().toString()+" ("+this.passengers+")");
            }else{
                gf.setBarcelonaTaxiArea("");
            }
        }
    }
    
        /**
         * Graphical interface output of the different runways
         * 
         * @param runway it is the gate where the airplane has entered
         * @param airport it is the actual airport
         * @param delete if it is true, it cleans the runway because the airplane has left if it is false it introduces the airplane in the boarding gate
         * @param takeOff true when the aiplane takes off, false when it is landing
         */
    public void graphicalRunway(int runway,Airport airport, boolean delete,boolean takeOff) {
        if (delete) {
            if (airport.toString().equals("MAD")) {
                switch (runway) {
                    case 0 -> gf.setMadridRunway1("");
                    case 1 -> gf.setMadridRunway2("");
                    case 2 -> gf.setMadridRunway3("");
                    case 3 -> gf.setMadridRunway4("");
                    default -> System.out.println("ERROR in runway graphicalRunway");
                }
            } else {
                switch (runway) {
                    case 0 -> gf.setBarcelonaRunway1("");
                    case 1 -> gf.setBarcelonaRunway2("");
                    case 2 -> gf.setBarcelonaRunway3("");
                    case 3 -> gf.setBarcelonaRunway4("");
                    default -> System.out.println("ERROR in runway graphicalRunway");
                }
            }
        } else {
            if(takeOff){
                if (airport.toString().equals("MAD")) {
                    switch (runway) {
                        case 0 -> gf.setMadridRunway1("Take-off: "+this.identifier+"("+this.passengers+")");
                        case 1 -> gf.setMadridRunway2("Take-off: "+this.identifier+"("+this.passengers+")");
                        case 2 -> gf.setMadridRunway3("Take-off: "+this.identifier+"("+this.passengers+")");
                        case 3 -> gf.setMadridRunway4("Take-off: "+this.identifier+"("+this.passengers+")");
                        default -> System.out.println("ERROR in runway graphicalRunway");
                    }
                } else {
                    switch (runway) {
                        case 0 -> gf.setBarcelonaRunway1("Take-off: "+this.identifier+"("+this.passengers+")");
                        case 1 -> gf.setBarcelonaRunway2("Take-off: "+this.identifier+"("+this.passengers+")");
                        case 2 -> gf.setBarcelonaRunway3("Take-off: "+this.identifier+"("+this.passengers+")");
                        case 3 -> gf.setBarcelonaRunway4("Take-off: "+this.identifier+"("+this.passengers+")");
                        default -> System.out.println("ERROR in runway graphicalRunway");
                    }
                }
            }else{
                if (airport.toString().equals("MAD")) {
                    switch (runway) {
                        case 0 -> gf.setMadridRunway1("Landing: "+this.identifier+"("+this.passengers+")");
                        case 1 -> gf.setMadridRunway2("Landing: "+this.identifier+"("+this.passengers+")");
                        case 2 -> gf.setMadridRunway3("Landing: "+this.identifier+"("+this.passengers+")");
                        case 3 -> gf.setMadridRunway4("Landing: "+this.identifier+"("+this.passengers+")");
                        default -> System.out.println("ERROR in runway graphicalRunway");
                    }
                } else {
                    switch (runway) {
                        case 0 -> gf.setBarcelonaRunway1("Landing:: "+this.identifier+"("+this.passengers+")");
                        case 1 -> gf.setBarcelonaRunway2("Landing: "+this.identifier+"("+this.passengers+")");
                        case 2 -> gf.setBarcelonaRunway3("Landing: "+this.identifier+"("+this.passengers+")");
                        case 3 -> gf.setBarcelonaRunway4("Landing: "+this.identifier+"("+this.passengers+")");
                        default -> System.out.println("ERROR in runway graphicalRunway");
                    }
                }
            }
        }
    }
            
            
        /**
         * Graphical interface output of the taxiArea
         * 
         * @param airport it is the actual airport
         */
        public void graphicalAirway(Airport airport) {
            if (airport.toString().equals("MAD")) {
                if(!airport.getMad_Bar().getAirplanes().isEmpty()){
                    gf.setAirwayMadridBarcelona(airport.getMad_Bar().toString()+" ("+this.passengers+")");
                }else{
                    gf.setAirwayMadridBarcelona("");
                }        
            } else {
                if(!airport.getBar_Mad().getAirplanes().isEmpty()){
                    gf.setAirwayBarcelonaMadrid(airport.getBar_Mad().toString()+" ("+this.passengers+")");
                }else{
                    gf.setAirwayBarcelonaMadrid("");
                }
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
        } catch (RemoteException ex) {
            Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}
    }

    public void lifeCycle(Airport airport1, Airport airport2) throws InterruptedException, RemoteException {
        numberFlights++; //Increment number of flights
        //while(true) {
        gf.getGw().look(); //Check the pause/resume bottons
        
    /*
    * ================ ENTER HANGAR OF STARTING AIRPORT =================
    */
    if (numberFlights == 1) { //if flight is first flight
        //Add airplane to hangar.
        airport1.getHangar().addAirplane(this,log);
        this.graphicalHangar(airport1); //Update hangar GUI. Airplane shpould display in hangar.
        this.log.write("The airplane " + this.identifier + " has been created in the hangar of the airport of: " + this.getCity());
        gf.getGw().look(); //Check the pause/resume bottons
    }
                
    /*
    * ================ ENTER PARKING OF STARTING AIRPORT =================
    */
    airport1.getParking().addAirplane(airport1.getHangar().releaseAirplane(this,log),log); //Take the airplane from the hangar and put in it in parking.
    this.graphicalHangar(airport1);
    this.graphicalParking(airport1);
    this.log.write("The airplane " + this.identifier + " leaves the hangar and enters the parking in the airport of: " + this.getCity());
    gf.getGw().look(); //Check the pause/resume bottons
                
    /*
    * ================ ENTER BOARDING GATE OF STARTING AIRPORT =================
    */
    int gate= -1; //Starts with no gate.
    gate=airport1.getBoardingGates().enterGate(this, airport1,log); //Enter into free boarding gate from parking.
    this.graphicalParking(airport1);
    this.graphicalBoardingGate(gate, airport1,false,true);
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
        this.log.write("Not yet full. Attempts left: " + remainingAttempts);
    }
    this.log.write("Airplane " + identifier + " is ready to start boarding.");
    for (int i = 0; i < passengers; i++) {
        Thread.sleep((long) Math.random() * 2000 + 1000); //Each passanger's transference to the airplane between 1 and 3 seconds 
        gf.getGw().look(); //Check the pause/resume bottons
    }
    //System.out.println("Airplane " + identifier + " successfully boarded.");
    this.log.write("Airplane " + identifier + " successfully boarded.");
            
    /*
    * ================ ENTER TAXI AREA OF STARTING AIRPORT =================
    */
    airport1.getTaxiArea().enterTaxiArea(airport1.getBoardingGates().releaseGate(this,log),log); //Enter taxi area
    this.graphicalBoardingGate(gate, airport1,true,true);
    this.graphicalTaxiArea(airport1);
    this.log.write("Pilot check for airplane " + this.getIdentifier());
    Thread.sleep((long) (1000 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
    gf.getGw().look(); //Check the pause/resume bottons

        /*
    * ================ ENTER RUNWAY OF STARTING AIRPORT =================
    */
    int rw=airport1.getRunways().enterRunway(airport1.getTaxiArea().releaseAirplane(this,log),log);
    this.graphicalTaxiArea(airport1);
    this.graphicalRunway(rw, airport1,false,true);
    this.log.write("Airplane " + this.getIdentifier() + " completing final checks.");
    Thread.sleep((long) Math.random() * 2000 + 1000); //Final checks between 1 and 3 seconds
    gf.getGw().look(); //Check the pause/resume bottons
    this.log.write("Airplane " + this.getIdentifier() + " is taking off.");
    Thread.sleep((long) Math.random() * 4000 + 1000); //Take off between 1 and 5 seconds
    gf.getGw().look(); //Check the pause/resume bottons
            
    /*
    * ================ ENTER AIRWAY OF STARTING AIRPORT =================
    */
    getAirway(airport1).enterAirway(airport1.getRunways().releaseRunway(this),log); //Enter airway and remove it from the runway
    this.graphicalRunway(rw, airport1, true,true);
    this.graphicalAirway(airport1); 
    Thread.sleep((long) Math.random() * 1500 + 1500); //Flight between 15 and 30 seconds
    gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================  REQUEST RUNWAY OF DESTINATION AIRPORT =================
    */
    //Attempt to access runway of the other airport
    this.log.write("Airplane " + this.getIdentifier() + " requested runway for landing.");
     
    /*
    * ================ ENTER RUNWAY OF DESTINATION AIRPORT =================
    */
    this.setLanding(true); //Set landing to true
    int runway = airport2.getRunways().enterRunway(this,log);
    this.graphicalRunway(runway, airport2, false,false); 
    if (runway != -1) {
        getAirway(airport1).releaseAirplane(this,log);
        this.graphicalAirway(airport1);
    }
    this.graphicalRunway(runway, airport2, false,false);
    while (runway == -1) {
        Thread.sleep((long) Math.random() * 4000 + 1000); //Detour random time between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons
        this.log.write("Airplane " + this.getIdentifier() + " taking a detour.");
        runway = airport2.getRunways().enterRunway(this,log);
        this.graphicalRunway(runway, airport2, false,false);
    }
    getAirway(airport1).releaseAirplane(this,log);
    this.graphicalAirway(airport1);
    this.log.write("Airplane " + this.getIdentifier() + " entered into runway " + runway);
    switchAirway();
                
    /*
    * ================ LAND AT DESTINATION AIRPORT =================
    */
    Thread.sleep((long) Math.random() * 4000 + 1000); //Land for a  random time between 1 and 5 seconds
    gf.getGw().look(); //Check the pause/resume bottons
            
    /*
    * ================ ENTER TAXI AREA OF DESTINATION AIRPORT =================
    */
    airport2.getTaxiArea().enterTaxiArea(airport2.getRunways().releaseRunway(this),log); //Leave runway and directly access taxi area
    this.graphicalRunway(runway, airport2, true,false);
    this.graphicalTaxiArea(airport2);
            
    /*
    * ================ ENTER BOARDING GATE OF DESTINATION AIRPORT =================
    */      
    
    gate=airport2.getBoardingGates().enterGate(this, airport2,log);
    this.log.write("Airplane " + this.getIdentifier() + " flying between taxi area and taxi boarding gate.");
    Thread.sleep((long) Math.random() * 2000 + 3000); 
    gf.getGw().look(); //Check the pause/resume bottons
    this.graphicalTaxiArea(airport2);
    this.log.write("Airplane " + identifier + " is ready to start debarking.");
    this.graphicalBoardingGate(gate, airport2, false,false);
    /*
    * ================ BEGIN DISEMBARKING =================
    */             
    for (int i = 0; i < passengers; i++) {
        Thread.sleep((long) Math.random() * 4000 + 1000); //Each passanger's transference from the airplane between 1 and 5 seconds 
        gf.getGw().look(); //Check the pause/resume bottons
    } 
    Thread.sleep((long) Math.random() * 4000 + 1000); //Each passenger's transference from the airplane between 1 and 5 seconds 
    gf.getGw().look(); //Check the pause/resume bottons        
    /*
    * ================ COMPLETE CHECKS IN PARKING AREA =================
    */     
    airport2.getParking().addAirplane(airport2.getBoardingGates().releaseGate(this,log),log);
    this.graphicalBoardingGate(gate, airport2, true,false);
    this.graphicalParking(airport2);
    this.log.write("Pilot check for airplane " + this.getIdentifier());
    Thread.sleep((long) (1 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
    gf.getGw().look(); //Check the pause/resume bottons
    
    /*
    * ================ GO TO THE MAINTENANCE HALL FOR INSPECTION =================
    */  
    
    this.log.write("Airplane " + this.getIdentifier() + " going through maintenance hall door.");
    Thread.sleep(1000); //Airplane takes 1 second to go through maintanence hall door
    gf.getGw().look(); //Check the pause/resume bottons
    this.getAirport(airport2).getMaintenanceHall().enterHall(this, airport2,log);
    this.graphicalParking(airport2);
    this.graphicalMaintenanceHall(airport2);

    //Check if airplane needs to be sent to the maintenance hall for deep or light inspection
    if (numberFlights % 15 == 0) { //if it's been 15 flight since last tune up'
        this.log.write("Airplane " + this.getIdentifier() + " going in for a deep inspection.");
        Thread.sleep((long) Math.random() * 500 + 500); //Inspection takes random time between 5 and 10 seconds
        gf.getGw().look(); //Check the pause/resume bottons
    } else {
        this.log.write("Airplane " + this.getIdentifier() + " going in for a quick inspection.");
        Thread.sleep((long) Math.random() * 1000 + 4000); //Inspection takes random time between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons
    }

    //Airplane decides to rest in hangar or continue life cycle
    int choice = 1 + (int) (Math.random() * 2000); //50% chance
    if (choice == 1) { //if choice 1, rest in hangar 
        airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this,log),log);
        this.graphicalHangar(airport2);
        this.graphicalMaintenanceHall(airport2);
        Thread.sleep((long) Math.random() * 1500 + 1500);
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalHangar(airport2);

    } else {
        airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this,log),log);
        this.graphicalMaintenanceHall(airport2);
        this.graphicalHangar(airport2);
    }

    this.setLanding(false);
    this.log.write("Airplane " + this.getIdentifier() + " has finished it's lap from " + airport1 + " to " + airport2);
    gf.getGw().look(); //Check the pause/resume bottons
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

