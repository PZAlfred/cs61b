package byog.Core;

import java.util.Random;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class RandomRoomGenerator {
    private int minRoomNum;
    private int maxRoomNum;
    private int roomNum;

    public TETile[][] generateRandomRooms(int width, int height, int seed) {
        minRoomNum = width * height / 120;
        maxRoomNum = width * height / 60;
        Random rd = new Random(seed);
        TETile[][] re = new TETile[width][height];

        roomNum = rd.nextInt(minRoomNum, maxRoomNum);
        roomNum = (roomNum / 4) * 4; // round to integer times of 4.

        Room[] rooms = new Room[roomNum];

        int n = roomNum / 4;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                re[i][j] = Tileset.NOTHING;
            }
        }

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
                int ind = q * 4 + i;
                rooms[ind] = new Room(x, y);
                re[x][y] = Tileset.FLOWER;
                System.out.println(x + ", " + y);
            }
        }

        return re;
    }

}
