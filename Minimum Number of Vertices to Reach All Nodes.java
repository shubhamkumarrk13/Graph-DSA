class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        int[] indegree = new int[n];

       // Map<Integer , List<Integer>> map = new HashMap<>(); // Not requried , just for understanding

       for(List<Integer> edge : edges) {

        int u = edge.get(0);

        int v = edge.get(1);

        indegree[v]++;

       }

       List<Integer> result = new ArrayList<>();

       for(int i=0 ; i<n ; i++) {

        if(indegree[i] == 0) result.add(i); // indegree == 0 tells us that particular node can't be reached by any other node so it is included in our result 
        // but indegree != 0 tells that node can be reached by other node , so no need to include that in our result
       }

       return result;
    }
}
