public class Palindrome {

    /**
     * Returns a Deque where the characters appear in the same order as in the
     * String.
     */
    public static Deque<Character> wordToDeque(String word) {
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




    




}
