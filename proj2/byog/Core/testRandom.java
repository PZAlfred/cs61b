package byog.Core;
import java.util.Random;

public class testRandom {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Random rd = new Random(125);
            for (int j = 0; j < 2; j++) {
                // System.out.println(rd.nextInt());
                System.out.println(rd.nextDouble());
                System.out.println(rd.nextInt(10, 20));
            }
        }
    }
}
