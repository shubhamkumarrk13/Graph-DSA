// Problem Link : https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/

// *********************** USING BFS ****************

class Pair {

    int node;

    int dist;

    public Pair(int node , int dist) {

        this.node = node;

        this.dist = dist;
    }
}
class Solution {

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

        Queue<Integer> q = new LinkedList<>();

        q.offer(1);

        visited.add(1);

        int result = Integer.MAX_VALUE;

        while(!q.isEmpty()) {

            int curr = q.poll();

            for(Pair pair : map.getOrDefault(curr , new ArrayList<>())) {

                int v = pair.node;

                int d = pair.dist;

                result = Math.min(result , d);

                if(!visited.contains(v)) {

                    q.offer(v);

                    visited.add(v);
                }
            }
        }

        return result;
    }
}
