// Problem Link : https://leetcode.com/problems/similar-string-groups/
// ************************** USING DFS ************************

class Solution {

    public boolean isSimilar(String s1 , String s2) {

        int n = s1.length();

        int diff = 0;

        for(int i=0 ; i<n ; i++) {

            if(s1.charAt(i) != s2.charAt(i)) diff++;
        }

        if(diff == 2 || diff == 0) return true;

        return false;
    }

    public void dfs(int u , Set<Integer> visited , Map<Integer , List<Integer>> map) {

        visited.add(u);

        for(int v : map.getOrDefault(u , new ArrayList<>())) {

            if(!visited.contains(v)) dfs(v , visited , map);
        }
    }

    public int numSimilarGroups(String[] strs) {

        int n = strs.length;

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int i=0 ; i<n ; i++) {

            for(int j=i+1 ; j<n ; j++) {

                if(isSimilar(strs[i] , strs[j])) {

                    map.computeIfAbsent(i , k -> new ArrayList<>()).add(j);

                    map.computeIfAbsent(j , k -> new ArrayList<>()).add(i);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();

        int result = 0;

        for(int i=0 ; i<n ; i++) {

            if(!visited.contains(i)) {

                result++;

                dfs(i , visited , map);
            }
        }

        return result;
        
    }
}
