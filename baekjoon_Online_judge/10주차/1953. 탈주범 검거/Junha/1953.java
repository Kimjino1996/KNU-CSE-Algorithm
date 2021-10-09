import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static int fx, fy;
	static int L;
	static int[][] arr;
	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] visit;
	static int cnt = 0;

	static class position {
		int x;
		int y;

		position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Queue<position> q = new LinkedList<>();

	static void bfs(position p) {
		int nx, ny;
		for (int i = 0; i < 4; i++) {
			if (i == 0 && (arr[p.x][p.y] == 3 || arr[p.x][p.y] == 5 || arr[p.x][p.y] == 6))
				continue;
			else if (i == 1 && (arr[p.x][p.y] == 2 || arr[p.x][p.y] == 6 || arr[p.x][p.y] == 7))
				continue;
			else if (i == 2 && (arr[p.x][p.y] == 3 || arr[p.x][p.y] == 4 || arr[p.x][p.y] == 7))
				continue;
			else if (i == 3 && (arr[p.x][p.y] == 2 || arr[p.x][p.y] == 4 || arr[p.x][p.y] == 5))
				continue;

			nx = p.x + d[i][0];
			ny = p.y + d[i][1];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] == true || arr[nx][ny] == 0)
				continue;

			if (i == 0 && (arr[nx][ny] == 3 || arr[nx][ny] == 4 || arr[nx][ny] == 7))
				continue;
			else if (i == 1 && (arr[nx][ny] == 2 || arr[nx][ny] == 4 || arr[nx][ny] == 5))
				continue;
			else if (i == 2 && (arr[nx][ny] == 3 || arr[nx][ny] == 5 || arr[nx][ny] == 6))
				continue;
			else if (i == 3 && (arr[nx][ny] == 2 || arr[nx][ny] == 6 || arr[nx][ny] == 7))
				continue;

			cnt++;
			visit[nx][ny] = true;
			q.add(new position(nx, ny));
		}
	}

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		arr = new int[51][51];
		visit = new boolean[51][51];
		for (int test_case = 1; test_case <= T; test_case++) {
			q.clear();
			cnt = 0;

			N = sc.nextInt();
			M = sc.nextInt();
			fx = sc.nextInt();
			fy = sc.nextInt();
			L = sc.nextInt();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			visit[fx][fy] = true;
			cnt++;
			q.add(new position(fx, fy));

			for (int time = 1; time < L; time++) {
				int k = q.size();
				if (k == 0)
					break;
				for (int i = 0; i < k; i++) {
					position p = q.poll();
					bfs(p);
				}
			}

			System.out.println("#" + test_case + " " + cnt);

			for (int i = 0; i < 51; i++) {
				Arrays.fill(arr[i], 0);
				Arrays.fill(visit[i], false);
			}
		}
	}
}
