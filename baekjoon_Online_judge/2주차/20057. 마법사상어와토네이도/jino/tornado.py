def tor_move(row, col, dir):  # 0,1,2,3 # row col 은 한중간
    a = [[-2, 0], [-1, -1], [-1, 0], [-1, 1], [0, -2], [0, -1], [1, -1], [1, 0], [1, 1], [2, 0]]
    tor_val=[0.02, 0.1, 0.07, 0.01, 0.05, 0.0, 0.1, 0.07, 0.01, 0.02]
    temp=[]
    sum=0
    temp_percent=0.55

    if dir == 0: # x,y
        col=col-1
        value = tor_map[row][col]
        tor_map[row][col]=0


        for i in range(len(a)):
            if row+a[i][0]>=0 and row+a[i][0]<m_size and col+a[i][1]>=0 and col+a[i][1]<m_size :
                if int(value * tor_val[i])!=0:
                    tor_map[row+a[i][0]][col+a[i][1]]=tor_map[row+a[i][0]][col+a[i][1]]+int(value*tor_val[i])
                else:
                    temp_percent=temp_percent+tor_val[i]
            else:
                if int(value * tor_val[i])!=0:
                    sum=sum+int(value*tor_val[i])
                else:
                    temp_percent=temp_percent+tor_val[i]
        if row + a[5][0] >= 0 and row + a[5][0] < m_size and col + a[5][1] >= 0 and col + a[5][1] < m_size:
            tor_map[row+a[5][0]][col+a[5][1]]=tor_map[row+a[5][0]][col+a[5][1]]+int(value*temp_percent)
        else:
            sum=sum+int(value*temp_percent)

        return row,col,sum

    if dir == 1: #-y,x 밑으로
        row=row+1
        value = tor_map[row][col]
        tor_map[row][col] = 0
        for i in range(len(a)):
            temp.append([-a[i][1],a[i][0]])

        for i in range(len(temp)):
            if row+temp[i][0]>=0 and row+temp[i][0]<m_size and col+temp[i][1]>=0 and col+temp[i][1]<m_size:
                if int(value * tor_val[i])!=0:
                    tor_map[row+temp[i][0]][col+temp[i][1]]=tor_map[row+temp[i][0]][col+temp[i][1]]+int(value*tor_val[i])
                else:
                    temp_percent=temp_percent+tor_val[i]
            else:
                if int(value * tor_val[i])!=0:
                    sum=sum+int(value*tor_val[i])
                else:
                    temp_percent=temp_percent+tor_val[i]

        if row+temp[5][0]>=0 and row+temp[5][0]<m_size and col+temp[5][1]>=0 and col+temp[5][1]<m_size:
            tor_map[row + temp[5][0]][col + temp[5][1]] = tor_map[row + temp[5][0]][col + temp[5][1]] + int(value * temp_percent)
        else:
            sum = sum + int(value * temp_percent)

        return row,col,sum

    if dir == 2 : # - x,-y
        col=col+1
        value = tor_map[row][col]
        tor_map[row][col] = 0
        for i in range(len(a)):
            temp.append([-a[i][0], -a[i][1]])

        for i in range(len(temp)):
            if row + temp[i][0] >= 0 and row + temp[i][0] < m_size and col + temp[i][1] >= 0 and col + temp[i][1] < m_size:
                if int(value * tor_val[i]) != 0:
                    tor_map[row + temp[i][0]][col + temp[i][1]] = tor_map[row + temp[i][0]][col + temp[i][1]] + int(value * tor_val[i])
                else:
                    temp_percent = temp_percent + tor_val[i]
            else:
                if int(value * tor_val[i]) != 0:
                    sum = sum + int(value * tor_val[i])
                else:
                    temp_percent = temp_percent + tor_val[i]

        if row + temp[5][0] >= 0 and row + temp[5][0] < m_size and col + temp[5][1] >= 0 and col + temp[5][1] < m_size:
            tor_map[row + temp[5][0]][col + temp[5][1]] = tor_map[row + temp[5][0]][col + temp[5][1]] + int(value * temp_percent)
        else:
            sum = sum + int(value * temp_percent)

        return row,col,sum

    if dir == 3: # y,-x
        row=row-1
        value = tor_map[row][col]
        tor_map[row][col] = 0
        for i in range(len(a)):
            temp.append([a[i][1], -a[i][0]])
        for i in range(len(temp)):
            if row + temp[i][0] >= 0 and row + temp[i][0] < m_size and col + temp[i][1] >= 0 and col + temp[i][1] < m_size:
                if int(value * tor_val[i]) != 0:
                    tor_map[row + temp[i][0]][col + temp[i][1]] = tor_map[row + temp[i][0]][col + temp[i][1]] + int(value * tor_val[i])
                else:
                    temp_percent = temp_percent + tor_val[i]
            else:
                if int(value * tor_val[i]) != 0:
                    sum = sum + int(value * tor_val[i])
                else:
                    temp_percent = temp_percent + tor_val[i]

        if row + temp[5][0] >= 0 and row + temp[5][0] < m_size and col + temp[5][1] >= 0 and col + temp[5][1] < m_size:
            tor_map[row + temp[5][0]][col + temp[5][1]] = tor_map[row + temp[5][0]][col + temp[5][1]] + int(value * temp_percent)
        else:
            sum = sum + int(value * temp_percent)

        return row,col,sum

# 위쪽에도 걸어야되나?;;


if __name__ == "__main__":

    m_size = int(input())

    tor_map = [list(map(int, input().split())) for _ in range(m_size)]

    start_row = int(m_size / 2)
    start_col = int(m_size / 2)
    total=0

    next = 0
    repeat = 1
    sum_temp=0

    r_sum=0
    for i in range(m_size):
        for j in range(m_size):
            r_sum=tor_map[i][j]+r_sum

    while True:
    #    print("hihi",repeat)
    #    print(total)
        for _ in range(repeat):
            start_row, start_col, sum_temp = tor_move(start_row, start_col, next)
     #       total=total+sum_temp

            if start_row == 0 and start_col == 0:
                break
        next = (next + 1) % 4
        if start_row == 0 and start_col == 0:
            break

        for _ in range(repeat):
            start_row, start_col, sum_temp = tor_move(start_row, start_col, next)
            #total = total + sum_temp
        next = (next + 1) % 4
        repeat = repeat + 1

    #print(total)

    sum=0
    for i in range(m_size):
        for j in range(m_size):
            sum=tor_map[i][j]+sum

    print(r_sum-sum)

'''
    for i in range(m_size):
        for j in range(m_size):
            print(tor_map[i][j],end=' ')
        print()

'''




