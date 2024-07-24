import byog.Core.Game;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class testGame {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);
        Game game = new Game();
        TETile[][] tiles = game.playWithInputString("n123123S:Q");
        ter.renderFrame(tiles);
    }
}
