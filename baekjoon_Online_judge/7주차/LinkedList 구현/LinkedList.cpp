#include <stdio.h>
#include <malloc.h>

typedef struct link* linkPoint;

typedef struct link {
	int data;
	linkPoint next;
}link;


linkPoint createNode(int num) {
	linkPoint newNode;
	newNode = (linkPoint)malloc(sizeof(link));
	newNode->data = num;
	newNode->next = NULL;
	return newNode;
}

void insertNode(linkPoint* head, int num) {
	linkPoint node = createNode(num);
	linkPoint cur;
	cur = *head;
	if (!*head) {
		*head = node;

	}

	else {
		while (cur->next) {
			cur = cur->next;
		}
		cur->next = node;
	}
}

void popNode(linkPoint head) {
	printf("hihi");
}
int main() {
	int case_num;
	int node_num;
	int num;
	linkPoint head=NULL;
	
	scanf("%d", &case_num);
	for (int i = 0; i < case_num; i++) {
		scanf("%d", &node_num);
		for (int j = 0; j < node_num; j++) {
			scanf("%d", &num);	
			insertNode(&head, num);
		}
	}

	
}