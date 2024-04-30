/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.catl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class CATL {

    public static void main(String[] args) {
        Log log = new Log();
        //Creation of Madrid and Barcelonaairport:
        //Shared class
        Airway Mad_Bar = new Airway();
        Airway Bar_Mad = new Airway();
        BoardingGates boardingGatesMadrid = new BoardingGates(1); //1 is exclusively for landing
        BoardingGates landingGatesMadrid = new BoardingGates(0); //0 is exclusively for boarding
        Runways runwaysMadrid = new Runways();
        BoardingGates boardingGatesBarcelona = new BoardingGates(1); //1 is exclusively for landing
        BoardingGates landingGatesBarcelona = new BoardingGates(0); //0 is exclusively for boarding
        Runways runwaysBarcelona = new Runways(); //
        
        //Madrid class
        Hangar hangarMadrid = new Hangar();
        MaintenanceHall maintenanceHallMadrid = new MaintenanceHall();
        Parking parkingMadrid = new Parking();
        TaxiArea taxiMadrid =new TaxiArea();
        Airport madrid = new Airport(hangarMadrid,Mad_Bar,Bar_Mad,taxiMadrid,parkingMadrid,maintenanceHallMadrid, boardingGatesMadrid, runwaysMadrid);
        //Barcelona class
        Hangar hangarBarcelona = new Hangar();
        MaintenanceHall maintenanceHallBarcelona = new MaintenanceHall();
        Parking parkingBarcelona = new Parking();
        TaxiArea taxiBarcelona =new TaxiArea();
        Airport barcelona = new Airport(hangarBarcelona,Mad_Bar,Bar_Mad,taxiBarcelona,parkingBarcelona,maintenanceHallBarcelona, boardingGatesBarcelona, runwaysBarcelona);
        
        Lock madridPassengersLock = new ReentrantLock();
        Lock barcelonaPassengersLock = new ReentrantLock();
        
       // GraphicalInterface gf = new GraphicalInterface();

        AirplaneCreator airplaneCreator = new  AirplaneCreator(log,madrid,barcelona);
        BusCreator busCreator = new BusCreator(log,madrid,barcelona,madridPassengersLock,barcelonaPassengersLock);
        
        //If the program is finished or interrupted the log is automaticly closed to avoid the loss of information
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            airplaneCreator.interrupt();
            busCreator.interrupt();
            log.close();
        }));
        
        airplaneCreator.start();
        busCreator.start();

    }
}
