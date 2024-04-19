/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.catl;

import java.util.List;

/**
 *
 * @author THINKPAD
 */
public class Parking {
    private List<Airplane> airplanes;

    public Parking(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }
}
