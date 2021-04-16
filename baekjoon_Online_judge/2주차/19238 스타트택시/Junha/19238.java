import java.io.*;
import java.util.*;

class Main {

    static int N, M, F;
    static int[][] arr;
    static int[][] visit;

    static class client { // 손님의 출발 지점과 도착 지점에 대한 클래스
        int sx;
        int sy;
        int tx;
        int ty;

        public client(int sx, int sy, int tx, int ty) {
            this.sx = sx;
            this.sy = sy;
            this.tx = tx;
            this.ty = ty;
        }
    }

    static class position implements Comparable<position>{ // 택시의 위치를 위한 클래스. 우선순위 큐를 사용하기 위해 comparable하게 작성
        int x;
        int y;

        public position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(position o) { // 문제에서의 조건대로 행 번호 --> 열 번호 순서로 정렬
            if (this.x == o.x){
                if(this.y > o.y)
                    return 1;
                else
                    return -1;
            }
            else{
                if(this.x > o.x)
                    return 1;
                else
                    return -1;
            }
        }
    }

    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Vector<client> v = new Vector<>(); // 최초에 고객들의 정보를 저장
    static Queue<position> qp = new LinkedList<>(); // bfs를 수행하기 위해 사용하는 큐
    static PriorityQueue<position> pq = new PriorityQueue<>(); // 택시가 동시에 둘 이상의 고객에 접근하면 먼저 태울 우선순위를 매겨줌
    static int gx, gy; // 손님의 목적지를 저장하기 위한 변수
    static int fuel_out=0; // 연료가 다 되어 실패하는 경우를 표시하기 위한 flag 변수

    public static void bfs(int x, int y) { // 승객을 찾기위한 bfs
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + d[i][0];
            ny = y + d[i][1];

            if (nx >= 1 && nx < N+1 && ny >= 1 && ny < N+1 && arr[nx][ny] != 1&& visit[nx][ny] == 0) { 
                visit[nx][ny] = 1;
                qp.add(new position(nx,ny)); 
                if(arr[nx][ny] == 2){ // 승객을 찾으면 우선순위 큐에 넣기.
                    pq.add(new position(nx,ny));
                }
            }
        }
    }

    public static void bfs_taxi(int x, int y) { // 태운 승객의 목적지를 찾기위한 bfs
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + d[i][0];
            ny = y + d[i][1];

            if (nx >= 1 && nx < N+1 && ny >= 1 && ny < N+1 && arr[nx][ny] != 1 && visit[nx][ny] == 0) {
                visit[nx][ny] = 1;
                qp.add(new position(nx,ny));
                if(nx == gx && ny == gy){ // 목적지에 다다르면
                    pq.add(new position(nx,ny)); // 여기선 사실 우선순위 큐를 사용할 필요는 없지만 (목적지가 하나 뿐이므로) 그냥 있어서 사용하는거.
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자의 크기
        M = Integer.parseInt(st.nextToken()); // 승객의 수
        F = Integer.parseInt(st.nextToken()); // 연료의 양
        arr = new int[N+2][N+2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x, y; //택시의 현재 위치
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int tx = Integer.parseInt(st.nextToken());
            int ty = Integer.parseInt(st.nextToken());
            arr[sx][sy] = 2; // 테이블에 고객의 위치를 표현
            v.add(new client(sx,sy,tx,ty));
        }

        while (M > 0) {
            int cnt = 0;
            visit = new int[N+2][N+2];

            qp.add(new position(x,y));
            while(true) { // 가장 가까운 고객을 찾아감
                int k = qp.size();
                if(k  == 0){ // 큐가 비면 == 택시가 더 이상 갈 곳이 없으면
                    bw.write("-1\n");
                    bw.flush();
                    bw.close();
                    br.close();
                    System.exit(0); // 실패
                }
                for (int i = 0; i < k; i++) { // bfs 한 턴 수행
                    position p = qp.poll();
                    if(cnt == 0 && arr[p.x][p.y] == 2){ // 만일 시작했을 때 해당 위치에 고객이 있다면
                        pq.add(new position(p.x,p.y));
                        cnt--; // 연료의 양을 변하지 않게 하기 위해 cnt 값을 조절
                        break;
                    }
                    bfs(p.x, p.y);
                }
                cnt++;
                if(!pq.isEmpty()) // 고객을 한명이상 발견했다면
                    break;
            }

            F -= cnt; // 남은 연료를 계산
            if(F <= 0) { // 연료가 부족하다면 실패
                fuel_out=1;
                break;
            }

            position p1 = pq.poll();
            arr[p1.x][p1.y] = 0;
            for (int i = 0; i < v.size(); i++) {
                if(p1.x == v.elementAt(i).sx && p1.y == v.elementAt(i).sy){
                    gx =  v.elementAt(i).tx;
                    gy = v.elementAt(i).ty;
                    v.remove(i);
                    break;
                }
            } // 고객들의 정보를 저장한 벡터에서 현재 태운 고객의 목적지를 확인

            pq.clear();
            qp.clear(); // 사용한 큐들을 초기화
            qp.add(new position(p1.x,p1.y));
            visit = new int[N+2][N+2];
            cnt = 0;
            while(true) {
                int k = qp.size();
                if(k  == 0){ // 택시가 더이상 갈 곳이 없으면 실패
                    bw.write("-1\n");
                    bw.flush();
                    bw.close();
                    br.close();
                    System.exit(0);
                }
                for (int i = 0; i < k; i++) { // bfs를 한 턴 수행
                    position p = qp.poll();
                    visit[p.x][p.y] = 1;
                    bfs_taxi(p.x, p.y);

                    if(!pq.isEmpty()) // 목적지에 다다르면
                        break;
                }
                cnt++;
                if(!pq.isEmpty())
                    break;
            }
            F -= cnt; 
            if(F < 0) { // 연료가 부족하면 실패
                fuel_out=1;
                break;
            }
            F+= cnt*2; // 연료를 최신화
            
            x = pq.peek().x;
            y = pq.peek().y; // 택시의 현재 위치를 승객을 내려준 위치로 변경
            pq.clear();
            qp.clear();
            M--;
        }

        if(fuel_out == 1){ // 연료가 부족해서 반복문을 탈출했다면
            bw.write("-1\n");
        } else{ // 모든 승객을 내려주고 정상적으로 반복문을 탈출했다면
            bw.write(String.valueOf(F)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
