import java.util.*;

class Solution {
    public int minimumTime(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // dp[i][j][k] represents the minimum time to reach cell (i,j) 
        // with next move cost k (0 for 1-second, 1 for 2-seconds)
        int[][][] dp = new int[n][m][2];
        
        // Initialize all states to infinity
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }
        
        // Start at (0,0) with time 0, next move costs 1 second
        dp[0][0][0] = 0;
        
        // Priority queue for Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 0, 0}); // time, row, col, nextMoveCost (0 for 1-second, 1 for 2-seconds)
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int r = curr[1];
            int c = curr[2];
            int nextMoveCost = curr[3];
            
            // If this is not the latest time for this state, skip
            if (time > dp[r][c][nextMoveCost]) continue;
            
            // Check all four directions
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    // Calculate move cost based on current state
                    int moveCost = (nextMoveCost == 0) ? 1 : 2;
                    
                    // We can only move to the next room when time >= moveTime[nr][nc]
                    // If we arrive early, we need to wait until moveTime[nr][nc]
                    int earliestStart = Math.max(time, moveTime[nr][nc]);
                    int arrivalTime = earliestStart + moveCost;
                    
                    // Next move will cost the opposite (1->2, 2->1)
                    int newNextMoveCost = 1 - nextMoveCost;
                    
                    // If we found a better time to reach this state
                    if (arrivalTime < dp[nr][nc][newNextMoveCost]) {
                        dp[nr][nc][newNextMoveCost] = arrivalTime;
                        pq.offer(new int[]{arrivalTime, nr, nc, newNextMoveCost});
                    }
                }
            }
        }
        
        // Return the minimum of both possible final states
        return Math.min(
            dp[n-1][m-1][0] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[n-1][m-1][0],
            dp[n-1][m-1][1] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[n-1][m-1][1]
        );
    }
    
    // Try other potential function names in case the expected function name differs
    public int minTimeToReach(int[][] moveTime) {
        return minimumTime(moveTime);
    }
}