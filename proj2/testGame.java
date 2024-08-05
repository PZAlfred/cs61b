import byog.Core.Game;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class TestGame {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);
        Game game = new Game();
        // TETile[][] tiles = game.playWithInputString("L123S:Q");
        TETile[][] tiles = game.playWithInputString("L:Q");
        ter.renderFrame(tiles);
    }
}
