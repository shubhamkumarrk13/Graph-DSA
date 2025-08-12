
// Problem Link : https://leetcode.com/problems/all-paths-from-source-to-target/

// ********************* USING DFS ***************

class Solution {

    public void DFS(int source , int target , List<Integer> temp , List<List<Integer>> result , Map<Integer , List<Integer>> map) {

        temp.add(source);

        if(source == target) {

            result.add(new ArrayList<>(temp));

        }

        for(int v : map.getOrDefault(source , new ArrayList<>())) {

            DFS(v , target , temp , result , map);
        }

        temp.remove(temp.size() - 1);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int u=0 ; u<graph.length ; u++) {

            for(int v : graph[u]) {

                map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> temp = new ArrayList<>();

        int source = 0;

        int target = graph.length - 1;

        DFS(source , target , temp , result , map);

        return result;
        
    }
}
