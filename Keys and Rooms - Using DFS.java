// Problem Link : https://leetcode.com/problems/keys-and-rooms/

//****************** USING DFS ******************** */


class Solution {

    public void dfs(int u , boolean[] visited , Map<Integer , List<Integer>> map) {

        visited[u] = true;

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(visited[v] == false) dfs(v , visited , map);
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        int n = rooms.size();

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int i=0 ; i<n ; i++) {

            map.put(i , rooms.get(i));
        }

        boolean[] visited = new boolean[n];

        dfs(0 , visited , map);

        for(int i=0 ; i<n ; i++) {

            if(visited[i] == false) return false;
        }

        return true;
        
    }
}
