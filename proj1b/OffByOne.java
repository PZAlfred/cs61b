public class OffByOne implements CharacterComparator {
    /**
     * Returns true if the two chars are off-by-one.
     */
    public boolean equalChars(char x, char y) {
        int d = x - y;
        if (d == 1 || d == -1) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if given word is off-by-one Palindrome.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> D = new LinkedListDeque<>();
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
