// Problem Link : https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1

 // ********************************* USING DFS **********************************

class Solution {
    
    public boolean check(int u , Set<Integer> visited , Map<Integer , List<Integer>> map , int parent) {
        
        visited.add(u);
        
        for(int v : map.get(u)) {
            
            if(v == parent) continue;
            
            if(visited.contains(v)) return true;
            
            if(check(v , visited , map , u)) return true;
        }
        
        return false;
    }
    
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        Map<Integer , List<Integer>> map = new HashMap<>();
        
        for(int i=0 ; i<edges.length ; i++) {
            
            int u = edges[i][0];
            
            int v = edges[i][1];
            
            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);
            
            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }
        
        Set<Integer> visited = new HashSet<>();
        
        for(Map.Entry<Integer , List<Integer>> entry : map.entrySet()) {
            
            int u = entry.getKey();
            
            if(!visited.contains(u) && check(u , visited , map , -1)) return true;
        }
        
        return false;
    }
}
