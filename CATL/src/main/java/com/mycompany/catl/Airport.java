/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Airport extends UnicastRemoteObject implements RemoteInterface {

    private AtomicInteger passengers;
    private Hangar hangar;
    private Airway Mad_Bar;
    private Airway Bar_Mad;
    private TaxiArea taxiArea;
    private Parking parking;
    private MaintenanceHall maintenanceHall;
    private BoardingGates boardingGates;
    private Runways runways;
    private String name;

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
        this.passengers = new AtomicInteger(0);

    }

    public AtomicInteger getPassengers() throws RemoteException {
        return passengers;
    }

    public void setPassengers(AtomicInteger passengers) {
        this.passengers = passengers;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }

    public Airway getMad_Bar() {
        return Mad_Bar;
    }

    public void setMad_Bar(Airway Mad_Bar) {
        this.Mad_Bar = Mad_Bar;
    }

    public Airway getBar_Mad() {
        return Bar_Mad;
    }

    public void setBar_Mad(Airway Bar_Mad) {
        this.Bar_Mad = Bar_Mad;
    }

    public TaxiArea getTaxiArea() {
        return taxiArea;
    }

    public void setTaxiArea(TaxiArea taxiArea) {
        this.taxiArea = taxiArea;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public MaintenanceHall getMaintenanceHall() {
        return maintenanceHall;
    }

    public void setMaintenanceHall(MaintenanceHall maintenanceHall) {
        this.maintenanceHall = maintenanceHall;
    }

    public BoardingGates getBoardingGates() {
        return boardingGates;
    }

    public Runways getRunways() {
        return runways;
    }

    public String toString() {
        return this.name;
    }

    public synchronized int numHangar() throws RemoteException {
        int num = this.hangar.getAirplanes().size();
        return num;
    }

    public synchronized int numMaintenance() throws RemoteException {
        int num = this.maintenanceHall.getAirplanes().size();
        return num;
    }

    public synchronized int numParking() throws RemoteException {
        int num = this.parking.getAirplanesForBoarding().size() + this.parking.getAirplanesForMaintenance().size();
        return num;
    }

    public synchronized int numTaxiArea() throws RemoteException {
        int num = this.taxiArea.getAirplanes().size();
        return num;
    }

    public synchronized String showMadBarAirway() throws RemoteException {
        String airway = this.Mad_Bar.toString();
        return airway;
    }

    public synchronized String showBarMadAirway() throws RemoteException {
        String airway = this.Bar_Mad.toString();
        return airway;
    }

    /* It opens or closes an airway
    * 
    * @param runway its the runway that is going to be oppendes or closed
    *@param opCl If it is true it opens the runway, if it is false it closes the runway 
     */
    public synchronized void openClose(int runway, boolean opCl) throws RemoteException {
        this.runways.openClose(runway, opCl);
    }

}
