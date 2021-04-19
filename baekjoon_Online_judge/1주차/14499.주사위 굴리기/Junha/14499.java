 import java.io.*;
import java.util.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][]arr = new int[N][M];
        int [][]d = {{0,0}, {0,1},{0,-1},{-1,0},{1,0}};
        int []dice = {0,0,0,0,0,0,0};

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int D = Integer.parseInt(st.nextToken());
            int []new_dice = {0,0,0,0,0,0,0};
            if(x+d[D][0] < 0 || x+d[D][0] >=N || y+d[D][1] < 0||y+d[D][1] >= M)
                continue;
            if(D == 1){
                new_dice = new int[]{0, dice[4], dice[2], dice[1], dice[6], dice[5], dice[3]};
            }
            else if(D==2){
                new_dice = new int[]{0,dice[3],dice[2],dice[6],dice[1],dice[5],dice[4]};
            }
            else if(D==3){
                new_dice = new int[]{0,dice[5],dice[1],dice[3],dice[4],dice[6],dice[2]};
            }
            else if(D==4){
                new_dice = new int[]{0,dice[2],dice[6],dice[3],dice[4],dice[1],dice[5]};
            }
            for (int j = 1; j < 7; j++) {
                dice[j] = new_dice[j];
            }
            
            x = x+d[D][0];
            y = y+d[D][1];
            if(arr[x][y] == 0){
                arr[x][y] = dice[6];
            }
            else{
                dice[6] = arr[x][y];
                arr[x][y] = 0;
            }
            bw.write(String.valueOf(dice[1])+"\n");

        }
        bw.flush();
        bw.close();
        br.close();
    }
}
