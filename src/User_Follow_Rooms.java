
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author danis
 */
public class User_Follow_Rooms extends javax.swing.JFrame {

    /**
     * Creates new form User_Follow_Rooms
     */
    String username;
    ArrayList<Rooms> arrayList = new ArrayList<>();
    MyTabelModel tm = new MyTabelModel();

    public User_Follow_Rooms(String username) {
        initComponents();
        setSize(500, 500);
        setVisible(true);
        this.username = username;

        getContentPane().setBackground(new Color(153, 255, 204));
        j_rooms.setModel(tm);
        get_all_rooms();

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
        j_rooms = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        j_rooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(j_rooms);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 20, 660, 460);

        jButton1.setText("Remove");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(370, 500, 190, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int ans = j_rooms.getSelectedRow();
        if(ans == -1){
            JOptionPane.showMessageDialog(rootPane," Select Row First");
        }
        else {
            try {
                HttpResponse<String> httpResponse = Unirest.get(GlobalClass.ip+"Delete_user_room")
                        .queryString("rid",arrayList.get(ans).rid)
                         .queryString("username",username)
                        .asString();
                if(httpResponse.getStatus() == 200){
                    JOptionPane.showMessageDialog(rootPane, httpResponse.getBody());
                    get_all_rooms();
                }
                
                
                
            } catch (UnirestException ex) {
                ex.printStackTrace();
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
            java.util.logging.Logger.getLogger(User_Follow_Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Follow_Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Follow_Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Follow_Rooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new User_Follow_Rooms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable j_rooms;
    // End of variables declaration//GEN-END:variables
public void get_all_rooms() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                try {
                    HttpResponse<String> httpResponse = Unirest.get(GlobalClass.ip + "fetch_user_rooms")
                            .queryString("username",username)
                            .asString();
                    if (httpResponse.getStatus() == 200) {
                        String res = httpResponse.getBody();

                        arrayList.clear();
                        StringTokenizer st1 = new StringTokenizer(res, "~~"); //row
                        while (st1.hasMoreTokens()) {
                            String res2 = st1.nextToken();
                            StringTokenizer st2 = new StringTokenizer(res2, "$$");//column
                            while (st2.hasMoreTokens()) {
                                int rid = Integer.parseInt(st2.nextToken());
                                String roomname = st2.nextToken();
                                String photo = st2.nextToken();
                                String category = st2.nextToken();
                                arrayList.add(new Rooms(rid, roomname, category, photo));
                                System.out.println("" + roomname);
                            }

                        }

                        System.out.println("" + arrayList.size());
                        j_rooms.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());
                        j_rooms.setRowHeight(100);
                        tm.fireTableDataChanged();

                    }

                } catch (Exception ex) {
                    Logger.getLogger(Admin_Mage_Rooms.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }

    class MyTabelModel extends AbstractTableModel {

        String columname[] = {"RoomId", "Room Name", "Category", "Photo"};

        @Override
        public String getColumnName(int column) {
            return this.columname[column]; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getRowCount() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            return arrayList.size();
        }

        @Override
        public int getColumnCount() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return columname.length;

        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            switch (columnIndex) {
                case 0:
                    return arrayList.get(rowIndex).rid;

                case 1:
                    return arrayList.get(rowIndex).roomname;

                case 2:
                    return arrayList.get(rowIndex).category;

                case 3:
                    return arrayList.get(rowIndex).photo;

                default:
                    return null;
            }

        }

    }

    class ImageRenderer extends DefaultTableCellRenderer {

        JLabel lbl = new JLabel();

        ImageIcon icon = new ImageIcon("");
        BufferedImage bufferedImage, newimage;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            try {
                URL url = new URL(GlobalClass.ip + "GetResource/" + arrayList.get(row).photo);
                System.out.println("url : " + url);
                bufferedImage = ImageIO.read(url);
                newimage = resizephoto(bufferedImage, 100, 100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            icon = new ImageIcon(newimage);
            lbl.setIcon(icon);
            lbl.setBounds(0, 0, 100, 100);
            return lbl;
        }

    }

    BufferedImage resizephoto(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

}
