// Problem Link : https://leetcode.com/problems/valid-arrangement-of-pairs/

// ***************** Using Hierholzer's Algorithm *******************

/*
step 1 : Build adjaceny map
step 2 : Find inDegree and outDegree
step 3 : Find start node of the Eulers's Path
step 4 : Perform Stack based DSA and store the values in a list
*/

class Solution {

    public int[][] validArrangement(int[][] pairs) {

        // Make the adjaceny map and indegree and outdegree map as well

        Map<Integer , List<Integer>> map = new HashMap<>();

        Map<Integer , Integer> inDegree = new HashMap<>();

        Map<Integer , Integer> outDegree = new HashMap<>();

        for(int[] edge : pairs) {

            int u = edge[0];

            int v = edge[1];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            outDegree.put(u , outDegree.getOrDefault(u , 0) + 1);

            inDegree.put(v , inDegree.getOrDefault(v , 0) + 1);
        }

        // Find the starting point of the Euler's path

        int startNode = -1;

        for(Map.Entry<Integer , List<Integer>> entry : map.entrySet()) {

            int key = entry.getKey();

            if(outDegree.getOrDefault(key , 0) - inDegree.getOrDefault(key , 0) == 1) startNode = key;
        }

        if(startNode == -1) startNode = pairs[0][0]; // If cycle is present

        // Perform DFS to get the Euler's path

        List<Integer> list = new ArrayList<>();

        Stack<Integer> st = new Stack<>();

        st.push(startNode);

        while(!st.isEmpty()) {

            int curr = st.peek();

            if(map.containsKey(curr) && !map.get(curr).isEmpty()) {

                List<Integer> temp = map.get(curr);

                int ngbr = temp.get(temp.size()-1);

                temp.remove(temp.size()-1);

                st.push(ngbr);
            }
            else {
                list.add(st.pop());
            }
        }

        Collections.reverse(list);

        // Return the ans;

        int result[][] = new int[pairs.length][2];

        for(int i=0 ; i<list.size()-1; i++) {

            int[] arr = new int[2];

            arr[0] = list.get(i);

            arr[1] = list.get(i+1);

            result[i] = arr;
        }

        return result;
    }
}
