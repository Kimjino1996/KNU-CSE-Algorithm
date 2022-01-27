#include <stdio.h>
#include <stdlib.h>

using namespace std;

typedef struct poistion {
	int x;
	int y;
	int break_block;
	int time;
}position;

void push(position temp);
position pop();
int bfs(int start_x, int start_y);

int** arr;
int** break_arr;
int N; // 목적지 Y 좌표
int M; // 목적지 X 좌표
int K; // 부술수 있는 벽 개수

position* queue;
int front = 0;// 빼낼 곳
int rear = -1; //넣는곳 넣기전에 ++


int main() {


	char* c;
	int ans;

	scanf("%d", &N);
	scanf("%d", &M);
	scanf("%d", &K);
	queue = (position*)malloc(sizeof(position) * M * N * (K + 1));

	arr = (int**)malloc(sizeof(int*) * (N + 1));
	break_arr = (int**)malloc(sizeof(int*) * (N + 1));
	c = (char*)malloc(sizeof(char) * M);
	for (int i = 1; i <= N; i++) {
		arr[i] = (int*)malloc(sizeof(int) * (M + 1));
		break_arr[i] = (int*)malloc(sizeof(int) * (M + 1));
		scanf("%s", c);
		for (int j = 1; j <= M; j++) {
			arr[i][j] = c[j - 1] - '0';
			break_arr[i][j] = 0;
		}
	}
	//check_map(M, N, K);
/*	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			printf("%d ", arr[i][j]);
		}
		printf("\n");
	}
*/

	ans = bfs(1, 1);

	printf("%d", ans + 1);

	return 0;
}

int bfs(int start_x, int start_y) {

	position temp;
	position move;
	int dir[4][2] = { {0,1},{1,0},{0,-1},{-1,0} };//{x,y}
	temp.x = start_x;
	temp.y = start_y;
	temp.break_block = 0;
	temp.time = 0;

	if (temp.x == M && temp.y == N) {
		return 0;
	}
	push(temp);

	while (1) {
		if (front > rear) {
			break;
		}
		temp = pop();

		// 아래랑이랑 오른쪽만 움직이기  취소 위 왼쪽도 가서 수정하기 

		for (int i = 0; i < 4; i++) {
			move.x = temp.x + dir[i][0];
			move.y = temp.y + dir[i][1];
			move.break_block = temp.break_block;
			move.time = temp.time + 1;
			if (move.x > M || move.x<1 || move.y>N || move.y < 1) {

			}
			else if (move.x == M && move.y == N) {
				return move.time;
			}
			else if (arr[move.y][move.x] == 2 && break_arr[move.y][move.x] <= move.break_block) { // 원래 벽돌있던지점을 무조건 2로 방문 등록하면 안되나보네 

			}
			else if (arr[move.y][move.x] == 3 && break_arr[move.y][move.x] <= move.break_block + 1) {

			}
			else if (arr[move.y][move.x] == 1) { // punch break
				if (move.break_block >= K) {

				}
				else {
					move.break_block++;
					arr[move.y][move.x] = 3;
					break_arr[move.y][move.x] = move.break_block;
					push(move);
				}
			}
			//else if (arr[move.y][move.x] == 0 || arr[move.y][move.x] == 2 || arr[move.y][move.x] == 3) {
			else {
				arr[move.y][move.x] = 2;
				break_arr[move.y][move.x] = move.break_block;
				push(move);
			}
		}

	}
	//	printf("\n hihi :%d %d temp: %d %d", front, rear,temp.x,temp.y);
	return -2;


}
void push(position temp) {
	queue[++rear].x = temp.x;
	queue[rear].y = temp.y;
	queue[rear].time = temp.time;
	queue[rear].break_block = temp.break_block;
}

position pop() {
	return queue[front++];
}

/*반례

7 4 2
0101
1100
1110
0000
0111
0111
0100

정답 16

visit 무작정 해당 칸에 방문등록을 하는게 아니라 해당 지점에서 벽돌을 몇번 깼는지도 인지하고 넘겨야 한다.

*/