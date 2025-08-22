// Problem Link : https://leetcode.com/problems/longest-cycle-in-a-graph/

// USING DFS - Cycle Detection in Directed Graph

class Solution {

    int result = 0;

    public void dfs(int u , boolean[] visited , int[] count , boolean[] inRecursion , int[] edges) {

        visited[u] = true;

        inRecursion[u] = true;

        int v = edges[u];

        if(v != -1) {

            if(visited[v] == true && inRecursion[v] == true) result = Math.max(result , count[u] - count[v] + 1);

            if(visited[v] == false) {

                count[v] = count[u] + 1;

                dfs(v , visited , count , inRecursion , edges);
            }
        }

        inRecursion[u] = false;
    }

    public int longestCycle(int[] edges) {

        int n = edges.length;

        boolean[] visited = new boolean[n];

        int[] count = new int[n];

        boolean[] inRecursion = new boolean[n];

        for(int i=0 ; i<n ; i++) {

            visited[i] = false;

            count[i] = 1;

            inRecursion[i] = false;
        }

        for(int i=0 ; i<n ; i++) {

            if(edges[i] == -1) continue;

            if(visited[i] == false) dfs(i , visited , count , inRecursion , edges);
        }

        if(result == 0) return -1;

        return result;
        
    }
}
