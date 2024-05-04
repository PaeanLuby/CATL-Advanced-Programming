/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.catl;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author THINKPAD
 */
public interface RemoteInterface extends Remote{
    public int numHangar() throws RemoteException;
    public int getPassengers() throws RemoteException;
    public int numMaintenance() throws RemoteException;
    public int numParking() throws RemoteException;
    public int numTaxiArea() throws RemoteException;
    public String showMadBarAirway() throws RemoteException;
    public String showBarMadAirway() throws RemoteException;
    public void openClose(int runway,boolean opCl) throws RemoteException;
}
