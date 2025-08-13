
// Problem Link : https://leetcode.com/problems/lexicographically-smallest-equivalent-string/

// ******************* USING DFS *************

class Solution {

    public char DFSFindMinChar(char u , Set<Character> visited , Map<Character , List<Character>> map) {

        visited.add(u);

        char minChar = u;

        for(char v : map.getOrDefault(u , new ArrayList<>())) {

            if(!visited.contains(v)) {

                char temp = DFSFindMinChar(v , visited , map);

                if(temp < minChar) minChar = temp;

            }
        }

        return minChar;
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {

        Map<Character , List<Character>> map = new HashMap<>();

        for(int i=0 ; i<s1.length() ; i++) {

            char u = s1.charAt(i);

            char v = s2.charAt(i);

            map.computeIfAbsent(u , k -> new ArrayList<>()).add(v);

            map.computeIfAbsent(v , k -> new ArrayList<>()).add(u);
        }

        StringBuilder result = new StringBuilder();

        for(int i=0 ; i<baseStr.length() ; i++) {

            Set<Character> visited = new HashSet<>();

            char ch = baseStr.charAt(i);

            char minChar = DFSFindMinChar(ch , visited , map);

            result.append(minChar);
        }

        return result.toString();
        
    }
}
