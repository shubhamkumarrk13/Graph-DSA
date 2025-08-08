
// Problem Link : https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/

class Solution {

    public Map<Integer , List<Integer>> findMap(int[][] edges) {

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
            
        }

        return map;
    }

    public int[] BFS(int u , Map<Integer , List<Integer>> map) {

        Queue<Integer> q = new LinkedList<>();

        Set<Integer> visited = new HashSet<>();

        q.offer(u);

        visited.add(u);

        int farthestNode = u;

        int level = 0;

        while(!q.isEmpty()) {

            int size = q.size();

            for(int i=0 ; i<size ; i++) {

                int curr = q.poll();

                farthestNode = curr;

                for(int v : map.getOrDefault(curr , new ArrayList<>())) {

                    if(!visited.contains(v)) {

                        q.offer(v);

                        visited.add(v);
                    }
                }
            }

            level++;


        }

        int[] arr = new int[2];

        arr[0] = farthestNode;

        arr[1] = level - 1; // -1 because at last node , it will increment the level , which is wrong since there is no next level beyond the last node

        return arr;
    }

    public int findDiameter(Map<Integer , List<Integer>> map) {

        // find farthest node from a random node , say 0;

        int[] arr1 = BFS(0 , map); // arr = [farthestnode , diameter]

        int farthestNode = arr1[0]; // one of the farthest node;

        // the length from this current node(arr[0] or farthestNode) to the farthest node from this is the diameter;

        int[] arr2 = BFS(farthestNode , map);

        int diameter = arr2[1];

        return diameter;


    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {

        Map<Integer , List<Integer>> map1 = findMap(edges1);

        Map<Integer , List<Integer>> map2 = findMap(edges2);

        int d1 = findDiameter(map1);

        int d2 = findDiameter(map2);

        int combined = (d1 + 1)/2 + (d2 + 1)/2 + 1;

        return Math.max(combined , Math.max(d1 , d2));
        
    }
}
