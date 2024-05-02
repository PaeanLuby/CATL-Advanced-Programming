/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
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
    private Condition available;
    private Lock access;
    private int remainingAttempts;
    private int excludedGate;
    
    public BoardingGates(int excludedGate) {
        this.type = type; //0 boarding, 1 landing, 2 both
        this.access = new ReentrantLock();
        this.gates = new Airplane[6];
        this.excludedGate = excludedGate;
        this.available = access.newCondition();

    }
    
    public int enterGate(Airplane airplane, Airport airport) throws InterruptedException {
        access.lock();
        int gate = -1;
        try {
            while (!Arrays.asList(gates).contains(null)) { //While there's no opening, signal to wait
                available.await();
            }
            gate = Arrays.asList(gates).indexOf(null);
            gates[gate] = airplane;
            System.out.println("Space " + gate + " available in the boarding gate.");
        } finally {
            access.unlock();
        } 
        
        return gate;
    }
    
    public Airplane releaseGate(Airplane airplane) throws NullPointerException {
        access.lock();
        try {
            int planeIndex = Arrays.asList(gates).indexOf(airplane);
            System.out.println(planeIndex);
            if (planeIndex != -1) {
                gates[planeIndex] = null;
                return airplane;
            } else {
                return null;
            }
        } finally {
            available.signal();
            access.unlock();   
        }
    }
}

    
    

