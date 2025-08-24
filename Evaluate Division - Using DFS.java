
// Problem Link : https://leetcode.com/problems/evaluate-division/
// ************************ USING DFS ************************

class Pair {

    String node;

    double val;

    public Pair(String node , double val) {

        this.node = node;

        this.val = val;
    }
}

class Solution {

    double ans;

    public void dfs(String source , String destination , Set<String> visited , Map<String , List<Pair>> map , double product) {

        visited.add(source);

        if(source.equals(destination)) {

            ans = product;
            
            return;
        }

        for(Pair pair : map.getOrDefault(source , new ArrayList<>())) {

            String v = pair.node;

            double value = pair.val;

            if(!visited.contains(v)) dfs(v , destination , visited , map , product * value);
        }
    }

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

            Set<String> visited = new HashSet<>();

            double product = 1.0;

            ans = -1;

            if(map.containsKey(source)) {

                dfs(source , destination , visited , map , product);
            }

            result[ind++] = ans;
        }

        return result;
    }
}
