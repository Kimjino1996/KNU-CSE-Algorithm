```c++
#include <string>
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
struct fuck {
	int weight, direction, speed;
	fuck(int a, int b, int c) {
		weight = a;
		direction = b;
		speed = c;
	}
};
vector <fuck> v[10][10];
vector <fuck> v2[10][10];
int dir[8][2] = { {-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1} };
int sz, fb, mov;

void move()
{
	for (int i = 0; i < sz; i++)
		for (int j = 0; j < sz; j++)
		{
			if (v[i][j].size() > 0)
			{
				for (int k = 0; k < v[i][j].size(); k++)
				{
					int nextrow = i + (dir[v[i][j].at(k).direction][0] * v[i][j].at(k).speed) % sz;
					int nextcol = j + (dir[v[i][j].at(k).direction][1] * v[i][j].at(k).speed) % sz;

					if (nextrow < 0)
						nextrow += sz;
					else if (nextrow >= sz)
						nextrow = nextrow - sz;
					if (nextcol < 0)
						nextcol += sz;
					else if (nextcol >= sz)
						nextcol = nextcol - sz;

					v2[nextrow][nextcol].push_back(v[i][j].at(k));
				}
			}
		}

	for (int i = 0; i < sz; i++)
		for (int j = 0; j < sz; j++)
		{
			if (v[i][j].size() != 0)
				v[i][j].clear();
		}

	for (int i = 0; i < sz; i++)
		for (int j = 0; j < sz; j++)
		{
			if (v2[i][j].size() == 1)
				v[i][j] = v2[i][j];
			else if (v2[i][j].size() > 1)
			{
				fuck tem(0, v2[i][j].front().direction % 2, 0);
				int flag = 0;
				for (int k = 0; k < v2[i][j].size(); k++)
				{
					tem.weight = tem.weight + v2[i][j].at(k).weight;
					tem.speed = tem.speed + v2[i][j].at(k).speed;
					if (v2[i][j].at(k).direction % 2 != tem.direction)
						flag = 1;
				}
				tem.weight = tem.weight / 5;
				if (tem.weight == 0)
				{
					continue;
				}
				tem.speed = tem.speed / v2[i][j].size();
				for (int k = 0; k < 8; k = k + 2)
				{
					if (flag == 1)
					{
						k = k + 1;
						flag = 0;
					}
					v[i][j].push_back(fuck(tem.weight, k, tem.speed));
				}
			}
		}
	for (int i = 0; i < sz; i++)
		for (int j = 0; j < sz; j++)
		{
			if (v2[i][j].size() != 0)
				v2[i][j].clear();
		}
}
int main(void)
{
	int t1, t2, t3, t4, t5, result = 0;
	scanf("%d %d %d", &sz, &fb, &mov);
	for (int i = 0; i < fb; i++)
	{
		scanf("%d %d %d %d %d", &t1, &t2, &t3, &t4, &t5);
		v[t1-1][t2-1].push_back(fuck(t3, t5, t4));
	}
	for (int i = 0; i < mov; i++)
		move();
	for (int i = 0; i < sz; i++)
		for (int j = 0; j < sz; j++)
		{
			if (v[i][j].size() > 0)
				for (int k = 0; k < v[i][j].size(); k++)
					result += v[i][j].at(k).weight;
		}
	printf("%d", result);

	return 0;
}
```

