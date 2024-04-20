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
    private BoardingGate[] boardingGates;
    private Runway[] runways;
    
//    private BoardingGate boardingBG;
//    private BoardingGate landingBG;
//    private BoardingGate universalBG1;
//    private BoardingGate universalBG2;
//    private BoardingGate universalBG3;
//    private BoardingGate universalBG4;
//    private Runway runway1;
//    private Runway runway2;
//    private Runway runway3;
//    private Runway runway4;

    public Airport(int passengers, Hangar hangar, Airway Mad_Bar, Airway Bar_Mad, TaxiArea taxiArea, Parking parking, MaintenanceHall maintenanceHall) { //, BoardingGate boardingBG, BoardingGate landingBG, BoardingGate universalBG1, BoardingGate universalBG2, BoardingGate universalBG3, BoardingGate universalBG4, Runway runway1, Runway runway2, Runway runway3, Runway runway4) {
        this.passengers = passengers;
        this.hangar = hangar;
        this.Mad_Bar = Mad_Bar;
        this.Bar_Mad = Bar_Mad;
        this.taxiArea = taxiArea;
        this.parking = parking;
        this.maintenanceHall = maintenanceHall;
        boardingGates = new BoardingGate[6]; //6 boarding gates
        runways = new Runway[4]; //4 runways
        
//        this.boardingBG = boardingBG;
//        this.landingBG = landingBG;
//        this.universalBG1 = universalBG1;
//        this.universalBG2 = universalBG2;
//        this.universalBG3 = universalBG3;
//        this.universalBG4 = universalBG4;
//        this.runway1 = runway1;
//        this.runway2 = runway2;
//        this.runway3 = runway3;
//        this.runway4 = runway4;
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
    
    public BoardingGate getBoardingGate(int index) {
        return boardingGates[index];
    }
    
    public void setBoardingGate(int index, BoardingGate bG) {
        boardingGates[index] = bG;
    }
    
    public Runway getRunway(int index) {
        return runways[index];
    }
    
    public void setRunway(int index, Runway rW) {
        runways[index] = rW;
    }
    
    

//    public BoardingGate getBoardingBG() {
//        return boardingBG;
//    }
//
//    public void setBoardingBG(BoardingGate boardingBG) {
//        this.boardingBG = boardingBG;
//    }
//
//    public BoardingGate getLandingBG() {
//        return landingBG;
//    }
//
//    public void setLandingBG(BoardingGate landingBG) {
//        this.landingBG = landingBG;
//    }
//
//    public BoardingGate getUniversalBG1() {
//        return universalBG1;
//    }
//
//    public void setUniversalBG1(BoardingGate universalBG1) {
//        this.universalBG1 = universalBG1;
//    }
//
//    public BoardingGate getUniversalBG2() {
//        return universalBG2;
//    }
//
//    public void setUniversalBG2(BoardingGate universalBG2) {
//        this.universalBG2 = universalBG2;
//    }
//
//    public BoardingGate getUniversalBG3() {
//        return universalBG3;
//    }
//
//    public void setUniversalBG3(BoardingGate universalBG3) {
//        this.universalBG3 = universalBG3;
//    }
//
//    public BoardingGate getUniversalBG4() {
//        return universalBG4;
//    }
//
//    public void setUniversalBG4(BoardingGate universalBG4) {
//        this.universalBG4 = universalBG4;
//    }
//
//    public Runway getRunway1() {
//        return runway1;
//    }
//
//    public void setRunway1(Runway runway1) {
//        this.runway1 = runway1;
//    }
//
//    public Runway getRunway2() {
//        return runway2;
//    }
//
//    public void setRunway2(Runway runway2) {
//        this.runway2 = runway2;
//    }
//
//    public Runway getRunway3() {
//        return runway3;
//    }
//
//    public void setRunway3(Runway runway3) {
//        this.runway3 = runway3;
//    }
//
//    public Runway getRunway4() {
//        return runway4;
//    }
//
//    public void setRunway4(Runway runway4) {
//        this.runway4 = runway4;
//    }
    
    
   
}
