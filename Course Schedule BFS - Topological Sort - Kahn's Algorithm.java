// Problem Link : https://leetcode.com/problems/course-schedule/description/

//*********************  USING BFS - TOPOLOGICAL SORT - KAHN'S ALGORITHM ****************************

class Solution {

    public boolean canFinish(int N, int[][] prerequisites) {

        // Creating adjacency map

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int[] arr : prerequisites) {

            int u = arr[0];

            int v = arr[1];

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        // Creating indegree array

        int[] indegree = new int[N];

        for(int u=0 ; u<N ; u++) {

            for(int v : map.getOrDefault(u , new ArrayList<>())) {

                indegree[v]++;
            }
        }

        // indegree = 0 are pushed into the queue and then simple BFS traversal

        Queue<Integer> q = new LinkedList<>();

        int cnt = 0; // To cnt the no. of visited nodes; 

        for(int i=0 ; i<N ; i++) {

            if(indegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {

            int u = q.poll();

            cnt++;

            for(int v : map.getOrDefault(u , new ArrayList<>())) {

                indegree[v]--;

                if(indegree[v] == 0) q.offer(v);
            }
        }

        if(cnt == N) return true; // Cycle is not detected , all courses are completed

        return false; // Cycle is detected , all courses are not completed
    }
}
