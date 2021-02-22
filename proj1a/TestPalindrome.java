import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();
    static OffByOne obo = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual = actual + d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        assertFalse(palindrome.isPalindrome("hello"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("Nobody"));
        assertTrue(palindrome.isPalindrome("H"));
    }

    @Test
    public void testIsPalindromeTwo()
    {
        assertFalse(palindrome.isPalindrome("hello",obo));
        assertTrue(palindrome.isPalindrome("ab",obo));
        assertTrue(palindrome.isPalindrome("flake",obo));
    }

}
