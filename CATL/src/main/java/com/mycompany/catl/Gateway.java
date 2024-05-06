package com.mycompany.catl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Gateway {

    private boolean close = false;
    private Lock lock = new ReentrantLock();
    private Condition stop = lock.newCondition();

    public void look() {
        try {
            lock.lock();
            while (close) {
                try {
                    stop.await();
                } catch (InterruptedException ie) {
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void open() {
        try {
            lock.lock();
            close = false;
            stop.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void close() {
        try {
            lock.lock();
            close = true;
        } finally {
            lock.unlock();
        }
    }
}
