public class OffByOne implements CharacterComparator {

    //returns true for characters that are off by exactly one
    @Override
    public boolean equalChars(char x, char y) {
        if (Character.toLowerCase(x) - 1 == Character.toLowerCase(y)) {
            return true;
        }
        else if (Character.toLowerCase(x) + 1 == Character.toLowerCase(y)) {
        return true;
        }
        else {
            return false;
        }
    }
}
