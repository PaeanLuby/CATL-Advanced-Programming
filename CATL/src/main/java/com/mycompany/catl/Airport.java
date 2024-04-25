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
    
//    private BoardingGates boardingBG;
//    private BoardingGates landingBG;
//    private BoardingGates universalBG1;
//    private BoardingGates universalBG2;
//    private BoardingGates universalBG3;
//    private BoardingGates universalBG4;
//    private Runways runway1;
//    private Runways runway2;
//    private Runways runway3;
//    private Runways runway4;

    public Airport(Hangar hangar, Airway Mad_Bar, Airway Bar_Mad, TaxiArea taxiArea, Parking parking, MaintenanceHall maintenanceHall, BoardingGates boardingGates, Runways runways) { //, BoardingGates boardingBG, BoardingGates landingBG, BoardingGates universalBG1, BoardingGates universalBG2, BoardingGates universalBG3, BoardingGates universalBG4, Runways runway1, Runways runway2, Runways runway3, Runways runway4) {
        this.passengers = 0;
        this.hangar = hangar;
        this.Mad_Bar = Mad_Bar;
        this.Bar_Mad = Bar_Mad;
        this.taxiArea = taxiArea;
        this.parking = parking;
        this.maintenanceHall = maintenanceHall;
        this.boardingGates = boardingGates;
        this.runways = runways;
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
    
//    public void setBoardingGate(int index, BoardingGates bG) {
//        //boardingGates[index] = bG;
//    }
//    
    public Runways getRunways() {
        return runways;
    }
//    
//    public void setRunways(int index, Runways rW) {
//        runways[index] = rW;
//    }
//    
    

//    public BoardingGates getBoardingBG() {
//        return boardingBG;
//    }
//
//    public void setBoardingBG(BoardingGates boardingBG) {
//        this.boardingBG = boardingBG;
//    }
//
//    public BoardingGates getLandingBG() {
//        return landingBG;
//    }
//
//    public void setLandingBG(BoardingGates landingBG) {
//        this.landingBG = landingBG;
//    }
//
//    public BoardingGates getUniversalBG1() {
//        return universalBG1;
//    }
//
//    public void setUniversalBG1(BoardingGates universalBG1) {
//        this.universalBG1 = universalBG1;
//    }
//
//    public BoardingGates getUniversalBG2() {
//        return universalBG2;
//    }
//
//    public void setUniversalBG2(BoardingGates universalBG2) {
//        this.universalBG2 = universalBG2;
//    }
//
//    public BoardingGates getUniversalBG3() {
//        return universalBG3;
//    }
//
//    public void setUniversalBG3(BoardingGates universalBG3) {
//        this.universalBG3 = universalBG3;
//    }
//
//    public BoardingGates getUniversalBG4() {
//        return universalBG4;
//    }
//
//    public void setUniversalBG4(BoardingGates universalBG4) {
//        this.universalBG4 = universalBG4;
//    }
//
//    public Runways getRunway1() {
//        return runway1;
//    }
//
//    public void setRunway1(Runways runway1) {
//        this.runway1 = runway1;
//    }
//
//    public Runways getRunway2() {
//        return runway2;
//    }
//
//    public void setRunway2(Runways runway2) {
//        this.runway2 = runway2;
//    }
//
//    public Runways getRunway3() {
//        return runway3;
//    }
//
//    public void setRunway3(Runways runway3) {
//        this.runway3 = runway3;
//    }
//
//    public Runways getRunway4() {
//        return runway4;
//    }
//
//    public void setRunway4(Runways runway4) {
//        this.runway4 = runway4;
//    }
    
    
   
}
