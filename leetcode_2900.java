import java.util.ArrayList;
import java.util.List;

public class Solution {

    // The method expected by the driver
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < words.length; ++i) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                result.add(words[i]);
            }
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Sample input
        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        int[] groups = {1, 1, 2, 2, 3};

        // Call the method
        List<String> result = sol.getLongestSubsequence(words, groups);

        // Print the result
        System.out.println("Longest subsequence of words:");
        for (String word : result) {
            System.out.println(word);
        }
    }
}
