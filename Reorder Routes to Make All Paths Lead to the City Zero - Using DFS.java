
// Problem Link : https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/

// USING DFS

class Pair {

    int node;

    int sign;

    public Pair(int node , int sign) {

        this.node = node;

        this.sign = sign;
    }
}

class Solution {

    int res = 0;

    public void DFS(int node , Set<Integer> visited , Map<Integer , List<Pair>> map) {

        visited.add(node);

        for(Pair pair : map.getOrDefault(node , new ArrayList<>())) {

            int v = pair.node;

            int sign = pair.sign;

            if(!visited.contains(v)) {

                if(sign == 1) res++;

                DFS(v , visited , map);
            }
        }
    }

    public int minReorder(int n, int[][] connections) {

        // sign : 1 --> given direction 
        // sign : 0 --> direction we made

        Map<Integer , List<Pair>> map = new HashMap<>();

        for(int[] edge : connections) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(new Pair(v , 1));

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(new Pair(u , 0));
        }

        Set<Integer> visited = new HashSet<>();

        DFS(0 , visited , map);

        return res;
        
    }
}
