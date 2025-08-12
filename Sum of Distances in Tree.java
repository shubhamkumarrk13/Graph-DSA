
// Problem Link : https://leetcode.com/problems/sum-of-distances-in-tree/

class Solution {

    int rootResult = 0;

    int[] count;

    int N;


    // Function for calculation the result of root and to fill the count array

    public int fillCount(int node , Map<Integer , List<Integer>> map , int prevNode , int currDepth) {

        int totalCount = 1;

        rootResult = rootResult + currDepth;

        for(int v : map.getOrDefault(node , new ArrayList<>())) {

            if(v == prevNode) continue;

            totalCount = totalCount + fillCount(v , map , node , currDepth + 1);
        }

        count[node] = totalCount;

        return totalCount;
    }

    // Function for filling the result array

    public void DFS(int node , Map<Integer , List<Integer>> map , int prevNode , int[] result) {

        for(int child : map.getOrDefault(node , new ArrayList<>())) {

            if(child == prevNode) continue;

            result[child] = result[node] - count[child] + (N - count[child]);

            DFS(child , map , node , result);
        }
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {

        N = n;

        count = new int[n];

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        fillCount(0 , map , -1 , 0);

        int[] result = new int[n];

        result[0] = rootResult;

        DFS(0 , map , -1 , result);

        return result;
        
    }
}
