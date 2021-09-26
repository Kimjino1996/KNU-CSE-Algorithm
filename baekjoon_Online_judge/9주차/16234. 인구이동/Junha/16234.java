import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int L;
	static int R;
	static int[][] arr;
	static boolean[][] visit;

	static class position {
		int x;
		int y;

		position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<position> q = new LinkedList<>();
	static int sum;

	static void dfs(position p) {
		int nx, ny;
		for (int i = 0; i < 4; i++) {
			nx = p.x + d[i][0];
			ny = p.y + d[i][1];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny] == true)
				continue;

			int tmp = Math.abs(arr[p.x][p.y] - arr[nx][ny]);
			if (tmp >= L && tmp <= R) {
				q.add(new position(nx, ny));
				visit[nx][ny] = true;
				sum += arr[nx][ny];
				dfs(new position(nx, ny));
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();

		arr = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int flag = 0;
		int cnt = 0;
		while (true) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j] == false) {
						sum = arr[i][j];
						visit[i][j] = true;
						q.add(new position(i, j));
						dfs(new position(i, j));

						if (q.size() > 1) {
							int avg = sum / q.size();
							while (!q.isEmpty()) {
								position p = q.poll();
								arr[p.x][p.y] = avg;
								flag = 1;
							}
						}

						q.clear();
					}
				}
			}
			if (flag == 0)
				break;
			flag = 0;
			cnt++;
			for (int i = 0; i < N; i++) {
				Arrays.fill(visit[i], false);
			}
		}

		System.out.println(cnt);
	}
}
