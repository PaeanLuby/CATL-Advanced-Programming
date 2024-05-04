/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.catl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class CATL {

    public static void main(String[] args) throws RemoteException {
        Log log = new Log();
        //Creation of Madrid and Barcelonaairport:
        //Shared class
        Airway Mad_Bar = new Airway("Mad_Bar");
        Airway Bar_Mad = new Airway("Bar_Mad");
        BoardingGates boardingGatesMadrid = new BoardingGates(1); //1 is exclusively for landing. 0 is exclusively for boarding
        Runways runwaysMadrid = new Runways();
        BoardingGates boardingGatesBarcelona = new BoardingGates(1); //1 is exclusively for landing. 0 is exclusively for boarding
        Runways runwaysBarcelona = new Runways(); //

        //Madrid class
        Hangar hangarMadrid = new Hangar();
        MaintenanceHall maintenanceHallMadrid = new MaintenanceHall();
        Parking parkingMadrid = new Parking();
        TaxiArea taxiMadrid = new TaxiArea();
        Airport madrid = new Airport(hangarMadrid, Mad_Bar, Bar_Mad, taxiMadrid, parkingMadrid, maintenanceHallMadrid, boardingGatesMadrid, runwaysMadrid, "MAD");
        //Barcelona class
        Hangar hangarBarcelona = new Hangar();
        MaintenanceHall maintenanceHallBarcelona = new MaintenanceHall();
        Parking parkingBarcelona = new Parking();
        TaxiArea taxiBarcelona = new TaxiArea();
        Airport barcelona = new Airport(hangarBarcelona, Mad_Bar, Bar_Mad, taxiBarcelona, parkingBarcelona, maintenanceHallBarcelona, boardingGatesBarcelona, runwaysBarcelona, "BAC");

        Lock madridPassengersLock = new ReentrantLock();
        Lock barcelonaPassengersLock = new ReentrantLock();
        
        GraphicalInterface gf = new GraphicalInterface();
        gf.setVisible(true);
        
        try{
            Registry reg =LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost/madrid", madrid);
            Naming.rebind("//localhost/barcelona", barcelona);
        }catch(Exception e){e.printStackTrace();}
        


        AirplaneCreator airplaneCreator = new AirplaneCreator(log, madrid, barcelona, gf);
        BusCreator busCreator = new BusCreator(log, madrid, barcelona, madridPassengersLock, barcelonaPassengersLock, gf);

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
