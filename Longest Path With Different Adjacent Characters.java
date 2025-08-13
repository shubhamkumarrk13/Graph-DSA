
// Problem Link : https://leetcode.com/problems/longest-path-with-different-adjacent-characters/

class Solution {

    int result = 0;

    public int DFS(int curr , int parent , Map<Integer , List<Integer>> map , String s) {

        int longest = 0;

        int secLongest = 0;

        for(int child : map.getOrDefault(curr , new ArrayList<>())) {

            if(child == parent) continue;

            int childLongestLength = DFS(child , curr , map , s);

            if(s.charAt(child) == s.charAt(curr)) continue;

            if(childLongestLength > longest) {

                secLongest = longest;

                longest = childLongestLength;
            }

            else if(childLongestLength > secLongest) secLongest = childLongestLength;
        }

        int nicheHiAnsMila = longest + secLongest + 1;

        int koiEkAacha = Math.max(longest , secLongest) + 1;

        int onlyRootAacha = 1;

        result = Math.max(Math.max(nicheHiAnsMila , result) , Math.max(onlyRootAacha , koiEkAacha));

        return Math.max(koiEkAacha , onlyRootAacha);
    }

    public int longestPath(int[] parent, String s) {

        int n = parent.length;

        Map<Integer , List<Integer>> map = new HashMap<>();

        for(int i=1 ; i<n ; i++) {

            int u = i;

            int v = parent[i];

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
           
        }

        DFS(0 , -1 , map , s);

        return result;
        
    }
}
