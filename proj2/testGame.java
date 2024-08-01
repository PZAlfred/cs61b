import byog.Core.Game;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class testGame {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);
        Game game = new Game();
        TETile[][] tiles = game.playWithInputString("n1S"); // 9223372036854775807
        ter.renderFrame(tiles);
    }
}
