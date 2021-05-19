
size = int(input())

a=[list(map(int,input().split())) for _ in range(size)]

#print(a)

# x, y,dir 랑 방향 필요
# 0과 1밖에 없긴해
dir =[[0,1],[1,0]]

stack =[]
start_x=0 # row
start_y=0 # col
start_dir=0

stack.append([start_x,start_y,start_dir])
flag=0# 못도착A
while True:
    if len(stack)<=0:
        print("Hing")
        break
    if flag==1:
        print("HaruHaru")
        break
    cur_x=stack[-1][0]
    cur_y=stack[-1][1]
    cur_dir=stack[-1][2]
    if cur_dir >= 2:
        stack.pop()
    elif cur_dir<2:
        stack[-1][2]+=1
        next_x=a[cur_x][cur_y]*dir[cur_dir][0]+cur_x
        next_y=a[cur_x][cur_y]*dir[cur_dir][1]+cur_y
        if(next_x>=0 and next_x<size and next_y>=0 and next_y<size):
            stack.append([next_x,next_y,start_dir])
            if a[next_x][next_y]==-1:
               flag=1