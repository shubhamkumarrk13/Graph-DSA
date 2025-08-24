//https://leetcode.com/problems/maximal-network-rank/

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {


        Map<Integer , Set<Integer>> map = new HashMap<>();

        for(int[] edge : roads) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new HashSet<>()).add(v);

            map.computeIfAbsent(v , k -> new HashSet<>()).add(u);
        }

        int maxRank = Integer.MIN_VALUE;

        for(int i=0 ; i<n ; i++) {

            for(int j=i+1 ; j<n ;j++) {

                int rankA = map.containsKey(i) ? map.get(i).size() : 0;

                int rankB = map.containsKey(j) ? map.get(j).size() : 0;

                int total = rankA + rankB ;

                if(map.containsKey(i) && map.get(i).contains(j)) {

                    total--;    
                }
                maxRank = Math.max(maxRank , total);
            }
        }

        return maxRank;

    }
}
