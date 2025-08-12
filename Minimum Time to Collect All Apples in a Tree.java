// Problem Link : https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

//********************** USING DFS***************** */

class Solution {

    public int DFS(int curr , int parent , List<Boolean> hasApple , Map<Integer , List<Integer>> map) {

        int time = 0;

        for(int child : map.getOrDefault(curr , new ArrayList<>())) {

            if(child == parent) continue;

            int timeFromChild = DFS(child , curr , hasApple , map);

            if(timeFromChild > 0 || hasApple.get(child) == true) {

                time = time + timeFromChild + 2;
            }
        }

        return time;
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        
        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        return DFS(0 , -1 , hasApple , map);
    }
}
