package byog.Core;

// import java.util.List;
// import java.util.LinkedList;

public class Hallway {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    // public List<Room> roomsConnected = new LinkedList<>();

    public Hallway(int x1, int y1, int x2, int y2, Room rm1, Room rm2) {
        startX = x1;
        startY = y1;
        endX = x2;
        endY = y2;
        // roomsConnected.add(rm1);
        // roomsConnected.add(rm2);
    }

    public boolean isHorizontal() {
        if (startX == endX) {
            return false;
        } 
        return true;
    }
    
    public boolean isVertital() {
        if (startX == endX) {
            return true;
        } 
        return false;
    }

    public int startX() {
        return startX;
    }

    public int startY() {
        return startY;
    }

    public int endX() {
        return endX;
    }

    public int endY() {
        return endY;
    }

}
