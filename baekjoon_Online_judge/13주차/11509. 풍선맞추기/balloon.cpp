#include<stdio.h>
#include<stdlib.h>

int main() {

	int N;
	int *height_arr;
	int* shoot_arr;
	scanf("%d", &N);
	height_arr = (int*)malloc(sizeof(int) * N);
	shoot_arr = (int*)malloc(sizeof(int) * N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &height_arr[i]);
	}
	int arrow_height;
	int shoot_count = 0;
	//arrow_height = height_arr[0];
	shoot_count++;
	shoot_arr[shoot_count-1] = height_arr[0];
	int flag = 0;
	for (int i = 0; i < N; i++) {
		flag = 0;
		for (int j = 0; j < shoot_count; j++) {
			if (shoot_arr[j] == height_arr[i]) {
				shoot_arr[j]--;
				flag = 1;//ÇÑ¹ß ¸ÂÃè´Ù.
			}
		}
		if (flag == 1) {
			continue;
		}
		else {
			shoot_count++;
			shoot_arr[shoot_count - 1] = height_arr[i]-1;//½ðÁï½Ã ÇÑ¹ß»®½Ã´Ù
			
		}
	}
	printf("%d", shoot_count);
}