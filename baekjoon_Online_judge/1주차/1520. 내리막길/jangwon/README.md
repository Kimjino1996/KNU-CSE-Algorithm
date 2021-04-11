```c++
#include <string>
#include <vector>
#include <stdio.h>
#include<math.h>
#include<sstream>
#include <algorithm>
#include<iostream>
#include<queue>
using namespace std;

int v[500][500];
int visited[1001];
int res[1001];
queue <pair<int,int> > q;
int a, b,countt, flag = 0, sflag = 0;
int dir[4][2] = { {0,1},{1,0},{0,-1},{-1,0} };
int dp[500][500];
int row, col;
int nowx, nowy, nextx, nexty;
int dfs(int x, int y)
{
	if (dp[x][y] != -1)return dp[x][y];
	if (x < 0 || y < 0 || x >= row || y >= col)return 0;
	if (x == 0 && y == 0) return 1;

	dp[x][y] = 0;
	for (int i = 0; i < 4; i++)
	{
		nextx = x + dir[i][0];
		nexty = y + dir[i][1];
		if (v[x][y] < v[nextx][nexty])
			dp[x][y]+=dfs(nextx, nexty);
	}
	return dp[x][y];
}
int main(void)
{
	
	
	scanf("%d %d", &row, &col);
	for (int i = 0; i < row; i++)
		for (int j = 0; j < col; j++)
			scanf("%d", &v[i][j]);
	memset(dp, -1, sizeof(dp));
	printf("%d", dfs(row-1,col-1));
	return 0;
}
```

