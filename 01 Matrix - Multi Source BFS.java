
// Problem Link : https://leetcode.com/problems/01-matrix/

// ************************ USING MULTI SOURCE BFS **********************

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

    public int[][] updateMatrix(int[][] mat) {

        n = mat.length;

        m = mat[0].length;

        int[][] result = new int[n][m];

        Queue<Pair> q = new LinkedList<>();

        // Enter into the queue the coord with value = 0

        for(int i=0 ; i<n ; i++) {

            for(int j=0 ; j<m ; j++) {

                if(mat[i][j] == 0) q.offer(new Pair(i , j));
            }
        }

        int level = 0;

        while(!q.isEmpty()) {

            int size = q.size();

            for(int i=0 ; i<size ; i++) {

                Pair pair = q.poll();

                int x = pair.x;

                int y = pair.y;

                result[x][y] = level;

                for(int[] dir : directions) {

                    int x_ = x + dir[0];

                    int y_ = y + dir[1];

                    if(isSafe(x_ , y_) && mat[x_][y_] == 1) {

                        mat[x_][y_] = 0; // Marking visited 

                        q.offer(new Pair(x_ , y_));
                    }
                }
            }

            level++;
        }

        return result;
    }
}
