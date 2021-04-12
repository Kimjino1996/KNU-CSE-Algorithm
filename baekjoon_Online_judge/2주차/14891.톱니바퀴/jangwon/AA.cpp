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
int dir[8][2] = { {-1,1},{-1,2},{0,2},{1,2},{1,1},{1,0},{0,0},{-1,0} };
int gear[12][3];
void move()
{
	for (int what = 1; what < 5; what++)
	{
		int check = (what - 1) * 3 + 1;
		if (gear[check][1] == 1)
		{
			gear[check][1] = gear[check - 1][0];
			for (int i = 0; i < 8; i++)
			{
				int temp = gear[check][1];
				gear[check][1] = gear[check + dir[i][0]][dir[i][1]];
				gear[check + dir[i][0]][dir[i][1]] = temp;
			}
			gear[check][1] = 1;
		}
		else if (gear[check][1] == -1)
		{
			gear[check][1] = gear[check - 1][1];
			for (int i = 0; i < 8; i++)
			{
				int temp = gear[check][1];
				gear[check][1] = gear[check + dir[7 - i][0]][dir[7 - i][1]];
				gear[check + dir[7 - i][0]][dir[7 - i][1]] = temp;
			}
			gear[check][1] = -1;
		}
	}
}
void checking(int what)
{
	
	for (int i = what - 1; i > 0; i--)
	{
		int check = i * 3 + 1;
		if (gear[check][0] != gear[check - 3][2])
		{
			gear[check - 3][1] = gear[check][1] * -1;
		}
	}
	for (int i = what; i < 4; i++)
	{
		int check = (i-1) * 3 + 1;
		if (gear[check][2] != gear[check + 3][0])
		{
			gear[check + 3][1] = gear[check][1] * -1;
		}
	}
}
void print()
{
	for (int i = 0; i < 12; i++)
	{
		for (int j = 0; j < 3; j++)
			printf("%d ", gear[i][j]);
		printf("\n");
		if (i % 3 == 2)
			printf("\n");
	}
}
void put()
{
	gear[1][1] = 2;
	gear[4][1] = 2;
	gear[7][1] = 2;
	gear[10][1] = 2;
}
int main(void)
{
	int order;
	int answer = 0;
	int what, direction;
	for (int i = 0; i < 8; i++)
		scanf("%1d", &gear[dir[i][0]+1][dir[i][1]]);
	for (int i = 0; i < 8; i++)
		scanf("%1d", &gear[dir[i][0] + 4][dir[i][1]]);
	for (int i = 0; i < 8; i++)
		scanf("%1d", &gear[dir[i][0] + 7][dir[i][1]]);
	for (int i = 0; i < 8; i++)
		scanf("%1d", &gear[dir[i][0] + 10][dir[i][1]]);

	scanf("%d", &order);
	for (int i = 0; i < order; i++)
	{
		scanf("%d %d", &what, &direction);
		put();
		gear[(what - 1) * 3 + 1][1] = direction;
		checking(what);
		move();
		print();
	}
	if (gear[0][1] == 1)
		answer += 1;
	if (gear[3][1] == 1)
		answer += 2;
	if (gear[6][1] == 1)
		answer += 4;
	if (gear[9][1] == 1)
		answer += 8;
	printf("%d", answer);
	return 0;
}
