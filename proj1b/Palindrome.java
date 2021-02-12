public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> characters = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            characters.addLast(word.charAt(i));
        }
        return characters;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> forward = wordToDeque(word);
        Deque<Character> backward = new LinkedListDeque<>();
        for (int i = 1; i < word.length() + 1; i++)
        {
            backward.addLast(word.charAt(word.length()-i));
        }

        for (int i = 0; i < word.length(); i++)
        {
            if (!forward.get(i).equals(backward.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        CharacterComparator obo = new OffByOne();
        Deque<Character> forward = wordToDeque(word);
        Deque<Character> backward = new LinkedListDeque<Character>();
        for (int i = 1; i < word.length() + 1; i++)
        {
            backward.addLast(word.charAt(word.length()-i));
        }
        for (int i = 0; i < word.length(); i++) {
            if (i == word.length() / 2) {
                continue;
            }
            if (!obo.equalChars(forward.get(i), backward.get(i))) {
                return false;
            }
        }
        return true;
    }
}

