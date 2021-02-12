public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int x) {
        N = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Character.toLowerCase(x) - N == Character.toLowerCase(y)) {
            return true;
        }
        if (Character.toLowerCase(x) + N == Character.toLowerCase(y)) {
            return true;
        }
        else {
            return false;
        }
    }
}
