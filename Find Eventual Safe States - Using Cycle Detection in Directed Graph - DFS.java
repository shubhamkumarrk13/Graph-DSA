// Problem Link : https://leetcode.com/problems/find-eventual-safe-states/

// ********************************* USING DFS - Cycle Detection in Directed Graph ***********************

class Solution {

    public boolean check(int u , boolean[] visited , boolean[] inRecursion , Map<Integer , List<Integer>> map) {

        visited[u] = true;

        inRecursion[u] = true;

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(visited[v] == false) {

                if(check(v , visited , inRecursion , map)) return true;
            }

            else if(inRecursion[v] == true) return true;

        }

        inRecursion[u] = false;

        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int i=0 ; i<n ; i++) {

            for(int j : graph[i]) {

                map.computeIfAbsent(i , k -> new ArrayList<>()).add(j);
            }
        }
        
        boolean[] visited = new boolean[n];

        boolean[] inRecursion = new boolean[n];

        for(int i=0 ; i<n ; i++) {

            if(visited[i] == false) {

                check(i , visited , inRecursion , map);
            }
        }

        List<Integer> result = new ArrayList<>();

        for(int i=0 ; i<n ; i++) {

            if(inRecursion[i] == false) result.add(i);
        }

        return result;
    }
}
