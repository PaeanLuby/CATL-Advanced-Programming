/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THINKPAD
 */
public class TaxiArea {
    private List<Airplane> airplanes;

    public TaxiArea() {
        airplanes = new ArrayList<>();
    }
    
    public void enterTaxiArea(Airplane airplane) {
        airplanes.add(airplane);
    }
    
    public void pilotChecks(int num, Airplane airplane) {
        System.out.println("Pilot check " + num + " for airplane " + airplane.getIdentifier());
    }
    
    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }
}
