

def make_min(str1="",str2=""):
    num_list=list(str1)+list(str2)
    min=10
    min_index=0
    for i in range(len(num_list)):
        if(min>int(num_list[i])) and num_list[i]!='0':
            min=int(num_list[i])
            min_index=i
        elif (min>6) and num_list[i]=='0':
            min=6
            min_index=i
    if min==6 and num_list[0]!='0':
        temp = num_list[0]
        num_list[0] = '6'
        num_list[min_index] = temp
    elif min==6 and num_list[0]=='0':
        num_list[0] = '6'



    temp=num_list[0]
    num_list[0]=num_list[min_index]
    num_list[min_index]=temp

    num_list[1:].sort()
    sum_str=''.join(num_list)

    return sum_str




if __name__=="__main__":
    test_count=int(input())
    test_num=[]
    for _ in range(test_count):
        test_num.append(int(input()))
    # 1=>2,2=>5,3=>5,4=>4,5=>5,6=>6,7=>3,8=>7,9=>6 0=>6


    # 만약에 8을 구할거면 1,7 2,6, 3,5, 4,4 조합 다 더해봐야되나?
    # 왜냐면 15가 1,7,7 이 나을지 2,6 이나을지 모르니까 근데 앵간하면 1들어가면
    # 젤 작긴한데 greedy 로 1 넣고 0 다때려박는게 이득일듯? 아니다 디피 써야될듯

    # 7 =>8 8=>10 9=>18 10 =>101 그럼 7까만 검사하는게 나을듯

    #2~7 은 다음 8->14 까지 15 -> 21

    #k는 현재 기준
    # 12 3   10 7

    # 1 5 + 7  2 4 +7


    #arr_max = ['x', 1, 7]
    # 3개로 나눠지면 7 => 홀수면 7스타트,111 짝수면 1 로도배
    num_count=0
    while num_count<test_count:

        arr_min = ['999999999', '999999999', '1', '7', '4', '2', '0', '8']
        k=8
        while k<test_num[num_count]+1:
            min=-1
            for i in range(1,8):
                temp=make_min(arr_min[int(k/8)*8 - i], arr_min[k-int(k/8)*8+i])
                if min==-1 or min>int(temp):
                    if int(temp)>0:
                        min=int(temp)
            arr_min.append(str(min))

            k=k+1

        min_value=[]
        for z in range(int(test_num[num_count]/2)):
            min_value.append('1')
        if(test_num[num_count]%2==1):
            min_value[0]='7'

        if(test_num[num_count]!=6):
            print(arr_min[test_num[num_count]],''.join(min_value))
        else:
            print('6',''.join(min_value))
        num_count=num_count+1


        #남은 과제 100개일 때 크기가 넘어버리는데 어떻게 비교할것인가 ?
        # 음 str로 그냥비교하자.
        # 13 일때가 문제 68 임