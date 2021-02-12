public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int x) {
        N = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - N == y) {
            return true;
        }
        if (x + N == y) {
            return true;
        }
        else {
            return false;
        }
    }
}
