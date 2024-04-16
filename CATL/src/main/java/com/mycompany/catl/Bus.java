/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THINKPAD
 */
public class Bus extends Thread{
    private String identifier;

    public Bus(String identifier) {
        this.identifier = identifier;
    }
    
    public void run(){
        try {
            long sleepTime = (long)(Math.random() * 3000 + 2000);
            sleep(sleepTime);                                                   //Arrival to downtown
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
