import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars(){
        assertTrue(offByOne.equalChars('a','b'));
        assertFalse(offByOne.equalChars('x','x'));
        assertFalse(offByOne.equalChars('A','a'));
        assertTrue(offByOne.equalChars('c','d'));

    }

}
