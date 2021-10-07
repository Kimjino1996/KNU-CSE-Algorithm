package Algorithm;

import java.io.*;
import java.util.*;

public class Main {

	// static int [][]blocks = {{0,50},{0,22},{2,10},{1,4},{4,-13}};
	static int[][] blocks = { { 0, 92 }, { 1, 20 }, { 2, 11 }, { 1, -81 }, { 3, 98 } };

	public static void main(String[] args) {
		int[][] arr = new int[blocks.length][blocks.length];
		int num = 0;

		for (int i = 1; i <= blocks.length; i++) {
			num += i;
		}
		num -= blocks.length;

		for (int i = 0; i < blocks.length; i++) {
			Arrays.fill(arr[i], -1000000);
		}

		for (int floor = 0; floor < blocks.length; floor++) {
			arr[floor][blocks[floor][0]] = blocks[floor][1];
		}

		while (num > 0) {
			for (int floor = 0; floor < blocks.length - 1; floor++) {
				for (int i = 0; i < floor + 1; i++) {

					if (arr[floor][i] > -1000000 && arr[floor + 1][i] > -1000000 && arr[floor + 1][i + 1] > -1000000)
						continue;

					if (arr[floor][i] != -1000000) {
						if (arr[floor + 1][i] > -1000000) {
							arr[floor + 1][i + 1] = arr[floor][i] - arr[floor + 1][i];
							num--;
						}

						else if (arr[floor + 1][i + 1] > -1000000) {
							arr[floor + 1][i] = arr[floor][i] - arr[floor + 1][i + 1];
							num--;
						}
					} else {
						if (arr[floor + 1][i] > -1000000 && arr[floor + 1][i + 1] > -1000000) {
							arr[floor][i] = arr[floor + 1][i] + arr[floor + 1][i + 1];
							num--;
						}
					}
				}
			}
		}

		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				System.out.print(arr[i][j] + " ");
			}
		}

	}

}
