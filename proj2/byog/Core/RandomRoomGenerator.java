package byog.Core;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class RandomRoomGenerator {
    private int minRoomNum;
    private int maxRoomNum;
    private int roomNum;

    public TETile[][] generateRandomRooms(int width, int height, long seed) {
        // minRoomNum = width * height / 240;
        // maxRoomNum = width * height / 120;
        minRoomNum = 8;
        maxRoomNum = 20;
        Random rd = new Random(seed);
        TETile[][] re = new TETile[width][height];

        roomNum = rd.nextInt(minRoomNum, maxRoomNum);
        // roomNum = 16; 
        roomNum = (roomNum / 4) * 4; // round to integer times of 4.

        List<Room> rooms = new LinkedList<>();

        int n = roomNum / 4;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                re[i][j] = Tileset.NOTHING;
            }
        }

        rooms = generateRooms(rd, width, height, n);

        while (needRegenerate(rooms)) {
            rooms = generateRooms(rd, width, height, n);
        }

        for (Room room : rooms) {
            re[room.x][room.y] = Tileset.FLOWER;
        }

        /*
         * Let the room grow.
         */
        int growLimit = rd.nextInt(n * 60, n * 80);
        for (int i = 0; i < growLimit; i++) {
            Room room = rooms.get(rd.nextInt(0, rooms.size()));
            room.grow(room.roomsWithoutThis(rooms), rd.nextInt(0, 4));
        }

        /*
         * Generate Hallway.
         */
        List<Hallway> hallways = new LinkedList<>();
        int hallwayLimit = rd.nextInt(n * 2, n * 4);
        for (int i = 0; i < hallwayLimit; i++) {
            Room room = rooms.get(rd.nextInt(0, rooms.size()));
            hallways.add(room.getHorizontalHallway(rooms, rd));
        }

        for (int i = 0; i < hallwayLimit; i++) {
            Room room = rooms.get(rd.nextInt(0, rooms.size()));
            hallways.add(room.getVerticalHallway(rooms, rd));
        }

        /*
         * while !isFullyConnected(List<Room>, List<Hallway>) {
         *   Generate Hallway}.
         */

        /*
         * Add the WALL tile into the Tile Matrix.
         */
        for (Room room : rooms) {
            int lengthHorizontal = room.eastWall() - room.westWall() + 1;
            int lengthVertical = room.northWall() - room.southWall() + 1;
            for (int i = 0; i < lengthHorizontal; i++) {
                re[room.westWall() + i][room.southWall()] = Tileset.WALL;
                re[room.westWall() + i][room.northWall()] = Tileset.WALL;
            }
            for (int i = 0; i < lengthVertical; i++) {
                re[room.westWall()][room.southWall() + i] = Tileset.WALL;
                re[room.eastWall()][room.southWall() + i] = Tileset.WALL;
            }
        }

        /*
         * Add the room Inside.
         */

        /*
         * Add the Hallway. 1. Add WALL Tile. 2. Add INSIDE Tile. 
         * This will connect with/without hallway WALL elements.
         */

        return re;
    }

    /*
     * check all rooms can grow all not.
     */
    private boolean allGrow(List<Room> rooms) {
        boolean status = false;
        for (Room room : rooms) {
            if (room.canGrow(room.roomsWithoutThis(rooms))) {
                status = true;
            }
        }
        return status;
    }

    /*
     * check all rooms x,y coordinate relation and see if need regenerate.
     */
    private boolean needRegenerate(List<Room> rooms) {
        boolean status = false;
        for (Room room : rooms) {
            for (Room room2 : room.roomsWithoutThis(rooms)) {
                if ((room.x - room2.x) * (room.x - room2.x) + (room.y - room2.y) * (room.y - room2.y) <= 8) {
                    status = true;
                }
            }
        }
        return status;
    }

    /*
     * Generate the rooms.
     */
    private List<Room> generateRooms(Random rd, int width, int height, int n) {
        /*
         * Generate the room origins.
         */
        List<Room> rooms = new LinkedList<>();
        for (int q = 0; q < 4; q++) {
            int xMin = 0, xMax = 0, yMin = 0, yMax = 0;
            switch (q) {
                case 0:
                    xMin = width / 2;
                    xMax = width - 2;
                    yMin = height / 2;
                    yMax = height - 2;
                    break;
                case 1:
                    xMin = 1;
                    xMax = width / 2 - 1;
                    yMin = height / 2;
                    yMax = height - 2;
                    break;
                case 2:
                    xMin = 1;
                    xMax = width / 2 - 1;
                    yMin = 1;
                    yMax = height / 2 - 1;
                    break;
                case 3:
                    xMin = width / 2;
                    xMax = width - 2;
                    yMin = 1;
                    yMax = height / 2 - 1;
                    break;
            }
            for (int i = 0; i < n; i++) {
                int x = rd.nextInt(xMin, xMax);
                int y = rd.nextInt(yMin, yMax);
                rooms.add(new Room(x, y));
                // re[x][y] = Tileset.FLOWER;
                System.out.println(x + ", " + y);
            }
        }
        return rooms;
    }
}
