// Problem Link : https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

// **************** USING DFS **********************

class Solution {

    int n;

    public void dfs(int u , boolean[] visited , int[][] stones) {

        visited[u] = true;

        for(int v=0 ; v<n ; v++) {

            int row = stones[u][0];

            int col = stones[u][1];

            if((visited[v] == false ) && (stones[v][0] == row || stones[v][1] == col)) dfs(v , visited , stones);
        }
    }

    public int removeStones(int[][] stones) {

        n = stones.length;

        boolean[] visited = new boolean[n]; // To store the index of the visited element

        Arrays.fill(visited , false);

        int groups = 0;

        for(int i=0 ; i<n ; i++) {

            if(visited[i] == false) {

                dfs(i , visited , stones);

                groups++;
            }
        }  

        return n - groups; 
    }
}
