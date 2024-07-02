import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(false, offByOne.equalChars('a', 'c'));
        assertEquals(true, offByOne.equalChars('c', 'b'));
        assertEquals(true, offByOne.equalChars('C', 'b'));
        assertEquals(true, offByOne.equalChars('C', 'B'));
    }
}
