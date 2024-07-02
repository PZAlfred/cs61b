public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    /**
     * Returns true if the two chars are off-by-one.
     */
    @Override
    public boolean equalChars(char x, char y) {
        int d = x - y;
        if (d == N || d == -N) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if given word is off-by-one Palindrome.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> D = Palindrome.wordToDeque(word);
        return newHelpPalindrome(D);
    }

    /**
     * Help method for new isPalindrome.
     */
    public boolean newHelpPalindrome(Deque<Character> D) {
        if (D.size() <= 1) {
            return true;
        } else {
            Character first = D.removeFirst();
            Character last = D.removeLast();
            if (equalChars(first, last)) {
                return newHelpPalindrome(D);
            } else {
                return false;
            }
        }
    }

}
