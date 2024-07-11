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
        return Math.abs(x - y) == N;
    }

}
