import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int K;
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

	static ArrayList<position> cow_list = new ArrayList<>();
	static Queue<position> q = new LinkedList<>();
	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int cnt = 0;

	static void bfs(position p) {
		int nx, ny;

		for (int i = 0; i < 4; i++) {
			nx = p.x + d[i][0];
			ny = p.y + d[i][1];

			if (nx < 0 || ny < 0 || nx >= N * 2 - 1 || ny >= N * 2 - 1 || visit[nx][ny] == true || arr[nx][ny] == 1)
				continue;

			visit[nx][ny] = true;
			q.add(new position(nx, ny));
		}
	}

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N * 2 - 1][N * 2 - 1];
		visit = new boolean[N * 2 - 1][N * 2 - 1];
		K = sc.nextInt();
		R = sc.nextInt();

		for (int i = 0; i < R; i++) {
			int sx = sc.nextInt() - 1;
			int sy = sc.nextInt() - 1;
			int ex = sc.nextInt() - 1;
			int ey = sc.nextInt() - 1;

			if (sx == ex) {
				arr[(sx * 2 + ex * 2) / 2][(sy * 2 + ey * 2) / 2] = 1;
				if ((sx * 2 + ex * 2) / 2 - 1 >= 0)
					arr[(sx * 2 + ex * 2) / 2 - 1][(sy * 2 + ey * 2) / 2] = 1;
				if ((sx * 2 + ex * 2) / 2 + 1 < N * 2 - 1)
					arr[(sx * 2 + ex * 2) / 2 + 1][(sy * 2 + ey * 2) / 2] = 1;
			} else if (sy == ey) {
				arr[(sx * 2 + ex * 2) / 2][(sy * 2 + ey * 2) / 2] = 1;
				if ((sy * 2 + ey * 2) / 2 - 1 >= 0)
					arr[(sx * 2 + ex * 2) / 2][(sy * 2 + ey * 2) / 2 - 1] = 1;
				if ((sy * 2 + ey * 2) / 2 + 1 < N * 2 - 1)
					arr[(sx * 2 + ex * 2) / 2][(sy * 2 + ey * 2) / 2 + 1] = 1;
			}
		}

		for (int i = 0; i < K; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;

			arr[x * 2][y * 2] = 2;
			cow_list.add(new position(x * 2, y * 2));
		}

		for (int cow = 0; cow < cow_list.size(); cow++) {
			q.add(cow_list.get(cow));
			visit[cow_list.get(cow).x][cow_list.get(cow).y] = true;
			while (!q.isEmpty()) {
				bfs(q.poll());
			}

			for (int i = 0; i < N * 2 - 1; i++) {
				for (int j = 0; j < N * 2 - 1; j++) {
					if (visit[i][j] == false && arr[i][j] == 2) {
						cnt++;
					}
				}
				Arrays.fill(visit[i], false);
			}
		}
		System.out.println(cnt / 2);
	}
}
