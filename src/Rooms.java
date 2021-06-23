
public class Rooms {
    int rid;
    String roomname,category,photo;

    public Rooms(int rid, String roomname, String category, String photo) {
        this.rid = rid;
        this.roomname = roomname;
        this.category = category;
        this.photo = photo;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
}
