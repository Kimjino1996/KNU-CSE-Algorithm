import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] d = {{0, 0}, {1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 0}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    static Queue<position> arr[][];
    static position jp = new position(0, 0); // 종수의 아두이노의 위치
    static int[][] n;
    static int cnt = 0;

    public static class position {
        int x;
        int y;

        public position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void mad_arduino(position p) throws IOException {
        int min = Integer.MAX_VALUE;
        int nx = 0, ny = 0;
        for (int i = 1; i <= 9; i++) { // 종수의 아두이노와 가장 가까운 방향을 구해서
            if (i == 5 || (p.x + d[i][0] < 1 || p.y + d[i][1] < 1 || p.x + d[i][0] > N || p.y + d[i][1] > M))
                continue;

            if (Math.abs(jp.x - (p.x + d[i][0])) + Math.abs(jp.y - (p.y + d[i][1])) < min) {
                min = Math.abs(jp.x - (p.x + d[i][0])) + Math.abs(jp.y - (p.y + d[i][1]));
                nx = p.x + d[i][0];
                ny = p.y + d[i][1];
            }
        }
        arr[nx][ny].add(new position(nx, ny)); // 이동을 시켜준다.
        if (nx == jp.x && ny == jp.y) { // 만일 미친 아두이노가 종수의 아두이노가 있는 칸으로 가면 종료
            bw.write("kraj ");
            bw.write(String.valueOf(cnt + 1) + "\n");
            bw.flush();
            bw.close();
            br.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        String s;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new Queue[N + 2][M + 2];
        n = new int[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                arr[i][j] = new LinkedList<>();
            }
        }

        char ch;
        for (int i = 1; i <= N; i++) {
            s = br.readLine();
            for (int j = 1; j <= M; j++) {
                ch = s.charAt(j - 1);
                if (ch == 'I') { // 종수의 아두이노의 초기 위치 저장
                    jp.x = i;
                    jp.y = j;
                }
                if (ch == 'R') { // 미친 아두이노들의 초기 위치 저장
                    arr[i][j].add(new position(i, j));
                }
            }
        }
        int[][] a = new int[N][M];
        s = br.readLine();
        position p;
        for (cnt = 0; cnt < s.length(); cnt++) { 
            int k = s.charAt(cnt) - '0'; // 종수의 아두이노 
            if (k != 5) { // 종수의 아두이노가 제자리에 머무는게 아니면 이동시켜준다
                jp.x = jp.x + d[k][0];
                jp.y = jp.y + d[k][1];

                if (arr[jp.x][jp.y].size() == 1) { // 종수의 아두이노가 이동할 위치에 미친 아두이노가 있으면 종료
                    bw.write("kraj ");
                    bw.write(String.valueOf(cnt + 1) + "\n");
                    bw.flush();
                    bw.close();
                    br.close();
                    System.exit(0);
                }
            } 

            for (int j = 1; j <= N; j++) { // 현재 각 위치에 존재하는 미친 아두이노들의 개수를 저장 (물론 1개겠지만)
                                            // --> 이동한 미친 아두이노들과 혼동되지 않도록
                for (int l = 1; l <= M; l++) {
                    n[j][l] = arr[j][l].size();
                }
            }

            for (int j = 1; j <= N; j++) { // 미친 아두이노들을 이동시켜 줌
                for (int l = 1; l <= M; l++) {
                    if (n[j][l] == 1) {
                        mad_arduino(arr[j][l].poll());
                    }
                }
            }

            for (int j = 1; j <= N; j++) { // 미친 아두이노들이 2개 이상 겹쳐진 칸의 아두이노들은 모두 없애준다
                for (int l = 1; l <= M; l++) {
                    if (arr[j][l].size() > 1) {
                        arr[j][l].clear();
                    }
                }
            }


        }

        for (int i = 1; i <= N; i++) { // 출력
            for (int j = 1; j <= M; j++) {
                if (i == jp.x && j == jp.y)
                    bw.write("I");
                else if (arr[i][j].size() == 1)
                    bw.write("R");
                else
                    bw.write(".");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
