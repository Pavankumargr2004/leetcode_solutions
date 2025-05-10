import java.util.*;

class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        
        // Create a priority queue for Dijkstra's algorithm
        // Format: [time, row, col]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0, 0}); // Start at (0,0) at time 0
        
        // Keep track of the earliest arrival time for each cell
        int[][] earliestArrival = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(earliestArrival[i], Integer.MAX_VALUE);
        }
        earliestArrival[0][0] = 0;
        
        // Define the four possible directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int row = current[1];
            int col = current[2];
            
            // If we've reached the destination, return the time
            if (row == n - 1 && col == m - 1) {
                return time;
            }
            
            // If we've already found a better path to this cell, skip it
            if (time > earliestArrival[row][col]) {
                continue;
            }
            
            // Try all four directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                // Check if the new position is valid
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    // Calculate the new time:
                    // We can only move to the next room when either:
                    // 1. We arrive after its moveTime
                    // 2. We wait until its moveTime and then move
                    int nextTime = Math.max(time + 1, moveTime[newRow][newCol] + 1);
                    
                    // If this is a better path, update and add to priority queue
                    if (nextTime < earliestArrival[newRow][newCol]) {
                        earliestArrival[newRow][newCol] = nextTime;
                        pq.offer(new int[]{nextTime, newRow, newCol});
                    }
                }
            }
        }
        
        // If we can't reach the destination
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        int[][][] testCases = {
            {{0, 4}, {4, 4}},          // Expected: 6
            {{0, 0, 0}, {0, 0, 0}},    // Expected: 3
            {{0, 1}, {1, 2}}           // Expected: 3
        };
        
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.minTimeToReach(testCases[i]);
            System.out.println("Test case " + (i + 1) + ": " + result);
        }
    }
}