// Problem Link : https://leetcode.com/problems/evaluate-division/

// ************************ USING BFS ************************
class Pair {

    String node;

    double value;

    public Pair(String node , double value) {

        this.node = node;

        this.value = value;
    }
}

class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int n = equations.size();

        Map<String , List<Pair>> map = new HashMap<>();

        for(int i=0 ; i<n ; i++) {

            String u = equations.get(i).get(0);

            String v = equations.get(i).get(1);

            double val = values[i];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(new Pair(v , val));

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(new Pair(u , 1.0 / val));

        }

        double[] result = new double[queries.size()];

        int ind = 0;

        for(List<String> query : queries) {

            String source = query.get(0);

            String destination = query.get(1);

            if(!map.containsKey(source) || !map.containsKey(destination)) {

                result[ind++] = -1;

                continue;
            }

            Set<String> visited = new HashSet<>();

            double ans = -1.0;

            Queue<Pair> q = new LinkedList<>();

            q.offer(new Pair(source , 1.0));

            visited.add(source);

            while(!q.isEmpty()) {

                Pair p = q.poll();

                String u = p.node;

                double product = p.value;

                if(u.equals(destination)) {

                    ans = product;

                    break;
                }

                for(Pair pair : map.getOrDefault(u , new ArrayList<>())) {

                    String v = pair.node;

                    double val = pair.value;

                    if(!visited.contains(v)) {

                        visited.add(v);

                        q.offer(new Pair(v , product * val));
                    }
                }
            }

            result[ind++] = ans;
        }

        return result;
    }
}
