// Problem Link : https://leetcode.com/problems/path-with-maximum-probability/
// *************************************** USING Dijkstra's Algorithm ********************************

class Pair1 {

    int node;

    double prob;

    public Pair1(int node , double prob) {

        this.node = node;

        this.prob = prob;
    }
}

class Pair2 {

    double totalProb;

    int node;

    public Pair2(double totalProb , int node) {

        this.totalProb = totalProb;

        this.node = node;
    }
}

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {

        Map<Integer , List<Pair1>> map = new HashMap<>(); // map : source -> {dest , probability}

        for(int i=0 ; i<edges.length ; i++) {

            int u = edges[i][0];

            int v = edges[i][1];

            double p = succProb[i];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(new Pair1(v , p));

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(new Pair1(u , p));
        }

        double[] result = new double[n];

        // Pair 2 -> {totalProbability , destination}

        PriorityQueue<Pair2> pq = new PriorityQueue<>((a , b) -> Double.compare(b.totalProb , a.totalProb)); // Using max-Heap

        result[start_node] = 1.0; // Probability of going from source to source is 1

        pq.offer(new Pair2(1.0 , start_node));

        while(!pq.isEmpty()) {

            Pair2 curr = pq.poll();

            int node = curr.node;

            double totalProb = curr.totalProb;

            for(Pair1 ngbr : map.getOrDefault(node , new ArrayList<>())) {

                int v = ngbr.node;

                double p = ngbr.prob;

                if(totalProb * p > result[v]) { // if probabilty is greater than prev probabability , only then push into heap and update the result vector

                    result[v] = totalProb * p;

                    pq.offer(new Pair2(totalProb * p , v));
                }
            }
        }

        return result[end_node];
        
    }
}
