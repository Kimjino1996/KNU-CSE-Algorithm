#include "pch.h"
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
int dir[4][2] = { {0,-1},{1,0},{0,1},{-1,0} };
int v[506][506];
int road[506][506];
int sz;
vector <pair<int, int> > now;
struct form {
	int mr;
	int mc;
	double per;
	form(int a, int b, double c) {
		mr = a;
		mc = b;
		per = c;
	}
};
vector <form> smove;

void makeroad()
{
	int row = sz / 2 + 2;
	int col = sz / 2 + 2;
	int count = 1;
	int direction = 0;
	road[row][col] = direction;
	now.push_back(make_pair(row, col));
	while (1)
	{
		
		for (int i = 0; i < count; i++)
		{
			road[row][col] = direction;
			row = row + dir[direction][0];
			col = col + dir[direction][1];
			
		}
		if (count == sz)
			break;
		direction++;
		direction = direction % 4;
		for (int i = 0; i < count; i++)
		{
			road[row][col] = direction;
			row = row + dir[direction][0];
			col = col + dir[direction][1];

		}
		direction++;
		direction = direction % 4;

		count++;
		

	}
}
void work(int row, int col)
{
	int direction, nextrow, nextcol;
	direction= road[row][col];
	nextrow = row + dir[direction][0];
	nextcol = col + dir[direction][1];
	now.pop_back();
	now.push_back(make_pair(nextrow, nextcol));
	if (direction == 0)
	{
		int sand = v[nextrow][nextcol];
		v[nextrow][nextcol] = 0;
		int alpha = sand;
		for (int i = 0; i < smove.size(); i++)
		{
			int minus = (int)(sand * smove.at(i).per);
			v[row + smove.at(i).mr][col + smove.at(i).mc] += minus;
			alpha = alpha - minus;
		}
		v[nextrow][nextcol - 1] += alpha;
	}
	else if (direction == 2)
	{
		int sand = v[nextrow][nextcol];
		v[nextrow][nextcol] = 0;
		int alpha = sand;
		for (int i = 0; i < smove.size(); i++)
		{
			int minus = (int)(sand * smove.at(i).per);
			v[row + smove.at(i).mr][col - smove.at(i).mc] += minus;
			alpha = alpha - minus;
		}
		v[nextrow][nextcol + 1] += alpha;
	}
	else if (direction == 3)
	{
		int sand = v[nextrow][nextcol];
		v[nextrow][nextcol] = 0;
		int alpha = sand;
		for (int i = 0; i < smove.size(); i++)
		{
			int minus = (int)(sand * smove.at(i).per);
			v[row + smove.at(i).mc][col + smove.at(i).mr] += minus;
			alpha = alpha - minus;
		}
		v[nextrow - 1][nextcol] += alpha;
	}
	else if (direction == 1)
	{
		int sand = v[nextrow][nextcol];
		v[nextrow][nextcol] = 0;
		int alpha = sand;
		for (int i = 0; i < smove.size(); i++)
		{
			int minus = (int)(sand * smove.at(i).per);
			v[row - smove.at(i).mc][col + smove.at(i).mr] += minus;
			alpha = alpha - minus;
		}
		v[nextrow+1][nextcol] += alpha;
	}

}
void makedirect()
{
	smove.push_back(form(-2, -1, 0.02));
	smove.push_back(form(-1, -2, 0.1));
	smove.push_back(form(-1, -1, 0.07));
	smove.push_back(form(-1, 0, 0.01));
	smove.push_back(form(0, -3, 0.05));
	smove.push_back(form(1, -2, 0.1));
	smove.push_back(form(1, -1, 0.07));
	smove.push_back(form(1, 0, 0.01));
	smove.push_back(form(2, -1, 0.02));
}
int main(void)
{
	int full = 0;
	int how = 0;
	scanf("%d", &sz);

	for (int i = 2; i < sz + 2; i++)
	{
		for (int j = 2; j < sz + 2; j++)
		{
			scanf("%d", &v[i][j]);
			full += v[i][j];
		}
	}
	memset(road, -1, sizeof(road));
	makeroad();
	makedirect();
	while (1)
	{
		int a = now.front().first;
		int b = now.front().second;
		if (a == 2 && b == 2)
			break;
		work(a, b);
	}
	for(int i=2;i<sz+2;i++)
		for (int j = 2; j < sz + 2; j++)
		{
			how += v[i][j];
		}
	printf("%d", full - how);
	return 0;
}
