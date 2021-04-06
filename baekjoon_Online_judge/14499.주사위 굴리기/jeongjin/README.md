```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] map;
        int N, M, x, y, k, go;
        int[] dx = {0,0, 0, -1, 1};
        int[] dy = {0,1, -1, 0, 0};

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[7];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            go = Integer.parseInt(st.nextToken());
            int nx,ny;
            nx = x + dx[go];
            ny = y + dy[go];
            if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                change(go);
                if(map[nx][ny]==0){
                    map[nx][ny] = dice[6];
                }
                else {
                    dice[6] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                bw.write(dice[1]+'0');
                bw.write('\n');
                x = nx;
                y= ny;
            }
        }
        bw.close();
    }

    static public void change(int dir) {
        int[] tmp = dice.clone();
        switch (dir) {
            case 1 :{ // east
                dice[1] = tmp[4];
                dice[3] = tmp[1];
                dice[4] = tmp[6];
                dice[6] = tmp[3];
                break;
            }
            case 2 :{ //west
                dice[1] = tmp[3];
                dice[3] = tmp[6];
                dice[4] = tmp[1];
                dice[6] = tmp[4];
                break;
            }
            case 3 : { //north
                dice[1] = tmp[5];
                dice[2] = tmp[1];
                dice[5] = tmp[6];
                dice[6] = tmp[2];
                break;
            }
            case 4 : { //south
                dice[1] = tmp[2];
                dice[2] = tmp[6];
                dice[5] = tmp[1];
                dice[6] = tmp[5];
                break;
            }
        }
    }

}
```