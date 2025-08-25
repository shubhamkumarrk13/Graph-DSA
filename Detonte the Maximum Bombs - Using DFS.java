// Problem Link : https://leetcode.com/problems/detonate-the-maximum-bombs/

class Solution {

    public int dfs(int u , boolean[] visited , Map<Integer , List<Integer>> map) {

        visited[u] = true;

        int size = 1;

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(visited[v] == false) {

                size = size + dfs(v , visited , map);
            }
        }

        return size;
    }

    public int maximumDetonation(int[][] bombs) {

        int n = bombs.length;

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int i=0 ; i<n ; i++) {

            for(int j=0 ; j<n ; j++) {

                if(i == j) continue;

                int[] bomb1 = bombs[i];

                int[] bomb2 = bombs[j];

                int x1 = bomb1[0];

                int y1 = bomb1[1];

                int x2 = bomb2[0];

                int y2 = bomb2[1];

                double dist =  Math.sqrt((Math.pow(x2 - x1,2)) + (Math.pow(y2 - y1,2)));

                if( dist <= bomb1[2]) map.computeIfAbsent(i , k -> new ArrayList<>()).add(j);
            }
            
        }

        int maxi = 0;

        for(int i=0 ; i<n ; i++) {

            boolean[] visited = new boolean[n];

            int size = dfs(i , visited , map);

            maxi = Math.max(maxi , size);
        }

        return maxi;
    }
}
