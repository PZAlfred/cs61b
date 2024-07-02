import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(3);

    // Your tests go here.
    // Uncomment this class once you've created your CharacterComparator interface
    // and OffByOne class.
    @Test
    public void testEqualChars() {
        assertEquals(true, offByN.equalChars('a', 'd'));
    }

    @Test
    public void testIsPalindrome() {
        assertEquals(true, offByN.isPalindrome("abd"));
    }
}
