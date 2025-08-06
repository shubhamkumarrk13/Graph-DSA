// Problem Link : https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1

class Solution {
    // Function to return Breadth First Search Traversal of given graph.
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        Map<Integer , ArrayList<Integer>> map = new HashMap<>();
        
        for(int i=0 ; i<adj.size() ; i++) {
            
            map.put(i , adj.get(i));
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        Set<Integer> visited = new HashSet<>();
        
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(0);
        
        visited.add(0);
        
        result.add(0);
        
        while(!q.isEmpty()) {
            
            int u = q.poll();
            
            for(int v : map.get(u)) {
                
                if(!visited.contains(v)) {
                    
                    q.offer(v);
                    
                    visited.add(v);
                    
                    result.add(v);
                }
            }
        }
        
        return result;
    }
}
