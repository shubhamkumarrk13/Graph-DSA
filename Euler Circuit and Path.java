
// Problem Link : https://www.geeksforgeeks.org/problems/euler-circuit-and-path/1

class Solution {
    
    public void DFS(int u , boolean[] visited , List<Integer>[] adj) {
        
        visited[u] = true;
        
        for(int v : adj[u]) {
            
            if(visited[v] == false) DFS(v , visited , adj);
        }
    }
    
    public boolean isConnected(int V , List<Integer>[] adj) { // find node with degree != 0 but not connected
        
        // find element with degree != 0;
        
        int node = -1;
        
        for(int i=0 ; i<V ; i++) {
            
            if(adj[i].size() != 0) {
                
                node = i;
                
                break;
            }
        }
        
        boolean[] visited = new boolean[V];
        
        // if all nodes with degree != 0 are connected , then they all must be visited by single DFS call
        
        DFS(node , visited , adj);
        
        for(int i=0 ; i<V ; i++) {
            
            if(adj[i].size() != 0 && visited[i] == false) return false; // node with degree != 0 but not visited , which implies it is of another component
        }
        
        return true;
        
    }
    
    public int isEulerCircuit(int V, List<Integer>[] adj) {
        
        if(isConnected(V , adj) == false) return 0;
        
        // find oddDegree count;
        
        int oddDegree = 0;
        
        for(int i=0 ; i<V ; i++) {
            
            if(adj[i].size() % 2 == 1) oddDegree++;
        }
        
        if(oddDegree == 0) return 2;
        
        else if(oddDegree == 2) return 1;
        
        return 0;
        
        // oddDegree --> 0 --> Euler circuit
        // oddDegree --> 2 --> Euler Path
        // oddDegree --> !(0 , 2) --> Non - Euler
        
    }
}
