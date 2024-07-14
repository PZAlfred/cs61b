package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(30, 25, 1, world, Tileset.SAND);
        ter.renderFrame(world);
    }

    private static int numOfRow(int side, int i) {
        return side + 2 * i;
    }

    private static void addLine(int x, int y, int lineLength, TETile[][] world, TETile t) {
        for (int i = 0; i < lineLength; i++) {
            world[x+i][y] = t;
        }
    }

    private static void addUpHalf(int x, int y, int s, TETile[][] world, TETile t) {
        for (int i = 0; i < s; i++) {
            int lineLength = numOfRow(s, i);
            addLine(x - i, y - i, lineLength, world, t);
        }
    }

    private static void addDownHalf(int x, int y, int s, TETile[][] world, TETile t) {
        for (int i = 0; i < s; i++) {
            int lineLength = numOfRow(s, s - 1 - i);
            addLine(x + i, y - i, lineLength, world, t);
        }
    }

    private static void addHexagon(int x, int y, int s, TETile[][] world, TETile t) {
        addUpHalf(x, y, s, world, t);
        addDownHalf(x - s + 1, y - s, s, world, t);
    }

}
