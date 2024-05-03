/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.Arrays;
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
                break;
            }
        }
        mutualExclusion.release();
        return runway;
    }

    public Airplane releaseRunway(Airplane airplane) throws InterruptedException {
        mutualExclusion.acquire();
        int index = -1;
        try {
            index = Arrays.asList(runways).indexOf(airplane); {
            if(index != -1) {
                runways[index] = null;
                return airplane;
            } else {
                return null;
            }
            }
        } 
        finally {
            full.release();
            mutualExclusion.release();
        }
    }

}
