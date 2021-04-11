import java.io.*;
import java.util.*;

class Main {

    static int arr[][] = new int[5][8];

    public static void gear_right(int n, int m) { // 오른쪽 톱니바퀴만 검사
        if (n == 4) { // 4번 톱니바퀴면 더이상 확인할 필요 x

        } else {
            if (arr[n][2] != arr[n + 1][6]) { // 오른쪽 톱니바퀴와 극이 다르면
                m = (m == 1) ? -1 : 1; // 회전 방향 바꿔주기
                gear_right(n + 1, m); // 재귀를 통한 다음 톱니바퀴 확인
                turn(n + 1, m); // 모든 확인이 끝난 뒤 회전 수행
            }
        }
    }

    public static void gear_left(int n, int m) { // 왼쪽 톱니바퀴만 검사
        if (n == 1) { // 1번 톱니바퀴면 더이상 확인할 필요 x

        } else {
            if (arr[n][6] != arr[n - 1][2]) { // 왼쪽 톱니바퀴와 극이 다르면
                m = (m == 1) ? -1 : 1;
                gear_left(n - 1, m);
                turn(n - 1, m);
            }
        }
    }

    public static void turn(int n, int m) { // 톱니바퀴 회전 시키기
        if (m == 1) { // 시계방향으로 회전
            int temp = arr[n][7];
            for (int i = 6; i >= 0; i--) {
                arr[n][i + 1] = arr[n][i];
            }
            arr[n][0] = temp;
        } else { // 반시계방향으로 회전
            int temp = arr[n][0];
            for (int i = 1; i < 8; i++) {
                arr[n][i - 1] = arr[n][i];
            }
            arr[n][7] = temp;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;
        StringTokenizer st;
        int K;

        for (int i = 1; i <= 4; i++) {
            s = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 1) { // 1번 톱니바퀴는 오른쪽만 검사하면 됨
                gear_right(n, m);
                turn(n, m);
            } else if (n == 4) { // 4번 톱니바퀴는 왼쪽만 검사하면 됨
                gear_left(n, m);
                turn(n, m);
            } else {
                gear_right(n, m);
                gear_left(n, m);
                turn(n, m);
            }
        }

        int sum = 0;
        sum += arr[1][0] + arr[2][0] * 2 + arr[3][0] * 4 + arr[4][0] * 8;
        bw.write(String.valueOf(sum) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
