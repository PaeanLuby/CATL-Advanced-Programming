package com.mycompany.catl;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author THINKPAD
 */
public class Log {
    private Lock textLock = new ReentrantLock();
    //"\\C:\\Users\\pluby\\Desktop\\AdvancedFINAL\\CATL\\CATL\\src\\main\\java\\com\\mycompany\\catl\\airportEvolution.txt\\"
    private String airportEvolution = "C:\\Users\\THINKPAD\\Documents\\GitHub\\CATL\\CATL\\src\\main\\java\\com\\mycompany\\catl\\airportEvolution.txt\\";
    private FileWriter writer;
    private BufferedWriter writerBuffer = null;

    public Log() {
        try {
        // Crear un FileWriter con el nombre del archivo, utilizando true para permitir agregar al final del archivo
        writer = new FileWriter(airportEvolution, true);
        // Crear un BufferedWriter para escribir en el archivo
        writerBuffer = new BufferedWriter(writer);
    } catch (IOException e) {
        System.err.println("Error al abrir el archivo: " + e.getMessage());
    }
    }

    public void write(String text){
        textLock.lock(); //lock the log for writing
        try{
            LocalDateTime date = LocalDateTime.now();
            writerBuffer.write(date+": "+text);
            writerBuffer.newLine();
        } catch(Exception e) {
        } finally {
            textLock.unlock();
        }
    }
    
    public void close() {
        try {
            if (writerBuffer != null) {
                writerBuffer.flush();
                writerBuffer.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el archivo: " + e.getMessage());
        }
    }
}
