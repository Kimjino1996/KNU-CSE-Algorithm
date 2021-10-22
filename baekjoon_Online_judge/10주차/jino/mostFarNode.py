from collections import deque


# import collections
def solution(n, edge):
    answer = 0

    edge_list = [[] for _ in range(n + 1)]
    for from_item, to_item in edge:
        edge_list[from_item].append(to_item)
        edge_list[to_item].append(from_item)

    edge_value = [-1 for _ in range(n + 1)]
    start = 1
    count = 0

    edge_value[start] = 0
    que = deque()
    que.append(start)
    # count += 1
    while len(que) >= 1:
        cur = que.popleft()

        # print(len(edge_list[cur]))
        while len(edge_list[cur]) >= 1:
            dest = edge_list[cur].pop()

            if edge_value[dest] == -1:
                edge_value[dest] = edge_value[cur] + 1
                que.append(dest)
        # print(que)
        # print(edge_list)
        # print(edge_value)
    answer = edge_value.count(max(edge_value))

    return answer