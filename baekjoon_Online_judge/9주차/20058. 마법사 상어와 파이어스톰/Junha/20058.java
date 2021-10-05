import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int Q;
	static int[][] arr;
	static int[][] temp;
	static boolean[][] visit;
	static int L;
	static int np;
	static int sum = 0;
	static int max = 0;
	static int count = 0;

	static class position {
		int x;
		int y;

		position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Queue<position> q = new LinkedList<>(); // 반드시 큐를 이용하여 얼음을 감소시킬 것.

	static void change(int fx, int fy, int range) {
		while (range >= 2) {
			for (int i = 0; i < range; i++) {
				temp[fx + i][fy + range - 1] = arr[fx][fy + i];
				temp[fx + range - 1][fy + range - 1 - i] = arr[fx + i][fy + range - 1];
				temp[fx + i][fy] = arr[fx + range - 1][fy + i];
				temp[fx][fy + range - 1 - i] = arr[fx + i][fy];
			}

			fx += 1;
			fy += 1;
			range -= 2;
		}

	}

	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void check(int x, int y) {
		int nx, ny;
		int chk = 0;
		for (int i = 0; i < 4; i++) {
			nx = x + d[i][0];
			ny = y + d[i][1];

			if (nx < 0 || ny < 0 || nx >= np || ny >= np || temp[nx][ny] < 1) {
				continue;
			}
			chk++;
		}
		if (chk < 3 && temp[x][y] > 0) { // 0 이하로 얼음을 내려가게 하지 말 것.
			q.add(new position(x, y));
		}
	}

	public static void dfs(int x, int y) {
		int nx, ny;
		for (int i = 0; i < 4; i++) {
			nx = x + d[i][0];
			ny = y + d[i][1];

			if (nx < 0 || ny < 0 || nx >= np || ny >= np || arr[nx][ny] < 1 || visit[nx][ny] == true) {
				continue;
			}

			visit[nx][ny] = true;
			count++;
			dfs(nx, ny);

		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		Q = sc.nextInt();
		np = (int) Math.pow(2, N);
		arr = new int[np][np];
		temp = new int[np][np];

		for (int i = 0; i < np; i++) {
			for (int j = 0; j < np; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		for (int times = 0; times < Q; times++) {
			L = sc.nextInt();
			if (L > 0) { // L이 0이면 얼음 감소만 시킬 것.
				int mp = (int) Math.pow(2, L);

				for (int i = 0; i < np; i += mp) {
					for (int j = 0; j < np; j += mp) {
						change(i, j, mp);
					}
				}
			} else {
				for (int i = 0; i < np; i++) {
					for (int j = 0; j < np; j++) {
						temp[i][j] = arr[i][j];
					}
				}
			}

			for (int i = 0; i < np; i++) {
				for (int j = 0; j < np; j++) {
					check(i, j);
					arr[i][j] = temp[i][j];
				}
			}
			while (!q.isEmpty()) {
				position p = q.poll();
				arr[p.x][p.y]--;
			}
		}

		visit = new boolean[np][np];
		for (int i = 0; i < np; i++) {
			for (int j = 0; j < np; j++) {
				if (arr[i][j] > 0)
					sum += arr[i][j];
				if (visit[i][j] == false && arr[i][j] > 0) {
					visit[i][j] = true;
					count = 1;
					dfs(i, j);
					max = Math.max(count, max);
				}
			}
		}

		System.out.println(sum);
		System.out.println(max);

	}

}
