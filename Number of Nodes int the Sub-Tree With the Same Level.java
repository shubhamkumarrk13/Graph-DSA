
// Problem Link : https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/

class Solution {

    public void DFS(int curr , int parent , String labels , int[] result , int[] count , Map<Integer , List<Integer>> map) {

        char ch = labels.charAt(curr);

        int countBeforeVisitingCurrAndItsChildren = count[ch - 'a'];

        count[ch - 'a']++; // adding the label of the current node to count array

        for(int child : map.getOrDefault(curr , new ArrayList<>())) {

            if(child == parent) continue;

            DFS(child , curr , labels , result , count , map);
        }

        int countAfterVisitingCurrAndItsChildren = count[ch - 'a'];

        result[curr] = countAfterVisitingCurrAndItsChildren - countBeforeVisitingCurrAndItsChildren;

    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        int[] result = new int[n];

        int[] count = new int[26]; // To store the count of letters

        DFS(0 , -1 , labels , result , count , map);

        return result;
        
    }
}
