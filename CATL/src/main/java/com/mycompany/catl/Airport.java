/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.concurrent.Semaphore;

public class Airport {

    private int passengers;
    private Hangar hangar;
    private Airway Mad_Bar;
    private Airway Bar_Mad;
    private TaxiArea taxiArea;
    private Parking parking;
    private MaintenanceHall maintenanceHall;
    private BoardingGates boardingGates;
    private Runways runways;
    private String name;

    public Airport(Hangar hangar, Airway Mad_Bar, Airway Bar_Mad, TaxiArea taxiArea, Parking parking, MaintenanceHall maintenanceHall, BoardingGates boardingGates, Runways runways, String name) { //, BoardingGates boardingBG, BoardingGates landingBG, BoardingGates universalBG1, BoardingGates universalBG2, BoardingGates universalBG3, BoardingGates universalBG4, Runways runway1, Runways runway2, Runways runway3, Runways runway4) {
        this.passengers = 0;
        this.hangar = hangar;
        this.Mad_Bar = Mad_Bar;
        this.Bar_Mad = Bar_Mad;
        this.taxiArea = taxiArea;
        this.parking = parking;
        this.maintenanceHall = maintenanceHall;
        this.boardingGates = boardingGates;
        this.runways = runways;
        this.name = name;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
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

}
