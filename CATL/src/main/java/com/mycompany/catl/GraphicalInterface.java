package com.mycompany.catl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Paean Luby 
 * @author Nicolás Rodríguez Sánchez 
 */
public class GraphicalInterface extends javax.swing.JFrame {

    /**
     * Creates new form GraphicalInterface
     */
    public GraphicalInterface() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pauseButton = new javax.swing.JButton();
        resumeButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        madridMaintenance = new javax.swing.JTextField();
        madridHangar = new javax.swing.JTextField();
        madridParking = new javax.swing.JTextField();
        madridGate1 = new javax.swing.JTextField();
        madridGate4 = new javax.swing.JTextField();
        madridGate2 = new javax.swing.JTextField();
        madridGate5 = new javax.swing.JTextField();
        madridGate3 = new javax.swing.JTextField();
        madridGate6 = new javax.swing.JTextField();
        madridTaxiArea = new javax.swing.JTextField();
        madridRunway1 = new javax.swing.JTextField();
        madridRunway2 = new javax.swing.JTextField();
        madridRunway3 = new javax.swing.JTextField();
        madridRunway4 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        madridBusTownAirport = new javax.swing.JTextField();
        madridBusAirportTown = new javax.swing.JTextField();
        madridPassengers = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        barcelonaBusTownAirport = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        barcelonaBusAirportTown = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        barcelonaPassengers = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        barcelonaHangar = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        barcelonaMaintenance = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        barcelonaParking = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        barcelonaGate1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        barcelonaGate2 = new javax.swing.JTextField();
        barcelonaGate4 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        barcelonaGate5 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        barcelonaGate6 = new javax.swing.JTextField();
        barcelonaGate3 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        barcelonaTaxiArea = new javax.swing.JTextField();
        barcelonaRunway1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        barcelonaRunway2 = new javax.swing.JTextField();
        barcelonaRunway4 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        barcelonaRunway3 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        airwayMadridBarcelona = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        airwayBarcelonaMadrid = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Madrid Airport");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(pauseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 76, 17));

