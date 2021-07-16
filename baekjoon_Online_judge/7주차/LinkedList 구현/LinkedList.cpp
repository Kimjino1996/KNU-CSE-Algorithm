#include <stdio.h>
#include <malloc.h>

typedef struct link* linkPoint;

typedef struct link {
	int data;
	linkPoint next;
}link;


linkPoint createNode(linkPoint* head, int num) {
	linkPoint newNode;
	newNode = (linkPoint)malloc(sizeof(link));
	newNode->data = num;
	newNode->next = *head;
	return newNode;
}

void insertNode(linkPoint* head, int num) {
	linkPoint node = createNode(head, num);
	linkPoint cur;
	cur = *head;
	if (!*head) {
		node->next = node;
		*head = node;
	}

	else {
		while (cur->next != (*head)) {
			cur = cur->next;
		}
		cur->next = node;
	}
}

void printList(linkPoint head) {
	linkPoint cur;

	cur = head;
	while (true) {
		printf("%d", cur->data);

		if (cur->next == head)
			break;
		cur = cur->next;
	}
	printf("\n");
}
int popNode(linkPoint* head) {

	linkPoint cur;

	if ((*head)->next) {
		cur = *head;
		while (cur->next != (*head)) {
			cur = cur->next;
		}
		cur->next = cur->next->next;
		cur = cur->next;

		//확인용
		printf("1번");
		printList(cur);

		while (cur->next != cur) {
			for (int i = 0; i < 1; i++) {
				cur = cur->next;
			}
			cur->next = cur->next->next;
			cur = cur->next;

			//확인용
			printf("n번");
			printList(cur);
		}
		*head = cur;
		return cur->data;
	}
	else if ((*head)) {
		return (*head)->data;
	}
	else {
		return -1;
	}

}
int main() {
	int case_num;
	int node_num;
	int num;
	linkPoint head = NULL;

	scanf("%d", &case_num);
	for (int i = 0; i < case_num; i++) {
		scanf("%d", &node_num);
		for (int j = 0; j < node_num; j++) {
			scanf("%d", &num);
			insertNode(&head, num);
		}
		printList(head);
	}
	int answer = popNode(&head);
	printf("%d", answer);


}