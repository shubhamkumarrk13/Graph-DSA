// Problem Link : https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/

// ***************** USING BFS *************************

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

    public boolean checkExit(int x , int y) {

        if(x<0 || x>=n || y<0 || y>=m) return true;

        return false;
    }

    public boolean isSafe(int x , int y) {

        if(x>=0 && x<n && y>=0 && y<m) return true;

        return false;
    }

    public int nearestExit(char[][] maze, int[] entrance) {

        n = maze.length;

        m = maze[0].length;
        
        Queue<Pair> q = new LinkedList<>();

        // Storing the entrance as pair

        int u = entrance[0];

        int v = entrance[1];

        q.offer(new Pair(u , v));

        maze[u][v] = '+'; // acts as visited array as well

        int level = 0;

        while(!q.isEmpty()) {

            int size = q.size();

            for(int i=0 ; i<size ; i++) {

                Pair pair = q.poll();

                int x = pair.x;

                int y = pair.y;

                for(int[] dir : directions) {

                    int x_ = x + dir[0];

                    int y_ = y + dir[1];

                    if(level > 0 && checkExit(x_ , y_)) return level; // level > 0 ensures that entrance is not the exit

                    if(isSafe(x_ , y_) && maze[x_][y_] == '.') {

                        q.offer(new Pair(x_ , y_));

                        maze[x_][y_] = '+'; // marks as visited
                    }
                }
                
            }

            level++;
        }
        
        return -1;
    }
}