        resumeButton.setText("Resume");
        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeButtonActionPerformed(evt);
            }
        });
        jPanel1.add(resumeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, 17));

        jLabel3.setText("Hangar:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Maintenance: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        jLabel5.setText("Parking Area:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        jLabel6.setText("Gate 1:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        jLabel7.setText("Gate 2:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));

        jLabel8.setText("Gate 3:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, -1));

        jLabel9.setText("Gate 5:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, -1, -1));

        jLabel10.setText("Gate 4:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, -1));

        jLabel11.setText("Gate 6:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 44, -1));

        jLabel12.setText("Taxi area:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        jLabel13.setText("Runway 2:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, -1, -1));

        jLabel14.setText("Runway 1:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, -1, -1));

        jLabel15.setText("Runway 3:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, -1, -1));

        jLabel16.setText("Runway 4:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, -1, -1));

        jLabel31.setText("Number airport passengers:");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));

        jLabel32.setText("Transfers Downtown:");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        jLabel33.setText("Transfers Airport:");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        madridMaintenance.setEditable(false);
        madridMaintenance.setBackground(new java.awt.Color(255, 255, 255));
        madridMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                madridMaintenanceActionPerformed(evt);
            }
        });
        jPanel1.add(madridMaintenance, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 494, -1));

        madridHangar.setEditable(false);
        madridHangar.setBackground(new java.awt.Color(255, 255, 255));
        madridHangar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                madridHangarActionPerformed(evt);
            }
        });
        jPanel1.add(madridHangar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 494, -1));

        madridParking.setEditable(false);
        madridParking.setBackground(new java.awt.Color(255, 255, 255));
        madridParking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                madridParkingActionPerformed(evt);
            }
        });
        jPanel1.add(madridParking, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 496, -1));

        madridGate1.setEditable(false);
        madridGate1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridGate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 183, -1));

        madridGate4.setEditable(false);
        madridGate4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridGate4, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 260, 183, -1));

        madridGate2.setEditable(false);
        madridGate2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridGate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 183, -1));

        madridGate5.setEditable(false);
        madridGate5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridGate5, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 290, 183, -1));

        madridGate3.setEditable(false);
        madridGate3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridGate3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 183, -1));

        madridGate6.setEditable(false);
        madridGate6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridGate6, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 330, 183, -1));

        madridTaxiArea.setEditable(false);
        madridTaxiArea.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridTaxiArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 496, -1));

        madridRunway1.setEditable(false);
        madridRunway1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridRunway1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 183, -1));

        madridRunway2.setEditable(false);
        madridRunway2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridRunway2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 183, -1));

        madridRunway3.setEditable(false);
        madridRunway3.setBackground(new java.awt.Color(255, 255, 255));
        madridRunway3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                madridRunway3ActionPerformed(evt);
            }
        });
        jPanel1.add(madridRunway3, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 390, 183, -1));

        madridRunway4.setEditable(false);
        madridRunway4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridRunway4, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 420, 183, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 795, -1));

        madridBusTownAirport.setEditable(false);
        madridBusTownAirport.setBackground(new java.awt.Color(255, 255, 255));
        madridBusTownAirport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                madridBusTownAirportActionPerformed(evt);
            }
        });
        jPanel1.add(madridBusTownAirport, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 160, -1));

        madridBusAirportTown.setEditable(false);
        madridBusAirportTown.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(madridBusAirportTown, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 160, -1));

        madridPassengers.setEditable(false);
        madridPassengers.setBackground(new java.awt.Color(255, 255, 255));
        madridPassengers.setText("0");
        madridPassengers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                madridPassengersActionPerformed(evt);
            }
        });
        jPanel1.add(madridPassengers, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 379, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Barcelona Airport");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 40, -1, -1));

        jLabel35.setText("Transfers Airport:");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, -1));

        barcelonaBusTownAirport.setEditable(false);
        barcelonaBusTownAirport.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaBusTownAirport, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 160, -1));

        jLabel36.setText("Transfers Downtown:");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, -1, -1));

        barcelonaBusAirportTown.setEditable(false);
        barcelonaBusAirportTown.setBackground(new java.awt.Color(255, 255, 255));
        barcelonaBusAirportTown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcelonaBusAirportTownActionPerformed(evt);
            }
        });
        jPanel1.add(barcelonaBusAirportTown, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 90, 160, -1));

        jLabel37.setText("Number airport passengers:");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, -1, -1));

        barcelonaPassengers.setEditable(false);
        barcelonaPassengers.setBackground(new java.awt.Color(255, 255, 255));
        barcelonaPassengers.setText("0");
        jPanel1.add(barcelonaPassengers, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 401, -1));

        jLabel38.setText("Hangar:");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 160, -1, -1));

        barcelonaHangar.setEditable(false);
        barcelonaHangar.setBackground(new java.awt.Color(255, 255, 255));
        barcelonaHangar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcelonaHangarActionPerformed(evt);
            }
        });
        jPanel1.add(barcelonaHangar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 494, -1));

        jLabel39.setText("Maintenance: ");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, -1, 28));

        barcelonaMaintenance.setEditable(false);
        barcelonaMaintenance.setBackground(new java.awt.Color(255, 255, 255));
        barcelonaMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcelonaMaintenanceActionPerformed(evt);
            }
        });
        jPanel1.add(barcelonaMaintenance, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 190, 494, -1));

        jLabel40.setText("Parking Area:");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        barcelonaParking.setEditable(false);
        barcelonaParking.setBackground(new java.awt.Color(255, 255, 255));
        barcelonaParking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcelonaParkingActionPerformed(evt);
            }
        });
        jPanel1.add(barcelonaParking, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, 494, -1));

        jLabel41.setText("Gate 1:");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 270, -1, -1));

        barcelonaGate1.setEditable(false);
        barcelonaGate1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaGate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 270, 183, -1));

        jLabel42.setText("Gate 2:");
        jPanel1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, -1, -1));

        barcelonaGate2.setEditable(false);
        barcelonaGate2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaGate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, 183, -1));

        barcelonaGate4.setEditable(false);
        barcelonaGate4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaGate4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 270, 183, -1));

        jLabel43.setText("Gate 4:");
        jPanel1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 270, -1, -1));

        jLabel44.setText("Gate 5:");
        jPanel1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 300, -1, -1));

        barcelonaGate5.setEditable(false);
        barcelonaGate5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaGate5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 300, 183, -1));

        jLabel45.setText("Gate 6:");
        jPanel1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 330, -1, -1));

        barcelonaGate6.setEditable(false);
        barcelonaGate6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaGate6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 330, 183, -1));

        barcelonaGate3.setEditable(false);
        barcelonaGate3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaGate3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 330, 183, -1));

        jLabel46.setText("Gate 3:");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 330, -1, -1));

        jLabel47.setText("Taxi area:");
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 360, -1, -1));

        barcelonaTaxiArea.setEditable(false);
        barcelonaTaxiArea.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaTaxiArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, 494, -1));

        barcelonaRunway1.setEditable(false);
        barcelonaRunway1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaRunway1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 390, 183, -1));

        jLabel48.setText("Runway 1: ");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, -1, -1));

        jLabel49.setText("Runway 2:");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, -1, -1));

        barcelonaRunway2.setEditable(false);
        barcelonaRunway2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaRunway2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 420, 183, -1));

        barcelonaRunway4.setEditable(false);
        barcelonaRunway4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaRunway4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 420, 183, -1));

        jLabel50.setText("Runway 4:");
        jPanel1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 420, -1, -1));

        jLabel51.setText("Runway 3:");
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 390, -1, -1));

        barcelonaRunway3.setEditable(false);
        barcelonaRunway3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barcelonaRunway3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 390, 183, -1));

        jLabel52.setText("Airway Madrid-Barcelona:");
        jPanel1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, -1, -1));

        airwayMadridBarcelona.setEditable(false);
        airwayMadridBarcelona.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(airwayMadridBarcelona, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, 1206, -1));

        jLabel53.setText("Airway Barcelona-Madrid:");
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, -1, -1));

        airwayBarcelonaMadrid.setEditable(false);
        airwayBarcelonaMadrid.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(airwayBarcelonaMadrid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 1206, -1));
        jPanel1.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1282, 64, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1528, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void madridParkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_madridParkingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_madridParkingActionPerformed

    private void madridHangarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_madridHangarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_madridHangarActionPerformed

    private void madridMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_madridMaintenanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_madridMaintenanceActionPerformed

    private void resumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeButtonActionPerformed
        if (!resumePressed) //if not pressed
        {
            resumePressed = true; //we change it to pressed
            pausePressed = false;
            gw.open();    //Open the gateway 
        }
    }//GEN-LAST:event_resumeButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        if (!pausePressed) //if not pressed
        {
            resumePressed = false; //we change it to pressed
            pausePressed = true;
            gw.close();    //Close the gateway 
        }
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void barcelonaHangarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcelonaHangarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcelonaHangarActionPerformed

    private void barcelonaMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcelonaMaintenanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcelonaMaintenanceActionPerformed

    private void barcelonaParkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcelonaParkingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcelonaParkingActionPerformed

    private void madridPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_madridPassengersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_madridPassengersActionPerformed

    private void madridBusTownAirportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_madridBusTownAirportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_madridBusTownAirportActionPerformed

    private void barcelonaBusAirportTownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcelonaBusAirportTownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcelonaBusAirportTownActionPerformed

    private void madridRunway3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_madridRunway3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_madridRunway3ActionPerformed

    //All set methods for the graphical interface avoiding mutual exclusion when necessary
    private Lock lockMadridPassengers = new ReentrantLock();
    private Lock lockBarcelonaPassengers = new ReentrantLock();
    private Lock lockMadridBusTownAirport = new ReentrantLock();
    private Lock lockBarcelonaBusTownAirport = new ReentrantLock();
    private Lock lockMadridBusAirportTown = new ReentrantLock();
    private Lock lockBarcelonaBusAirportTown = new ReentrantLock();
    private Lock lockMadridHangar = new ReentrantLock();
    private Lock lockBarcelonaHangar = new ReentrantLock();
    private Lock lockMadridParking = new ReentrantLock();
    private Lock lockBarcelonaParking = new ReentrantLock();
    private Lock lockMadridTaxiArea = new ReentrantLock();
    private Lock lockBarcelonaTaxiArea = new ReentrantLock();
    private Lock lockAirwayMadridBarcelona = new ReentrantLock();
    private Lock lockAirwayBarcelonaMadrid = new ReentrantLock();
    private Lock lockMadridMaintenanceHall = new ReentrantLock();
    private Lock lockBarcelonaMaintenanceHall = new ReentrantLock();
    private Gateway gw = new Gateway();
    private boolean resumePressed = false;
    private boolean pausePressed = false;

    public Gateway getGw() {
        return gw;
    }

    public void setMadridPassengers(int madridPassengers) {
        lockMadridPassengers.lock();
        try {
            this.madridPassengers.setText(String.valueOf(madridPassengers));
        } catch (Exception e) {
        } finally {
            lockMadridPassengers.unlock();
        }
    }

    public void setBarcelonaPassengers(int barcelonaPassengers) {
        lockBarcelonaPassengers.lock();
        try {
            this.barcelonaPassengers.setText(String.valueOf(barcelonaPassengers));
        } catch (Exception e) {
        } finally {
            lockBarcelonaPassengers.unlock();
        }
    }

    public void setMadridBusTownAirport(String bus) {
        lockMadridBusTownAirport.lock();
        try {
            this.madridBusTownAirport.setText(bus);
        } catch (Exception e) {
        } finally {
            lockMadridBusTownAirport.unlock();
        }
    }

    public void setBarcelonaBusTownAirport(String bus) {
        lockBarcelonaBusTownAirport.lock();
        try {
            this.barcelonaBusTownAirport.setText(bus);
        } catch (Exception e) {
        } finally {
            lockBarcelonaBusTownAirport.unlock();
        }
    }

    public void setMadridBusAirportTown(String bus) {
        lockMadridBusAirportTown.lock();
        try {
            this.madridBusAirportTown.setText(bus);
        } catch (Exception e) {
        } finally {
            lockMadridBusAirportTown.unlock();
        }
    }

    public void setBarcelonaBusAirportTown(String bus) {
        lockBarcelonaBusAirportTown.lock();
        try {
            this.barcelonaBusAirportTown.setText(bus);
        } catch (Exception e) {
        } finally {
            lockBarcelonaBusAirportTown.unlock();
        }
    }

    public void setMadridHangar(String hangar) {
        lockMadridHangar.lock();
        try {
            this.madridHangar.setText(hangar);
        } catch (Exception e) {
        } finally {
            lockMadridHangar.unlock();
        }
    }

    public void setBarcelonaHangar(String hangar) {
        lockBarcelonaHangar.lock();
        try {
            this.barcelonaHangar.setText(hangar);
        } catch (Exception e) {
            // Aquí podrías manejar la excepción si lo deseas
        } finally {
            lockBarcelonaHangar.unlock();
        }
    }

    public void setMadridParking(String parking) {
        lockMadridParking.lock();
        try {
            this.madridParking.setText(parking);
        } catch (Exception e) {
            // Aquí podrías manejar la excepción si lo deseas
        } finally {
            lockMadridParking.unlock();
        }
    }

    public void setBarcelonaParking(String parking) {
        lockBarcelonaParking.lock();
        try {
            this.barcelonaParking.setText(parking);
        } catch (Exception e) {
            // Aquí podrías manejar la excepción si lo deseas
        } finally {
            lockBarcelonaParking.unlock();
        }
    }

    public void setMadridGate1(String gate) {
        this.madridGate1.setText(gate);
    }

    public void setMadridGate2(String gate) {
        this.madridGate2.setText(gate);
    }

    public void setMadridGate3(String gate) {
        this.madridGate3.setText(gate);
    }

    public void setMadridGate4(String gate) {
        this.madridGate4.setText(gate);
    }

    public void setMadridGate5(String gate) {
        this.madridGate5.setText(gate);
    }

    public void setMadridGate6(String gate) {
        this.madridGate6.setText(gate);
    }

    public void setBarcelonaGate1(String gate) {
        this.barcelonaGate1.setText(gate);
    }

    public void setBarcelonaGate2(String gate) {
        this.barcelonaGate2.setText(gate);
    }

    public void setBarcelonaGate3(String gate) {
        this.barcelonaGate3.setText(gate);
    }

    public void setBarcelonaGate4(String gate) {
        this.barcelonaGate4.setText(gate);
    }

    public void setBarcelonaGate5(String gate) {
        this.barcelonaGate5.setText(gate);
    }

    public void setBarcelonaGate6(String gate) {
        this.barcelonaGate6.setText(gate);
    }

    public void setMadridTaxiArea(String taxiArea) {
        lockMadridTaxiArea.lock();
        try {
            this.madridTaxiArea.setText(taxiArea);
        } catch (Exception e) {
        } finally {
            lockMadridTaxiArea.unlock();
        }
    }

    public void setMadridRunway1(String runway) {
        this.madridRunway1.setText(runway);
    }

    public void setMadridRunway2(String runway) {
        this.madridRunway2.setText(runway);
    }

    public void setMadridRunway3(String runway) {
        this.madridRunway3.setText(runway);
    }

    public void setMadridRunway4(String runway) {
        this.madridRunway4.setText(runway);
    }

    public void setBarcelonaTaxiArea(String taxiArea) {
        lockBarcelonaTaxiArea.lock();
        try {
            this.barcelonaTaxiArea.setText(taxiArea);
        } catch (Exception e) {
        } finally {
            lockBarcelonaTaxiArea.unlock();
        }
    }

    public void setBarcelonaRunway1(String runway) {
        this.barcelonaRunway1.setText(runway);
    }

    public void setBarcelonaRunway2(String runway) {
        this.barcelonaRunway2.setText(runway);
    }

    public void setBarcelonaRunway3(String runway) {
        this.barcelonaRunway3.setText(runway);
    }

    public void setBarcelonaRunway4(String runway) {
        this.barcelonaRunway4.setText(runway);
    }

    public void setAirwayMadridBarcelona(String airway) {
        lockAirwayMadridBarcelona.lock();
        try {
            this.airwayMadridBarcelona.setText(airway);
        } catch (Exception e) {
        } finally {
            lockAirwayMadridBarcelona.unlock();
        }
    }

    public void setAirwayBarcelonaMadrid(String airway) {
        lockAirwayBarcelonaMadrid.lock();
        try {
            this.airwayBarcelonaMadrid.setText(airway);
        } catch (Exception e) {
        } finally {
            lockAirwayBarcelonaMadrid.unlock();
        }
    }

    public void setMadridMaintenanceHall(String maintenanceHall) {
        lockMadridMaintenanceHall.lock();
        try {
            this.madridMaintenance.setText(maintenanceHall);
        } catch (Exception e) {
        } finally {
            lockMadridMaintenanceHall.unlock();
        }
    }

    public void setBarcelonaMaintenanceHall(String maintenanceHall) {
        lockBarcelonaMaintenanceHall.lock();
        try {
            this.barcelonaMaintenance.setText(maintenanceHall);
        } catch (Exception e) {
        } finally {
            lockBarcelonaMaintenanceHall.unlock();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphicalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphicalInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField airwayBarcelonaMadrid;
    private javax.swing.JTextField airwayMadridBarcelona;
    private javax.swing.JTextField barcelonaBusAirportTown;
    private javax.swing.JTextField barcelonaBusTownAirport;
    private javax.swing.JTextField barcelonaGate1;
    private javax.swing.JTextField barcelonaGate2;
    private javax.swing.JTextField barcelonaGate3;
    private javax.swing.JTextField barcelonaGate4;
    private javax.swing.JTextField barcelonaGate5;
    private javax.swing.JTextField barcelonaGate6;
    private javax.swing.JTextField barcelonaHangar;
    private javax.swing.JTextField barcelonaMaintenance;
    private javax.swing.JTextField barcelonaParking;
    private javax.swing.JTextField barcelonaPassengers;
    private javax.swing.JTextField barcelonaRunway1;
    private javax.swing.JTextField barcelonaRunway2;
    private javax.swing.JTextField barcelonaRunway3;
    private javax.swing.JTextField barcelonaRunway4;
    private javax.swing.JTextField barcelonaTaxiArea;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField madridBusAirportTown;
    private javax.swing.JTextField madridBusTownAirport;
    private javax.swing.JTextField madridGate1;
    private javax.swing.JTextField madridGate2;
    private javax.swing.JTextField madridGate3;
    private javax.swing.JTextField madridGate4;
    private javax.swing.JTextField madridGate5;
    private javax.swing.JTextField madridGate6;
    private javax.swing.JTextField madridHangar;
    private javax.swing.JTextField madridMaintenance;
    private javax.swing.JTextField madridParking;
    private javax.swing.JTextField madridPassengers;
    private javax.swing.JTextField madridRunway1;
    private javax.swing.JTextField madridRunway2;
    private javax.swing.JTextField madridRunway3;
    private javax.swing.JTextField madridRunway4;
    private javax.swing.JTextField madridTaxiArea;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton resumeButton;
    // End of variables declaration//GEN-END:variables
}
