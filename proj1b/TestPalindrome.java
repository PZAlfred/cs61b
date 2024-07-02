import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class. 

    @Test
    public void testIsPalindrome() {
        assertEquals(false, palindrome.isPalindrome("word"));
        assertEquals(true, palindrome.isPalindrome("radar"));
        assertEquals(true, palindrome.isPalindrome("noon"));
        assertEquals(true, palindrome.isPalindrome("a"));
        assertEquals(true, palindrome.isPalindrome(""));
        assertEquals(false, palindrome.isPalindrome("cat")); 

        CharacterComparator cc = new OffByOne();
        assertEquals(true, palindrome.isPalindrome("flake", cc));

        CharacterComparator cc1 = new OffByN(3);
        assertEquals(true, palindrome.isPalindrome("aed", cc1));
    }
}
