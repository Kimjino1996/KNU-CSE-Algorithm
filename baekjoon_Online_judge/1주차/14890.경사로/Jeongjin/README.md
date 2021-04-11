```java
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,L;
    static int sum;
    static int[][] stairs;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        stairs = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                stairs[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
          if(check_col(i) == 0)
              sum += check_colr(i);
          else
              sum++;
          if(check(i) == 0)
              sum+= check_r(i);
          else sum++;
        }
        System.out.println(sum);
    }
    static int check(int i){
        int now = stairs[i][0];
        int diff = 0;
        int tiltup = 0;
        int tiltdown = 0;
        for (int j = 0; j < N; j++) {
            if(diff == 0 &&now == stairs[i][j]) {
                tiltup++;
                tiltdown = 0;
            }
            else if(now - stairs[i][j] == -1 && tiltup >= L){//거꾸로 올라가기 + 경사로 가능
                tiltup = 1;
                now++;
            }
            else if(now - stairs[i][j] == 1){ //내려오기
               tiltup = 0;
                diff = 1;
                tiltdown++;
                if(tiltdown == L)
                {
                    now--;
                    tiltdown = 0;
                    diff = 0;
                }
            }
            else
                return 0;
        }if(tiltdown != 0)
            return 0;
        return 1;
    }
    static int check_r(int i){
        int now = stairs[i][N-1];
        int diff = 0;
        int tiltup = 0;
        int tiltdown = 0;
        for (int j = N-1; j >= 0; j--) {
            if(diff == 0 &&now == stairs[i][j]) {
                tiltup++;
                tiltdown = 0;
            }
            else if(now - stairs[i][j] == -1 && tiltup >= L){//거꾸로 올라가기 + 경사로 가능
                tiltup = 0;
                now++;
            }
            else if(now - stairs[i][j] == 1){ //내려오기
                diff = 1;
                tiltdown++;
                tiltup = 0;

                if(tiltdown == L)
                {
                    now--;
                    tiltdown = 0;
                    diff = 0;
                }
            }
            else
                return 0;
        }if(tiltdown != 0)
            return 0;
        return 1;
    }
    static int check_col(int i){
        int now = stairs[0][i];
        int diff = 0;
        int tiltup = 0;
        int tiltdown = 0;
        for (int j = 0; j < N; j++) {
            if(diff == 0 &&now == stairs[j][i]) {
                tiltup++;
                tiltdown = 0;
            }
            else if(now - stairs[j][i] == -1 && tiltup >= L){//거꾸로 올라가기 + 경사로 가능
                tiltup = 0;
                now++;
            }
            else if(now - stairs[j][i] == 1){ //내려오기
                diff = 1;
                tiltdown++;
                if(tiltdown == L)
                {
                    now--;               tiltup = 0;

                    tiltdown = 0;
                    diff = 0;
                }
            }
            else
                return 0;
        }if(tiltdown != 0)
            return 0;
        return 1;
    }
    static int check_colr(int i){
        int now = stairs[N-1][i];
        int diff = 0;
        int tilt = 0;
        int tiltdown = 0;
        for (int j = N-1; j >= 0; j--) {
            if(diff == 0 &&now == stairs[j][i]) {
                tilt++;
                tiltdown = 0;
            }

            else if(now - stairs[j][i] == -1 && tilt >= L){//거꾸로 올라가기 + 경사로 가능
                tilt = 0;
                now++;
            }
            else if(now - stairs[j][i] == 1){ //내려오기
                diff = 1;
                tiltdown++;
                if(tiltdown == L)
                {
                    now--;
                    tilt = 0;

                    tiltdown = 0;
                    diff = 0;
                }
            }
            else
                return 0;
        }
        if(tiltdown != 0)
            return 0;
        return 1;
    }
}
```