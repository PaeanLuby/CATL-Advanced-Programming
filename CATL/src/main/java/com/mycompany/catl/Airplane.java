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
public class Airplane extends Thread {
    private int capacity;
    private int passengers;
    private String identifier;
    private Lock textLock;
    private Airport airport;
    private BufferedWriter writerBuffer;
    
    public void run(){
        //Airplane creation in the Hangar
        int positionHangar = airport.getHangar().addAirplane(this);  //initializing the airplane in the hangar and saving its position in the list
        if (positionHangar==-1){System.out.println("ERROR inserting the plane in the hangar");} //possible error detection
        textLock.lock(); //lock the log for writing
                try{
                    LocalDateTime date = LocalDateTime.now();
                    writerBuffer.write(date+": The airplane "+this.identifier+" has been created in the hangar of the airport of: "+this.getCity());
                    writerBuffer.newLine();
                } catch(Exception e) {
                } finally {
                    textLock.unlock();
                }
        //Airplane moves to the Parking Area
        airport.getParking().addAirplane(airport.getHangar().releaseAirplane(positionHangar)); //taking the airplane from the hangar and put in it in Parking Area
        textLock.lock(); //lock the log for writing
                try{
                    LocalDateTime date = LocalDateTime.now();
                    writerBuffer.write(date+": The airplane "+this.identifier+" leaves the hangar and enters the parking in the airport of: "+this.getCity());
                    writerBuffer.newLine();
                } catch(Exception e) {
                } finally {
                    textLock.unlock();
                }
        //Airplane  awaits the availability of one BOARDING GATE (FIFO strategy)
        int index = 1; //index 0 is specific for landings
        while (airport.getBoardingGate(index) != null) {
            index++; 
            index = index % 5;
            if (index == 0) {
                index++;
            } 
        }
        airport.getBoardingGate(index).enterPlane(this); //enter into free space
        airport.getBoardingGate(index).startBoarding(false, this); //capacity initially not reached 
    }

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

    public Airplane(int capacity, String identifier, Lock textLock, Airport airport, BufferedWriter writerBuffer) {
        this.capacity = capacity;
        this.passengers = 0;
        this.identifier = identifier;
        this.textLock = textLock;
        this.airport = airport;
        this.writerBuffer = writerBuffer;
    }
    
    /**
    * Determine if the airplane is in Madrid or in Barcelona
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


    
    
}
