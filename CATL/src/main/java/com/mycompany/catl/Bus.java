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

/**
 *
 * @author THINKPAD
 */
public class Bus extends Thread{
    private String identifier;
    Lock textLock ;
    private static final String airportEvolution = "airportEvolution.txt";
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


    public Bus(String identifier, Lock textLock) {
        this.identifier = identifier;
        this.textLock = textLock;
    }
    
    public void getCity(){
        
    }
    
    public void run(){
        try {
            textLock.lock();
            try{
                writerBuffer.write("The bus "+this.identifier+" has arrived to the city of");
                writerBuffer.newLine();
            }catch(Exception e) {}
            finally{
            textLock.unlock();
            }
            long sleepTime = (long)(Math.random() * 3000 + 2000);
            sleep(sleepTime);                                                   //Arrival to downtown
            
            
            
            
            
            
            try {
                // Cerrar el BufferedWriter (esto también cierra el FileWriter)
                writerBuffer.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
