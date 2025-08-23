
// Problem Link : https://leetcode.com/problems/similar-string-groups/
// // ************************** USING DSU ************************

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

    public boolean isSimilar(String s1 , String s2) {

        int n = s1.length();

        int diff = 0;

        for(int i=0 ; i<n ; i++) {

            if(s1.charAt(i) != s2.charAt(i)) diff++;
        }

        if(diff == 2 || diff == 0) return true;

        return false;
    }

    public int numSimilarGroups(String[] strs) {

        int n = strs.length;

        parent = new int[n];

        rank = new int[n];

        for(int i=0 ; i<n ; i++) {

            parent[i] = i;

            rank[i] = 0;
        }

        int groups = n;

        for(int i=0 ; i<n ; i++) {

            for(int j=i+1 ; j<n ; j++) {

                if(isSimilar(strs[i] , strs[j])) {

                    if(find(i) != find(j)) {

                        union(i , j);

                        groups--;
                    }
                }
            }
        }


        return groups;
    }
}
