~~~c++
#include <vector>
#include <cmath>
#include <stdio.h>
#include<math.h>
#include<sstream>
#include<cstring>
#include <algorithm>
#include<iostream>
#include<queue>
using namespace std;
int v[20][20];
int dice[4][3];
int check[101][101];
int dir[4][2] = { {0,1},{0,-1},{-1,0},{1,0} };
int row, col,sz;
void move(int order)
{
	int temp;
	if (order == 2)
	{
		temp = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = temp;
		temp = dice[1][1];
		dice[1][1] = dice[3][1];
		dice[3][1] = temp;
		temp = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = temp;
	}
	else if (order == 1)
	{
		temp = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = temp;
		temp = dice[1][1];
		dice[1][1] = dice[3][1];
		dice[3][1] = temp;
		temp = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = temp;
	}
	else if (order == 3)
	{
		for (int i = 0; i < 3; i++)
		{
			temp = dice[i][1];
			dice[i][1] = dice[i+1][1];
			dice[i+1][1] = temp;
		}
	}
	else if (order == 4)
	{
		for (int i = 3; i > 0; i--)
		{
			temp = dice[i][1];
			dice[i][1] = dice[i - 1][1];
			dice[i - 1][1] = temp;
		}
	}
}
int main(void)
{
	int dx, dy;
	int order;
	scanf("%d %d %d %d %d",&row,&col,&dx,&dy,&sz);
	for (int i = 0; i < row; i++)
		for (int j = 0; j < col; j++)
			scanf("%d", &v[i][j]);

	for (int i = 0; i < sz; i++)
	{
		scanf("%d", &order);
		if (dx + dir[order - 1][0] < 0 || dx + dir[order - 1][0] >= row || dy + dir[order - 1][1] < 0 || dy + dir[order - 1][1] >= col)
			continue;
		dx = dx + dir[order - 1][0]; dy = dy + dir[order - 1][1];
		move(order);
		if (v[dx][dy] == 0)
		{
			v[dx][dy] = dice[3][1];
		}
		else
		{
			dice[3][1] = v[dx][dy];
			v[dx][dy] = 0;
		}
		printf("%d\n", dice[1][1]);
	}
	return 0;
}
~~~

