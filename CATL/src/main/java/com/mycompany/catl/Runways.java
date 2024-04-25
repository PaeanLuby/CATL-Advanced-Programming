/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class Runways {
    private Airplane[] runways;
    private Lock access;
   
    public Runways() {
        runways = new Airplane[4]; //runway can fit 4 airplanes
        this.access = new ReentrantLock();
    }
    
    public int enterRunway(Airplane airplane) {
        access.lock();
        int runway = -1;
        while (runway == -1) {
            for (int i = 0; i < 6; i++) {
            if (runways[i] == null) {
                System.out.println("Space " + i + " available in the runway.");
                runways[i] = airplane;
                runway = i;
                System.out.println("Plane " + airplane.getIdentifier() + " entered into runway " + i);
                break;
            }
            } 
        } 
        access.unlock();
        return runway;
    }
    
}
