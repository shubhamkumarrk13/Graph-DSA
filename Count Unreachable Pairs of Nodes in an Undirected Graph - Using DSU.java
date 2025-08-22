// Problem Link : https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/

// *********************************** USING DSU ***************************************

class Solution {

    int[] parent;

    int[] rank;

    public int find(int x) {

        if(x == parent[x]) return x;

        parent[x] = find(parent[x]);

        return parent[x];
    }

    public void union(int x , int y) {

        int x_parent = find(x);

        int y_parent = find(y);

        if(x_parent == y_parent) return;

        if(rank[x_parent] > rank[y_parent]) parent[y_parent] = x_parent;

        else if(rank[x_parent] < rank[y_parent]) parent[x_parent] = y_parent;

        else {

            parent[x_parent] = y_parent;

            rank[y_parent]++;
        }

    }

    public long countPairs(int n, int[][] edges) {

        parent = new int[n];

        rank = new int[n];

        for(int i=0 ; i<n ; i++) {

            parent[i] = i;

            rank[i] = 0;
        }

        for(int[] arr : edges) {

            int x = arr[0];

            int y = arr[1];

            union(x , y);
        }

        Map<Integer , Integer> map = new HashMap<>();

        for(int i=0 ; i<n ; i++) {

            int papaji = find(i);

            map.put(papaji , map.getOrDefault(papaji , 0) + 1);
        }

        long result = 0;

        int rem = n;

        for(Map.Entry<Integer , Integer> entry : map.entrySet()) {

            int size = entry.getValue();

            result = result + ((long)size * (rem - size));

            rem = rem - size;
        }

        return result;
        
    }
}
