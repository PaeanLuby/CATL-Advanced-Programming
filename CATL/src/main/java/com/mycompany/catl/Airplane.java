package com.mycompany.catl;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Airplane extends Thread {

    private final int CAPACITY;
    private int passengers;
    private String identifier;
    private final Log log;
    private final Airport airportFirst; //Starting airport
    private final Airport airportSecond; //Destination airport
    private final GraphicalInterface gf;
    private boolean mAD; //Whether the airway is Mad_Bar
    private boolean landing; //Whether the plane is landing
    private int numberFlights;

    public Airplane(int CAPACITY, String identifier, Log log, Airport airportFirst, Airport airportSecond, GraphicalInterface gf, boolean mAD) {
        this.CAPACITY = CAPACITY;
        this.passengers = 0;
        this.identifier = identifier;
        this.log = log;
        this.airportFirst = airportFirst;
        this.airportSecond = airportSecond;
        this.gf = gf;
        this.numberFlights = 0;
        this.mAD = mAD;
        this.landing = false; //All airplanes start as boarding, so landing is false
        this.log.write("Airplane " + this.getIdentifier() + " with capacity " + CAPACITY + " has been created.");
    }

    @Override
    public void run() {
        while (true) {
            try {
                lifeCycle(airportFirst, airportSecond);
                lifeCycle(airportSecond, airportFirst);
            } catch (InterruptedException | RemoteException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Graphical interface output of the hangar
     *
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
     * @param airport it is the actual airport
     * @param delete if it is true, it cleans the runway because the airplane
     * has left if it is false it introduces the airplane in the boarding gate
     * @param boarding is true if plane is boarding
     */
    public void graphicalBoardingGate(int gate, Airport airport, boolean delete, boolean boarding) {
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
            if (boarding) {
                if (airport.toString().equals("MAD")) {
                    switch (gate) {
                        case 0 ->
                            gf.setMadridGate1("Boarding: " + this.identifier);
                        case 1 ->
                            gf.setMadridGate2("Boarding: " + this.identifier);
                        case 2 ->
                            gf.setMadridGate3("Boarding: " + this.identifier);
                        case 3 ->
                            gf.setMadridGate4("Boarding: " + this.identifier);
                        case 4 ->
                            gf.setMadridGate5("Boarding: " + this.identifier);
                        case 5 ->
                            gf.setMadridGate6("Boarding: " + this.identifier);
                        default ->
                            System.out.println("ERROR in boarding gates graphicalBoardingGate");
                    }
                } else {
                    switch (gate) {
                        case 0 ->
                            gf.setBarcelonaGate1("Boarding: " + this.identifier);
                        case 1 ->
                            gf.setBarcelonaGate2("Boarding: " + this.identifier);
                        case 2 ->
                            gf.setBarcelonaGate3("Boarding: " + this.identifier);
                        case 3 ->
                            gf.setBarcelonaGate4("Boarding: " + this.identifier);
                        case 4 ->
                            gf.setBarcelonaGate5("Boarding: " + this.identifier);
                        case 5 ->
                            gf.setBarcelonaGate6("Boarding: " + this.identifier);
                        default ->
                            System.out.println("ERROR in boarding gates graphicalBoardingGate");
                    }
                }
            } else {
                if (airport.toString().equals("MAD")) {
                    switch (gate) {
                        case 0 ->
                            gf.setMadridGate1("Disembark: " + this.identifier);
                        case 1 ->
                            gf.setMadridGate2("Disembark: " + this.identifier);
                        case 2 ->
                            gf.setMadridGate3("Disembark: " + this.identifier);
                        case 3 ->
                            gf.setMadridGate4("Disembark: " + this.identifier);
                        case 4 ->
                            gf.setMadridGate5("Disembark: " + this.identifier);
                        case 5 ->
                            gf.setMadridGate6("Disembark: " + this.identifier);
                        default ->
                            System.out.println("ERROR in boarding gates graphicalBoardingGate");
                    }
                } else {
                    switch (gate) {
                        case 0 ->
                            gf.setBarcelonaGate1("Disembark: " + this.identifier);
                        case 1 ->
                            gf.setBarcelonaGate2("Disembark: " + this.identifier);
                        case 2 ->
                            gf.setBarcelonaGate3("Disembark: " + this.identifier);
                        case 3 ->
                            gf.setBarcelonaGate4("Disembark: " + this.identifier);
                        case 4 ->
                            gf.setBarcelonaGate5("Disembark: " + this.identifier);
                        case 5 ->
                            gf.setBarcelonaGate6("Disembark: " + this.identifier);
                        default ->
                            System.out.println("ERROR in boarding gates graphicalBoardingGate");
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
    public void graphicalTaxiArea(Airport airport) {
        if (airport.toString().equals("MAD")) {
            if (!airport.getTaxiArea().getAirplanes().isEmpty()) {
                gf.setMadridTaxiArea(airport.getTaxiArea().toString());
            } else {
                gf.setMadridTaxiArea("");
            }
        } else {
            if (!airport.getTaxiArea().getAirplanes().isEmpty()) {
                gf.setBarcelonaTaxiArea(airport.getTaxiArea().toString());
            } else {
                gf.setBarcelonaTaxiArea("");
            }
        }
    }

    /**
     * Graphical interface output of the different runways
     *
     * @param runway it is the gate where the airplane has entered
     * @param airport it is the actual airport
     * @param delete if it is true, it cleans the runway because the airplane
     * has left if it is false it introduces the airplane in the boarding gate
     * @param takeOff true when the airplane takes off, false when it is landing
     */
    public void graphicalRunway(int runway, Airport airport, boolean delete, boolean takeOff) {
        if (delete) {
            if (airport.toString().equals("MAD")) {
                switch (runway) {
                    case 0 ->
                        gf.setMadridRunway1("");
                    case 1 ->
                        gf.setMadridRunway2("");
                    case 2 ->
                        gf.setMadridRunway3("");
                    case 3 ->
                        gf.setMadridRunway4("");
                    default ->
                        System.out.println("ERROR in runway graphicalRunway");
                }
            } else {
                switch (runway) {
                    case 0 ->
                        gf.setBarcelonaRunway1("");
                    case 1 ->
                        gf.setBarcelonaRunway2("");
                    case 2 ->
                        gf.setBarcelonaRunway3("");
                    case 3 ->
                        gf.setBarcelonaRunway4("");
                    default ->
                        System.out.println("ERROR in runway graphicalRunway");
                }
            }
        } else {
            if (airport.toString().equals("MAD")) {
                switch (runway) {
                    case 0 ->
                        gf.setMadridRunway1(this.identifier);
                    case 1 ->
                        gf.setMadridRunway2(this.identifier);
                    case 2 ->
                        gf.setMadridRunway3(this.identifier);
                    case 3 ->
                        gf.setMadridRunway4(this.identifier);
                    default ->
                        System.out.println("ERROR in runway graphicalRunway");
                }
            } else {
                switch (runway) {
                    case 0 ->
                        gf.setBarcelonaRunway1(this.identifier);
                    case 1 ->
                        gf.setBarcelonaRunway2(this.identifier);
                    case 2 ->
                        gf.setBarcelonaRunway3(this.identifier);
                    case 3 ->
                        gf.setBarcelonaRunway4(this.identifier);
                    default ->
                        System.out.println("ERROR in runway graphicalRunway");
                }
            }
            if (takeOff) {
                if (airport.toString().equals("MAD")) {
                    switch (runway) {
                        case 0 ->
                            gf.setMadridRunway1("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        case 1 ->
                            gf.setMadridRunway2("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        case 2 ->
                            gf.setMadridRunway3("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        case 3 ->
                            gf.setMadridRunway4("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        default ->
                            System.out.println("ERROR in runway graphicalRunway");
                    }
                } else {
                    switch (runway) {
                        case 0 ->
                            gf.setBarcelonaRunway1("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        case 1 ->
                            gf.setBarcelonaRunway2("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        case 2 ->
                            gf.setBarcelonaRunway3("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        case 3 ->
                            gf.setBarcelonaRunway4("Take-off: " + this.identifier + "(" + this.passengers + ")");
                        default ->
                            System.out.println("ERROR in runway graphicalRunway");
                    }
                }
            } else {
                if (airport.toString().equals("MAD")) {
                    switch (runway) {
                        case 0 ->
                            gf.setMadridRunway1("Landing: " + this.identifier + "(" + this.passengers + ")");
                        case 1 ->
                            gf.setMadridRunway2("Landing: " + this.identifier + "(" + this.passengers + ")");
                        case 2 ->
                            gf.setMadridRunway3("Landing: " + this.identifier + "(" + this.passengers + ")");
                        case 3 ->
                            gf.setMadridRunway4("Landing: " + this.identifier + "(" + this.passengers + ")");
                        default ->
                            System.out.println("ERROR in runway graphicalRunway");
                    }
                } else {
                    switch (runway) {
                        case 0 ->
                            gf.setBarcelonaRunway1("Landing: " + this.identifier + "(" + this.passengers + ")");
                        case 1 ->
                            gf.setBarcelonaRunway2("Landing: " + this.identifier + "(" + this.passengers + ")");
                        case 2 ->
                            gf.setBarcelonaRunway3("Landing: " + this.identifier + "(" + this.passengers + ")");
                        case 3 ->
                            gf.setBarcelonaRunway4("Landing: " + this.identifier + "(" + this.passengers + ")");
                        default ->
                            System.out.println("ERROR in runway graphicalRunway");
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
            if (!airport.getMad_Bar().getAirplanes().isEmpty()) {
                gf.setAirwayMadridBarcelona(airport.getMad_Bar().toString());
            } else {
                gf.setAirwayMadridBarcelona("");
            }
        } else {
            if (!airport.getBar_Mad().getAirplanes().isEmpty()) {
                gf.setAirwayBarcelonaMadrid(airport.getBar_Mad().toString());
            } else {
                gf.setAirwayBarcelonaMadrid("");
            }
        }
    }

    /**
     * Graphical interface output of the maintenance hall
     *
     * @param airport it is the actual airport
     */
    public void graphicalMaintenanceHall(Airport airport) {
        if (airport.toString().equals("MAD")) {
            gf.setMadridMaintenanceHall(airport.getMaintenanceHall().toString());
        } else {
            gf.setBarcelonaMaintenanceHall(airport.getMaintenanceHall().toString());
        }
    }

    public void lifeCycle(Airport airport1, Airport airport2) throws InterruptedException, RemoteException {
        numberFlights++; //Increment number of flights
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================ ENTER HANGAR OF STARTING AIRPORT ========================
   `*/
        if (numberFlights == 1) { //If flight is first flight
            //Add airplane to hangar.
            airport1.getHangar().addAirplane(this, log);
            this.graphicalHangar(airport1); //Update hangar GUI. Airplane shpould display in hangar
            this.log.write("The airplane " + this.getIdentifier() + " has been created in the hangar of the airport of: " + airport1);
            gf.getGw().look(); //Check the pause/resume bottons
        }

    /*
    * ================ ENTER PARKING OF STARTING AIRPORT =======================
    */
        airport1.getParking().addAirplane(airport1.getHangar().releaseAirplane(this, log)); //Take the airplane from the hangar and put in it in parking
        this.graphicalHangar(airport1);
        this.graphicalParking(airport1);
        this.log.write("The airplane " + this.getIdentifier() + " leaves the hangar and enters the parking in airport " + airport1 + ".");
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================ ENTER BOARDING GATE OF STARTING AIRPORT =================
    */
        int gate;
        gate = airport1.getBoardingGates().enterGateFromParking(this, airport1); //Enter into free boarding gate from parking
        this.log.write("Airplane " + this.getIdentifier() + " has entered into boarding gate " + gate + " of airport " + airport1 + ".");
        this.graphicalBoardingGate(gate, airport1, false, true);
        this.graphicalParking(airport1);
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================ ATTEMPT TO BOARD AT STARTING AIRPORT ====================
    */
        int remainingAttempts = 2;
        int currentPassengers = airport1.getPassengers().get(); //Single fetch per iteration. Attempt 1
        this.attemptBoarding(Math.min(CAPACITY, currentPassengers), airport1); //Takes maximum number of passengers or current number at airport
        while (CAPACITY > this.getPassengers() && remainingAttempts > 0) { //While plane hasnt reached capacity from the airport passengers, keep picking them up
            remainingAttempts--;
            this.attemptBoarding(Math.min(CAPACITY, this.getPassengers() + airport1.getPassengers().get()), airport1);
            long timeWait = (long) (Math.random() * 4000 + 1000);
            Thread.sleep(timeWait); //Sleep for random time between 1 and 5 seconds if there aren't enough passengers
            this.log.write("Airplane " + identifier + " is  finishing boarding attempt " + (3 - remainingAttempts) + " with " + this.getPassengers() + " in gate " + gate + " of airport " + airport1 + ".");
            gf.getGw().look(); //Check the pause/resume bottons
        }

        this.log.write("Airplane " + identifier + " is  finishing boarding on attempt " + (3 - remainingAttempts) + ". Preparing for takeoff with " + this.getPassengers() + " from gate " + gate + " of airport " + airport1 + ".");


    /*
    * ================ ENTER TAXI AREA OF STARTING AIRPORT =================
    */
        airport1.getTaxiArea().enterTaxiArea(airport1.getBoardingGates().releaseGate(this)); //Enter taxi area
        this.log.write("Airplane " + this.getIdentifier() + " has left the boarding gate and entered the taxi area of airport " + airport1 + ".");
        this.graphicalBoardingGate(gate, airport1, true, false);
        this.graphicalTaxiArea(airport1);
        this.log.write("Airplane " + this.getIdentifier() + " completing checks in taxi area before requesting runway in " + airport1 + ".");
        Thread.sleep((long) (1000 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================ ENTER RUNWAY OF STARTING AIRPORT =================
    */
        int rw = airport1.getRunways().enterRunway(airport1.getTaxiArea().releaseAirplane(this));
        this.graphicalTaxiArea(airport1);
        this.graphicalRunway(rw, airport1, false, true);
        this.log.write("Airplane " + this.getIdentifier() + " completing final checks in runway of airport " + airport1);
        Thread.sleep((long) Math.random() * 2000 + 1000); //Final checks between 1 and 3 seconds
        gf.getGw().look(); //Check the pause/resume bottons
        this.log.write("Airplane " + this.getIdentifier() + " is taking off from runway of airport " + airport1);
        Thread.sleep((long) Math.random() * 4000 + 1000); //Take off between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================ ENTER AIRWAY OF STARTING AIRPORT =================
    */
        getAirway(airport1).enterAirway(airport1.getRunways().releaseRunway(this)); //Enter airway and remove it from the runway
        this.log.write("Airplane " + this.getIdentifier() + " is entering airway " + getAirway(airport1).getAirwayName());
        this.graphicalRunway(rw, airport1, true, true);
        this.graphicalAirway(airport1);
        Thread.sleep((long) (Math.random() * 1500 + 1500)); //Flight between 15 and 30 seconds
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================  REQUEST RUNWAY OF DESTINATION AIRPORT =================
    */
        //Attempt to access runway of the other airport
        this.log.write("Airplane " + this.getIdentifier() + " requested runway for landing at airport " + airport2);

    /*
    * ================ ENTER RUNWAY OF DESTINATION AIRPORT =================
    */
        this.setLanding(true); //Set landing to true
        int runway = airport2.getRunways().enterRunway(this);
        this.graphicalRunway(runway, airport2, false, false);
        while (runway == -1) {
            this.log.write("Airplane " + this.getIdentifier() + " taking a detour.");
            Thread.sleep((long) (Math.random() * 4000 + 1000)); //Detour random time between 1 and 5 seconds
            gf.getGw().look(); //Check the pause/resume bottons
            runway = airport2.getRunways().enterRunway(this);
            this.graphicalRunway(runway, airport2, false, false);
        }
        getAirway(airport1).releaseAirplane(this, log);
        this.log.write("Airplane " + this.getIdentifier() + " entered runway " + runway + "at airport " + airport2);
        this.graphicalAirway(airport1);
        switchAirway();

    /*
    * ================ LAND AT DESTINATION AIRPORT =================
    */
        Thread.sleep((long) (Math.random() * 4000 + 1000)); //Land for a  random time between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================ ENTER TAXI AREA OF DESTINATION AIRPORT =================
    */
        airport2.getTaxiArea().enterTaxiArea(airport2.getRunways().releaseRunway(this)); //Leave runway and directly access taxi area
        this.log.write("Airplane " + this.getIdentifier() + " entered the taxi area of airport " + airport2);
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalRunway(runway, airport2, true, false);
        this.graphicalTaxiArea(airport2);

    /*
    * ================ ENTER BOARDING GATE OF DESTINATION AIRPORT =================
    */
        gate = airport2.getBoardingGates().enterGateFromTaxiArea(this, airport2);
        gf.getGw().look(); //Check the pause/resume bottons
        this.log.write("Airplane " + this.getIdentifier() + " flying between taxi area and boarding gate at airport " + airport2);
        this.graphicalTaxiArea(airport2);
        this.graphicalBoardingGate(gate, airport2, false, false);
        Thread.sleep((long) (Math.random() * 2000 + 3000)); //Flight time between 2 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons 

    /*
    * ================ BEGIN DISEMBARKING =================
    */
        this.log.write("Airplane " + this.getIdentifier() + " disembarking " + this.getPassengers() + " at boarding gate of " + airport2);
        Thread.sleep((long) (Math.random() * 4000 + 1000)); //All passengers' transference from the airplane between 1 and 5 seconds 
        airport2.addPassengers(this.getPassengers());
        this.setPassengers(0); //Reset passengers
        gf.getGw().look(); //Check the pause/resume bottons

    /*
    * ================ COMPLETE CHECKS IN PARKING AREA =================
    */
        airport2.getParking().addAirplane(airport2.getBoardingGates().releaseGate(this));
        this.log.write("Airplane " + this.getIdentifier() + " disembarked . Accessing parking area of " + airport2);
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalBoardingGate(gate, airport2, true, false);
        this.graphicalParking(airport2);
        this.log.write("Airplane " + this.getIdentifier() + " completing pilot checks in parking area of " + airport2);
        Thread.sleep((long) (1000 + Math.random() * 4000)); //Check for period between 1 and 5 seconds
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalBoardingGate(gate, airport2, true, false);
        this.graphicalParking(airport2);

    /*
    * ================ GO TO THE MAINTENANCE HALL FOR INSPECTION =================
    */
        this.log.write("Airplane " + this.getIdentifier() + " will attempt to enter maintenance hall door of airport " + airport2);
        this.getAirport(airport2).getMaintenanceHall().enterHallDoor(this, airport2, log);
        gf.getGw().look(); //Check the pause/resume bottons
        this.graphicalParking(airport2);
        this.graphicalMaintenanceHall(airport2);

        //Check if airplane needs to be sent to the maintenance hall for deep or light inspection
        if (numberFlights % 15 == 0) { //if it's been 15 flight since last tune up
            this.log.write("Airplane " + this.getIdentifier() + " going in for a deep inspection in maintenance hall of " + airport2);
            Thread.sleep((long) (Math.random() * 5000 + 5000)); //Inspection takes random time between 5 and 10 seconds
            gf.getGw().look(); //Check the pause/resume bottons
        } else {
            this.log.write("Airplane " + this.getIdentifier() + " going in for a quick inspection in maintenance hall of " + airport2);
            Thread.sleep((long) (Math.random() * 1000 + 4000)); //Inspection takes random time between 1 and 5 seconds
            gf.getGw().look(); //Check the pause/resume bottons
        }

        this.log.write("Airplane " + this.getIdentifier() + " finished inspection in airport " + airport2);
        //Airplane decides to rest in hangar or continue life cycle
        int choice = 1 + (int) (Math.random() * 2); //50% chance
        if (choice == 1) { //if choice 1, rest in hangar 
            this.log.write("Airplane " + this.getIdentifier() + " resting in hangar of " + airport2);
            airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this), log);
            this.graphicalHangar(airport2);
            this.graphicalMaintenanceHall(airport2);
            Thread.sleep((long) (Math.random() * 15000 + 15000));
            gf.getGw().look(); //Check the pause/resume bottons
        } else {
            this.log.write("Airplane " + this.getIdentifier() + " immediately restarting it's life cycle.");
            airport2.getHangar().addAirplane(airport2.getMaintenanceHall().releaseHall(this), log);
            this.graphicalMaintenanceHall(airport2);
            this.graphicalHangar(airport2);
        }
        this.setLanding(false);
        this.log.write("Airplane " + this.getIdentifier() + " has finished lap " + numberFlights + " from " + airport1 + " to " + airport2);
        gf.getGw().look(); //Check the pause/resume bottons
    }

    public int getCapacity() {
        return CAPACITY;
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

    public boolean getLanding() {
        return landing;
    }

    public void setLanding(boolean landing) {
        this.landing = landing;
    }

    private void attemptBoarding(int passengersToTake, Airport airport) throws InterruptedException {
        this.setPassengers(passengersToTake); //Add new passengers
        this.getAirport(airport).releasePassengers(passengersToTake); //Subtract those passengers from the airport

        for (int i = 0; i < passengersToTake; i++) {
            Thread.sleep((long) (Math.random() * 2000 + 1000)); //Each passanger's transference to the airplane between 1 and 3 seconds 
            gf.getGw().look(); //Check the pause/resume bottons
        }

    }
}
