#https://www.acmicpc.net/problem/14891

# S=1, N=0
# 톱니바퀴의 회전은 한칸을 기준으로 회전은 시계방향과 반시계
# 회전시킬 톱니를 정하고 방향을 결정
# 한쪽이 회전시키면 회전 방향과 반대방향으로 회전하게됨
# 1인경우 시계방향 => 시계 방향으로 회전하면 12시 [0] 에 있던게 1시 [1] 로감
# 이전것이 회전하지 않았을 경우
def rotate(num,dir):
    prev=0
    rot_flag=[0,0,0,0]
    if num == 0: # 첫번째 돌리면 2, 3, 4 순서대로 회전
        if topni[0][2]!=topni[1][6]:
            # dir 1 일 경우 -dir 시계 방향임
            prev=1
            rot_flag[1]=1
        if prev==1:
            prev=0
            if topni[1][2]!=topni[2][6]:
                prev=1
                rot_flag[2]=1
        if prev==1:
            if topni[2][2]!=topni[3][6]:
                rot_flag[3]=1
        if rot_flag[1]==1:
            topni[1] = [topni[1][(j + dir) % 8] for j in range(8)]
        if rot_flag[2]==1:
            topni[2] = [topni[2][(j - dir) % 8] for j in range(8)]
        if rot_flag[3]==1:
            topni[3] = [topni[3][(j + dir) % 8] for j in range(8)]

    elif num == 1: # 0, 2 3
        if topni[0][2] != topni[1][6]:
            rot_flag[0]=1

        if topni[2][6] != topni[1][2]:
            prev=1
            rot_flag[2]=1
        if prev==1:
            if topni[3][6] != topni[2][2]:
                rot_flag[3]=1
        if rot_flag[0]==1:
            topni[0] = [topni[0][(j + dir) % 8] for j in range(8)]
        if rot_flag[2]==1:
            topni[2] = [topni[2][(j + dir) % 8] for j in range(8)]
        if rot_flag[3]==1:
            topni[3] = [topni[3][(j - dir) % 8] for j in range(8)]
    elif num == 2: #0,1 ,3
        if topni[1][2]!=topni[2][6]:
            prev=1
            rot_flag[1]=1
        if prev==1:
            if topni[0][2]!=topni[1][6]:
                rot_flag[0]=1

        if topni[3][6]!=topni[2][2]:
            rot_flag[3]=1
        if rot_flag[1]==1:
            topni[1] = [topni[1][(j + dir) % 8] for j in range(8)]
        if rot_flag[0]==1:
            topni[0] = [topni[0][(j - dir) % 8] for j in range(8)]
        if rot_flag[3]==1:
            topni[3] = [topni[3][(j + dir) % 8] for j in range(8)]

    elif num == 3: # 2,1,0
        if topni[2][2]!=topni[3][6]:
            prev=1
            rot_flag[2]=1
        if prev==1:
            prev=0
            if topni[1][2]!=topni[2][6]:
                prev=1
                rot_flag[1]=1
        if prev==1:
            if topni[0][2]!=topni[1][6]:
                rot_flag[0]=1
        if rot_flag[2]==1:
            topni[2] = [topni[2][(j + dir) % 8] for j in range(8)]
        if rot_flag[1]==1:
            topni[1] = [topni[1][(j - dir) % 8] for j in range(8)]
        if rot_flag[0]==1:
            topni[0] = [topni[0][(j + dir) % 8] for j in range(8)]
    return 1

if __name__=="__main__":
    topni = [list(input()) for _ in range(4)]
    command_num=int(input())
    top_num=[]
    dir_num=[]
    for _ in range(command_num):
        temp1,temp2=list(map(int,input().split()))
        top_num.append(temp1-1)
        dir_num.append(temp2)
    for i in range(command_num):
        rotate(top_num[i],dir_num[i])
        topni[top_num[i]] = [topni[top_num[i]][(j - dir_num[i]) % 8] for j in range(8)]

    value=1
    sum=0
    for i in range(4):
        if(topni[i][0]=='1'):
            sum=sum+value
        value=value*2
    print(sum)
