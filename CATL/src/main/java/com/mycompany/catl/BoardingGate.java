/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

/**
 *
 * @author THINKPAD
 */
public class BoardingGate {
    private Airplane airplane;
    private boolean boarding;
    private boolean landing;

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public boolean isBoarding() {
        return boarding;
    }

    public void setBoarding(boolean boarding) {
        this.boarding = boarding;
    }

    public boolean isLanding() {
        return landing;
    }

    public void setLanding(boolean landing) {
        this.landing = landing;
    }

    public BoardingGate(Airplane airplane, boolean boarding, boolean landing) {
        this.airplane = airplane;
        this.boarding = boarding;
        this.landing = landing;
    }
    
}
