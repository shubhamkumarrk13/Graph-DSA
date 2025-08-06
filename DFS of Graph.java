// Problem Link : https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1

class Solution {
    // Function to return a list containing the DFS traversal of the graph.
    
    public void dfs(int u , Set<Integer> visited , ArrayList<Integer> result , Map<Integer , ArrayList<Integer>> map) {
        
        if(visited.contains(u)) return;
        
        visited.add(u);
        
        result.add(u);
        
        for(int v : map.get(u)) {
            
            if(!visited.contains(v)) dfs(v , visited , result , map);
        }
    }
    
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        Map<Integer , ArrayList<Integer>> map = new HashMap<>();
        
        for(int i=0 ; i<adj.size() ; i++) {
            
            map.put(i , adj.get(i));
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        Set<Integer> visited = new HashSet<>();
        
        dfs(0 , visited , result , map);
        
        return result;
    }
}
