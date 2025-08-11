// Problem Link : https://leetcode.com/problems/find-if-path-exists-in-graph/

// *************** USING BFS *******************

class Solution {

    public boolean validPath(int n, int[][] edges, int source, int destination) {

       Map<Integer , List<Integer>> map = new HashMap<>();
       
       for(int[] edge : edges) {

        int u = edge[0];

        int v = edge[1];

        map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

        map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);

       }

       boolean[] visited = new boolean[n];

       Queue<Integer> q = new LinkedList<>();

       q.offer(source);

       visited[source] = true;

       while(!q.isEmpty()) {

        int size = q.size();

        for(int i=0 ; i<size ; i++) {

            int curr = q.poll();

            for(int v : map.getOrDefault(curr , new ArrayList<>())) {

                if(visited[v] == false) {

                    q.offer(v);

                    visited[v] = true;
                }
            }
        }

       }

       if(visited[destination] == true) return true;

       return false;
    }
}
