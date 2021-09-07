import java.io.*;
import java.util.*;

class Solution {
    public int [][]d = {{-1,0},{0,-1},{1,0},{0,1}};
    public class position{
        int x;
        int y;
        int dir;
        
        public position(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public Queue<position> q = new LinkedList<>();
    public int answer = Integer.MAX_VALUE;
    
    public void bfs(position p, int [][] board){
        for(int i=0;i<4;i++){
            if(Math.abs(i-p.dir) == 2)
                continue;
            
            int nx = p.x + d[i][0];
            int ny = p.y + d[i][1];
            
            if(nx < 0 || ny < 0 || nx > board.length - 1 || ny > board[0].length - 1 || board[nx][ny] == 1){
                continue;
            }
            
            if(visit[nx][ny][i] == 0){
                if(p.dir != i)
                    visit[nx][ny][i] = visit[p.x][p.y][p.dir] + 600;
                else
                    visit[nx][ny][i] = visit[p.x][p.y][p.dir] + 100;
                q.add(new position(nx, ny, i));   
            }
            else{
                if(p.dir != i){
                    if(visit[nx][ny][i] > visit[p.x][p.y][p.dir] + 600){
                        visit[nx][ny][i] = visit[p.x][p.y][p.dir] + 600;
                        q.add(new position(nx, ny, i));   
                    }
                }
                else{
                    if(visit[nx][ny][i] > visit[p.x][p.y][p.dir] + 100){
                        visit[nx][ny][i] = visit[p.x][p.y][p.dir] + 100;
                        q.add(new position(nx, ny, i));   
                    }
                }
                
            }
            
        }
    }
    
    public int [][][] visit;
    public int solution(int[][] board) {
        visit = new int[board.length][board[0].length][4];
        q.add(new position(0,0,2));
        q.add(new position(0,0,3));
        board[0][0] = 0;

        while(!q.isEmpty()){
            position p = q.poll();
            bfs(p, board);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < 4; i++){
            if(visit[board.length - 1][board[0].length - 1][i] != 0)
                min = Math.min(min, visit[board.length - 1][board[0].length - 1][i]);
        }
        return min;
    }
}
