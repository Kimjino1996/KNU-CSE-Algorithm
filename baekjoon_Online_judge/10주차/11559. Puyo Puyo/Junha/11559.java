import java.io.*;
import java.util.*;

public class Main {
	static char[][] arr = new char[12][6];
	static boolean[][] visit = new boolean[12][6];

	static class position {
		int x;
		int y;

		position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int flag = 0;
	static Queue<position> q = new LinkedList<>();

	static void dfs(position p, char ch) {
		int nx, ny;

		for (int i = 0; i < 4; i++) {
			nx = p.x + d[i][0];
			ny = p.y + d[i][1];

			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6 || arr[nx][ny] != ch || visit[nx][ny] == true) {
				continue;
			}
			q.add(new position(nx, ny));
			visit[nx][ny] = true;
			dfs(new position(nx, ny), ch);
		}
	}

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 12; i++) {
			String s = sc.next();
			for (int j = 0; j < 6; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		int cnt = 0;
		while (true) {
			flag = 0;

			for (int i = 0; i < 12; i++) { // 부술 칸 찾아서 부수기
				for (int j = 0; j < 6; j++) {
					if (arr[i][j] != '.' && visit[i][j] == false) {
						visit[i][j] = true;
						q.add(new position(i, j));
						dfs(new position(i, j), arr[i][j]);
						if (q.size() >= 4) { // 큐의 크기가 4 이상이다 == 4개 이상의 같은 모양이 있다 == 부순다
							flag = 1; // 4개 이상인 부분이 하나라도 있으면 while문 지속
							while (!q.isEmpty()) {
								position p = q.poll();
								arr[p.x][p.y] = '.';
							}
						} else { // 다음 칸에 영향을 미치지 않기 위해 큐 비워주기
							q.clear();
						}

					}
				}
			}

			if (flag == 0) {
				break;
			}

			for (int i = 1; i < 12; i++) { // 빈 칸 없애주기
				for (int j = 0; j < 6; j++) {
					if (arr[i][j] == '.' && arr[i - 1][j] != '.') {
						for (int k = i; k > 0; k--) {
							arr[k][j] = arr[k - 1][j];
						}
						arr[0][j] = '.';
					}
				}
			}

			for (int i = 0; i < 12; i++) {
				Arrays.fill(visit[i], false);
			}

			cnt++;

		}
		System.out.print(cnt);
	}
}
