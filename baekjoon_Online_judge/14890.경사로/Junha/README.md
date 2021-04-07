~~~
import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int [][]arr = new int[N+1][N+1];
        int [][]slope = new int[N][N];
        int cnt=0;
        int flag=0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                flag=0;
                if(arr[i][j+1] == 0){ // 해당 줄은 지나갈 수 있음
                    cnt++;
                    break;
                }
                if(arr[i][j] == arr[i][j+1]) // 다음 칸이랑 높이가 같으면 넘어감
                    continue;
                else { // 그렇지 않으면
                    if(arr[i][j] == arr[i][j+1]+1) { // 낮으면 == 내려가야하면
                        for (int k = 1; k <= L; k++) { // 경사면이 놓아질 수 있는지
                            if(j+k >= N){
                                flag=1;
                                break;
                            }
                            if (arr[i][j + 1] != arr[i][j + k]) {
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 1) {
                            break;
                        }
                        for (int k = 1; k <= L; k++) { // 경사면이 놓아진 걸 표시
                            slope[i][j + k] = 1;
                        }
                        j += L-1;
                    }
                    else if(arr[i][j] == arr[i][j+1]-1){
                        for (int k = 0; k < L; k++) {// 경사면이 놓아질 수 있는지
                            if(j-k < 0){
                                flag=1;
                                break;
                            }
                            if (arr[i][j] != arr[i][j - k] ||slope[i][j-k] == 1) {
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 1) {
                            break;
                        }
                        for (int k = 0; k < L; k++) { // 경사면이 놓아진 걸 표시
                            slope[i][j - k] = 1;
                        }
                    }
                    else
                        break;

                }
            }
        }
        slope = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                flag=0;
                if(arr[j+1][i] == 0){ // 해당 줄은 지나갈 수 있음
                    cnt++;
                    break;
                }
                if(arr[j][i] == arr[j+1][i]) // 다음 칸이랑 높이가 같으면 넘어감
                    continue;
                else { // 그렇지 않으면
                    if(arr[j][i] == arr[j+1][i]+1) { // 낮으면 == 내려가야하면
                        for (int k = 1; k <= L; k++) { // 경사면이 놓아질 수 있는지
                            if(j+k >= N){
                                flag=1;
                                break;
                            }
                            if ( arr[j+1][i] != arr[j+k][i]) {
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 1) {
                            break;
                        }
                        for (int k = 1; k <= L; k++) { // 경사면이 놓아진 걸 표시
                            slope[j+k][i] = 1;
                        }
                        j += L-1;
                    }
                    else if(arr[j][i] == arr[j+1][i]-1){
                        for (int k = 0; k < L; k++) {// 경사면이 놓아질 수 있는지
                            if(j-k < 0){
                                flag=1;
                                break;
                            }
                            if (arr[j][i] != arr[j-k][i] ||slope[j-k][i] == 1) {
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 1) {
                            break;
                        }
                        for (int k = 0; k < L; k++) { // 경사면이 놓아진 걸 표시
                            slope[j-k][i] = 1;
                        }
                    }
                    else
                        break;

                }
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
~~~
