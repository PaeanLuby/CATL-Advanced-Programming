package com.mycompany.catl;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public interface RemoteInterface extends Remote {

    public int numHangar() throws RemoteException;

    public AtomicInteger getPassengers() throws RemoteException;

    public int numMaintenance() throws RemoteException;

    public int numParking() throws RemoteException;

    public int numTaxiArea() throws RemoteException;

    public String showMadBarAirway() throws RemoteException;

    public String showBarMadAirway() throws RemoteException;

    public void openClose(int runway, boolean opCl) throws RemoteException;
}
