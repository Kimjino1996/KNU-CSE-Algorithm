```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] high, route;
    static int dx[] = {0, 1, 0, -1}; // right down left up
    static int dy[] = {1, 0, -1, 0}; // right down left up
    static int nx, ny;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        high = new int[M][N];
        route = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                high[i][j] = Integer.parseInt(st.nextToken());
                route[i][j] = -1;
            }
        }
        route[M-1][N-1] = 1;
        System.out.println(search(0,0));
        print();
    }
    static public void print()
    {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", route[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    static public int search(int i, int j){

        if(route[i][j] != -1)
            return route[i][j];
        route[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            nx = i + dx[k];
            ny = j + dy[k];
            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (high[nx][ny] < high[i][j])
                    if (route[nx][ny] == -1)
                        route[i][j] += search(nx,ny);
                    else route[i][j] += route[nx][ny];
            }
        }
        return route[i][j];
    }
}
```