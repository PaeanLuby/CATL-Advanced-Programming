package com.mycompany.catl;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.RemoteException;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Bus extends Thread {

    private final String identifier;
    private int passengers;
    private final Log log;
    private final Airport airport;
    private final GraphicalInterface gf;

    public Bus(String identifier, Log log, Airport airport, GraphicalInterface gf) {
        this.identifier = identifier;
        this.log = log;
        this.airport = airport;
        this.gf = gf;
        this.passengers = 0;
    }

    /**
     * Determine if the bus is in Madrid or Barcelona
     *
     * @return Madrid if the bus has an even identifier, Barcelona if it has an
     * odd identifier
     */
    private String getCity() {
        if (Character.getNumericValue(this.identifier.charAt(5)) % 2 == 0) {
            return "Madrid";
        } else {
            return "Barcelona";
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Arrival to downtown
                gf.getGw().look(); //Check the pause/resume bottons
                arriveDowntown();
                //Passengers board
                gf.getGw().look();//Check the pause/resume bottons
                boardDowntownPassengers();
                gf.getGw().look(); //Check the pause/resume bottons

                if (this.getCity().equals("Madrid")) {
                    gf.setMadridBusTownAirport(identifier + " (" + this.getPassengers() + ")");
                } else {
                    gf.setBarcelonaBusTownAirport(identifier + " (" + this.getPassengers() + ")");
                }
                travelToAirport();
                gf.getGw().look(); //Check the pause/resume bottons

                
                this.log.write("The bus " + this.identifier + " has arrived to the airport of " + this.getCity() + " with " + this.getPassengers() + " passengers.");
                arriveAtAirport();
                gf.getGw().look(); //Check the pause/resume bottons
                if (this.getCity().equals("Madrid")) {
                    gf.setMadridPassengers(airport.getPassengers().get());
                } else {
                    gf.setBarcelonaPassengers(airport.getPassengers().get());
                }
                
                gf.getGw().look(); //Check the pause/resume bottons
                boardPassengersAtAirport();
                if (this.getCity().equals("Madrid")) {
                    gf.setMadridPassengers(airport.getPassengers().get());
                } else {
                    gf.setBarcelonaPassengers(airport.getPassengers().get());
                }
                gf.getGw().look(); //Check the pause/resume bottons
                
                if (this.getCity().equals("Madrid")) {
                    gf.setMadridBusAirportTown(identifier + " (" + this.getPassengers() + ")");
                } else {
                    gf.setBarcelonaBusAirportTown(identifier + " (" + this.getPassengers() + ")");
                }
                travelDowntown();
                gf.getGw().look(); //Check the pause/resume bottons
                this.log.write("The bus " + this.identifier + " has arrived to the downtown of " + this.getCity() + " with " + passengers + " passengers.");
            } catch (RemoteException ex) {
                Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void arriveDowntown() {
        this.log.write("The bus " + this.identifier + " has arrived to the city of " + this.getCity());
        try {
            Thread.sleep((long) (Math.random() * 3000 + 2000));
            gf.getGw().look();
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void boardDowntownPassengers() {
        int jumpIn = (int) (Math.random() * 51);
        this.setPassengers(jumpIn);
        this.log.write(jumpIn + " passengers have accessed to the bus " + this.identifier + " that initiates its route toward the airport of " + this.getCity());
    }

    private void travelToAirport() {
        try {
            Thread.sleep((long) (Math.random() * 5000 + 5000)); //Drive from downtown to the airport takes between 5 and 10 seconds
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void arriveAtAirport() {
        try {
            //Bus waits for 2-5 seconds for new passengers
            Thread.sleep((long) (Math.random() * 3000 + 2000));
            airport.addPassengers(this.getPassengers());
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void boardPassengersAtAirport() {
        int jumpIn = (int) (Math.random() * 51);
        this.setPassengers(this.airport.releasePassengers(jumpIn));
        this.log.write(jumpIn + " passengers have accessed to the bus " + this.identifier + " that initiates its route towards the downtown of " + this.getCity());
    }

    private void travelDowntown() {
        try {
            Thread.sleep((long) (Math.random() * 5000 + 5000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getPassengers() {
        return passengers;
    }
}
