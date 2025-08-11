
// Problem Link : https://leetcode.com/problems/find-if-path-exists-in-graph/

// *************** USING DFS *******************

class Solution {

    public void dfs(int u , boolean[] visited , Map<Integer , List<Integer>> map) {

        visited[u] = true;

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(visited[v] == false) dfs(v , visited , map);
        }
    } 

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        boolean[] visited = new boolean[n];

        dfs(source , visited , map);

        if(visited[destination] == true) return true;

        return false;
        
    }
}
