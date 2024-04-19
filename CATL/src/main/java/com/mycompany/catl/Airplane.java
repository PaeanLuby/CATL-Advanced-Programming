/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author THINKPAD
 */
public class Airplane extends Thread{
    private int capacity;
    private int passengers;
    private String identifier;
    private Lock textLock;
    private Airport airport;
    private BufferedWriter writerBuffer;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Lock getTextLock() {
        return textLock;
    }

    public void setTextLock(Lock textLock) {
        this.textLock = textLock;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Airplane(int capacity, int passengers, String identifier, Lock textLock, Airport airport, BufferedWriter writerBuffer) {
        this.capacity = capacity;
        this.passengers = passengers;
        this.identifier = identifier;
        this.textLock = textLock;
        this.airport = airport;
        this.writerBuffer = writerBuffer;
    }
    
    /**
    * Determina si el avion esta en Madrid o en Barcelona
    * 
    * @return Madrid if the bus has an even identifier, Barcelona if it has an odd identifier
    */
    public String getCity() {
        if (Character.getNumericValue(this.identifier.charAt(6)) % 2 == 0) {
            return "Madrid";
        } else {
            return "Barcelona";
        }
    }


    
    public void run(){
        //Airplane creation in the Hangar
        int puestoHangar = airport.getHangar().addAirplane(this);  //initializing the airplane in the hangar and saving its position in the list
        if (puestoHangar==-1){System.out.println("ERROR insertando avion en hangar");} //possible error detection
        textLock.lock(); //lock the log for writing
                try{
                    LocalDateTime date = LocalDateTime.now();
                    writerBuffer.write(date+": The airplane "+this.identifier+" have been created in the hangar of the airport of: "+this.getCity());
                    writerBuffer.newLine();
                }catch(Exception e) {}
                finally{
                textLock.unlock();
                }
        //Airplane moves to the Parking Area
        airport.getParking().addAirplane(airport.getHangar().takeAirplane(puestoHangar)); //taking the airplane from the hangar and put in it in Parking Area
        textLock.lock(); //lock the log for writing
                try{
                    LocalDateTime date = LocalDateTime.now();
                    writerBuffer.write(date+": The airplane "+this.identifier+" leaves the hangar and enters the parking in the airport of: "+this.getCity());
                    writerBuffer.newLine();
                }catch(Exception e) {}
                finally{
                textLock.unlock();
                }
        //Airplane  awaits the availability of one BOARDING GATE (FIFO strategy)
    }
}
