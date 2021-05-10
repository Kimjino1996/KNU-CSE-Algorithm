from itertools import combinations
from itertools import product

combi_list = []


def solution(user_id, banned_id):
    answer = 0
    # print(user_id)

    # print(banned_id)

    capt_id = []

    for bann in banned_id:
        capt_list = []
        for user in user_id:
            flag = 1  # 발견 했다.
            if len(user) == len(bann):
                for i in range(len(bann)):
                    if bann[i] == user[i] or bann[i] == '*':
                        continue
                    else:
                        flag = 0
                        break
                if flag == 1:
                    capt_list.append(user)
        if len(capt_list) > 0:
            capt_id.append(capt_list)
    # print(capt_id)
    s = ""
    k = 0
    recur(s, 0, capt_id)
    # print(combi_list)
    # print(capt_id)
    count = 0
    answer_list1 = []
    for item in combi_list:
        temp_list = []
        for i in range(len(item)):
            temp_list.append(capt_id[i][int(item[i])])
        temp_list2 = list(set(temp_list))
        if len(temp_list2) == len(temp_list):
            answer_list1.append(temp_list)

    # print(answer_list1)
    # print()
    # print()
    for i in range(len(answer_list1)):
        answer_list1[i].sort()
    answer = len(list(set([tuple(set(item)) for item in answer_list1])))
    # print(answer)

    return answer


def recur(string, num, capt_id):
    if (num >= len(capt_id) or len(string) > len(capt_id)):
        combi_list.append(string)
        return string
    for i in range(len(capt_id[num])):
        recur(string + str(i), num + 1, capt_id)

