/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.Lock;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author THINKPAD
 */
public class Bus extends Thread{
    private String identifier;
    private int passengers=0;
    Lock textLock ;
    Airport airport;
    private static final String airportEvolution = "src\\main\\java\\com\\mycompany\\catl\\airportEvolution.txt";
    private static FileWriter writer;
    private static BufferedWriter writerBuffer;

    static {
        try {
            // Crear un FileWriter con el nombre del archivo, utilizando true para permitir agregar al final del archivo
            writer = new FileWriter(airportEvolution, true);
            // Crear un BufferedWriter para escribir en el archivo
            writerBuffer = new BufferedWriter(writer);
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo: " + e.getMessage());
        }
    }


    public Bus(String identifier, Lock textLock, Airport airport) {
        this.identifier = identifier;
        this.textLock = textLock;
        this.airport=airport;
    }
    
    /**
    * Determina si el bus esta en Madrid o en Barcelona
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
            try {
                //Arrival to downtown
                textLock.lock();
                try{
                    LocalDateTime date = LocalDateTime.now();
                    writerBuffer.write(date+": The bus "+this.identifier+" has arrived to the city of "+this.getCity());
                    writerBuffer.newLine();
                }catch(Exception e) {}
                finally{
                textLock.unlock();
                }
                long sleepTime = (long)(Math.random() * 3000 + 2000);
                sleep(sleepTime);                                                   

                //Passengers access
                textLock.lock();
                try{
                    LocalDateTime date = LocalDateTime.now();
                    long jumpIn = (long)(Math.random() * 50);
                    passengers+=jumpIn;
                    writerBuffer.write(date+": "+jumpIn+" passengers have accessed to the bus "+this.identifier+" that initiates its route towards the airport of "+this.getCity());
                    writerBuffer.newLine();
                }catch(Exception e) {}
                finally{
                textLock.unlock();
                }
                
                //Bus initiates its route towards the airport
                sleepTime = (long)(Math.random() * 5000 + 5000);
                sleep(sleepTime);
                
                //Arrival to airport bus-stop
                textLock.lock();
                try{
                    LocalDateTime date = LocalDateTime.now();
                    writerBuffer.write(date+": The bus "+this.identifier+" has arrived to the airport of "+this.getCity());
                    writerBuffer.newLine();
                }catch(Exception e) {}
                finally{
                textLock.unlock();
                }


                // Close the BufferedWriter (it also closes the FileWriter)
                try {
                    writerBuffer.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
}
