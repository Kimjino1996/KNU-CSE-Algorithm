#include"pch.h"
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
int sz, len;
int v[101][101];
int check[101][101];
int main(void)
{
	int result = 0;
	int dif;
	int i, j, k;
	
	scanf("%d %d", &sz, &len);
	for (i = 0; i < sz; i++)
		for (j = 0; j < sz; j++)
			scanf("%d", &v[i][j]);

	for (i = 0; i < sz; i++)
	{
		int flag = 0;
		for (j = 0; j < sz-1; j++)
		{
			if (flag == 1)break;
			if (v[i][j] == v[i][j + 1])
				continue;
			else
			{
				dif = v[i][j] - v[i][j + 1];
				if (dif == 1)
				{
					if (j + len >= sz)
					{
						flag = 1;
						break;
					}
					for (k = 0; k < len; k++)
					{
						if (v[i][j + 1] != v[i][j + 1 + k])
						{
							flag = 1;
							break;
						}
						check[i][j + 1 + k] = 1;
					}
					j = j + k-1;
					
				}
				else if (dif == -1)
				{
					if (j - len + 1 < 0)
					{
						flag = 1;
						break;
					}
					for (k = 0; k < len; k++)
					{
						if (check[i][j - len + 1+k] != 0)
						{
							flag = 1; break;
						}
						check[i][j - len + 1 + k] = 1;

					}
				}
				else
				{
					flag = 1;
					break;
				}


			}

		}
		if (flag == 0)
			result++;
		

	}
	for (i = 0; i < sz; i++)
		for (j = 0; j < sz; j++)
			check[i][j] = 0;

	for (i = 0; i < sz; i++)
	{
		int flag = 0;
		for (j = 0; j < sz - 1; j++)
		{
			if (flag == 1)break;
			if (v[j][i] == v[j+1][i])
				continue;
			else
			{
				dif = v[j][i] - v[j+1][i];
				if (dif == 1)
				{
					if (j + len >= sz)
					{
						flag = 1;
						break;
					}
					for (k = 0; k < len; k++)
					{
						if (v[j + 1][i] != v[j + 1 + k][i])
						{
							flag = 1;
							break;
						}
						check[j + 1 + k][i] = 1;
					}
					j = j + k-1;

				}
				else if (dif == -1)
				{
					if (j - len + 1 < 0)
					{
						flag = 1;
						break;
					}
					for (k = 0; k < len; k++)
					{
						if (check[j - len + 1 + k][i] != 0)
						{
							flag = 1; break;
						}
						check[j - len + 1 + k][i] = 1;

					}
				}
				else
				{
					flag = 1;
					break;
				}
			}
		}
		if (flag == 0)
			result++;


	}
	printf("%d", result);
	return 0;
}