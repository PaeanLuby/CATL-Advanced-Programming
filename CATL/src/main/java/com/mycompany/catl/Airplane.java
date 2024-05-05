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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class Airplane extends Thread {

    private final int CAPACITY;
    private AtomicInteger passengers;
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
        this.CAPACITY = 0;
        //Default constructor
    }

    public Airplane(int CAPACITY, String identifier, Log log, Airport airportFirst, Airport airportSecond, GraphicalInterface gf, boolean mAD) {
        this.CAPACITY = CAPACITY;
        this.passengers = new AtomicInteger(0);
        this.identifier = identifier;
        this.log = log;
        this.airportFirst = airportFirst;
        this.airportSecond = airportSecond;
        this.gf = gf;
        this.numberFlights = 0;
        this.mAD = mAD;
        this.landing = false; //all airplanes start as boarding
        System.out.println("Airplane " + identifier + " with capacity " + CAPACITY + " has been created.");
        this.log.write("Airplane " + identifier + " with capacity " + CAPACITY + " has been created.");

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
     * Graphical interface output of the different runways
     *
     * @param gate it is the gate where the airplane has entered
     * @param actualAirport True if it is in Madrid, false if it is in Barcelona
     * @param airport it is the actual airport
     * @param delete if it is true, it cleans the runway because the airplane
     * has left if it is false it introduces the airplane in the boarding gate
     */
    public void graphicalBoardingGate(int gate, Airport airport, boolean delete) {
        if (delete) {
            if (airport.toString().equals("MAD")) {
                switch (gate) {
                    case 0 ->
                        gf.setMadridGate1("");
                    case 1 ->
                        gf.setMadridGate2("");
                    case 2 ->
                        gf.setMadridGate3("");
                    case 3 ->
                        gf.setMadridGate4("");
                    case 4 ->
                        gf.setMadridGate5("");
                    case 5 ->
                        gf.setMadridGate6("");
                    default ->
                        System.out.println("ERROR in boarding gates graphicalBoardingGate");
                }
            } else {
                switch (gate) {
                    case 0 ->
                        gf.setBarcelonaGate1("");
                    case 1 ->
                        gf.setBarcelonaGate2("");
                    case 2 ->
                        gf.setBarcelonaGate3("");
                    case 3 ->
                        gf.setBarcelonaGate4("");
                    case 4 ->
                        gf.setBarcelonaGate5("");
                    case 5 ->
                        gf.setBarcelonaGate6("");
                    default ->
                        System.out.println("ERROR in boarding gates graphicalBoardingGate");
                }
            }

        } else {
            if (airport.toString().equals("MAD")) {
                switch (gate) {
                    case 0 ->
                        gf.setMadridGate1(this.identifier);
                    case 1 ->
                        gf.setMadridGate2(this.identifier);
                    case 2 ->
                        gf.setMadridGate3(this.identifier);
                    case 3 ->
                        gf.setMadridGate4(this.identifier);
                    case 4 ->
                        gf.setMadridGate5(this.identifier);
                    case 5 ->
                        gf.setMadridGate6(this.identifier);
                    default ->
                        System.out.println("ERROR in boarding gates graphicalBoardingGate");
                }
            } else {
                switch (gate) {
                    case 0 ->
                        gf.setBarcelonaGate1(this.identifier);
                    case 1 ->
                        gf.setBarcelonaGate2(this.identifier);
                    case 2 ->
                        gf.setBarcelonaGate3(this.identifier);
                    case 3 ->
                        gf.setBarcelonaGate4(this.identifier);
                    case 4 ->
                        gf.setBarcelonaGate5(this.identifier);
                    case 5 ->
                        gf.setBarcelonaGate6(this.identifier);
                    default ->
                        System.out.println("ERROR in boarding gates graphicalBoardingGate");
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
     * @param delete if it is true, it cleans the runway because the airplane
     * has left if it is false it introduces the airplane in the boarding gate
     */
    public void graphicalRunway(int runway,Airport airport, boolean delete) {
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
            if (airport.toString().equals("MAD")) {
                switch (runway) {
                    case 0 -> gf.setMadridRunway1(this.identifier);
                    case 1 -> gf.setMadridRunway2(this.identifier);
                    case 2 -> gf.setMadridRunway3(this.identifier);
                    case 3 -> gf.setMadridRunway4(this.identifier);
                    default -> System.out.println("ERROR in runway graphicalRunway");
                }
            } else {
                switch (runway) {
                    case 0 -> gf.setBarcelonaRunway1(this.identifier);
                    case 1 -> gf.setBarcelonaRunway2(this.identifier);
                    case 2 -> gf.setBarcelonaRunway3(this.identifier);
                    case 3 -> gf.setBarcelonaRunway4(this.identifier);
                    default -> System.out.println("ERROR in runway graphicalRunway");
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
        while (true) {
            try {
                lifeCycle(airportFirst, airportSecond);
                lifeCycle(airportSecond, airportFirst);

            } catch (InterruptedException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            airport1.getHangar().addAirplane(this);
            this.graphicalHangar(airport1); //Update hangar GUI. Airplane shpould display in hangar.
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
        int gate = -1; //Starts with no gate.
        gate = airport1.getBoardingGates().enterGateFromParking(this, airport1); //Enter into free boarding gate from parking.
        this.log.write("Airplane " + identifier + " has entered into boarding gate " + gate + ".");
        this.graphicalParking(airport1);
        this.graphicalBoardingGate(gate, airport1, false);
        gf.getGw().look(); //Check the pause/resume bottons

        /*
    * ================ ATTEMPT TO BOARD AT STARTING AIRPORT =================
         */
        int remainingAttempts = 3;
        int passengersToBoard = airport1.getPassengers().get();
        
        while (CAPACITY >= passengersToBoard && remainingAttempts > 0) { //while plane hasnt reached capacity from the airport passengers
            remainingAttempts--;
            passengersToBoard = airport1.getPassengers().get();
            this.attemptBoarding(passengersToBoard, airport1);
            System.out.println("Not yet full. Attempts left: " + remainingAttempts);
            long timeWait = (long) Math.random() * 4000 + 1000;
            Thread.sleep(timeWait); //Sleep for random time between 1 and 5 seconds if there aren't enough passengers
            gf.getGw().look(); //Check the pause/resume bottons
            
        }
        
        if (remainingAttempts == 3) {this.attemptBoarding(passengersToBoard, airport1);} //If sufficient number of passengers the first time
        System.out.println("Airplane " + identifier + " successfully boarded.");
        this.log.write("Airplane " + identifier + " successfully boarded.");

        /*
    * ================ ENTER TAXI AREA OF STARTING AIRPORT =================
         */
        airport1.getTaxiArea().enterTaxiArea(airport1.getBoardingGates().releaseGate(this)); //Enter taxi area
        this.graphicalBoardingGate(gate, airport1, true);
        this.graphicalTaxiArea(airport1);
        System.out.println("Pilot check for airplane " + this.getIdentifier());
        Thread.sleep((long) (1000 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons

        /*
    * ================ ENTER RUNWAY OF STARTING AIRPORT =================
         */
        int rw = airport1.getRunways().enterRunway(airport1.getTaxiArea().releaseAirplane(this));
        this.graphicalTaxiArea(airport1);
        this.graphicalRunway(rw, airport1, false);
        System.out.println("Airplane " + this.getIdentifier() + " completing final checks.");
        Thread.sleep((long) Math.random() * 2000 + 1000); //Final checks between 1 and 3 seconds
        gf.getGw().look(); //Check the pause/resume bottons
        System.out.println("Airplane " + this.getIdentifier() + " is taking off.");
        Thread.sleep((long) Math.random() * 4000 + 1000); //Take off between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons

        /*
    * ================ ENTER AIRWAY OF STARTING AIRPORT =================
         */
        getAirway(airport1).enterAirway(airport1.getRunways().releaseRunway(this)); //Enter airway and remove it from the runway
        this.graphicalRunway(rw, airport1, true);
        this.graphicalAirway(airport1);
        Thread.sleep((long) Math.random() * 1500 + 1500); //Flight between 15 and 30 seconds
        gf.getGw().look(); //Check the pause/resume bottons

        /*
    * ================  REQUEST RUNWAY OF DESTINATION AIRPORT =================
         */
        //Attempt to access runway of the other airport
        System.out.println("Airplane " + this.getIdentifier() + " requested runway for landing.");

        /*
    * ================ ENTER RUNWAY OF DESTINATION AIRPORT =================
         */
        this.setLanding(true); //Set landing to true
        int runway = airport2.getRunways().enterRunway(getAirway(airport2).releaseAirplane(this));
        this.graphicalRunway(runway, airport2, false);
        while (runway == -1) {
            Thread.sleep((long) Math.random() * 4000 + 1000); //Detour random time between 1 and 5 seconds
            gf.getGw().look(); //Check the pause/resume bottons
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
        gf.getGw().look(); //Check the pause/resume bottons

        /*
    * ================ ENTER TAXI AREA OF DESTINATION AIRPORT =================
         */
        airport2.getTaxiArea().enterTaxiArea(airport2.getRunways().releaseRunway(this)); //Leave runway and directly access taxi area
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalRunway(runway, airport2, true);
        this.graphicalTaxiArea(airport2);

        /*
    * ================ ENTER BOARDING GATE OF DESTINATION AIRPORT =================
         */
        gate = airport2.getBoardingGates().enterGateFromTaxiArea(this, airport2);
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalTaxiArea(airport2);
        this.graphicalBoardingGate(gate, airport2, false);
        System.out.println("Airplane " + this.getIdentifier() + " flying between taxi area and taxi boarding gate.");
        Thread.sleep((long) Math.random() * 2000 + 3000);
        gf.getGw().look(); //Check the pause/resume bottons
        System.out.println("Airplane " + identifier + " is ready to start debarking.");

        /*
    * ================ BEGIN DISEMBARKING =================
         */
        Thread.sleep((long) Math.random() * 4000 + 100); //All passengers' transference from the airplane between 1 and 5 seconds 
        gf.getGw().look(); //Check the pause/resume bottons

        /*
    * ================ COMPLETE CHECKS IN PARKING AREA =================
         */
        airport2.getParking().addAirplane(airport2.getBoardingGates().releaseGate(this));
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalBoardingGate(gate, airport2, true);
        this.graphicalParking(airport2);
        System.out.println("Pilot check for airplane " + this.getIdentifier());
        Thread.sleep((long) (1000 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalBoardingGate(gate, airport2, true);
        this.graphicalParking(airport2);

        /*
    * ================ GO TO THE MAINTENANCE HALL FOR INSPECTION =================
         */
        System.out.println("Airplane " + this.getIdentifier() + " will attempt to enter maintenance hall door.");
        this.getAirport(airport2).getMaintenanceHall().enterHallDoor(this, airport2);
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalParking(airport2);
        this.graphicalMaintenanceHall(airport2);
        System.out.println("Airplane " + this.getIdentifier() + " went through maintenance hall door.");

        //Check if airplane needs to be sent to the maintenance hall for deep or light inspection
        if (numberFlights % 15 == 0) { //if it's been 15 flight since last tune up
            System.out.println("Airplane " + this.getIdentifier() + " going in for a deep inspection.");
            Thread.sleep((long) Math.random() * 500 + 500); //Inspection takes random time between 5 and 10 seconds
            gf.getGw().look(); //Check the pause/resume bottons
        } else {
            System.out.println("Airplane " + this.getIdentifier() + " going in for a quick inspection.");
            Thread.sleep((long) Math.random() * 1000 + 4000); //Inspection takes random time between 1 and 5 seconds
            gf.getGw().look(); //Check the pause/resume bottons
        }

        //Airplane decides to rest in hangar or continue life cycle
        int choice = 1 + (int) (Math.random() * 2); //50% chance
        if (choice == 1) { //if choice 1, rest in hangar 
            airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this));
            this.graphicalHangar(airport2);
            this.graphicalMaintenanceHall(airport2);
            Thread.sleep((long) Math.random() * 15000 + 15000);
            gf.getGw().look(); //Check the pause/resume bottons

        } else {
            airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this));
            this.graphicalMaintenanceHall(airport2);
            this.graphicalHangar(airport2);
        }

        this.setLanding(false);
        System.out.println("Airplane " + this.getIdentifier() + " has finished it's lap from " + airport1 + " to " + airport2);
        gf.getGw().look(); //Check the pause/resume bottons
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public AtomicInteger getPassengers() {
        return passengers;
    }

    public void setPassengers(AtomicInteger passengers) {
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

    public void attemptBoarding(int passengersToTake, Airport airport) throws InterruptedException {
        if (passengersToTake > CAPACITY) {
                passengersToTake = CAPACITY;
        } 

        this.getPassengers().set(passengersToTake); //Take available passengers+
        try {
            this.getAirport(airport).getPassengers().addAndGet(-passengersToTake); //Subtract those passengers from the airport 
        } catch (RemoteException ex) {
            Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < passengersToTake; i++) {
            Thread.sleep((long) Math.random() * 20 + 10); //Each passanger's transference to the airplane between 1 and 3 seconds 
            gf.getGw().look(); //Check the pause/resume bottons
        }
    }
}
