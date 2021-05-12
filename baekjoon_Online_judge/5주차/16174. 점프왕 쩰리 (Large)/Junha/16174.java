import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] arr;
    static boolean[][] visit;
    static int[][] d = {{0, 1}, {1, 0}};

    static class position {
        int x;
        int y;

        public position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Queue<position> q = new LinkedList<>();

    static void bfs(position p) throws IOException {
        int nx, ny;
        for (int i = 0; i < 2; i++) {
            nx = p.x + (d[i][0] * arr[p.x][p.y]);
            ny = p.y + (d[i][1] * arr[p.x][p.y]);

            if (nx >= 0 && ny >= 0 && nx < N && ny < N && visit[nx][ny] != true) {
                if (arr[nx][ny] == -1) {
                    bw.write("HaruHaru\n");
                    bw.flush();
                    bw.close();
                    br.close();
                    System.exit(0);
                }
                visit[nx][ny] = true;
                q.add(new position(nx, ny));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q.add(new position(0, 0));
        visit[0][0] = true;
        while (!q.isEmpty()) {
            int k = q.size();
            for (int i = 0; i < k; i++) {
                bfs(q.poll());
            }
        }

        bw.write("Hing\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
