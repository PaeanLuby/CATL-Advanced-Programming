/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author THINKPAD
 */
public class Hangar {
    private List<Airplane> airplanes = new ArrayList<>();
    private Lock hangarLock = new ReentrantLock();
    
    //Constructor
    public Hangar() {
    }



    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }
    
    /**
    * It adds an airplane into the hangar and returns its position on the list
    * 
    * @param airplane the new airplane
    * @return its position on the list
    */
    public int addAirplane(Airplane airplane){
        int puesto=-1;           //initialize puesto with -1 as an error signal
        this.hangarLock.lock();  //lock to avoid mutual exclusion between threads 
        try{
            this.airplanes.add(airplane);  //adds the airplane to the airplanes list of hangar
            puesto= airplanes.size();  //saves its position in puesto
        }catch(Exception e) {}
        finally{
        hangarLock.unlock(); //unlock the lock
        }
        return puesto; //returns the position of the airplane in the airplanes hangar list
    }
    
    /**
    * It takes an airplane from the hangar
    * 
    * @param puesto the position of the airplane ib the list
    * @return the airplane in the position puesto from the list
    */
    public Airplane takeAirplane(int puesto){
        Airplane a=null;                      //initialize a with null as an error signal
        this.hangarLock.lock();               //lock to avoid mutual exclusion between threads
        try{
            if (!this.airplanes.isEmpty()){   //we check if the list isn't empty
            a= this.airplanes.remove(puesto); //we take out the puesto's (position) airplane from the hangar airplanes list and remove it from the list 
            }
        }catch(Exception e) {}
        finally{
        hangarLock.unlock();                  //unlock the lock
        }
        if(a==null){                          //signal a possible error
            System.out.println("Error sacando avión del hangar");
        }
        return a;                            //return the airplane
    }
}
