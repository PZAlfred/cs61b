public class OffByOne implements CharacterComparator {
    /**
     * Returns true if the two chars are off-by-one.
     */
    @Override
    public boolean equalChars(char x, char y) {
        int d = Math.abs(x - y);
        if (d == 1) {
            return true;
        }
        return false;
    }

}
