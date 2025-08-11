// Problem Link : https://leetcode.com/problems/keys-and-rooms/

//****************** USING BFS ******************** */


class Solution {
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        int n = rooms.size();

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int i=0 ; i<n ; i++) {

            map.put(i , rooms.get(i));
        }

        boolean[] visited = new boolean[n];

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);

        visited[0] = true;

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

        for(int i=0 ; i<n ; i++) {

            if(visited[i] == false) return false;
        }

        return true;
        
    }
}
