// Problem Link : https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
// ************************** USING DSU **********************

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

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {

        parent = new int[n];

        rank = new int[n];

        for(int i=0 ; i<n ; i++) {

            parent[i] = i;

            rank[i] = 0;
        }

        Arrays.sort(edgeList , (a , b) -> Integer.compare(a[2] , b[2]));

        int[][] q = new int[queries.length][4]; // q = [u , v , Threshold , index]

        for(int i=0 ; i<queries.length ; i++) {

            q[i][0] = queries[i][0];

            q[i][1] = queries[i][1];

            q[i][2] = queries[i][2];

            q[i][3] = i;
        }

        Arrays.sort(q , (a , b) -> Integer.compare(a[2] , b[2]));

        boolean[] result = new boolean[q.length];

        int j=0; // for traversing in edgeList

        for(int[] arr : q) {

            int u = arr[0];

            int v = arr[1];

            int threshold = arr[2];

            int ind = arr[3]; // Original index of query

            while(j < edgeList.length && edgeList[j][2] < threshold) {

                union(edgeList[j][0] , edgeList[j][1]);

                j++;
            }

            if(find(u) == find(v)) result[ind] = true;

            else result[ind] = false;
        }


        return result;
    }
}
