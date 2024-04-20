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
        for (int i=0;i<2;i++){
            Hangar hangar
        }
//        Airport madrid = new Airport(30);
//        Airport barcelona = new Airport(30);
        Lock l = new ReentrantLock();
        String airportEvolution = "C:\\Users\\THINKPAD\\Documents\\GitHub\\CATL\\CATL\\src\\main\\java\\com\\mycompany\\catl\\airportEvolution.txt";
        FileWriter writer;
        BufferedWriter writerBuffer = null;
//        try {
//            // Crear un FileWriter con el nombre del archivo, utilizando true para permitir agregar al final del archivo
//            writer = new FileWriter(airportEvolution, true);
//            // Crear un BufferedWriter para escribir en el archivo
//            writerBuffer = new BufferedWriter(writer);
//        } catch (IOException e) {
//            System.err.println("Error al abrir el archivo: " + e.getMessage());
//        }
//        
//        Bus b1=new Bus("b-0001",l,barcelona,writerBuffer);
//        Bus b2=new Bus("b-0002",l,madrid,writerBuffer);
//        Bus b3=new Bus("b-0003",l,barcelona,writerBuffer);
//        b1.start();
//        b2.start();
//        b3.start();
//        
//        try {
//            b1.join();
//            b2.join();
//            b3.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        
//        try {
//        // Cerrar el BufferedWriter
//            writerBuffer.close();
//        } catch (IOException e) {
//            System.err.println("Error al cerrar el BufferedWriter: " + e.getMessage());
//        }

    }
}
