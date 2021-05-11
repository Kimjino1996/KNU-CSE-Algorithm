import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][]arr = new int[K][N+1];

        Arrays.fill(arr[0],1);
        for (int i = 0; i < K; i++) {
            arr[i][0] = 1;
        }

        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = (arr[i][j-1] + arr[i-1][j])%1000000000;
            }
        }

        bw.write(String.valueOf(arr[K-1][N]));
        bw.flush();
        bw.close();
        br.close();

    }
}
