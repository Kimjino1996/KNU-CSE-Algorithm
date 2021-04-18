# 855 0909stop 0914 start
# 손님도착지 =>연료충전
# 벽있음
#현재위치에서 거리가 가장 짤븡ㄴ 승객을 고름.
# 행번호 우선, 그다음 열번호
#sys.stdin.readline()
#deque
#list의 옅은 카피

from collections import deque
def detect_object(row,col,dst_row,dst_col):
    k_map=[[0]*map_size for _ in range(map_size)]
    dir=[[-1,0],[0,1],[1,0],[0,-1]]#위 오른쪽 밑 왼쪽
    q=deque()
    count = 0
    q.append([row,col,count])
    k_map[row][col]=1

    if row==dst_row and col==dst_col:
        return 0
    while True:
        if(len(q)>0):
            temp=q.popleft()
            for i in range(4):
                if temp[0]+dir[i][0]<0 or temp[0]+dir[i][0] >=map_size or temp[1]+dir[i][1]<0 or temp[1]+dir[i][1]>=map_size:
                    continue
                elif temp[0]+dir[i][0]==dst_row and temp[1]+dir[i][1]==dst_col:
                    return temp[2]+1
                elif(k_map[temp[0]+dir[i][0]][temp[1]+dir[i][1]]==0 and r_map[temp[0]+dir[i][0]][temp[1]+dir[i][1]]==0):
                    q.append([temp[0]+dir[i][0],temp[1]+dir[i][1],temp[2]+1])
                    k_map[temp[0]+dir[i][0]][temp[1]+dir[i][1]]=1
                else:
                    continue
        else:
            break
    return -1
def detect_son(row,col):
    k_map=[[0]*map_size for _ in range(map_size)]
    dir=[[-1,0],[0,1],[1,0],[0,-1]]#위 오른쪽 밑 왼쪽
    q=deque()
    count = 0
    q.append([row,col,count])
    k_map[row][col]=1
    flag=0 # 이거바뀌면 해당 정답 list 에서 하나 골라서 꺼내자
    answer_list=[]

    if s_map[row][col]==1 and s_map[row][col]==1:
        return row,col,0
    while True:
        if(len(q)>0):
            temp=q.popleft()
            for i in range(4):
                if temp[0]+dir[i][0]<0 or temp[0]+dir[i][0] >=map_size or temp[1]+dir[i][1]<0 or temp[1]+dir[i][1]>=map_size:
                    continue
                elif s_map[temp[0]+dir[i][0]][temp[1]+dir[i][1]]==1:
                    answer_list.append([temp[0]+dir[i][0],temp[1]+dir[i][1],temp[2]+1])
                    flag=1
                elif(k_map[temp[0]+dir[i][0]][temp[1]+dir[i][1]]==0 and r_map[temp[0]+dir[i][0]][temp[1]+dir[i][1]]==0 and flag!=1):
                    q.append([temp[0]+dir[i][0],temp[1]+dir[i][1],temp[2]+1])
                    k_map[temp[0]+dir[i][0]][temp[1]+dir[i][1]]=1
                else:
                    continue
        else:
            if(flag==1):
                answer_list.sort(key=lambda x: (x[0], x[1]),reverse=True)
                answer_row, answer_col,answer_cnt = answer_list.pop()
                return answer_row, answer_col,answer_cnt
            break
    return -1,-1,-1

if __name__=="__main__":
    map_size,p_num,fuel=list(map(int,input().split()))
    r_map=[list(map(int,input().split())) for _ in range(map_size)]
    taxi_row,taxi_col=list(map(int,input().split()))
    taxi_row=taxi_row-1
    taxi_col=taxi_col-1
    total=[list(map(int,input().split())) for _ in range(p_num)]
    s_map = [[0] * map_size for _ in range(map_size)]

    for i in range(p_num):
        total[i][0]=total[i][0]-1
        total[i][1]=total[i][1]-1
        total[i][2]=total[i][2]-1
        total[i][3]=total[i][3]-1
        s_map[total[i][0]][total[i][1]]=1
    for _ in range(p_num):
        min=-1
        temp_row,temp_col,min=detect_son(taxi_row, taxi_col)
        if min==-1:
            fuel=-1
            break
        if(fuel<min):
            fuel=-1
            break
        for i in range(len(total)):
           if temp_row==total[i][0] and temp_col==total[i][1]:
                next_taxi=total.pop(i)
                s_map[temp_row][temp_col]=0
                break
        detect_val=detect_object(temp_row, temp_col, next_taxi[2], next_taxi[3])
        if detect_val<0:
            fuel=-1
            break
        elif fuel-min>=detect_val:
            fuel=fuel+detect_val-min
            taxi_row=next_taxi[2]
            taxi_col=next_taxi[3]
        else:
            fuel=-1
            break
    print(fuel)
