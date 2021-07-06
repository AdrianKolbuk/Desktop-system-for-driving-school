/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.DefaultListModel;
import model.EventReservation;
import model.Shared;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 *
 * @author 48601
 */
public class EmployeeEventListFrame extends javax.swing.JFrame {

    static DefaultListModel eventsModel;
    static DefaultListModel reservationsModel;
    /**
     * Creates new form EmployeeEventListFrame
     */
    public EmployeeEventListFrame() {
        eventsModel = new DefaultListModel();
        reservationsModel = new DefaultListModel();
        
        for(int i = 0; i < Shared.eventsFromDb.size(); i++){
            eventsModel.add(i, Shared.eventsFromDb.get(i));
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jEmployeeEventList = new javax.swing.JList<>();
        listLabel = new javax.swing.JLabel();
        listLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEmployeeReservationList = new javax.swing.JList<>();
        buttonLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Events and reservations");

        jEmployeeEventList.setModel(eventsModel);
        jEmployeeEventList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEmployeeEventListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jEmployeeEventList);

        listLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listLabel.setText("List of available events");
        listLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        listLabel2.setText("Reservations for selected event");
        listLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jEmployeeReservationList.setModel(reservationsModel);
        jEmployeeReservationList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEmployeeReservationListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jEmployeeReservationList);

        buttonLogout.setText("Log out");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(listLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addComponent(listLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLogout))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listLabel)
                    .addComponent(buttonLogout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jEmployeeEventListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEmployeeEventListMouseClicked
        reservationsModel.removeAllElements();
        int selected_index = jEmployeeEventList.getSelectedIndex();   

        for (int i = 0; i < Shared.eventsFromDb.get(selected_index).getEventReservations().size(); i++) {
            reservationsModel.add(i, Shared.eventsFromDb.get(selected_index).getEventReservations().get(i));
        }
           
    }//GEN-LAST:event_jEmployeeEventListMouseClicked

    private void jEmployeeReservationListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEmployeeReservationListMouseClicked
        int selected_index = jEmployeeReservationList.getSelectedIndex();
        Shared.selectedReservation = (EventReservation) reservationsModel.get(selected_index);
        System.out.println("selected = " + Shared.selectedReservation);
        this.setVisible(false);
        new EmployeeReservationFrame().setVisible(true);
    }//GEN-LAST:event_jEmployeeReservationListMouseClicked

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        this.setVisible(false);
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_buttonLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeEventListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeEventListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeEventListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeEventListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeEventListFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogout;
    private javax.swing.JList<String> jEmployeeEventList;
    private javax.swing.JList<String> jEmployeeReservationList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel listLabel;
    private javax.swing.JLabel listLabel2;
    // End of variables declaration//GEN-END:variables
}
