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

    public TETile[][] generateRandomRooms(int width, int height, int seed) {
        minRoomNum = width * height / 240;
        maxRoomNum = width * height / 120;
        Random rd = new Random(seed);
        TETile[][] re = new TETile[width][height];

        // roomNum = rd.nextInt(minRoomNum, maxRoomNum);
        roomNum = 16; 
        roomNum = (roomNum / 4) * 4; // round to integer times of 4.

        List<Room> rooms = new LinkedList<>();

        int n = roomNum / 4;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                re[i][j] = Tileset.NOTHING;
            }
        }

        /*
         * Generate the room origins.
         */
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
                re[x][y] = Tileset.FLOWER;
                System.out.println(x + ", " + y);
            }
        }

        /*
         * Let the room grow.
         */
        for (int i = 0; i < 5; i++) {
            for (Room room : rooms) {
                room.grow(room.roomsWithoutThis(rooms));
            }
        }

        /*
         * Add the room tile into the Tile Matrix.
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
}
