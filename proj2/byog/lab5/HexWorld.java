package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 29;
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
        int x_start = 3;
        int y_start = HEIGHT / 2 - 6 + 2;
        // addHexagon(x_start, y_start, 3, world, Tileset.TREE);
        // addHexagon(x_start+5, y_start-3, 3, world, Tileset.WATER);
        // addHexagon(x_start+10, y_start-6, 3, world, Tileset.SAND);
        // ter.renderFrame(world);
        int[] I = new int[] { 3, 4, 5, 4, 3 };
        for (int i = 0; i < 5; i++) {
            int x = x_start + i * 5;
            for (int j = 0; j < I[i]; j++) {
                int y = y_start - 6 + Math.abs(i-2) * 3 + j * 6;
                TETile t = randomTile();
                addHexagon(x, y, 3, world, t);
            }
        }
        ter.renderFrame(world);
    }

    private static int numOfRow(int side, int i) {
        return side + 2 * i;
    }

    private static void addLine(int x, int y, int lineLength, TETile[][] world, TETile t) {
        for (int i = 0; i < lineLength; i++) {
            world[x + i][y] = t;
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

    private static TETile randomTile() {
        int tileNum = StdRandom.uniform(7);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            case 3:
                return Tileset.WATER;
            case 4:
                return Tileset.SAND;
            case 5:
                return Tileset.MOUNTAIN;
            case 6:
                return Tileset.TREE;
            default:
                return Tileset.NOTHING;
        }
    }
}
