// Problem Link : https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/

// *********************************** USING DFS ***************************************

class Solution {

    public int dfs(int u , Set<Integer> visited , Map<Integer , List<Integer>> map) {

        visited.add(u);

        int size = 1;

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(!visited.contains(v)) size = size + dfs(v , visited , map);
        }

        return size;
    }

    public long countPairs(int n, int[][] edges) {

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        long result = 0;

        int total = n;

        Set<Integer> visited = new HashSet<>();

        for(int u=0 ; u<n ; u++) {

            if(!visited.contains(u)) {

                int size = dfs(u , visited , map);

                result = result + ((long)size * (total - size));

                total = total - size;
            }
        }

        return result;
        
    }
}
