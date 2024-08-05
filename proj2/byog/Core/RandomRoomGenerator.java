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
        minRoomNum = 4;
        maxRoomNum = 25;
        Random rd = new Random(seed);
        TETile[][] re = new TETile[width][height];

        roomNum = RandomUtils.uniform(rd, minRoomNum, maxRoomNum);
        // roomNum = 8;
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

        /*
         * Let the room grow.
         */
        int growLimit = RandomUtils.uniform(rd, n * 40, n * 80);
        for (int i = 0; i < growLimit; i++) {
            Room room = rooms.get(RandomUtils.uniform(rd, 0, rooms.size()));
            room.grow(room.roomsWithoutThis(rooms), RandomUtils.uniform(rd, 0, 4));
        }

        /*
         * Generate Hallway.
         */
        List<Hallway> hallways = new LinkedList<>();

        int hallwayLimit = RandomUtils.uniform(rd, n * 100, n * 200);
        hallways = generateAllHallways(hallwayLimit, rd, rooms);

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

        for (Room room : rooms) {
            for (int x = room.westWall() + 1; x < room.eastWall(); x++) {
                for (int y = room.southWall() + 1; y < room.northWall(); y++) {
                    re[x][y] = Tileset.FLOOR;
                }
            }
        }

        /*
         * Add the Hallway.
         */
        re = addHallways(hallways, re);

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
                if ((room.x() - room2.x()) * (room.x() - room2.x()) + (room.y()
                - room2.y()) * (room.y() - room2.y()) <= 8) {
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
                default:
            }
            for (int i = 0; i < n; i++) {
                int x = RandomUtils.uniform(rd, xMin, xMax);
                int y = RandomUtils.uniform(rd, yMin, yMax);
                rooms.add(new Room(x, y));
                // re[x][y] = Tileset.FLOWER;
                // System.out.println(x + ", " + y);
            }
        }
        return rooms;
    }

    /*
     * Generate the hallway based on room1, room2, rd.
     */
    private Hallway generateHallway(Room room1, List<Room> rooms, 
    int directionN, Random rd) {
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        int xmin = 0, xmax = 0, ymin = 0, ymax = 0;
        Room room2 = new Room(0, 0);
        switch (directionN) {
            case 0:
                room2 = room1.findCloseNorthRoom(rooms);
                room1.northConnected = true;
                room2.southConnected = true;
                y1 = room1.northWall();
                y2 = room2.southWall();
                for (int x = room1.westWall() + 1; x <= room1.eastWall() - 1; x++) {
                    if (xmin == 0 && x > room2.westWall()) {
                        xmin = x;
                    }
                    if (x == room1.eastWall() - 1 || x == room2.eastWall() - 1) {
                        xmax = x;
                        x1 = RandomUtils.uniform(rd, xmin, xmax + 1);
                        x2 = x1;
                        break;
                    }
                }
                break;
            case 1:
                room2 = room1.findCloseSouthRoom(rooms);
                room1.southConnected = true;
                room2.northConnected = true;
                y1 = room2.northWall();
                y2 = room1.southWall();
                for (int x = room1.westWall() + 1; x <= room1.eastWall() - 1; x++) {
                    if (xmin == 0 && x > room2.westWall()) {
                        xmin = x;
                    }
                    if (x == room1.eastWall() - 1 || x == room2.eastWall() - 1) {
                        xmax = x;
                        x1 = RandomUtils.uniform(rd, xmin, xmax + 1);
                        x2 = x1;
                        break;
                    }
                }
                break;
            case 2:
                room2 = room1.findCloseWestRoom(rooms);
                room1.westConnected = true;
                room2.eastConnected = true;
                x1 = room2.eastWall();
                x2 = room1.westWall();
                for (int y = room1.southWall() + 1; y <= room1.northWall() - 1; y++) {
                    if (ymin == 0 && y > room2.southWall()) {
                        ymin = y;
                    }
                    if (y == room1.northWall() - 1 || y == room2.northWall() - 1) {
                        ymax = y;
                        y1 = RandomUtils.uniform(rd, ymin, ymax + 1);
                        y2 = y1;
                        break;
                    }
                }
                break;
            case 3:
                room2 = room1.findCloseEastRoom(rooms);
                room1.eastConnected = true;
                room2.westConnected = true;
                x1 = room1.eastWall();
                x2 = room2.westWall();
                for (int y = room1.southWall() + 1; y <= room1.northWall() - 1; y++) {
                    if (ymin == 0 && y > room2.southWall()) {
                        ymin = y;
                    }
                    if (y == room1.northWall() - 1 || y == room2.northWall() - 1) {
                        ymax = y;
                        y1 = RandomUtils.uniform(rd, ymin, ymax + 1);
                        y2 = y1;
                        break;
                    }
                }
                break;
            default:
        }

        Hallway hallway = new Hallway(x1, y1, x2, y2, room1, room2);
        // room1.hallwayConnected.add(hallway);
        // room2.hallwayConnected.add(hallway);
        return hallway;
    }

    /*
     * Generate ALL the hallways.
     */
    private List<Hallway> generateAllHallways(int hallwayLimit, Random rd, List<Room> rooms) {
        List<Hallway> hallways = new LinkedList<>();
        int ithHallway = 0;
        while (ithHallway < hallwayLimit) {
            int directionN = RandomUtils.uniform(rd, 0, 4);
            Room room = rooms.get(RandomUtils.uniform(rd, 0, rooms.size()));
            if (!room.canLink(rooms, directionN)) {
                ithHallway++;
                continue;
            }
            Hallway hallway = generateHallway(room, rooms, directionN, rd);
            hallways.add(hallway);
            ithHallway++;
        }
        return hallways;
    }


    /*
     * add the hallways into Tilematrix.
     */
    private TETile[][] addHallways(List<Hallway> hallways, TETile[][] re) {

        for (Hallway hallway : hallways) {
            if (hallway.isHorizontal()) {
                for (int x = hallway.startX(); x <= hallway.endX(); x++) {
                    re[x][hallway.endY() - 1] = Tileset.WALL;
                    re[x][hallway.endY() + 1] = Tileset.WALL;
                    re[x][hallway.endY()] = Tileset.FLOOR;
                }
            } else if (hallway.isVertital()) {
                for (int y = hallway.startY(); y <= hallway.endY(); y++) {
                    re[hallway.endX() - 1][y] = Tileset.WALL;
                    re[hallway.endX() + 1][y] = Tileset.WALL;
                    re[hallway.endX()][y] = Tileset.FLOOR;
                }
            }
        }
        return re;
    }
}
