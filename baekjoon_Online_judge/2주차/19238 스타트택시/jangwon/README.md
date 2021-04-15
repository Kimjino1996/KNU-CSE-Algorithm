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
int v[21][21];
int trow, tcol;
int check[21][21];
int dir[4][2] = { {-1,0},{0,-1},{0,1},{1,0} };
int fuel;
int sz, obj;
int nnum;
struct customer {
	int crow;
	int ccol;
	int corow;
	int cocol;
	customer(int a, int b, int c, int d) {
		crow = a;
		ccol = b;
		corow = c;
		cocol = d;
	}
	bool operator<(const customer &b)const {
		return crow < b.crow;
	}
};
struct road {
	int row;
	int col;
	int count;
	road(int a, int b, int c) {
		row = a;
		col = b;
		count = c;
	}
	bool operator<(const road &b)const {
		return row < b.row;
	}
};
struct compare {
	bool operator()(road &I, road &C)
	{
		if (I.count != C.count)
			return I.count > C.count;
		else if (I.row != C.row)
			return I.row > C.row;
		return I.col > C.col;
	}
};

vector <customer> cust;
int move()
{
	
	int tnextrow, tnextcol,nextcount;
	priority_queue <road,vector<road>,compare> find;
	vector <road> an;
	find.push(road(trow,tcol,0));
	check[trow][tcol] = 1;
	while (!find.empty())
	{
		int flag = 0;
		int tnowrow = find.top().row;
		int tnowcol = find.top().col;
		int nowcount = find.top().count;
		for (int i = 0; i < cust.size(); i++)
		{
			if (cust[i].crow == tnowrow && cust[i].ccol == tnowcol)
			{
				an.push_back(road(tnowrow, tnowcol, i));
				flag = 1;
			}
		}

		if (flag == 1)
		{
			sort(an.begin(), an.end());
			trow = an[0].row;
			tcol = an[0].col;
			nnum = an[0].count;
			return nowcount;
		}
		find.pop();
		for (int i = 0; i < 4; i++)
		{
			tnextrow = tnowrow + dir[i][0];
			tnextcol = tnowcol + dir[i][1];
			if (tnextrow<1 || tnextrow>sz || tnextcol<1 || tnextcol>sz)
				continue;
			if (v[tnextrow][tnextcol] == 1||check[tnextrow][tnextcol]==1)
				continue;
			nextcount = nowcount + 1;
			check[tnextrow][tnextcol] = 1;
			find.push(road(tnextrow, tnextcol, nextcount));
		}

	}
	return fuel + 1;
	
}
int go()
{
	int tnextrow, tnextcol, nextcount;
	queue <road> find;
	find.push(road(trow, tcol, 0));
	while (!find.empty())
	{
		int tnowrow = find.front().row;
		int tnowcol = find.front().col;
		int nowcount = find.front().count;
		if (cust[nnum].corow == tnowrow && cust[nnum].cocol == tnowcol)
		{
			trow = tnowrow;
			tcol = tnowcol;
			cust.erase(cust.begin() + nnum );
			return nowcount;
		}
		find.pop();
		for (int i = 0; i < 4; i++)
		{
			tnextrow = tnowrow + dir[i][0];
			tnextcol = tnowcol + dir[i][1];
			if (tnextrow<1 || tnextrow>sz || tnextcol<1 || tnextcol>sz)
				continue;
			if (v[tnextrow][tnextcol] == 1||check[tnextrow][tnextcol]==1)
				continue;
			nextcount = nowcount + 1;
			check[tnextrow][tnextcol] = 1;
			find.push(road(tnextrow, tnextcol, nextcount));
		}
	}
	return fuel + 1;
}
int main(void)
{
	
	int crow, ccol, corow, cocol;
	int plus, minus;
	scanf("%d %d %d", &sz, &obj, &fuel);
	for (int i = 0; i < sz; i++)
		for (int j = 0; j < sz; j++)
			scanf("%d", &v[i+1][j+1]);
	scanf("%d %d", &trow, &tcol);
	for (int i = 0; i < obj; i++)
	{
		scanf("%d %d %d %d", &crow, &ccol, &corow, &cocol);
		cust.push_back(customer(crow, ccol, corow, cocol));
	}
	sort(cust.begin(), cust.end());
	for (int i = 0; i < obj; i++)
	{
		minus = move();
		memset(check, 0, sizeof(check));
		fuel = fuel - minus;
		if (fuel <= 0)
		{
			printf("%d", -1);
			return 0;
		}
		plus=go();
		memset(check, 0, sizeof(check));
		if (fuel - plus < 0)
		{
			printf("%d", -1);
			return 0;
		}
		fuel = fuel + plus;
	}
	printf("%d", fuel);
	return 0;
}
```

