
// Problem Link : https://leetcode.com/problems/map-of-highest-peak/

// ******************** USING MULTI SOURCE BFS ***************

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

    public int[][] highestPeak(int[][] isWater) {

        n = isWater.length;

        m = isWater[0].length;

        int[][] result = new int[n][m];

        Queue<Pair> q = new LinkedList<>();

        // Store int the que the coord with val = 1

        for(int i=0 ; i<n ; i++) {

            for(int j=0 ; j<m ; j++) {

                if(isWater[i][j] == 1) {

                    q.offer(new Pair(i , j));

                }
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

                    if(isSafe(x_ , y_) && isWater[x_][y_] == 0) {

                        isWater[x_][y_] = 1; // acts as visited array as well

                        q.offer(new Pair(x_ , y_));
                    }
                }
            }

            level++;
        }

        return result;
        
    }
}
