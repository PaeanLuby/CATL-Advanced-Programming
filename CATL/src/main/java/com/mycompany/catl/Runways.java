/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class Runways {
    private Airplane[] runways;
    private Semaphore full;
    private Semaphore mutualExclusion; 
    
    public Runways() {
        runways = new Airplane[4]; //runway can fit 4 airplanes
        mutualExclusion = new Semaphore(1);
        full = new Semaphore(4);
    }
    
    public int enterRunway(Airplane airplane) throws InterruptedException {
        full.acquire();
        mutualExclusion.acquire();
        int runway = -1;
        for (int i = 0; i < 4; i++) {
            if (runways[i] == null) {
                System.out.println("Space " + i + " available in the runway.");
                runways[i] = airplane;
                runway = i;
                System.out.println("Plane " + airplane.getIdentifier() + " entered into runway " + i);
                break;
            }
        } 
        mutualExclusion.release(); 
        return runway;
    }
    
    public Airplane releaseRunway(Airplane airplane) throws InterruptedException {
        mutualExclusion.acquire();
        try {
            for(int i = 0; i < 4; i++) { 
            if (runways[i].equals(airplane)) {
                runways[i] = null;
                return airplane;
            }
        }
        return null;
        } finally {
            full.release();
            mutualExclusion.release();
        }
    }

}
