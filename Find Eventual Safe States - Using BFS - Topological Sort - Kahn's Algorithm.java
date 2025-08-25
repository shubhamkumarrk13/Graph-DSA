// Problem Link : https://leetcode.com/problems/find-eventual-safe-states/

// ********************************* USING BFS - Cycle Detection in Directed Graph - Kahn's Algorithm ***********************

class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        // We are reversing the map

        Map<Integer , List<Integer>> map = new HashMap<>();

        int[] indegree = new int[n]; // To store the indegree; 

        for(int i=0 ; i<n ; i++) {

            for(int j : graph[i]) {

                map.computeIfAbsent(j , k -> new ArrayList<>()).add(i);

                indegree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0 ; i<n ; i++) {

            if(indegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {

            int curr = q.poll();

            for(int v : map.getOrDefault(curr , new ArrayList<>())) {

                indegree[v]--;

                if(indegree[v] == 0) q.offer(v);
            }
        }

        List<Integer> result = new ArrayList<>();

        for(int i=0 ; i<n ; i++) {

            if(indegree[i] == 0) result.add(i);
        }

        return result;

    }
}
