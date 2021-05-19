from collections import deque

dir =[[1,-1],[1,0],[1,1],[0,-1],[0,0],[0,1],[-1,-1],[-1,0],[-1,1]]

r_size,c_size=map(int,input().split())


r_map=[list(input().split()) for _ in range(r_size)]

print(r_map)
cur_r=0
cur_c=0
move_que=deque()
crazy_stack=[]
for i in range(len(r_size)):
    for j in range(len(c_size)):
        if('I'==r_map[i][j]):
            cur_r=i
            cur_c=j
        elif('R'==r_map[i][j]):
            crazy_stack.append([i,j])
game_over=0

while True:
    if len(move_que)<=0:
        break
    cur_dir=move_que.popleft()-1
    r_map[cur_r][cur_c]='.'
    if r_map[cur_r+dir[cur_dir][0]][cur_c+dir[cur_dir][1]]=='R':
        game_over=1
        break
    r_map[cur_r+dir[cur_dir][0]][cur_c+dir[cur_dir][1]]='I'
    cur_r+=dir[cur_dir][0]
    cur_c+=dir[cur_dir][1]

    min_dir =0
    min=99999
    stack_count=0
    while True:
        if len(crazy_stack)<=stack_count:
            break
        crazy_row,crazy_col=crazy_stack[stack_count]


        for i in range(len(dir)):
            next_c_row=crazy_row+dir[i][0]
            next_c_col=crazy_col+dir[i][1]
            temp=abs(cur_r-next_c_row)+abs(cur_c-next_c_col)
            if(min>temp):
                min=temp
                min_dir=i
        r_map[crazy_stack[stack_count][0]][crazy_stack[stack_count][1]]='.'
        crazy_stack[stack_count][0]+=dir[min_dir][0]
        crazy_stack[stack_count][1]+=dir[min_dir][1]
        r_map[crazy_stack[stack_count][0]][crazy_stack[stack_count][1]] = 'R'
        stack_count += 1

