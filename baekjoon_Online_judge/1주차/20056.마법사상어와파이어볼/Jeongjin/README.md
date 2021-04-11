```java
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        PriorityQueue<fireboll>[] pq = new PriorityQueue[K + 1];
        Queue<fireboll>[] q = new Queue[K+1];
        for (int i = 0; i <= K; i++) {
            pq[i] = new PriorityQueue<>();
            q[i] = new LinkedList<>();
        }
        int[][] map = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row, col, mess, speed, dir;
            row = Integer.parseInt(st.nextToken()) - 1;
            col = Integer.parseInt(st.nextToken()) - 1;
            mess = Integer.parseInt(st.nextToken());
            speed = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());
            q[0].offer(new fireboll(row, col, mess, speed, dir));
            map[row][col]++;
        }
        for (int i = 0; i < K; i++) {
            int next = i + 1;
            while (!q[i].isEmpty()) {
                fireboll p = q[i].poll();
                map[p.x][p.y]--;
                p.x = ((p.x + (dx[p.dir] * p.speed) + (10000 * N)) % N);
                p.y = ((p.y + (dy[p.dir] * p.speed) + (10000 * N)) % N);
                map[p.x][p.y]++;
                pq[i].offer(p);
            }

            while (!pq[i].isEmpty()) {
                fireboll p = pq[i].peek();
                if (map[p.x][p.y] > 1) {
                    int cnt, sum, speed, odd, even;
                    cnt = map[p.x][p.y];
                    sum = 0;
                    speed = 0;
                    odd = 0;
                    even = 0;
                    for (int j = 0; j < cnt; j++) {
                        p = pq[i].poll();
                        map[p.x][p.y]--;
                        speed += p.speed;
                        sum += p.mess;
                        if (p.dir % 2 == 0) {
                            even++;
                        } else {
                            odd++;
                        }
                    }
                    sum /= 5;
                    speed /= cnt;
                    if (sum != 0) {
                        if (even * odd == 0) {
                            for (int j = 0; j < 4; j++) {
                                q[next].offer(new fireboll(p.x, p.y, sum, speed, j * 2));
                            }
                        } else {
                            for (int j = 0; j < 4; j++) {
                                q[next].offer(new fireboll(p.x, p.y, sum, speed, j * 2 + 1));
                            }
                        }
                        map[p.x][p.y] += 4;
                    }
                } else
                    q[next].offer(pq[i].poll());
            }
        }
            int answer = 0;
            while (!q[K].isEmpty())
                answer+= q[K].poll().mess;
            System.out.println(answer);
    }
}
class fireboll implements Comparable<fireboll>{
    int x, y, mess, speed, dir;

    public fireboll(int x, int y, int mess, int speed, int dir) {
        this.x = x;
        this.y = y;
        this.mess = mess;
        this.speed = speed;
        this.dir = dir;
    }
    @Override
    public int compareTo(fireboll o) {
        return o.x > this.x ? -1 : o.x == this.x ? Integer.compare(this.y, o.y) : 1;
    }
}
```