
// Problem Link : https://leetcode.com/problems/possible-bipartition/

// ***************** USING BFS **************************


class Solution {

    public boolean bfsCheckBipartite(int u , int[] colors , Map<Integer , List<Integer>> map) {

        Queue<Integer> q = new LinkedList<>();

        q.offer(u);

        colors[u] = 1; // assign 1 as color of 1 so the neighbours should have color 0

        while(!q.isEmpty()) {

            int curr = q.poll();

            for(int v : map.getOrDefault(curr , new ArrayList<>())) {

                if(colors[curr] == colors[v]) return false;

                if(colors[v] == -1) {

                    q.offer(v);

                    colors[v] = 1 - colors[curr];
                }
            }
        }

        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] edge : dislikes) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }
        
        int[] colors = new int[n+1]; // acts as visited array and should contains two values, 0 -> red and 1 -> green

        Arrays.fill(colors , -1);

        for(int i=1 ; i<=n ; i++) {

            if(colors[i] == -1) {

                if(bfsCheckBipartite(i , colors , map) == false) return false;
            }
        }

        return true;
    }
}
