/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class BoardingGates {
    //private boolean boarding;
    //private boolean landing;
    private int type;
    private Airplane[] gates;
    private Lock access;
    private int remainingAttempts;
    private int excludedGate;
    
    public BoardingGates(int excludedGate) {
        this.type = type; //0 boarding, 1 landing, 2 both
        this.access = new ReentrantLock();
        this.gates = new Airplane[6];
        this.excludedGate = excludedGate;
    }
    
    public int enterGate(Airplane airplane) throws InterruptedException {
        access.lock();
        int gate = -1;
        while (gate == -1) {
            for (int i = 0; i < 6; i++) {
            if (gates[i] == null && i != excludedGate) {
                System.out.println("Space " + i + " available in the boarding gate.");
                gates[i] = airplane.getAirport().getParking().releaseAirplane(airplane);
                gate = i;
                System.out.println("Plane " + airplane.getIdentifier() + " entered into boarding gate " + i);
                break;
            }
            } 
        } 
        access.unlock();
        return gate;
    }
    
    public void releaseGate(Airplane airplane) {
        access.lock();
        for(int i = 0; i < 6; i++) { 
            if (gates[i] == airplane) {
                gates[i] = null;
                break;
            }
        }
        access.unlock();
    }

    
    
}
