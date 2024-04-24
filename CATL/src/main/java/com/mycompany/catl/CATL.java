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
        Runway[] runwaysMadrid = new Runway[4];
        BoardingGates boardingGatesBarcelona = new BoardingGates(1); //1 is exclusively for landing
        BoardingGates landingGatesBarcelona = new BoardingGates(0); //0 is exclusively for boarding
        Runway[] runwaysBarcelona = new Runway[4]; //
        
//                
//        for (int i = 0; i < 6; i++) {
//            boardingGatesMadrid[i] = new BoardingGates(i);
//            boardingGatesBarcelona[i] = new BoardingGates(i);
//            if (i < 4) {
//                runwaysMadrid[i] = new Runway();
//                runwaysBarcelona[i] = new Runway();
//            }
//        }
        
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
        

        AirplaneCreator airplaneCreator = new  AirplaneCreator(log,madrid,barcelona);
        BusCreator busCreator = new BusCreator(log,madrid,barcelona,madridPassengersLock,barcelonaPassengersLock);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            airplaneCreator.interrupt();
            busCreator.interrupt();
            log.close();
        }));
        
        airplaneCreator.start();
        busCreator.start();
        
//        try {
//            airplaneCreator.join();
//            busCreator.join();
//            log.close();
//            System.out.println("THE END BBY");
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CATL.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
