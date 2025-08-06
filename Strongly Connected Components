// Steps involved :
// Fill the stack using topological sort using original map
// Reverse the map
// Perform DFS based on the stack elements

class Solution {
    // Function to find number of strongly connected components in the graph.
    
    Stack<Integer> st = new Stack<>();
    
    public void fillStack(int u , boolean[] visited , ArrayList<ArrayList<Integer>> adj) {
        
        visited[u] = true;
        
        for(int v : adj.get(u)) {
            
            if(visited[v] == false) fillStack(v , visited , adj);
        }
        
        st.push(u);
    }
    
    public void DFS(int u , boolean[] visited , Map<Integer , List<Integer>> revMap) {
        
        visited[u] = true;
        
        for(int v : revMap.getOrDefault(u , new ArrayList<>())) {
            
            if(visited[v] == false) DFS(v , visited , revMap);
        }
    }
    
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here
        
        // Fill the stack using Topological sort
        
        boolean[] visited = new boolean[adj.size()];
        
        Arrays.fill(visited , false);
        
        for(int u=0 ; u<adj.size() ; u++) {
            
            if(visited[u] == false) fillStack(u , visited , adj);
        }
        
        // Reverse the map
        
        Map<Integer , List<Integer>> revMap = new HashMap<>();
        
        for(int u=0 ; u<adj.size() ; u++) {
            
            for(int v : adj.get(u)) {
                
                revMap.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
            }
        }
        
        // Perform DFS on stack elements
        
        int cnt = 0;
        
        Arrays.fill(visited , false);
        
        while(!st.isEmpty()) {
            
            int u = st.pop();
            
            if(visited[u] == false) {
                
                DFS(u , visited , revMap);
                
                cnt++;
            }
        }
        
        return cnt;
    }
}
