public class OffByOne implements CharacterComparator {

    //returns true for characters that are off by exactly one
    @Override
    public boolean equalChars(char x, char y) {
        if (x - 1 == y) {
            return true;
        }
        else if (x + 1 == y) {
        return true;
        }
        else {
            return false;
        }
    }
}
