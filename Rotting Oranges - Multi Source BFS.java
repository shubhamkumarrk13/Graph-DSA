
// Problem Link : https://leetcode.com/problems/rotting-oranges/

// *************************** USING MULTI-SOURCE BFS *****************


class Pair {

    int x;

    int y;

    public Pair(int x , int y) {

        this.x = x;

        this.y = y;
    }
}

class Solution {

    int n;

    int m;

    int[][] directions = {{-1 , 0} , {0 , 1} , {1 , 0} , {0 , -1}};

    public boolean isSafe(int x , int y) {

        if(x>=0 && x<n && y>=0 && y<m) return true;

        return false;
    }

    public int orangesRotting(int[][] grid) {

        n = grid.length;

        m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();

        int freshOranges = 0;

        int time = 0;

        for(int i=0 ; i<n ; i++) {

            for(int j=0 ; j<m ; j++) {

                if(grid[i][j] == 2) q.offer(new Pair(i , j));

                if(grid[i][j] == 1) freshOranges++;
            }
        }

        if(q.isEmpty() && freshOranges == 1) return -1; // if there is no rotten oranges but have fresh oranges

        if(freshOranges == 0) return 0; // when there is no fresh oranges

        while(!q.isEmpty()) {

            int size = q.size();

            for(int i=0 ; i<size ; i++) {

                Pair pair = q.poll();

                int x = pair.x;

                int y = pair.y;

                for(int[] dir : directions) {

                    int x_ = x + dir[0];

                    int y_ = y + dir[1];

                    if(isSafe(x_ , y_) && grid[x_][y_] == 1) {

                        grid[x_][y_] = 2;

                        q.offer(new Pair(x_ , y_));

                        freshOranges--;
                    }
                }
            }

            time++;
        }

        if(freshOranges == 0) return time - 1;

        return -1;
        
    }
}
