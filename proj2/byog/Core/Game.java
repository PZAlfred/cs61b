package byog.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 50;
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

        TETile[][] finalWorldFrame = null;

        /*
         * Check input is Valid/Invalid.
         */
        boolean saveStatus = false;
        boolean startGame = false;
        boolean loadStatus = false;
        String numStr = "";
        input = input.toLowerCase();
        if (input.charAt(0) == 'l') {
            loadStatus = true;
            finalWorldFrame = loadGame();
        }
        if (!loadStatus && !(input.charAt(0) == 'n')) {
            System.exit(0);
        }
        input = input.substring(1, input.length());
        for (int i = 0; i < input.length() - 1; i++) {
            char ci = input.charAt(i);
            if (Character.isDigit(ci)) {
                numStr += ci;
                if (input.charAt(i + 1) == 's') {
                    startGame = true;
                }
            } else if (startGame) {
                if (input.substring(i, i + 2).equals(":q")) {
                    saveStatus = true;
                    break;
                }
            }
        }
        if (!loadStatus) {
            if (!startGame) {
                System.exit(0);
            }
            /*
             * check if nunStr is valid.
             */
            String maxValue = "9223372036854775807";
            if (numStr.length() > 19) {
                System.exit(0);
            } else if (numStr.length() == 19) {
                for (int i = 0; i < numStr.length(); i++) {
                    if ((int) numStr.charAt(i) > (int) maxValue.charAt(i)) {
                        System.exit(0);
                    }
                }
            }

            /*
             * A valid input and process the matrix.
             */
            long seed = Long.parseLong(numStr);

            /*
             * Generate the rooms using SEED.
             */
            RandomRoomGenerator rrg = new RandomRoomGenerator();
            finalWorldFrame = rrg.generateRandomRooms(WIDTH, HEIGHT, seed);

        }
        if (saveStatus) {
            // save game
            saveGame(finalWorldFrame);
        }
        return finalWorldFrame;

    }

    /*
    * save game.
    */
    private static void saveGame(TETile[][] finalWorldFrame) {
        File f = new File("./game.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(finalWorldFrame);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    /*
     * load game.
     */
    private static TETile[][] loadGame() {
        File f = new File("./game.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                TETile[][] loadGame = (TETile[][]) os.readObject();
                os.close();
                return loadGame;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        return new TETile[WIDTH][HEIGHT];
    }
}
