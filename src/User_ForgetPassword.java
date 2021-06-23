
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.Global;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author danis
 */
public class User_ForgetPassword extends javax.swing.JFrame {

    /**
     * Creates new form User_ForgetPassword
     */
    public User_ForgetPassword() {
        initComponents();
        setVisible(true);
        setSize(500, 500);
 getContentPane().setBackground(new Color(153,255,204));
        jComboBox1.removeAllItems();
        for (int i = 0; i < GlobalClass.Sec_ques.length; i++) {
            jComboBox1.addItem(GlobalClass.Sec_ques[i]);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        juser = new javax.swing.JTextField();
        jans = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("Username");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 40, 140, 50);

        jLabel2.setText("Security Ques");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 120, 150, 50);

        jLabel3.setText("Sec Ans");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 200, 120, 50);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(210, 120, 220, 50);
        getContentPane().add(juser);
        juser.setBounds(210, 40, 230, 50);
        getContentPane().add(jans);
        jans.setBounds(210, 200, 220, 50);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(170, 320, 160, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String username = juser.getText();
        String ques = jComboBox1.getSelectedItem().toString();
        String ans = jans.getText();
        if (username.isEmpty() || ans.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "All felds are required");
        } else {
            try {
                HttpResponse<String> httpres = Unirest.get(GlobalClass.ip + "user_forget_pass")
                        .queryString("user", username)
                        .queryString("ques", ques)
                        .queryString("ans", ans)
                        .asString();

                if (httpres.getStatus() == 200) {
                    String res = httpres.getBody();
//                    System.out.println(""+res);
                    if (res.equals("fails")) {
                        JOptionPane.showMessageDialog(rootPane, "Invalid details");
                    } else {
                        StringTokenizer st = new StringTokenizer(res,"~~");
                        String mobile = st.nextToken();
                        String pass = st.nextToken();
                        Sendsms.smstobesend(mobile, "Your Old Password is "+pass+"  Please dont share it with anyone");
                        JOptionPane.showMessageDialog(rootPane, "Password send to your registered Mobile number");
                    }
                }

            } catch (UnirestException ex) {
                Logger.getLogger(User_ForgetPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(User_ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_ForgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_ForgetPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jans;
    private javax.swing.JTextField juser;
    // End of variables declaration//GEN-END:variables
}
