
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danis
 */
public class Admin_Home extends javax.swing.JFrame {

    /**
     * Creates new form Admin_Home
     */
    String username;
    public Admin_Home(String username) {
        initComponents();
        this.username = username;
        setSize(500, 500);
        setVisible(true);
        
        j_welcome.setText("Welcome "+username);
         getContentPane().setBackground(new Color(153,255,204));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        j_welcome = new javax.swing.JLabel();
        j_change_Password = new javax.swing.JButton();
        j_manage_rooms = new javax.swing.JButton();
        j_logout = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        j_welcome.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        getContentPane().add(j_welcome);
        j_welcome.setBounds(40, 10, 240, 60);

        j_change_Password.setText("Chnage Password");
        j_change_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_change_PasswordActionPerformed(evt);
            }
        });
        getContentPane().add(j_change_Password);
        j_change_Password.setBounds(20, 100, 130, 60);

        j_manage_rooms.setText("Manage Rooms");
        j_manage_rooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_manage_roomsActionPerformed(evt);
            }
        });
        getContentPane().add(j_manage_rooms);
        j_manage_rooms.setBounds(20, 200, 140, 60);

        j_logout.setText("Logout");
        getContentPane().add(j_logout);
        j_logout.setBounds(250, 210, 130, 50);

        jButton1.setText("Add Rooms");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(220, 110, 150, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void j_change_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_change_PasswordActionPerformed
        // TODO add your handling code here:
        
        Admin_changePassword obj = new Admin_changePassword(username);
        
    }//GEN-LAST:event_j_change_PasswordActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Admin_Add_Rooms oj = new Admin_Add_Rooms();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void j_manage_roomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_manage_roomsActionPerformed
        // TODO add your handling code here:
        
        Admin_Mage_Rooms obj = new Admin_Mage_Rooms();
        
    }//GEN-LAST:event_j_manage_roomsActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Admin_Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton j_change_Password;
    private javax.swing.JButton j_logout;
    private javax.swing.JButton j_manage_rooms;
    private javax.swing.JLabel j_welcome;
    // End of variables declaration//GEN-END:variables
}