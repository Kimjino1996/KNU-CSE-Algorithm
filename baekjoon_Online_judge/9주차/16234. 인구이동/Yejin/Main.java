import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, L, R;
    static int[][] Map;
    static int[][] afterMap;
    static boolean[][] visit;
    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Queue<pos> queue = new LinkedList<>();
    static Stack<pos> merged = new Stack<>();
    static int tPeople = 0; // total people
    static int vCountry = 0; // visited country
    static int day = 0;

    static class pos{
        int x;
        int y;
        pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static pos unvisitedPos(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visit[i][j])
                    return new pos(i, j);
            }
        }
        return new pos(-1, -1); //모두 방문함
    }

    static void copyMap(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Map[i][j] = afterMap[i][j];
            }
        }
    }

    static void bfs() throws IOException {
        pos p = queue.poll();
        //////
        /*
        bw.write("방문함 : (" + p.x + "," + p.y + ")\n");
        bw.flush();
         */
        ///////

        for (int i = 0; i < d.length; i++) { // 이동 가능하면 스택에 넣어주기
            int moveX = p.x + d[i][0];
            int moveY = p.y + d[i][1];
            if(moveX >= 0 && moveY >= 0 && moveX < N && moveY < N){ // 맵을 벗어나지 않고
                int diff =  Math.abs(Map[moveX][moveY]- Map[p.x][p.y]);
                //////
                /*
                bw.write("방문할까?(" + moveX + "," + moveY + ")\n");
                bw.flush();

                 */
                ///////
                if(!visit[moveX][moveY] && diff >= L && diff <= R) { // 방문한 적 없고 인구차이가 범위 내이면
                    //////
                    /*
                    bw.write("차이 : " + diff + "\n");
                    bw.flush();
                    bw.write("큐에 넣자\n");
                    bw.flush();

                     */
                    ///////
                    queue.add(new pos(moveX, moveY));

                    visit[moveX][moveY] = true; // 방문체크
                    merged.push(new pos(moveX, moveY)); // 병합된 나라의 좌표들 넣어주기
                    tPeople += Map[moveX][moveY]; // 총 인구수 더해주기
                    vCountry++; // 방문한 나라 더하기
                }
            }
        }
    }

    static boolean IsSame(int[][] map1 , int[][] map2) throws IOException {
        ///////////
        /*
        bw.write("두 맵 비교하기\n");
        bw.flush();
        printMap(map1);
        printMap(map2);

         */
        ///////////
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map1[i][j] != map2[i][j]) {
                    ///////////
                    /*
                    bw.write("다릅니다.\n");
                    bw.flush();

                     */
                    //////////
                    return false;
                }
            }
        }
        /*
        bw.write("같습니다.\n");
        bw.flush();

         */
        return true;
    }

    static void clear(int[][] map){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = 0;
            }
        }
    }
    static void clearVisit(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visit[i][j] = false;
            }
        }
    }

    static void printMap(int[][] map) throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(map[i][j] + " ");
                bw.flush();
            }
            bw.write("\n");
            bw.flush();
        }
        bw.write("\n");
        bw.flush();
    }

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        Map = new int[N][N];
        afterMap = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            while(true){
                pos unvisited = unvisitedPos(); //방문 안한 곳 부터
                if(unvisited.x == -1 && unvisited.y == -1)
                    break;
                vCountry = 0;
                tPeople = 0;
                merged.clear();
                /*
                bw.write("좌표 (" + unvisited.x + "," + unvisited.y + ") 부터 탐색을 시작합니다.\n");
                bw.flush();

                 */
                queue.add(unvisited);
                visit[unvisited.x][unvisited.y] = true; // 방문체크
                merged.push(new pos(unvisited.x, unvisited.y)); // 병합된 나라의 좌표들 넣어주기
                tPeople += Map[unvisited.x][unvisited.y]; // 총 인구수 더해주기
                vCountry++; // 방문한 나라 더하기
                while(!queue.isEmpty()){
                    bfs();
                }

                // 평균 인구를 계산하여 afterMap에 넣어줌
                int avgPeople = tPeople/vCountry;
                ////////
                /*
                bw.write("방문한 나라 개수 : " + vCountry +"\n평균인구 : " + tPeople + "\n");
                bw.flush();

                 */
                //////////
                while(!merged.empty()){
                    pos p = merged.pop();
                    afterMap[p.x][p.y] = avgPeople;
                }

            }
            if(IsSame(afterMap,Map)){ //두 맵이 완전히 똑같을 시 인구이동 완료
                ///////////
                /*
                bw.write("인구이동을 완료합니다.\n");
                bw.flush();

                 */
                ///////////
                break;
            }
            day++;
            // 다를 시 다시 인구이동
            //////////
            /*
            bw.write("현재 map\n");
            bw.flush();
            printMap(Map);
            bw.write("나중 Map으로 덮어쓰기합니다. \n");
            bw.flush();
            printMap(afterMap);

             */
            ///////////
            copyMap();
            clear(afterMap);
            clearVisit();
        }

        bw.write(String.valueOf(day));
        bw.flush();
        bw.close();
        br.close();
    }
}