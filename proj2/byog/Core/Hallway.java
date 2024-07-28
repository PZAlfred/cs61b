package byog.Core;

import java.util.List;
import java.util.LinkedList;

public class Hallway {
    /*
     * The class of HallWay.
     * 1. start, end, coordinates (X, Y).
     * 2. Two rooms connected.
     * 3. method: hallwayWithoutThisHallway(List<Hallway> hallways)
     * 4. method: isHorizontal(), isVertical() to check intersection.
     * 5. 
     */
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public List<Room> roomsConnected = new LinkedList<>();

    public Hallway(int x1, int y1, int x2, int y2, Room rm1, Room rm2) {
        startX = x1;
        startY = y1;
        endX = x2;
        endY = y2;
        roomsConnected.add(rm1);
        roomsConnected.add(rm2);
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
}
