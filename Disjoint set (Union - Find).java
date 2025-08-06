// Problem Link : https://www.geeksforgeeks.org/problems/disjoint-set-union-find/1

class GfG {
    
    int find(int parent[], int x) {

        if(x == parent[x]) return x;
        
        parent[x] = find(parent , parent[x]);
        
        return parent[x];
    }

    void unionSet(int parent[], int x, int z) {

        int x_parent = find(parent , x);
        
        int z_parent = find(parent , z);
        
        if(x_parent == z_parent) return;
        
        else parent[x_parent] = z_parent;
              
    }
}
