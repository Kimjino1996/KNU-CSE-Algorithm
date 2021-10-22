import java.io.*;
import java.util.*;

public class Main {
    //입출력을 위한 변수 br, bw
    static BufferedReaderbr= new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriterbw= new BufferedWriter(new OutputStreamWriter(System.out));

    // 목초지 크기 NxN, 소K마리, 길 개수 R
    static intN,K,R;

    //static int meet[][]; // 0 만남여부 모름, -1 못 만남, 1 만남
    static intcount= 0;
    static booleanmeet[];
    static intd[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 오른쪽, 아래, 왼쪽, 위
    static booleanway[][][];
    static intcow[][];
    static poscowPos[];
    static booleanvisit[][];
    static intcowNum= -1;

    static class pos{
        int x;
        int y;
        pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static Queue<pos>q= new LinkedList<pos>();

    /*
    static int noMoveCow(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                if(meet[i][j] == 0)
                    return i;
            }
        }
        return -1;
    }
     */

    static void bfs() throws IOException {
        pos p =q.poll();
        if(cow[p.x][p.y] != 0){
meet[cow[p.x][p.y]-1] = true;
        }

        for (int i = 0; i <d.length; i++) {
            int x = p.x+d[i][0];
            int y = p.y+d[i][1];
            if(x >= 0 && y >= 0 && x <N&& y <N){
                if(visit[x][y] != true &&way[x][y][i] != true){
visit[x][y]=true;
q.add(new pos(x, y));
                }
            }
        }
    }

    /*
    static int noMeetCowCount(){
        int sadCow = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i==j) continue;
                if(meet[i][j] == -1)
                    sadCow++;
            }
        }
        return sadCow/2;
    }
     */

    public static void main(String[] args) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

N= Integer.parseInt(st.nextToken());
K= Integer.parseInt(st.nextToken());
R= Integer.parseInt(st.nextToken());

        //meet = new int[N][N];
meet= new boolean[N];
way= new boolean[N][N][4];
cow= new int[N][N];
visit= new boolean[N][N];
cowPos= new pos[K];

        for (int i = 0; i <R; i++) {
            // 길 설정
            // 초기값은 false이고 진행방향 (0,0) -> (0,1) 으로 오려한다면 0,1은 오른쪽진행방향을 통해 올 경우 길이 있다는 true 설정을 해준다.
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;
            if(x1-x2 == -1){
way[x1][y1][3]=true;
way[x2][y2][1]=true;
            }
            else if(x1-x2 == 1){
way[x1][y1][1]=true;
way[x2][y2][3]=true;
            }
            else if(y1-y2 == -1){
way[x1][y1][2]=true;
way[x2][y2][0]=true;
            }
            else if(y1-y2 == 1){
way[x1][y1][0]=true;
way[x2][y2][2]=true;
            }
        }

        // 소 위치 설정
        for (int i = 0; i <K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
cow[x][y] = i+1;
cowPos[i] = new pos(x, y);
        }

        for (int i = 0; i <K; i++) {
cowNum= i;
            pos p =cowPos[i];
visit[p.x][p.y] = true;
q.add(p);
            while(!q.isEmpty()){
bfs();
            }
            // 못 만난 쌍 ++
            for (int j = i+1; j <K; j++) {
                if(!meet[j])
count++;
            }
            // visit 초기화
            for (int j = 0; j <N; j++) {
                for (int k = 0; k <N; k++) {
visit[j][k] = false;
                }
            }
            // meet 초기화
            for (int j = i+1; j <K; j++) {
meet[j] = false;
            }
        }

        /*
        while(true){
            cowNum = noMoveCow();
            if(cowNum == -1)
                break;
            int x = 0;
            int y = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(cow[i][j] == cowNum+1){
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            pos p = new pos(x, y);
            q.add(p);
            visit[p.x][p.y] = true;
            while(!q.isEmpty()){
                bfs();
            }
            for (int i = 0; i < N; i++) {
                // meet 테이블 못만난소 정리
                if(meet[cowNum][i] != 1){
                    meet[cowNum][i] = -1;
                }
                // 만난소 상대소 테이블 정리
                meet[i][cowNum] = meet[cowNum][i];
            }
            // visit 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visit[i][j] = false;
                }
            }
        }

        int sadCow = noMeetCowCount();


         */
bw.write(String.valueOf(count));
bw.flush();
bw.close();
br.close();
    }
}
