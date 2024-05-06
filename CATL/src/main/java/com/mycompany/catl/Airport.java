package com.mycompany.catl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Airport extends UnicastRemoteObject implements RemoteInterface {

    private AtomicInteger currentPassengers;
    private final Hangar hangar;
    private final Airway Mad_Bar;
    private final Airway Bar_Mad;
    private final TaxiArea taxiArea;
    private final Parking parking;
    private final MaintenanceHall maintenanceHall;
    private final BoardingGates boardingGates;
    private final Runways runways;
    private final String name;

    public Airport(Hangar hangar, Airway Mad_Bar, Airway Bar_Mad, TaxiArea taxiArea, Parking parking, MaintenanceHall maintenanceHall, BoardingGates boardingGates, Runways runways, String name) throws RemoteException {
        this.hangar = hangar;
        this.Mad_Bar = Mad_Bar;
        this.Bar_Mad = Bar_Mad;
        this.taxiArea = taxiArea;
        this.parking = parking;
        this.maintenanceHall = maintenanceHall;
        this.boardingGates = boardingGates;
        this.runways = runways;
        this.name = name;
        this.currentPassengers = new AtomicInteger(0);
    }

    // Method to add passengers.
    public void addPassengers(int passengers) {
        currentPassengers.addAndGet(passengers);
    }

    // Method to offload passengers, ensuring total passengers never go below 0.
    public int releasePassengers(int passengers) {
        while (true) {
            int nowPassengers = this.currentPassengers.get();
            int passengersToOffload = Math.min(passengers, nowPassengers);
            int newPassengers = Math.max(0, nowPassengers - passengers);
            if (this.currentPassengers.compareAndSet(nowPassengers, newPassengers)) {
                return passengersToOffload;
            }
        }
    }

    public AtomicInteger getPassengers() throws RemoteException {
        return currentPassengers;
    }

    public void setPassengers(AtomicInteger passengers) {
        this.currentPassengers = passengers;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public Airway getMad_Bar() {
        return Mad_Bar;
    }

    public Airway getBar_Mad() {
        return Bar_Mad;
    }

    public TaxiArea getTaxiArea() {
        return taxiArea;
    }

    public Parking getParking() {
        return parking;
    }

    public MaintenanceHall getMaintenanceHall() {
        return maintenanceHall;
    }

    public BoardingGates getBoardingGates() {
        return boardingGates;
    }

    public Runways getRunways() {
        return runways;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int numHangar() throws RemoteException {
        int num = this.hangar.getAirplanes().size();
        return num;
    }

    public int numMaintenance() throws RemoteException {
        int num = this.maintenanceHall.getAirplanes().size();
        return num;
    }

    public int numParking() throws RemoteException {
        int num = this.parking.getAirplanesForBoarding().size() + this.parking.getAirplanesForMaintenance().size();
        return num;
    }

    public int numTaxiArea() throws RemoteException {
        int num = this.taxiArea.getAirplanes().size();
        return num;
    }

    public String showMadBarAirway() throws RemoteException {
        String airway = this.Mad_Bar.toString();
        return airway;
    }

    public String showBarMadAirway() throws RemoteException {
        String airway = this.Bar_Mad.toString();
        return airway;
    }

    /* It opens or closes a runway. It is synchronized in case there is more than one client
    * 
    *@param runway its the runway that is going to be oppendes or closed
    *@param opCl If it is true it opens the runway, if it is false it closes the runway 
     */
    public synchronized void openClose(int runway, boolean opCl) throws RemoteException {
        this.runways.openClose(runway, opCl);
    }

}
