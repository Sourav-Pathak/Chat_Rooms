
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import jdk.nashorn.internal.objects.Global;
import sun.util.locale.StringTokenIterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author danis
 */
public class Group_chat extends javax.swing.JFrame {

    /**
     * Creates new form Group_chat
     */
    int w, h, rid;
    String username;
    String smiley[] = {"src/emojis/sm1.png", "src/emojis/sm2.jfif", "src/emojis/sm3.jfif", "src/emojis/sm4.png", "src/emojis/sm5.jfif", "src/emojis/sm6.jfif",
        "src/emojis/sm7.jfif"};
    String symbols[] = {":D", "N(", "::@", ":'-)", "B-)", "**", ":(", ":)"};
    JFileChooser jfc = new JFileChooser();
    File obj_file;

    public Group_chat(int rid, String username) {
        initComponents();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        w = d.width;
        h = d.height;
        setVisible(true);
        setSize(w, h);

        this.rid = rid;
        this.username = username;
        getContentPane().setBackground(new Color(153, 255, 204));

        make_visible_false();
        checkroomjoin();
        fetch_room_members();
        fetch_room_details();
        loadsmileys();
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        new Thread(new MyClass()).start();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                new Thread(new MyClass()).start();
            }
        }, 5000, 5000);

        jEditorPane1.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    System.out.println("File url from hyperlink" + e.getURL());
                    
                    String u = e.getURL().toString();
                    String path = u.substring(u.indexOf("/") + 2);
                    String fname = path.substring(path.lastIndexOf("/") + 1);

                    System.out.println("" + fname);
                    try {

                        HttpResponse<InputStream> httpResponse = Unirest.get(GlobalClass.ip + "GetResource/" + path).asBinary();
                        if (httpResponse.getStatus() == 200) {
//                         System.out.println("test "+httpResponse.getBody());
                            InputStream is = httpResponse.getBody();
                            long fiesize = Integer.parseInt(httpResponse.getHeaders().getFirst("Content-Length"));
                            FileOutputStream fos = new FileOutputStream("d:\\libs\\" + fname);
                            long count = 0;
                            int r = 0;
                            byte b[] = new byte[100000];
                            while (true) {
                                r = is.read(b, 0, b.length);
                                fos.write(b, 0, r);
                                count = count + r;
                                if (count == fiesize) {
                                    System.out.println("File Downloaded.");
                                    break;
                                }
                            }

                            fos.close();
                            int ans = JOptionPane.showConfirmDialog(rootPane, "Do you wanat to open this file ?","Open File?",JOptionPane.YES_NO_OPTION);
                            if(ans == JOptionPane.YES_OPTION){
                                Desktop d = Desktop.getDesktop();
                                d.open(new File("d:\\libs\\" + fname));
                            }

                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        j_roomimg = new javax.swing.JLabel();
        j_join = new javax.swing.JButton();
        j_roomname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        j_msg_sent = new javax.swing.JTextField();
        j_sent_msg = new javax.swing.JButton();
        j_sent_img = new javax.swing.JButton();
        j_simly_pannel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        j_grp_members = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        j_roomimg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(j_roomimg);
        j_roomimg.setBounds(10, 11, 190, 180);

        j_join.setText("Join");
        j_join.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_joinActionPerformed(evt);
            }
        });
        getContentPane().add(j_join);
        j_join.setBounds(220, 60, 130, 50);

        j_roomname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(j_roomname);
        j_roomname.setBounds(40, 230, 160, 50);

        jScrollPane1.setViewportView(jEditorPane1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(450, 10, 550, 380);
        getContentPane().add(j_msg_sent);
        j_msg_sent.setBounds(450, 420, 270, 70);

        j_sent_msg.setText("Sent");
        j_sent_msg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_sent_msgActionPerformed(evt);
            }
        });
        getContentPane().add(j_sent_msg);
        j_sent_msg.setBounds(740, 420, 100, 70);

        j_sent_img.setText("Image");
        j_sent_img.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_sent_imgActionPerformed(evt);
            }
        });
        getContentPane().add(j_sent_img);
        j_sent_img.setBounds(850, 420, 90, 70);

        j_simly_pannel.setBackground(new java.awt.Color(255, 255, 255));
        j_simly_pannel.setLayout(null);
        getContentPane().add(j_simly_pannel);
        j_simly_pannel.setBounds(450, 510, 570, 70);

        jButton1.setText("File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(950, 420, 70, 70);

        j_grp_members.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(j_grp_members);
        j_grp_members.setBounds(20, 310, 250, 330);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void j_joinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_joinActionPerformed
        try {
            // TODO add your handling code here:
            HttpResponse<String> httpResponse = Unirest.get(GlobalClass.ip + "join_room")
                    .queryString("username", username)
                    .queryString("rid", rid)
                    .asString();
            if (httpResponse.getStatus() == 200) {
                String res = httpResponse.getBody();
                System.out.println("" + res);
                if (res.equals("success")) {
                    j_join.setText("Joined");
                    j_join.setEnabled(false);
                    make_visible();
                } else {
                    make_visible_false();
                }
            }

        } catch (UnirestException ex) {
            Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_j_joinActionPerformed

    private void j_sent_msgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_sent_msgActionPerformed
        try {
            String message = j_msg_sent.getText().toString();
            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "All fields are required.");
            } else {
                HttpResponse<String> httpResponse = Unirest.get(GlobalClass.ip + "save_text_msg")
                        .queryString("message", message)
                        .queryString("posted_by", username)
                        .queryString("rid", rid)
                        .queryString("type", "text")
                        .asString();
                if (httpResponse.getStatus() == 200) {
                    JOptionPane.showMessageDialog(rootPane, httpResponse.getBody());
                    j_msg_sent.setText("");

                }
            }
            // TODO add your handling code here:

        } catch (UnirestException ex) {
            Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_j_sent_msgActionPerformed

    private void j_sent_imgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_sent_imgActionPerformed
        // TODO add your handling code here:
        int ans = jfc.showOpenDialog(this);
        if (ans == JFileChooser.APPROVE_OPTION) {
            try {
                obj_file = jfc.getSelectedFile();
                HttpResponse<String> httpResponse = Unirest.post(GlobalClass.ip + "save_img_msg")
                        .queryString("posted_by", username)
                        .queryString("rid", rid)
                        .queryString("type", "photo")
                        .field("img", obj_file)
                        .asString();
                if (httpResponse.getStatus() == 200) {
                    JOptionPane.showMessageDialog(rootPane, httpResponse.getBody());

                }
            } catch (UnirestException ex) {
                Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_j_sent_imgActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int ans = jfc.showOpenDialog(this);
        if (ans == JFileChooser.APPROVE_OPTION) {
            try {
                obj_file = jfc.getSelectedFile();
                HttpResponse<String> httpResponse = Unirest.post(GlobalClass.ip + "save_img_msg")
                        .queryString("posted_by", username)
                        .queryString("rid", rid)
                        .queryString("type", "file")
                        .field("img", obj_file)
                        .asString();
                if (httpResponse.getStatus() == 200) {
                    JOptionPane.showMessageDialog(rootPane, httpResponse.getBody());

                }
            } catch (UnirestException ex) {
                Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Group_chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Group_chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Group_chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Group_chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Group_chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField j_grp_members;
    private javax.swing.JButton j_join;
    private javax.swing.JTextField j_msg_sent;
    private javax.swing.JLabel j_roomimg;
    private javax.swing.JLabel j_roomname;
    private javax.swing.JButton j_sent_img;
    private javax.swing.JButton j_sent_msg;
    private javax.swing.JPanel j_simly_pannel;
    // End of variables declaration//GEN-END:variables

    public void checkroomjoin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                HttpResponse<String> httpResponse;
                try {
                    httpResponse = Unirest.get(GlobalClass.ip + "check_room")
                            .queryString("username", username)
                            .queryString("rid", rid)
                            .asString();
                    if (httpResponse.getStatus() == 200) {
                        String res = httpResponse.getBody();
//                        System.out.println("" + res);
                        if (res.equals("exists")) {
                            j_join.setText("Joined");
                            j_join.setEnabled(false);
                            make_visible();
                        } else {
                            make_visible_false();
                        }
                    }
                } catch (UnirestException ex) {
                    Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }

    public void fetch_room_members() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    HttpResponse<String> httpResponse = Unirest.get(GlobalClass.ip + "fetch_room_members")
                            .queryString("rid", rid)
                            .asString();
                    if (httpResponse.getStatus() == 200) {
                        String res = httpResponse.getBody();
                        String name = "";
                        StringTokenizer st = new StringTokenizer(res, "~~");
                        while (st.hasMoreTokens()) {
                            String user_name = st.nextToken();
                            name += user_name + "\n";

                        }
                        j_grp_members.setText(name);

                    }

                } catch (UnirestException ex) {
                    Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }

    public void fetch_room_details() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    HttpResponse<String> httpResponse = Unirest.get(GlobalClass.ip + "fetch_room_details")
                            .queryString("rid", rid)
                            .asString();
                    if (httpResponse.getStatus() == 200) {
                        String res = httpResponse.getBody();
                        StringTokenizer st = new StringTokenizer(res, "~~");
                        while (st.hasMoreTokens()) {
                            String roomname = st.nextToken();
                            String pic = st.nextToken();
                            ImageIcon icon = new ImageIcon("");
                            BufferedImage bufferedImage = null, newimage;

                            try {
                                URL url = new URL(GlobalClass.ip + "GetResource/" + pic);
                                System.out.println("url3 : " + url);
                                bufferedImage = ImageIO.read(url);
                            } catch (Exception ex) {
                                Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            newimage = resizephoto(bufferedImage, j_roomimg.getWidth(), j_roomimg.getHeight());
                            icon = new ImageIcon(newimage);
                            j_roomimg.setIcon(icon);
                            j_roomname.setText(roomname);

                        }
                    }

                } catch (UnirestException ex) {
                    Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }

    public void make_visible() {
        jScrollPane1.setVisible(true);
        jEditorPane1.setVisible(true);
        j_msg_sent.setVisible(true);
        j_sent_msg.setVisible(true);
        j_sent_img.setVisible(true);
        j_simly_pannel.setVisible(true);
    }

    public void make_visible_false() {
        jScrollPane1.setVisible(false);
        jEditorPane1.setVisible(false);
        j_msg_sent.setVisible(false);
        j_sent_msg.setVisible(false);
        j_sent_img.setVisible(false);
        j_simly_pannel.setVisible(false);
    }

    void loadsmileys() {
        System.out.println("done");
        int x = 5, y = 5;
        JLabel lb[] = new JLabel[smiley.length];
        for (int i = 0; i < smiley.length; i++) {
            lb[i] = new JLabel();
            lb[i].setBounds(x, y, 40, 40);
            ImageIcon icon = new ImageIcon(smiley[i]);
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(lb[i].getWidth(), lb[i].getHeight(), Image.SCALE_SMOOTH);
            lb[i].setIcon(new ImageIcon(newimg));
            final int newi = i;
            lb[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                    try {
                        String msg = symbols[newi];
                        HttpResponse<String> httpResponse = Unirest.post(GlobalClass.ip + "save_emojis_msg")
                                .queryString("posted_by", username)
                                .queryString("rid", rid)
                                .queryString("type", "emojis")
                                .field("message", msg)
                                .asString();
                        if (httpResponse.getStatus() == 200) {
                            JOptionPane.showMessageDialog(rootPane, httpResponse.getBody());

                        }

                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }

                }

            });
            j_simly_pannel.add(lb[i]);
            x = x + 50;

            repaint();
            j_simly_pannel.repaint();
            lb[i].repaint();
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

    public class MyClass implements Runnable {

        @Override
        public void run() {
            try {
                //            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                HttpResponse<String> httpResponse = Unirest.get(GlobalClass.ip + "fetch_mess")
                        .queryString("rid", rid)
                        .asString();
                if (httpResponse.getStatus() == 200) {
                    String res = httpResponse.getBody();
                    StringTokenizer st = new StringTokenizer(res, "~~");

                    String message1 = "";
                    while (st.hasMoreTokens()) {
                        String res2 = st.nextToken();
                        StringTokenizer st2 = new StringTokenizer(res2, "$$");
                        String mesid = st2.nextToken();
                        String msg = st2.nextToken();
                        String postedby = st2.nextToken();
                        String dt = st2.nextToken();
                        String type = st2.nextToken();

                        if (postedby.equals(username)) {
                            if (type.equals("text")) {
                                message1 += "<div style = 'text-align : right; background-color: #4FFCE4'><h3>" + msg + "</h3>";
                                message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";
                            } else if (type.equals("photo")) {
                                message1 += "<div style = 'text-align : right; background-color: #4FFCE4'><img src = 'file:" + msg + "' width='150' height='150' />";
                                message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";

                            } else if (type.equals("file")) {
                                message1 += "<div style = 'text-align : right; background-color: #4FFCE4'><a href = 'http://" + msg + "'><img src = 'file:src/emojis/red-file.png' width='80' height='80' /></a>";
                                message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";

                            } else if (type.equals("emojis")) {

                                for (int i = 0; i < smiley.length; i++) {
                                    if (msg.equals(symbols[i])) {
                                        message1 += "<div style = 'text-align : right; background-color: #4FFCE4'><img src = 'file:" + smiley[i] + "' width='80' height='80' />";
                                        message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";

                                    }

                                }

                            }
                        } else {
                            if (type.equals("text")) {
                                message1 += "<div style = 'text-align : left; background-color:yellow'><h3>" + msg + "</h3>";
                                message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";
                            } else if (type.equals("photo")) {
                                message1 += "<div style = 'text-align : left; background-color:yellow'><img src = 'file:" + msg + "' width='150' height='150' />";
                                message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";

                            } else if (type.equals("file")) {
                                message1 += "<div style = 'text-align : left; background-color:yellow'><a href = 'http://" + msg + "'>  <img src = 'file:src/emojis/red-file.png' width='80' height='80' /></a>";
                                message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";

                            } else if (type.equals("emojis")) {

                                for (int i = 0; i < smiley.length; i++) {
                                    if (msg.equals(symbols[i])) {
                                        message1 += "<div style = 'text-align : left; background-color: yellow'><img src = 'file:" + smiley[i] + "' width='80' height='80' />";
                                        message1 += "<p>" + postedby + "&nbsp;&nbsp; " + dt + " </p></div><hr>";

                                    }

                                }

                            }
                        }

                        jEditorPane1.setText(message1);
                        scrollToBottom(jScrollPane1);

                    }
                }

            } catch (UnirestException ex) {
                Logger.getLogger(Group_chat.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }

        };
        verticalBar.addAdjustmentListener(downScroller);
    }

}
