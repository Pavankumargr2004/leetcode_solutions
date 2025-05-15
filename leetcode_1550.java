class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int x : arr) {
            if (x % 2 == 1) {
                if (++count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}