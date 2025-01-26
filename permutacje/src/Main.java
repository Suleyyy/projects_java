import java.util.*;

public class Main {
    // recursively adds anagrams to the set
    private static void permute(String word, String prefix, Set<String> anagrams) {
        if (word.isEmpty()) {
            anagrams.add(prefix);
        } else {
            for (int i = 0; i < word.length(); i++) {
                String remaining = word.substring(0, i) + word.substring(i + 1);
                permute(remaining, prefix + word.charAt(i), anagrams);
            }
        }
    }

    public static void main(String[] args) {
        String example = "abca";
        Set<String> anagrams = new HashSet<>();
        permute(example, "", anagrams);
        System.out.println(anagrams);
    }
}