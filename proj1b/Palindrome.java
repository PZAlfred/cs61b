public class Palindrome {

    /**
     * Returns a Deque where the characters appear in the same order as in the
     * String.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> D = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            D.addLast(word.charAt(i));
        }
        return D;
    }

    /**
     * Returns true if given word is a palindrome.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> D = wordToDeque(word);
        return helpPalindromeRecursive(D);
    }

    /**
     * Help method for isPalindrome using recursion.
     */
    private boolean helpPalindromeRecursive(Deque<Character> D) {
        if (D.size() <= 1) {
            return true;
        } else {
            Character first = D.removeFirst();
            Character last = D.removeLast();
            if (first.equals(last)) {
                return helpPalindromeRecursive(D);
            } else {
                return false;
            }
        }

    }

    /**
     * The third public method overload isPalindrome.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> D = wordToDeque(word);
        return helpPalindromeRecursiveCC(D, cc);
    }

    /**
     * Help method for overload isPalindrome.
     */
    private boolean helpPalindromeRecursiveCC(Deque<Character> D, CharacterComparator cc) {
        if (D.size() <= 1) {
            return true;
        } else {
            Character first = D.removeFirst();
            Character last = D.removeLast();
            // first = Character.toLowerCase(first);
            // last = Character.toLowerCase(last);
            if (cc.equalChars(first, last)) {
                return helpPalindromeRecursiveCC(D, cc);
            } else {
                return false;
            }
        }
    }

}
