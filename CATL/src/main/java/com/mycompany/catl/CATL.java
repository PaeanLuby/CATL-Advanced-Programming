/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.catl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class CATL {

    public static void main(String[] args) {
        //Creation of Madrid and Barcelonaairport:
        //Shared class
        Airway Mad_Bar = new Airway();
        Airway Bar_Mad = new Airway();
        BoardingGate[] boardingGatesMadrid = new BoardingGate[6];
        Runway[] runwaysMadrid = new Runway[4];
        BoardingGate[] boardingGatesBarcelona = new BoardingGate[6];
        Runway[] runwaysBarcelona = new Runway[4];
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
        
        Lock textLock = new ReentrantLock();
        String airportEvolution = "C:\\Users\\THINKPAD\\Documents\\GitHub\\CATL\\CATL\\src\\main\\java\\com\\mycompany\\catl\\airportEvolution.txt";
        FileWriter writer;
        BufferedWriter writerBuffer = null;
        
        try {
            // Crear un FileWriter con el nombre del archivo, utilizando true para permitir agregar al final del archivo
            writer = new FileWriter(airportEvolution, true);
            // Crear un BufferedWriter para escribir en el archivo
            writerBuffer = new BufferedWriter(writer);
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo: " + e.getMessage());
        }

        AirplaneCreator airplaneCreator = new  AirplaneCreator(textLock,madrid,barcelona,writerBuffer);
        BusCreator busCreator = new BusCreator(textLock,madrid,barcelona,writerBuffer);
        
        airplaneCreator.start();
        busCreator.start();
        
        try {
            airplaneCreator.join();
            busCreator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        try {
        // Cerrar el BufferedWriter
            writerBuffer.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar el BufferedWriter: " + e.getMessage());
        }

    }
}
