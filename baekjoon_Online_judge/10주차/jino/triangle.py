def solution(triangle):
    answer = 0

    # 이전 최고 위치 기억 해당 루트와
    # 현재 최고 위치로 이동할 수 있는지, 이동할 수 없다면 무엇이 큰지
    # 이렇게 하지말고 모든 위치에 이동할 수있는 최대값 구하면 되잖아
    # count=0
    # record=[]
    scored = [[] for _ in range(len(triangle))]
    # for item_list in triangle:
    #    for item in item_list:
    #        record.append(item)

    # scored.append(record[0])

    scored[0].append(triangle[0][0])
    # for item_list in traingle:
    for i in range(1, len(triangle)):
        for j in range(len(triangle[i])):
            # 그 전줄을 참고 해야함
            temp = -1
            if j - 1 >= 0:
                temp = scored[i - 1][j - 1] + triangle[i][j]

            elif j <= len(triangle[i - 1]):
                temp2 = scored[i - 1][j] + triangle[i][j]

            if temp < temp2:
                temp = temp2
            scored[i].append(temp)
    print(scored)
    # print(scored)
    return answer