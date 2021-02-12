import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('x', 'x'));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('z','a'));
        assertFalse(offByOne.equalChars('a','g'));
        assertTrue(offByOne.equalChars('c', 'd'));
        assertFalse(offByOne.equalChars('a','B'));
        assertTrue(offByOne.equalChars('$','%'));
        assertFalse(offByOne.equalChars('A','b'));
        assertTrue(offByOne.equalChars('1','2'));
        assertFalse(offByOne.equalChars('1','3'));
    }

}
