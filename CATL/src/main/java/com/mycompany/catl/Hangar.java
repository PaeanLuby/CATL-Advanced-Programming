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
    int position;
    
    //Constructor
    public Hangar() {
        position = -1;
    }

    public List<Airplane> getAirplanes() {
//        for (Airplane plane : airplanes) {
//            System.out.println(plane);
//        }
//        
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
    public boolean addAirplane(Airplane airplane){
        boolean added = false;
        //int position=-1;           initialize puesto with -1 as an error signal
        this.hangarLock.lock();  //lock to avoid mutual exclusion between threads 
        try{
            added = this.airplanes.add(airplane);  //adds the airplane to the airplanes list of hangar
            //position= airplanes.size() - 1;  saves its position in puesto
        }catch(Exception e) {}
        finally{
            hangarLock.unlock(); //unlock the lock
        }
        return added; //returns whether the airplane was added to the airplanes hangar list
    }
    
    /**
    * It takes an airplane from the hangar
    * 
    * @param puesto the position of the airplane ib the list
    * @return the airplane in the position from the list
    */
    public Airplane releaseAirplane(Airplane airplane){
        boolean removed = false;                    //initialize a with null as an error signal
        this.hangarLock.lock();               //lock to avoid mutual exclusion between threads
        try {
            if (!this.airplanes.isEmpty()){   //we check if the list isn't empty
                removed = this.airplanes.remove(airplane); //we take out the puesto's (position) airplane from the hangar airplanes list and remove it from the list 
            }
        System.out.println("Successfully removed the airplane from the hangar.");
        } catch(Exception e) {
        } finally{
            if(!removed){                          //signal a possible error
            System.out.println("Error removing the plane from the hangar.");
            }
            hangarLock.unlock();                  //unlock the lock
        }
        return airplane;                            //return the airplane
    }
    /**
    * It transform the hangar array into a String
    * 
    * @return a string of the airplanes in the hangar
    */
    public String hangarToString(){
        String answer="";
        for(int i=0;i<airplanes.size();i++){
            answer=answer+airplanes.get(i).getIdentifier()+", ";
        }
        return answer;
    }
}
