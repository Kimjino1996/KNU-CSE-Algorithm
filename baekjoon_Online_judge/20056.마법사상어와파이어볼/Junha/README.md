~~~
import java.io.*;
import java.util.*;

class Main {

    static public class fireball implements Comparable<fireball> {
        int r;
        int c;
        int m;
        int s;
        int d;

        public fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public int compareTo(fireball o) {
            if (this.r == o.r){
                if(this.c > o.c)
                    return 1;
                else if(this.c < o.c)
                    return -1;
                return 0;
            }
            else {
                if(this.r > o.r)
                    return 1;
                else if (this.r < o.r)
                    return -1;
                return 0;
            }
        }
    }

    static int N, M, K;
    static fireball[][][] arr;
    static int[][] cnt;
    static PriorityQueue<fireball> pq = new PriorityQueue<>();
    static Queue<fireball> q = new LinkedList<>();
    static Queue<fireball> wq = new LinkedList<>();
    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new fireball[N + 2][N + 2][];
        cnt = new int[N + 2][N + 2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            q.add(new fireball(r, c, m, s, d));
            cnt[r][c] = 1;
        }

        for (int i = 0; i < K; i++) {
            int k = q.size();
            for (int j = 0; j < k; j++) {
                fireball fb = q.remove();
                fb.r += fb.s * dir[fb.d][0];
                fb.c += fb.s * dir[fb.d][1]; // 이동시킴

                while (fb.r <= 0)
                    fb.r = N + fb.r;
                while (fb.r > N)
                    fb.r = fb.r - N;
                while (fb.c <= 0)
                    fb.c = N + fb.c;
                while (fb.c > N)
                    fb.c = fb.c - N;

                pq.add(fb);
            }

            q.clear();
            while (!pq.isEmpty()) {
                fireball fb = pq.poll();
                if (wq.isEmpty()) {
                    wq.add(fb);
                } else {
                    if (wq.peek().r == fb.r && wq.peek().c == fb.c) {
                        wq.add(fb);
                    } else {
                        if (wq.size() == 1) {
                            q.add(wq.poll());
                            wq.add(fb);
                        } else {
                            int m_n = 0, s_n = 0;
                            int flag = 0;
                            int flag2 = 0;
                            int first = 0;
                            int cnt = wq.size();
                            fireball fb2 = new fireball(0, 0, 0, 0, 0);
                            while (!wq.isEmpty()) {
                                fb2 = wq.poll();
                                m_n += fb2.m;
                                s_n += fb2.s;

                                if (flag == 0) {
                                    first = fb2.d % 2;
                                    flag = 1;
                                } else if (fb2.d % 2 != first) {
                                    flag2 = 1;
                                }
                            }
                            m_n /= 5;
                            s_n /= cnt;
                            if (m_n > 0) {
                                q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 0 + flag2));
                                q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 2 + flag2));
                                q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 4 + flag2));
                                q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 6 + flag2));
                            }
                            wq.add(fb);
                        }
                    }

                }
                if (!wq.isEmpty() && pq.isEmpty()) {
                    if (wq.size() == 1) {
                        q.add(wq.poll());
                    } else {
                        int m_n = 0, s_n = 0;
                        int flag = 0;
                        int flag2 = 0;
                        int first = 0;
                        int cnt = wq.size();
                        fireball fb2 = new fireball(0, 0, 0, 0, 0);
                        while (!wq.isEmpty()) {
                            fb2 = wq.poll();
                            m_n += fb2.m;
                            s_n += fb2.s;

                            if (flag == 0) {
                                first = fb2.d % 2;
                                flag = 1;
                            } else if (fb2.d % 2 != first) {
                                flag2 = 1;
                            }
                        }
                        m_n /= 5;
                        s_n /= cnt;
                        if (m_n > 0) {
                            q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 0 + flag2));
                            q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 2 + flag2));
                            q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 4 + flag2));
                            q.add(new fireball(fb2.r, fb2.c, m_n, s_n, 6 + flag2));
                        }

                    }

                }
            }
        }
        int result = 0;
        while (!q.isEmpty()) {
            result += q.poll().m;
        }
        System.out.println(result);
        br.close();
    }


}
~~~
