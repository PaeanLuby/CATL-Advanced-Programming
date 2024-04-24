/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class Bus extends Thread{
    private String identifier;
    private int passengers=0;
    private Log log;
    private Airport airport;
    private Lock passengersLock;
    

    public Bus(String identifier,Log log, Airport airport,Lock passengersLock){
        this.identifier = identifier;
        this.log = log;
        this.airport = airport;
        this.passengersLock = passengersLock;
    }
    
    /**
    * Determine if the bus is in Madrid or Barcelona
    * 
    * @return Madrid if the bus has an even identifier, Barcelona if it has an odd identifier
    */
    public String getCity() {
        if (Character.getNumericValue(this.identifier.charAt(5)) % 2 == 0) {
            return "Madrid";
        } else {
            return "Barcelona";
        }
    }

    
    public void run(){
       // while (true){     
            try {
                //Arrival to downtown
                this.log.write("The bus "+this.identifier+" has arrived to the city of "+this.getCity());
                long sleepTime = (long)(Math.random() * 3 + 2);
                sleep(sleepTime);                                                   

                //Passengers access
                long jumpIn = (long)(Math.random() * 50);
                passengers+=jumpIn;
                this.log.write(jumpIn+" passengers have accessed to the bus "+this.identifier+" that initiates its route towards the airport of "+this.getCity());  
                //Bus initiates its route towards the airport
                sleepTime = (long)(Math.random() * 5 + 5);
                sleep(sleepTime);
                
                //Arrival to airport bus-
                passengersLock.lock();
                try{
                airport.setPassengers(airport.getPassengers() + passengers);
                }catch(Exception e) {}
                finally{
                    passengersLock.unlock();
                }
                System.out.println("Number of airport passengers is: " + airport.getPassengers());
                this.log.write("Number of airport passengers is: " + airport.getPassengers());
                this.log.write("The bus "+this.identifier+" has arrived to the airport of "+this.getCity()+" with "+passengers+" passengers.");
                passengers=0;              
                //Wait for passengers
                sleepTime = (long)(Math.random() * 3000 + 2000);
                sleep(sleepTime);
                
                //Passengers from the airport enter the bus
                jumpIn = (long)(Math.random() * 50);
                passengers+=jumpIn;
                passengersLock.lock();
                try{
                if (airport.getPassengers()>passengers){
                    airport.setPassengers(airport.getPassengers()-passengers);
                }else{
                    airport.setPassengers(0);
                }
                }catch(Exception e) {}
                finally{
                    passengersLock.unlock();
                }
                this.log.write(jumpIn+" passengers have accessed to the bus "+this.identifier+" that initiates its route towards the downtown of "+this.getCity());
                System.out.println("Number of airport passengers is: " + airport.getPassengers());
                this.log.write("Number of airport passengers is: " + airport.getPassengers());
                
                //Bus initiates its route towards downtown
                sleepTime = (long)(Math.random() * 5000 + 5000);
                sleep(sleepTime);
                
                //Arrival to downtown bus-stop
                this.log.write("The bus "+this.identifier+" has arrived to the downtown of "+this.getCity()+" with "+passengers+" passengers.");
                    passengers=0;
            } catch (InterruptedException ex) {
                Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
    } 
}
