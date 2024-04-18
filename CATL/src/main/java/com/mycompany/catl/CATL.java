/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.catl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author THINKPAD
 */
public class CATL {

    public static void main(String[] args) {
<<<<<<< Updated upstream
        System.out.println("Hello World!");
        System.out.println("This is a test");
=======
        Lock l = new ReentrantLock();
        Bus b = new Bus("B-0001",l);
        b.start();
>>>>>>> Stashed changes
    }
}
