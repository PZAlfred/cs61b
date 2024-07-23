package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * 
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;

        /*
         * Check input is Valid/Invalid.
         */
        boolean saveStatus = false;
        boolean pureNum = false;
        String numStr = "";
        input = input.toLowerCase();
        if (input.substring(input.length() - 2, input.length()).equals(":q")) {
            input = input.substring(0, input.length() - 2);
            saveStatus = true;
        }
        if (input.charAt(0) == 'n' && input.charAt(input.length() - 1) == 's') {
            numStr = input.substring(1, input.length() - 1);
            if (numStr.matches("[0-9]*")) {
                pureNum = true;
            }
        }

        /*
         * A valid input and process the matrix.
         */
        int SEED = 0;
        if (pureNum) {
            SEED = Integer.valueOf(numStr);
        } else {
            System.exit(SEED);
        }

        /*
         * Generate the rooms using SEED.
         */
        RandomRoomGenerator rrg = new RandomRoomGenerator();
        finalWorldFrame = rrg.generateRandomRooms(WIDTH, HEIGHT, SEED);

        if (saveStatus) {
            // save game
        }
        return finalWorldFrame;

    }
}
