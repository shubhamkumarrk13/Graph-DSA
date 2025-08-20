// Problem Link : https://leetcode.com/problems/cheapest-flights-within-k-stops/

class Pair {

    int node;

    int cost;

    public Pair(int node , int cost) {

        this.node = node;

        this.cost = cost;
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer , List<Pair>> map = new HashMap<>();

        for(int[] flight : flights) {

            int u = flight[0];

            int v = flight[1];

            int price = flight[2];

            map.computeIfAbsent(u , x -> new ArrayList<>()).add(new Pair(v , price));
        }

        int[] result = new int[n];

        Arrays.fill(result , Integer.MAX_VALUE);

        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(src , 0));

        result[src] = 0;

        int level = 0;

        while(!q.isEmpty()) {

            int size = q.size();

            for(int i=0 ; i<size ; i++) {

                Pair pair = q.poll();

                int curr = pair.node;

                int totalCost = pair.cost;

                for(Pair ngbr : map.getOrDefault(curr , new ArrayList<>())) {

                    int v = ngbr.node;

                    int cost = ngbr.cost;

                    int tempCost = totalCost + cost;

                    if(tempCost < result[v]) {

                        result[v] = tempCost;

                        q.offer(new Pair(v , tempCost));
                    }
                }

            }

            level++;

            if(level > k) break;
        }

        if(result[dst] == Integer.MAX_VALUE) return -1;

        return result[dst];
        
    }
}
