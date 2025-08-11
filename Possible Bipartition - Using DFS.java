// Problem Link : https://leetcode.com/problems/possible-bipartition/

// ***************** USING DFS **************************


class Solution {

    public boolean dfs(int u , int[] color , int colr , Map<Integer , List<Integer>> map) {

        color[u] = colr;

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(color[v] == color[u]) return false;

            if(color[v] == -1) {

                int nextColor = 1 - color[u];

                if(dfs(v , color , nextColor , map) == false) return false;
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

        int[] color = new int[n + 1]; // acts as visited array , -1 --> not visited , 0 --> red , 1 --> green

        Arrays.fill(color , -1);

        for(int i=1 ; i<=n ; i++) {

            if(color[i] == -1) {

                int colr = 1;

                if(dfs(i , color , colr , map) == false) return false;
            }
        }

        return true;
    }
}
