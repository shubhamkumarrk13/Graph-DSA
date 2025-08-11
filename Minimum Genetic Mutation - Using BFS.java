// Problem Link : https://leetcode.com/problems/minimum-genetic-mutation/description/

//******************** USING BFS **************************** */

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {

        Set<String> bankSet = new HashSet<>(); // Storing all bank elements in a set

        for(String ele : bank) bankSet.add(ele);

        Set<String> visited = new HashSet<>(); // for marking all visited elements

        Queue<String> q = new LinkedList<>();

        q.offer(startGene);

        visited.add(startGene);

        char[] choices = {'A' , 'C' , 'G' , 'T'};

        int level = 0;

        while(!q.isEmpty()) {

            int size = q.size();

            for(int x=0 ; x<size ; x++) {

                String curr = q.poll();

                if(curr.equals(endGene)) return level;

                for(char ch : choices) {

                    for(int i=0 ; i<curr.length() ; i++) {

                        char[] arr = curr.toCharArray();

                        arr[i] = ch;

                        String nextGene = new String(arr);

                        if(!visited.contains(nextGene) && bankSet.contains(nextGene)) {

                            visited.add(nextGene);

                            q.offer(nextGene);
                        }
                    }
                }
            }

            level++;
        }

        return -1;
    }
}
