
// Problem Link : https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/

// *********************** USING DFS ****************

class Pair {

    int node;

    int dist;

    public Pair(int node , int dist) {

        this.node = node;

        this.dist = dist;
    }
}

class Solution {

    int result = Integer.MAX_VALUE;

    public void DFS(int source , int target , Set<Integer> visited , Map<Integer , List<Pair>> map) {

        visited.add(source);

        for(Pair pair : map.getOrDefault(source , new ArrayList<>())) {

            int v = pair.node;

            int d = pair.dist;

            result = Math.min(result , d); // Check before visitng since the node might be visited by other route

            if(!visited.contains(v)) {

                DFS(v , target , visited , map);
            }
        }
    }

    public int minScore(int n, int[][] roads) {

        Map<Integer , List<Pair>> map = new HashMap<>();

        for(int[] edge : roads) {

            int u = edge[0];

            int v = edge[1];

            int d = edge[2];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(new Pair(v , d));

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(new Pair(u , d));
        }

        Set<Integer> visited = new HashSet<>();

        DFS(1 , n-1, visited , map);

        return result;
        
    }
}
