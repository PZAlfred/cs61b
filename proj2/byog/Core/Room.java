package byog.Core;

// import java.util.Random;
import java.util.List;
// import java.util.Map;
// import java.util.HashMap;
import java.util.LinkedList;

public class Room {

    private int x;
    private int y;
    private int westX;
    private int eastX;
    private int northY;
    private int southY;
    public boolean northConnected;
    public boolean southConnected;
    public boolean westConnected;
    public boolean eastConnected;
    // public List<Hallway> hallwayConnected = new LinkedList<>();

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
        northConnected = false;
        southConnected = false;
        westConnected = false;
        eastConnected = false;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
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
        if (this.northWall() < rm2.southWall() || this.southWall() > rm2.northWall()) {
            return false;
        }
        return true;
    }

    /*
     * Return if this room overlap vertically with rm2.
     */
    private boolean overlapVerticalWith(Room rm2) {
        if (this.eastWall() < rm2.westWall() || this.westWall() > rm2.eastWall()) {
            return false;
        }
        return true;
    }

    /*
     * Return if this room overlap horizontally with rm2 inside Room.
     */
    private boolean overlapRoomHorizontalWith(Room rm2) {
        if (this.northWall() <= rm2.southWall() + 1 || this.southWall() >= rm2.northWall() - 1) {
            return false;
        }
        return true;
    }

    /*
     * Return if this room overlap vertically with rm2 inside Room.
     */
    private boolean overlapRoomVerticalWith(Room rm2) {
        if (this.eastWall() <= rm2.westWall() + 1 || this.westWall() >= rm2.eastWall() - 1) {
            return false;
        }
        return true;
    }

    /*
     * Return the east side can Link or Not.
     */
    private boolean eastCanLink(List<Room> rooms) {
        boolean status = false;
        for (Room room : rooms) {
            if (!this.equals(room) && !this.eastConnected && this.overlapRoomHorizontalWith(room)
                    && this.eastX < room.westX) {
                status = true;
                break;
            }
        }
        return status;
    }

    /*
     * Return the west side can Link or Not.
     */
    private boolean westCanLink(List<Room> rooms) {
        boolean status = false;
        for (Room room : rooms) {
            if (!this.equals(room) && !this.westConnected && this.overlapRoomHorizontalWith(room)
                    && this.westX > room.eastX) {
                status = true;
                break;
            }
        }
        return status;
    }

    /*
     * Return the north side can Link or Not.
     */
    private boolean northCanLink(List<Room> rooms) {
        boolean status = false;
        for (Room room : rooms) {
            if (!this.equals(room) && !this.northConnected && this.overlapRoomVerticalWith(room)
                    && this.northY < room.southY) {
                status = true;
                break;
            }
        }
        return status;
    }

    /*
     * Return the south side can Link or Not.
     */
    private boolean southCanLink(List<Room> rooms) {
        boolean status = false;
        for (Room room : rooms) {
            if (!this.equals(room) && !this.southConnected && this.overlapRoomVerticalWith(room)
                    && this.southY > room.northY) {
                status = true;
                break;
            }
        }
        return status;
    }

    /*
     * Return the closest room on east side.
     */
    public Room findCloseEastRoom(List<Room> rooms) {
        int minDis = Game.WIDTH;
        Room returnRoom = new Room(0, 0);
        for (Room room : rooms) {
            if (this.equals(room) || !this.overlapRoomHorizontalWith(room) || this.westX > room.eastX) {
                continue;
            }
            int dis = room.westX - this.eastX;
            if (dis < minDis) {
                minDis = dis;
                returnRoom = room;
            }
        }
        return returnRoom;
    }

    /*
     * Return the closest room on west side.
     */
    public Room findCloseWestRoom(List<Room> rooms) {
        int minDis = Game.WIDTH;
        Room returnRoom = new Room(0, 0);
        for (Room room : rooms) {
            if (this.equals(room) || !this.overlapRoomHorizontalWith(room) || this.eastX < room.westX) {
                continue;
            }
            int dis = this.westX - room.eastX;
            if (dis < minDis) {
                minDis = dis;
                returnRoom = room;
            }
        }
        return returnRoom;
    }

    /*
     * Return the closest room on north side.
     */
    public Room findCloseNorthRoom(List<Room> rooms) {
        int minDis = Game.HEIGHT;
        Room returnRoom = new Room(0, 0);
        for (Room room : rooms) {
            if (this.equals(room) || !this.overlapRoomVerticalWith(room) || this.southY > room.northY) {
                continue;
            }
            int dis = room.southY - this.northY;
            if (dis < minDis) {
                minDis = dis;
                returnRoom = room;
            }
        }
        return returnRoom;
    }

    /*
     * Return the closest room on south side.
     */
    public Room findCloseSouthRoom(List<Room> rooms) {
        int minDis = Game.HEIGHT;
        Room returnRoom = new Room(0, 0);
        for (Room room : rooms) {
            if (this.equals(room) || !this.overlapRoomVerticalWith(room) || this.northY < room.southY) {
                continue;
            }
            int dis = this.southY - room.northY;
            if (dis < minDis) {
                minDis = dis;
                returnRoom = room;
            }
        }
        return returnRoom;
    }

    /*
     * return status of this room can link based on side.
     */
    public boolean canLink(List<Room> rooms, int n) {
        boolean status = false;
        switch (n) {
            case 0:
                if (this.northCanLink(rooms)) {
                    status = true;
                }
                break;
            case 1:
                if (this.southCanLink(rooms)) {
                    status = true;
                }
                break;
            case 2:
                if (this.westCanLink(rooms)) {
                    status = true;
                }
                break;
            case 3:
                if (this.eastCanLink(rooms)) {
                    status = true;
                }
                break;
            default:
        }
        return status;
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
