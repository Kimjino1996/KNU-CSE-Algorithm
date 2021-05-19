import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] arr;
    static boolean[][] visit;
    static int[][] d = {{0, 1}, {1, 0}};

    static class position{
        int x;
        int y;

        public position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static Queue<position> q = new LinkedList<>();

    static void bfs(position p) throws IOException{
        int power = arr[p.x][p.y];
        if(power == -1){
            //승리
            bw.write("HaruHaru");
            bw.flush();
            bw.close();
            br.close();
            System.exit(0);
        }
        //오른쪽으로 이동
        if((p.x+power < N) && (visit[p.x+power][p.y] == false)){//맵 사이즈보단 작고, 큐에 넣지 않았던 곳
            q.add(new position(p.x+power, p.y)); //큐에 넣는다
            visit[p.x+power][p.y] = true; //방문 표시
        }
        //아래로 이동
        if((p.y+power < N) && (visit[p.x][p.y+power] == false)){
            q.add(new position(p.x, p.y+power));
            visit[p.x][p.y+power] = true;
        }
    }

    public static void main(String[] args) throws IOException{
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        q.add(new position(0,0));
        visit[0][0] = true;

        while(!q.isEmpty()){
            int k = q.size();
            for(int i = 0; i < k; i++) {
                bfs(q.poll());
            }
        }

        bw.write("Hing");
        bw.flush();
        bw.close();
        br.close();
    }
}
