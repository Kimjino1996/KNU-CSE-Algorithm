package com.company;

import java.io.*;
import java.util.*;

public class Main {
    //입출력을 위한 변수 br, bw
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[][] Map; // 전체 게임맵 저장
    static boolean[][] visit; // 방문기록
    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 진행방향(대각선 제외)
    static Queue<pos> queue = new LinkedList<>(); // dfs를 위한 큐
    static LinkedList<Stack> bomb_list = new LinkedList<>(); // 색깔뭉치별 터트릴 리스트
    static Stack<pos> bomb = new Stack<>();
    static int row = 12;
    static int col = 6;
    static int explode = 0;

    static class pos{
        int x;
        int y;
        pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static pos unvisitedPos(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(!visit[i][j])
                    // 방문한 적 없고 .(빈칸)이 아닐 경우.. bfs에서 해주어야 할거같기도 한데 빈칸일 경우에도 계속 들어가면 오버헤드가 클 것같아서?
                    return new pos(i, j);
            }
        }
        return new pos(-1, -1); //모두 방문함
    }
    static void clearVisit(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(Map[i][j].equals("."))
                    visit[i][j] = true;
                else
                    visit[i][j] = false;
            }
        }
    }
    static void bfs() {
        pos p = queue.poll();
        for (int i = 0; i < d.length; i++) {
            int moveX = p.x + d[i][0];
            int moveY = p.y + d[i][1];
            if(moveX < row && moveY < col && moveX >= 0 && moveY >= 0){
                // 이동 할 범위가 게임 맵 이내여야
                if(visit[moveX][moveY] != true && Map[p.x][p.y].equals(Map[moveX][moveY])){
                    // 이동 할 곳이 색깔이 같고 방문한 적 없음
                    queue.add(new pos(moveX, moveY));
                    visit[moveX][moveY] = true;
                    bomb.push(new pos(moveX, moveY));
                }
            }
        }
    }
    static void falling(){
        for (int j = 0; j < col; j++) {
            for (int i = row - 1; i > 0; i--) {
                if(Map[i][j].equals(".")){
                    // 가장 가까운 위에 있는 뿌요 떨어뜨리기
                    for (int k = i-1; k >= 0; k--) {
                        if(!Map[k][j].equals(".")){
                            Map[i][j] = Map[k][j];
                            Map[k][j] = ".";
                            break;
                        }
                    }
                }
            }
        }
    }

    static void printMap() throws IOException {
        /*
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bw.write(String.valueOf(Map[i][j]));
                bw.flush();
            }
            bw.write("\n");
            bw.flush();
        }
        bw.write("\n");
        bw.flush();

         */
    }

    public static void main(String[] args) throws IOException{
        Map = new String[row][col];
        visit = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                    // 초기 게임 맵 저장
                    Map[i][j] = String.valueOf(str.charAt(j));
            }
        }

        clearVisit();

        while(true){
            if(bomb.size() >= 4){
                Stack b = new Stack();
                b = (Stack) bomb.clone();
                bomb_list.add(b);
            }
            bomb.clear();
            pos p = unvisitedPos();
            if(p.x == -1 && p.y == -1){
                // 방문 할 노드가 더 이상 없다면
                printMap();
                if(bomb_list.size() == 0) // 터뜨릴 뿌요가 없다면 끝
                    break;
                for (int i = 0; i < bomb_list.size(); i++) {
                    Stack b = bomb_list.get(i);
                    while(!b.isEmpty()){
                        // 뿌요 터트리기
                        pos pop = (pos)b.pop();
                        Map[pop.x][pop.y] = ".";
                    }
                    printMap();
                }
                // 터트리고 중력 받아서 밑으로 떨어지기
                falling();
                printMap();
                explode++;
                clearVisit();
                bomb_list.clear();
            }
            else{
                //방문 할 노드가 있다면 큐에 담고 bfs 시작
                queue.add(p);
                visit[p.x][p.y] = true;
                bomb.push(p);
                while(!queue.isEmpty()){
                    bfs();
                }
            }
        }

        bw.write(String.valueOf(explode));
        bw.flush();
        bw.close();
        br.close();
    }
}
