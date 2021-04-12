import java.io.*;
import java.util.*;

class Main {

    static int cnt;
    static long dp_min[]; // 최솟값들을 구하기 위한 다이나믹 프로그래밍 배열
    static int num[] = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6}; // 0~9 각 숫자를 만들기 위해 사용되는 성냥개비의 수
    static Queue<Integer> q = new LinkedList<>(); // 최댓값 찾을 때 사용 할 큐
    
    public static void make_dp_min() { // 최솟값을 찾기 위한 dp 배열 초기화 (특정 성냥개비 갯수로 만들수 있는 최소값)
        dp_min[2] = 1;
        dp_min[3] = 7;
        dp_min[4] = 4;
        dp_min[5] = 2;
        dp_min[6] = 6;
        dp_min[7] = 8;
    }

    public static void find_min(int n) { // 최솟값을 찾는 dp 작성
        cnt = n % 7 == 0 ? n / 7 : n / 7 + 1; // 몇 자리 수여야 최솟값을 가지는 지를 먼저 확인해서 cnt 변수에 넣음

        for (int i = 1; i < 8; i++) {
            if (dp_min[i] != Long.MAX_VALUE && dp_min[n - i] != Long.MAX_VALUE && dp_min[n - i] < (long) Math.pow(10, cnt - 1) && dp_min[i] != 0) {
                dp_min[n] = Math.min(dp_min[n], dp_min[n - i] + dp_min[i] * (long) Math.pow(10, cnt - 1));

            }
        }
    }

    public static long change_zero(long n) { // 특정 숫자들은 0으로 바꾸었을 때 더 작아질 수 있음
        long[] arr = new long[100];
        int i = 0;
        int c = 0;

        while (true) { // 첫자리를 제외하고는 6을 0으로 바꾸면 최솟값이 더 작아짐
            arr[i] = n % 10;
            n /= 10;
            if (arr[i] == 6)
                arr[i] = 0;
            i++;
            if (n / 10 == 0)
                break;
        }

        arr[i] = n % 10;
        n = 0;
        for (int j = i - 1; j > 0; j--) { // 인접한 두 수를 더했을 때 12가 나오면 0 두개로 바꿀 수 있음. 물론 첫 자리는 제외.
            if (num[(int) arr[j]] + num[(int) arr[j - 1]] == 12) {
                arr[j] = 0;
                arr[j - 1] = 0;
            }
        }
        for (int j = 0; j <= i; j++) {
            n += arr[j] * Math.pow(10, c);
            c++;
        }
        return n;
    }

    public static void find_max(int n) { // 최댓값을 구하는 함수
        if (n % 2 == 1) { // n이 홀수면 가장 앞에 7
            q.add(7);
            n -= 3;
        } else {
            q.add(1); // n이 짝수면 가장 앞에 1
            n -= 2;
        }
        while (n > 0) {
            q.add(1); // 그 뒤는 모두 1을 채우는 것이 가장 큰 수를 만드는 방법
            n -= 2;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int test_case[] = new int[T];
        int max_test_value = Integer.MIN_VALUE;
        
        for (int i = 0; i < T; i++) {
            test_case[i] = Integer.parseInt(br.readLine());
            max_test_value = Math.max(max_test_value, test_case[i]);
        }

        if (max_test_value >= 8) { // 테스트 케이스 중 가장 큰 수가 8이 넘으면 수행
            dp_min = new long[max_test_value + 1];
            Arrays.fill(dp_min, Long.MAX_VALUE);
            make_dp_min();

            for (int i = 8; i <= max_test_value; i++) {
                find_min(i); // dp를 이용해 최솟값을 찾고
                dp_min[i] = change_zero(dp_min[i]); // 0으로 치환이 가능한 숫자는 치환해 줌
            }
        } else { // 테스트 케이스 중 가장 큰 수가 8을 넘지 않으면
            for (int i = 0; i < max_test_value; i++) { // 기본적으로 사용되는 값 만으로도 답을 구할 수 있음
                dp_min = new long[8]; 
                make_dp_min();
            }
        }

        for (int i = 0; i < T; i++) {
            find_max(test_case[i]);
            bw.write(String.valueOf(dp_min[test_case[i]] + " ")); // 최솟값 출력
            while (!q.isEmpty())
                bw.write(String.valueOf(q.poll())); // 최댓값 출력
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
