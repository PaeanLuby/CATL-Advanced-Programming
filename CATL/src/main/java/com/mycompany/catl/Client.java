package com.mycompany.catl;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DistributedProgrammingGUI gui = new DistributedProgrammingGUI();
        gui.setVisible(true);
        try {
            gui.set();
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
