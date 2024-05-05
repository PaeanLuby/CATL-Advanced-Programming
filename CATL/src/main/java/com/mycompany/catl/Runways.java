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
    private boolean[] openCloseList;
    private Semaphore mutualExclusion;

    public Runways() {
        runways = new Airplane[4]; //runway can fit 4 airplanes
        mutualExclusion = new Semaphore(1);
        openCloseList= new boolean[]{true,true,true,true};
    }

    public int enterRunway(Airplane airplane, Log log) throws InterruptedException {
        mutualExclusion.acquire();
        int runway = -1;
        for (int i = 0; i < 4; i++) {
            if (runways[i] == null && openCloseList[i]) {
                log.write("Space " + i + " available in the runway.");
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
        index = Arrays.asList(runways).indexOf(airplane); {
        try {
            if(index != -1) {
                runways[index] = null;
                return airplane;
            } else {
                return null;
            }
        } finally {
            mutualExclusion.release();
        }
        
    }

    }
    
    /* It opens or closes an runway
    * 
    * @param runway its the runway that is going to be oppendes or closed
    *@param opCl If it is true it opens the runway, if it is false it closes the runway 
    */
    public void openClose(int runway,boolean opCl){
        if (opCl){
            this.openCloseList[runway]=true;
        }else{
            this.openCloseList[runway]=false;
        }
    }
}
