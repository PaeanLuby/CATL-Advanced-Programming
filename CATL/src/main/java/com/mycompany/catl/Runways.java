package com.mycompany.catl;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Runways {

    private Airplane[] runways;
    private boolean[] openCloseList;
    private Lock runwayLock;
    private Condition full;

    public Runways() {
        runways = new Airplane[4]; //runway can fit 4 airplanes
        runwayLock = new ReentrantLock();
        full = runwayLock.newCondition();
        openCloseList = new boolean[]{true, true, true, true};
    }

    public int enterRunway(Airplane airplane) throws InterruptedException {
        runwayLock.lock();
        try {
            int runway = -1;
            while (!Arrays.asList(runways).contains(null)) {
                full.await();
            }
            for (int i = 0; i < 4; i++) {
                if (runways[i] == null && openCloseList[i]) {
                    runways[i] = airplane;
                    runway = i;
                    break;
                }
            }
            return runway;
        } finally {
            runwayLock.unlock();
        }

    }

    public Airplane releaseRunway(Airplane airplane) throws InterruptedException {
        runwayLock.lock();
        try {
            int index = -1;
            index = Arrays.asList(runways).indexOf(airplane);
            if (index != -1) {
                runways[index] = null;
                full.signalAll();
                return airplane;
            } else {
                return null;
            }
        } finally {
            runwayLock.unlock();
        }

    }

    /* It opens or closes an runway
    * 
    *@param runway is the runway that will be open or closed
    *@param opCl if it is true it opens the runway, if it is false it closes the runway 
     */
    public void openClose(int runway, boolean opCl) {
        this.openCloseList[runway] = opCl;
    }
}
