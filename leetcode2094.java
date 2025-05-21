class Solution {
    public int[] findEvenNumbers(int[] digits) {
        // Count the occurrences of each digit
        int[] digitCount = countDigitOccurrences(digits);
        List<Integer> evenNumbers = new ArrayList<>();
      
        // Iterate over even 3-digit numbers
        for (int num = 100; num < 1000; num += 2) {
            // Get the counts for the current number
            int[] currentDigitCount = getDigitCount(num);
            // Check if the number can be made from given digits
            if (canMakeNumber(digitCount, currentDigitCount)) {
                // Add number to the results list
                evenNumbers.add(num);
            }
        }
      
        // Convert List<Integer> to int[]
        return evenNumbers.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Checks if a number's digits can be constructed from the available digit counts.
     */
    private boolean canMakeNumber(int[] availableCount, int[] requiredCount) {
        for (int i = 0; i < 10; i++) {
            // If the required count for a digit exceeds the available count, return false
            if (availableCount[i] < requiredCount[i]) {
                return false;
            }
        }
        // If all required digits are available, return true
        return true;
    }

    /**
     * Count the occurrences of each digit in the given array.
     */
    private int[] countDigitOccurrences(int[] nums) {
        int[] counter = new int[10];
        for (int num : nums) {
            counter[num]++;
        }
        return counter;
    }

    /**
     * Returns an array representing the count of each digit in the given number.
     */
    private int[] getDigitCount(int num) {
        int[] count = new int[10];
        // Split the number into its digits and count each occurrence
        for (; num > 0; num /= 10) {
            int digit = num % 10;
            count[digit]++;
        }
        return count;
    }
}
