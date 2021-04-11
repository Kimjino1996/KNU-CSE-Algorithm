~~~
import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int [][]arr;
    static int [][]dp;
    static int [][]visit;
    static int [][]d = {{-1,0},{0,1},{1,0},{0,-1}};

    public static class position{
        int x;
        int y;
        int c;

        public position(int x, int y, int c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    public static int dfs(position p){
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = p.x + d[i][0];
            ny = p.y + d[i][1];

            if(arr[nx][ny] != 0 && arr[nx][ny] < arr[p.x][p.y]){
                if(nx==N && ny ==M){
                    visit[nx][ny] = 1;
                    p.c++;
                    dp[nx][ny] ++;
                    dp[p.x][p.y] ++;
                }
                else if(dp[nx][ny] > 0){
                    dp[p.x][p.y] += dp[nx][ny];
                    p.c = dp[p.x][p.y];
                }

                else if(visit[nx][ny] == 0){
                    visit[nx][ny] = 1;

                    p.c += dfs(new position(nx,ny,0));
                    dp[p.x][p.y] = p.c;
                }
            }
        }
        return p.c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+2][M+2];
        dp = new int[N+2][M+2];
        visit = new int[N+2][M+2];


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= M ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit[1][1] = 1;
        dfs(new position(1,1, 0));
        bw.write(String.valueOf(dp[1][1]));
        bw.flush();
        bw.close();
        br.close();



    }
}
~~~
