import java.io.*;
import java.util.*;

class Main {

    static int[][][] tornado = {{{-2, 0}, {-1, -1}, {-1, 0}, {-1, 1}, {0, -2}, {1, -1}, {1, 0}, {1, 1}, {2, 0}, {0, -1}},
            {{0, -2}, {1, -1}, {0, -1}, {-1, -1}, {2, 0}, {1, 1}, {0, 1}, {-1, 1}, {0, 2}, {1, 0}},
            {{-2, 0}, {-1, 1}, {-1, 0}, {-1, -1}, {0, 2}, {1, 1}, {1, 0}, {1, -1}, {2, 0}, {0, 1}},
            {{0, 2}, {-1, 1}, {0, 1}, {1, 1}, {-2, 0}, {-1, -1}, {0, -1}, {1, -1}, {0, -2}, {-1, 0}}
    }; // 각 방향별 흩어지는 위치
    static int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 이동 방향
    static int[] percent = {2, 10, 7, 1, 5, 10, 7, 1, 2}; // 각 위치 별 모래가 흩어지는 비율
    static int[][] arr;
    static int N;
    static int x, y;
    static int out = 0; // 밖으로 나가는 모래의 총량

    public static void move(int d) {
        int nx, ny;
        int sand = 0;
        for (int i = 0; i < 9; i++) {
            nx = x + tornado[d][i][0];
            ny = y + tornado[d][i][1]; // 모래가 흩어질 위치 지정

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 격자의 밖으로 나가는 부분
                out += arr[x][y] * (percent[i] * 0.01); // out 변수에 합산
            } else {
                arr[nx][ny] += arr[x][y] * (percent[i] * 0.01); // 격자의 내부라면 해당 위치에 값 합산
            }
            sand += arr[x][y] * (percent[i] * 0.01); // 각각의 위치에 흩어진 모래의 양 계산
        }
        nx = x + tornado[d][9][0];
        ny = y + tornado[d][9][1]; // 알파 값에 해당하는 위치
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            out += arr[x][y] - sand; // 해당 위치가 격자 밖이라면 out 변수에 합산. 알파에는 다른 곳에 뿌려진 모래를 제외한 나머지 모래가 모두 합산됨.
        } else {
            arr[nx][ny] += arr[x][y] - sand; // 격자 내부라면 알파 위치에 값 합산
        }
        arr[x][y] = 0; // 기존의 위치를 0
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        x = N / 2;
        y = N / 2; // 격자의 가운데에서 시작
        int times = 0;
        int dist = 1;
        int d = 0;
        while (x != 0 || y != 0) {
            for (int i = 0; i < dist; i++) {
                x += dir[d][0];
                y += dir[d][1]; // 좌표를 토네이도 방향으로 이동
                move(d); // 모래 흩어지는 것 구현
                if (x == 0 && y == 0) // 좌측 상단에 도달하면 종료
                    break;
            }
            times++;
            if (times == 2) {
                dist++;
                times = 0;
            }
            d = d + 1 == 4 ? 0 : d + 1; // 방향 조절
        }
        bw.write(String.valueOf(out) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
