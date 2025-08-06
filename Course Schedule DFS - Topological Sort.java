// Problem Link : https://leetcode.com/problems/course-schedule/description/

//*********************  USING DFS - TOPOLOGICAL SORT  ****************************

class Solution {

    public boolean isCycleDFS(int u , Set<Integer> visited , Set<Integer> inRecursion , Map<Integer , List<Integer>> map) {

        visited.add(u);

        inRecursion.add(u);

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(!visited.contains(v)) {

                if(isCycleDFS(v , visited , inRecursion , map)) return true;
            }

            else if(inRecursion.contains(v)) return true;
        }

        inRecursion.remove(u);

        return false;
    }

    public boolean canFinish(int N, int[][] prerequisites) {

        // Creating adjacency map

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] arr : prerequisites) {

            int u = arr[0];

            int v = arr[1];

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        Set<Integer> visited = new HashSet<>();

        Set<Integer> inRecursion = new HashSet<>();

        for(Map.Entry<Integer , List<Integer>> entry : map.entrySet()) {

            int u = entry.getKey();

            if(!visited.contains(u) && isCycleDFS(u , visited , inRecursion , map)) return false; // Cycle is detected , all courses are not completed
        } 

        return true;

    }
}
