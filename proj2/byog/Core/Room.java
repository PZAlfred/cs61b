package byog.Core;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class Room {

    public int x;
    public int y;
    private int westX;
    private int eastX;
    private int northY;
    private int southY;
    public List<Room> roomConnected = new LinkedList<>();

    /*
     * Initiate room object at (X, Y).
     */
    public Room(int X, int Y) {
        westX = X;
        eastX = X;
        northY = Y;
        southY = Y;
        x = X;
        y = Y;
    }

    /*
     * return room list with this room.
     */
    public List<Room> roomsWithoutThis(List<Room> rooms) {
        List<Room> returnRooms = new LinkedList<>();
        for (Room room : rooms) {
            if (!room.equals(this)) {
                returnRooms.add(room);
            }
        }
        return returnRooms;
    }

    /*
     * Return if this room overlap horizontally with rm2.
     */
    private boolean overlapHorizontalWith(Room rm2) {
        if (this.equals(rm2)) {
            return true;
        }
        if (this.northWall() < rm2.southWall() || this.southWall() > rm2.northWall()) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Return if this room overlap vertically with rm2.
     */
    private boolean overlapVerticalWith(Room rm2) {
        if (this.equals(rm2)) {
            return true;
        }
        if (this.eastWall() < rm2.westWall() || this.westWall() > rm2.eastWall()) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Generate horizontal hallway.
     */
    public Hallway getHorizontalHallway(List<Room> rooms, Random rd) {
        Room returnRoom = new Room(0, 0);
        int returnY = this.southY;
        for (Room room : rooms) {
            if (this.equals(room) || this.overlapHorizontalWith(room)) {
                continue;
            } else if (this.northWall() >= room.southWall() || this.southWall() <= room.northWall()) {
                continue;
            } else {
                int y1 = 0, y2 = 0;
                for (int y = this.southY; y <= this.northY; y++) {
                    if (y1 == 0 && y > room.southWall()) {
                        if (y == room.northWall() - 1 || y == this.northWall() - 1) {
                            y2 = y;
                        }
                        y1 = y;
                    } else if (y1 != 0 && y == room.northWall() - 1) {
                        y2 = y;
                    }
                }
                returnY = rd.nextInt(y1, y2);
                returnRoom = room;
                if (rd.nextDouble() > 0.5) {
                    break;
                }
            }
        }
        if (this.eastWall() < returnRoom.westWall()) {
            return new Hallway(this.eastWall(), returnY, returnRoom.westWall(), returnY, this, returnRoom);
        } else {
            return new Hallway(this.westWall(), returnY, returnRoom.eastWall(), returnY, this, returnRoom);
        }
    }

    /*
     * Generate vertical hallway.
     */
    public Hallway getVerticalHallway(List<Room> rooms, Random rd) {
        return new Hallway(x, y, x, y, null, null);
    }

    /**
     * Method to grow the room one time, if there is a boundary, don't grow.
     * Grow --> update the value of 4 boundaries.
     */
    public void grow(List<Room> rooms, int n) {
        switch (n) {
            case 0:
                if (this.northStatus(rooms)) {
                    northY += 1;
                }
                break;
            case 1:
                if (this.southStatus(rooms)) {
                    southY -= 1;
                }
                break;
            case 2:
                if (this.westStatus(rooms)) {
                    westX -= 1;
                }
                break;
            case 3:
                if (this.eastStatus(rooms)) {
                    eastX += 1;
                }
                break;
            default:
                break;
        }
    }

    /**
     * North side status to grow.
     */
    private boolean northStatus(List<Room> rooms) {
        if (this.northWall() >= Game.HEIGHT - 1) {
            return false;
        }
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (this.overlapVerticalWith(room) && this.northWall() >= room.southWall() - 1
                    && this.southWall() < room.northWall()) {
                return false;
            }
        }
        return true;
    }

    /**
     * South side status to grow.
     */
    private boolean southStatus(List<Room> rooms) {
        if (this.southWall() <= 0) {
            return false;
        }
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (this.overlapVerticalWith(room) && this.southWall() <= room.northWall() + 1
                    && this.northWall() > room.southWall()) {
                return false;
            }
        }
        return true;
    }

    /**
     * West side status to grow.
     */
    private boolean westStatus(List<Room> rooms) {
        if (this.westWall() <= 0) {
            return false;
        }
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (this.overlapHorizontalWith(room) && this.westWall() <= room.eastWall() + 1
                    && this.eastWall() > room.westWall()) {
                return false;
            }
        }
        return true;
    }

    /**
     * East side status to grow.
     */
    private boolean eastStatus(List<Room> rooms) {
        if (this.eastWall() >= Game.WIDTH - 1) {
            return false;
        }
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (this.overlapHorizontalWith(room) && this.eastWall() >= room.westWall() - 1
                    && this.westWall() < room.eastWall()) {
                return false;
            }
        }
        return true;
    }

    /*
     * Return status of the room can grow or not.
     */
    public boolean canGrow(List<Room> rooms) {
        boolean status = false;
        if (northStatus(rooms)) {
            status = true;
        }
        if (southStatus(rooms)) {
            status = true;
        }
        if (westStatus(rooms)) {
            status = true;
        }
        if (eastStatus(rooms)) {
            status = true;
        }
        return status;
    }

    /*
     * Return the X coordinate of west Wall.
     */
    public int westWall() {
        return westX - 1;
    }

    /*
     * Return the X coordinate of east Wall.
     */
    public int eastWall() {
        return eastX + 1;
    }

    /*
     * Return the Y coordinate of south Wall.
     */
    public int southWall() {
        return southY - 1;
    }

    /*
     * Return the Y coordinate of north Wall.
     */
    public int northWall() {
        return northY + 1;
    }
}
