package com.mycompany.catl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Log {

    private Lock textLock = new ReentrantLock();
    private String airportEvolution = "src\\main\\java\\com\\mycompany\\catl\\airportEvolution.txt";
    private FileWriter writer;
    private BufferedWriter writerBuffer = null;

    public Log() {
        try {
            // Create a FileWriter with the file name, using true to allow appending to the end of the file
            writer = new FileWriter(airportEvolution, true);
            // Create a BufferedWriter to write in the file
            writerBuffer = new BufferedWriter(writer);
        } catch (IOException e) {
            System.err.println("Error upon opening the file " + e.getMessage());
        }
    }

    /**
     * It writes what ever you want in the log avoiding mutual exclusion
     *
     * @param text the text that you want to write
     */
    public void write(String text) {
        textLock.lock(); //lock the log for writing
        try {
            LocalDateTime date = LocalDateTime.now(); //take the date to the second
            writerBuffer.write(date + ": " + text); //write date: text
            writerBuffer.newLine();             //new line
        } catch (Exception e) {
        } finally {
            textLock.unlock();//unlock the log
        }
    }

    /**
     * It closes the buffer and the filewriter
     */
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
            System.err.println("Error upon opening the file " + e.getMessage());
        }
    }
}
