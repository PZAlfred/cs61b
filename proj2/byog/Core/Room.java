package byog.Core;

public class Room {

    private int x;
    private int y;
    private int westX;
    private int eastX;
    private int northY;
    private int southY;
    private Room[] roomConnected = new Room[100];

    /*
     * Initiate room object at (X, Y).
     */
    public Room(int X, int Y) {
        westX = X;
        eastX = X;
        northY = Y;
        southY = Y;
        this.x = X;
        this.y = Y;
    }

    /*
     * Return the X coordinate of west Wall.
     */
    public int westWall() {
        return westX - 1;
    }

    /*
     * Return the Y coordinate of south Wall.
     */
    public int southWall() {
        return southY - 1;
    }

}
