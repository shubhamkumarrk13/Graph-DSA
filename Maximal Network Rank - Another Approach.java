//Problem Link : https://leetcode.com/problems/maximal-network-rank/

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {

        int[] connections = new int[n]; //  To store the number of links for each node

        boolean[][] connected = new boolean[n][n]; // To store if two nodes are connected

        for(int[] edge : roads) {

            int u = edge[0];

            int v = edge[1];

            connections[u]++;

            connections[v]++;

            connected[u][v] = true;

            connected[v][u] = true;
        }

        int maxRank = 0;

        for(int i=0 ; i<n ; i++) {

            for(int j=i+1 ; j<n ; j++) {

                int rankA = connections[i];

                int rankB = connections[j];

                int total = rankA + rankB;

                if(connected[i][j] == true) total--; // If two nodes are connected , the link between the two must have been calculated twice so subtract 1 from the total

                maxRank = Math.max(maxRank , total);
            }
        }

        return maxRank;

    }
}
