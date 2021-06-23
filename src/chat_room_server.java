
import com.vmm.JHTTPServer;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class chat_room_server extends JHTTPServer {

    public chat_room_server(int port) throws IOException {
        super(port);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
//        return super.serve(uri, method, header, parms, files); //To change body of generated methods, choose Tools | Templates.
        Response res;
        res = new Response(HTTP_OK, "text/plain", "Hello From Java Server");
        System.out.println("" + uri);
        if (uri.contains("/admin_login")) {
            try {
                String ans;
                String username = parms.getProperty("username");
                String password = parms.getProperty("password");

                ResultSet rs = DBLoader.executeQuery("select * from admin where username = '" + username + "' and password ='" + password + "'");
                if (rs.next()) {
                    ans = "success";
                } else {
                    ans = "fail";
                }

                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/admin_change_pass")) {
            String ans;
            String username = parms.getProperty("username");
            String oldpass = parms.getProperty("oldpass");
            String newpass = parms.getProperty("newpass");
            System.out.println("" + username + ".." + newpass + "..." + oldpass);

            try {
                ResultSet rs = DBLoader.executeQuery("select * from admin where username='" + username + "' and password = '" + oldpass + "'");
                if (rs.next()) {
                    rs.moveToCurrentRow();
                    rs.updateString("password", newpass);
                    rs.updateRow();
                    ans = "success";
                } else {
                    ans = "fail";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/add_rooms")) {
            try {
                String category = parms.getProperty("category");
                String roomname = parms.getProperty("roomname");

                String imagepath = saveFileOnServerWithRandomName(files, parms, "photo", "src/uploads");

                ResultSet rs = DBLoader.executeQuery("select * from rooms");
                rs.moveToInsertRow();
                rs.updateString("roomname", roomname);
                rs.updateString("category", category);
                rs.updateString("photo", "src/uploads/" + imagepath);
                rs.insertRow();
                res = new Response(HTTP_OK, "text/plain", "success");

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/fetch_all_rooms")) {
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeQuery("select * from rooms");
                while (rs.next()) {
                    int rid = rs.getInt("rid");
                    String roomname = rs.getString("roomname");
                    String photo = rs.getString("photo");
                    String category = rs.getString("category");

                    ans += rid + "$$" + roomname + "$$" + photo + "$$" + category + "~~";
                }
                System.out.println("" + ans);
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/GetResource")) {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            System.out.println("" + uri);
            res = sendCompleteFile(uri);
        } else if (uri.contains("/Delete_room")) {
            int rid = Integer.parseInt(parms.getProperty("rid"));
            try {
                ResultSet rs = DBLoader.executeQuery("select * from rooms where rid =" + rid);
                if (rs.next()) {
                    rs.deleteRow();
                    res = new Response(HTTP_OK, "text/plain", "success");
                }

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/user_signup")) {
            String username = parms.getProperty("username");
            String email = parms.getProperty("email");
            String pass = parms.getProperty("password");
            String mobileno = parms.getProperty("mobileno");
            String sec_ques = parms.getProperty("sec_ques");
            String sec_ans = parms.getProperty("sec_ans");

            String photopath = saveFileOnServerWithRandomName(files, parms, "photo", "src/userprofile");

            String ans;
            try {
                ResultSet rs = DBLoader.executeQuery("select * from users where username = '" + username + "'");
                if (rs.next()) {
                    ans = "Username Already Exists.";

                } else {
                    rs.moveToInsertRow();
                    rs.updateString("username", username);
                    rs.updateString("email", email);
                    rs.updateString("password", pass);
                    rs.updateString("mobile_no", mobileno);
                    rs.updateString("sec_ques", sec_ques);
                    rs.updateString("sec_ans", sec_ans);
                    rs.updateString("photo", "src/userprofile/" + photopath);
                    rs.insertRow();
                    ans = "success";

                }

                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/user_login")) {
            String username = parms.getProperty("username");
            String password = parms.getProperty("password");
            String ans;
            try {
                ResultSet rs = DBLoader.executeQuery("select * from users where username = '" + username + "' and password = '" + password + "'");

                if (rs.next()) {
                    ans = "success";
                } else {
                    ans = "Invalid Credentials";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/user_change_pass")) {
            String username = parms.getProperty("username");
            String oldpass = parms.getProperty("oldpass");
            String newpass = parms.getProperty("newpass");

            String ans;
            try {
                ResultSet rs = DBLoader.executeQuery("select * from users where username = '" + username + "' and password = '" + oldpass + "'");

                if (rs.next()) {
                    rs.moveToCurrentRow();
                    rs.updateString("password", newpass);
                    rs.updateRow();

                    ans = "success";
                } else {
                    ans = "Invalid old password";
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/fetch_rooms_cat")) {
            String category = parms.getProperty("category");
            String ans = "";
            try {
                ResultSet rs = DBLoader.executeQuery("select * from rooms where category='" + category + "'");
                while (rs.next()) {
                    int rid = rs.getInt("rid");
                    String roomname = rs.getString("roomname");
                    String photo = rs.getString("photo");
                    String category1 = rs.getString("category");

                    ans += rid + "$$" + roomname + "$$" + photo + "$$" + category1 + "~~";
                }
                System.out.println("" + ans);
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/user_forget_pass")) {
            String user = parms.getProperty("user");
            String ques = parms.getProperty("ques");
            String ans = parms.getProperty("ans");
            String ans2;
            try {
                ResultSet rs = DBLoader.executeQuery("select * from users where username='" + user + "' and sec_ques='" + ques + "' and sec_ans='" + ans + "'");

                if (rs.next()) {
                    String mobile = rs.getString("mobile_no");
                    String pass = rs.getString("password");
                    ans2 = mobile+"~~"+pass;
                } else {
                    ans2 = "fails";
                }

                res = new Response(HTTP_OK, "text/plain", ans2);
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/join_room")) {
            try {
                int rid = Integer.parseInt(parms.getProperty("rid"));
                String username = parms.getProperty("username");
                String ans;
                ResultSet rs = DBLoader.executeQuery("select * from myrooms where username = '" + username + "' and room_id ='" + rid + "'");
                if (rs.next()) {
                    ans = "exists";
                } else {
                    rs.moveToInsertRow();
                    rs.updateString("username", username);
                    rs.updateInt("room_id", rid);
                    rs.insertRow();
                    ans = "success";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/check_room")) {
            try {
                int rid = Integer.parseInt(parms.getProperty("rid"));
                String username = parms.getProperty("username");
                String ans;
                ResultSet rs = DBLoader.executeQuery("select * from myrooms where username = '" + username + "' and room_id ='" + rid + "'");
                if (rs.next()) {
                    ans = "exists";
                } else {
                    ans = "fails";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("/fetch_room_members")) {
            try {
                String ans = "";
                int rid = Integer.parseInt(parms.getProperty("rid"));
                ResultSet rs = DBLoader.executeQuery("select * from myrooms where room_id =" + rid);
                while (rs.next()) {
                    ans += rs.getString("username") + "~~";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/fetch_room_details")) {
            try {
                String ans = "";
                int rid = Integer.parseInt(parms.getProperty("rid"));
                ResultSet rs = DBLoader.executeQuery("select * from rooms where rid =" + rid);
                while (rs.next()) {
                    ans += rs.getString("roomname") + "~~" + rs.getString("photo");
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("/fetch_user_rooms")) {
            String username = parms.getProperty("username");
            String ans = "";
            try {
                ResultSet rs_user = DBLoader.executeQuery("select * from myrooms where username= '" + username + "'");
                while (rs_user.next()) {
                    int myid = rs_user.getInt("myrid");
                    int roomid = rs_user.getInt("room_id");
                    ResultSet rs = DBLoader.executeQuery("select * from rooms where rid="+roomid);
                    while (rs.next()) {
                        int rid = rs.getInt("rid");
                        String roomname = rs.getString("roomname");
                        String photo = rs.getString("photo");
                        String category = rs.getString("category");

                        ans += rid + "$$" + roomname + "$$" + photo + "$$" + category + "~~";
                    }

                }

                System.out.println("" + ans);
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (uri.contains("/Delete_user_room")) {
            int rid = Integer.parseInt(parms.getProperty("rid"));
            String username = parms.getProperty("username");
            try {
                ResultSet rs = DBLoader.executeQuery("select * from myrooms where room_id ='" + rid+"' and username='"+username+"'");
                if (rs.next()) {
                    rs.deleteRow();
                    res = new Response(HTTP_OK, "text/plain", "success");
                }

            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if(uri.contains("/save_text_msg")){
            try {
                String message = parms.getProperty("message");
                String posted_by = parms.getProperty("posted_by");
                String rid = parms.getProperty("rid");
                String type = parms.getProperty("type");
                
                ResultSet rs = DBLoader.executeQuery("select * from messages");
                rs.moveToInsertRow();
                rs.updateString("message", message);
                rs.updateString("postedby", posted_by);
                rs.updateString("type", type);
                rs.updateInt("rid", Integer.parseInt(rid));
                rs.insertRow();
                 res = new Response(HTTP_OK, "text/plain", "success");
                
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else if(uri.contains("/save_img_msg")){
            try {
                String posted_by = parms.getProperty("posted_by");
                String rid = parms.getProperty("rid");
                String type = parms.getProperty("type");
                String img_path = saveFileOnServerWithRandomName(files, parms, "img", "src/uploads");
                
                ResultSet rs = DBLoader.executeQuery("select * from messages");
                rs.moveToInsertRow();
                rs.updateString("message", "src/uploads/"+img_path);
                rs.updateString("postedby", posted_by);
                rs.updateString("type", type);
                rs.updateInt("rid", Integer.parseInt(rid));
                rs.insertRow();
                 res = new Response(HTTP_OK, "text/plain", "success");
                
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else if(uri.contains("/fetch_mess")){
            try{
                String ans = "";
                int rid = Integer.parseInt(parms.getProperty("rid"));
                ResultSet rs = DBLoader.executeQuery("select * from messages where rid='"+rid+"'");
                while(rs.next()){
                    int mesid = rs.getInt("mesgid");
                        String message = rs.getString("message");
                        String postedby = rs.getString("postedby");
                                           

                        String datetime = rs.getString("datetime");
                        String type = rs.getString("type");

                        ans += mesid + "$$" + message + "$$" + postedby + "$$" + datetime + "$$" + type + "~~";
                }
                 res = new Response(HTTP_OK, "text/plain", ans);
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(uri.contains("/save_emojis_msg")){
            try {
                String posted_by = parms.getProperty("posted_by");
                String rid = parms.getProperty("rid");
                String type = parms.getProperty("type");
                String message = parms.getProperty("message");
                
                ResultSet rs = DBLoader.executeQuery("select * from messages");
                rs.moveToInsertRow();
                rs.updateString("message", message);
                rs.updateString("postedby", posted_by);
                rs.updateString("type", type);
                rs.updateInt("rid", Integer.parseInt(rid));
                rs.insertRow();
                 res = new Response(HTTP_OK, "text/plain", "success");
                
            } catch (Exception ex) {
                Logger.getLogger(chat_room_server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }

        return res;
    }

}
