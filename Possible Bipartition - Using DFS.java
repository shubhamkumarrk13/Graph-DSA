// Problem Link : https://leetcode.com/problems/possible-bipartition/

// ***************** USING DFS **************************


class Solution {

    public boolean DFS(int u , int[] color , int presentColor , Map<Integer , List<Integer>> map) {

        color[u] = presentColor;

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(color[v] == color[u]) return false;

            if(color[v] == -1) {

                int nextColor = 1 - presentColor;

                if(DFS(v , color , nextColor , map) == false) return false;
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

        int[] color = new int[n + 1];

        Arrays.fill(color , -1);

        for(int i=1 ; i<=n ; i++) {

            if(color[i] == -1) {

                int presentColor = 1;

                if(DFS(i , color , presentColor , map) == false) return false;
            }
        }

        return true;
    }
}
