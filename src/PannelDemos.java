/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danis
 */
public class PannelDemos extends javax.swing.JFrame {

    /**
     * Creates new form PannelDemos
     */
    int x = 10 , y = 10;
    public PannelDemos() {
        initComponents();
        setSize(500,500);
        setVisible(true);
       
        Pannel_Design pd[] = new Pannel_Design[GlobalClass.category.length];
         
          for (int i = 0; i < GlobalClass.category.length; i++) {
              
         pd[i] = new Pannel_Design();
         
         pd[i].setBounds(x, y, 250, 100);
         y = y + 120;
         
         pd[i].j_text.setText(GlobalClass.category[i]);
        
         mainpannel.add(pd[i]);
         repaint();
         mainpannel.repaint();
//         pd[i].repaint();
         
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

        jScrollPane1 = new javax.swing.JScrollPane();
        mainpannel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        mainpannel.setBackground(new java.awt.Color(204, 204, 255));
        mainpannel.setPreferredSize(new java.awt.Dimension(4880, 3580));
        mainpannel.setLayout(null);
        jScrollPane1.setViewportView(mainpannel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 490, 360);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PannelDemos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PannelDemos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PannelDemos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PannelDemos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PannelDemos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpannel;
    // End of variables declaration//GEN-END:variables
}
