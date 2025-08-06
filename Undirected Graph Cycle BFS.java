// Problem Link : https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
// ********************************* USING BFS **********************************
class Pair {
    
    int node;
    
    int parent;
    
    public Pair(int node, int parent) {
        
        this.node = node;
        
        this.parent = parent;
    }
}

class Solution {
    
    public boolean check(int u , Set<Integer> visited , Map<Integer , List<Integer>> map) {
        
        Queue<Pair> q = new LinkedList<>();
        
        q.offer(new Pair(u , -1));
        
        visited.add(u);
        
        while(!q.isEmpty()) {
            
            Pair pair = q.poll();
            
            int node = pair.node;
            
            int parent = pair.parent;
            
            for(int v : map.get(node)) {
                
                if(!visited.contains(v)) {
                    
                    visited.add(v);
                    
                    q.offer(new Pair(v , node));
                }
                
                else if(v == parent) continue;
                
                else return true;
            }
        }
        
        return false;
    }
    
    public boolean isCycle(int V, int[][] edges) {
        
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
            
            if(!visited.contains(u) && check(u , visited , map)) return true;
        }
        
        return false;
    }
}
