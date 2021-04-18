class Fire_ball:
    m = 0
    d = 0
    s = 0

    def __init__(self, lst=[m, s, d]):
        self.m = lst[0]
        self.s = lst[1]
        self.d = lst[2]

def fire_move():
    temp_map=[[[] for _ in range(map_size)] for _ in range(map_size)]

    for row in range(map_size):
        for col in range(map_size):
            while True:
                if len(fire_map[row][col])>0:
                    temp=fire_map[row][col].pop()
                    temp_row=row
                    temp_col=col

                    temp_row = temp_row + (move[temp.d][0])*temp.s
                    temp_col=temp_col+(move[temp.d][1])*temp.s
                    if temp_row >= map_size or temp_row<0:
                        temp_row=temp_row%map_size
                    if temp_col >= map_size or temp_col<0:
                        temp_col=temp_col%map_size
                    temp_map[temp_row][temp_col].append(temp)
                else:
                    break

    return temp_map

def fire_fusion(row,col):
    flag =0 # 모두 홀 or 짝

    if len(fire_map[row][col])<2:
        return

    k = fire_map[row][col][0].d % 2 # 홀짝 판단
    sum_m=0
    sum_s=0
    fire_length=len(fire_map[row][col])
    for temp in fire_map[row][col]:
        if(k!=temp.d%2):
            flag=1
        sum_m=temp.m+sum_m
        sum_s=temp.s+sum_s


    fire_map[row][col].clear()
    if sum_m<5:
        return

    if flag==0:
        for i in range(0,8,2):
            fire_map[row][col].append(Fire_ball([int(sum_m/5),int(sum_s/fire_length),i]))

    else:
        for i in range(1,8,2):
            fire_map[row][col].append(Fire_ball([int(sum_m/5),int(sum_s/fire_length),i]))

if __name__ == "__main__":
    move = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]  # row ,col

    map_size, ball_num, command_num = list(map(int, input().split()))
    # row ,col, 질량 속력 방향

    # print(command_num)

    fire_map=[[[] for _ in range(map_size)] for _ in range(map_size)]
    #fire_map=[[] for _ in range(map_size)]
    #print(fire_map)

    for _ in range(ball_num):
        row,col,m,s,d=list(map(int,input().split()))
        fire_map[row-1][col-1].append(Fire_ball([m,s,d]))

    #print(len(fire_map[0][0]))


    for _ in range(command_num):
        fire_map=fire_move()
        for i in range(map_size):
            for j in range(map_size):
                fire_fusion(i,j)

    sum=0
    for i in range(map_size):
        for j in range(map_size):
            for z in fire_map[i][j]:
                sum=sum+z.m
    print (sum)