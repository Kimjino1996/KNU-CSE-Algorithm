import java.io.*;
import java.util.*;


class Solution
{
	static int N;
	static int K;
	static int [][]arr;
	static boolean [][] check;
	static ArrayList<position> list = new ArrayList<>();
	int x, y;
	static int max;
	
	static class position{
		int x;
		int y;
		int c; //깎기 찬스를 사용했는지
		int d; //현재까지의 길이
		
		position(int x, int y, int c, int d){
			this.x = x;
			this.y = y;
			this.c = c;
			this.d = d;
		}
	}
	
	static int [][]d = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void dfs(position p) {
		int nx, ny;
		for(int i=0;i<4;i++) {
			nx = p.x + d[i][0];
			ny = p.y + d[i][1];
			
			if(nx < 0 || ny < 0|| nx >= N || ny >= N || check[nx][ny] == true)
				continue;
			
			if(arr[nx][ny] >= arr[p.x][p.y] && p.c == 1)
				continue;
			
			if(arr[nx][ny] >= arr[p.x][p.y] && p.c == 0) {
				for(int j=1;j<=K;j++) {
					if(arr[nx][ny]-j < arr[p.x][p.y] ) {
						arr[nx][ny] -= j;
						max = Math.max(max, p.d+1);
						check[p.x][p.y] = true;
						dfs(new position(nx, ny, 1, p.d + 1));
						check[p.x][p.y] = false;
						arr[nx][ny] += j;
					}
				}
				continue;
			}
			
			max = Math.max(max, p.d+1);
			check[p.x][p.y] = true;
			dfs(new position(nx, ny, p.c, p.d + 1));
			check[p.x][p.y] = false;

		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			K = sc.nextInt();
			arr = new int[N][N];
			check = new boolean[N][N];
			max = 0;
			
			int max_height = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					arr[i][j] = sc.nextInt();
					max_height = Math.max(max_height, arr[i][j]);
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(arr[i][j] == max_height) {
						list.add(new position(i,j,0,1));
					}
				}
			}
			
			for(int start = 0; start<list.size(); start++) {	
				check[list.get(start).x][list.get(start).y] = true;
				dfs(list.get(start));
				check[list.get(start).x][list.get(start).y] = false;
				
			}
			
			System.out.println("#"+test_case + " " + max);
			list.clear();
		}
	}
}
