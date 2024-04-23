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
    
    private int freeGate() {
        for (int i = 0; i < 6; i++) {
            if (gates[i] == null && i != excludedGate) {
                System.out.println("Space " + i + " available.");
                return i;
            }
        } return -1; 
    }
    
    public int enterGate(Airplane airplane) {
        access.lock();
        int gate = freeGate();
        while (gate == -1) {
            gate = freeGate();
        } 
        gates[gate] = airplane.getAirport().getParking().releaseAirplane(airplane);
        System.out.println("Plane entered into boarding gate " + gate);
        access.unlock();
        return gate;
    }
    
    public void releaseGate(Airplane airplane) {
        for(int i = 0; i < 6; i++) { 
            if (gates[i] == airplane) {
                gates[i] = null;
                break;
            }
        }
    }

    
    
}
