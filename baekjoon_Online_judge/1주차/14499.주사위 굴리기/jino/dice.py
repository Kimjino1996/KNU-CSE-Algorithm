
class dice_abstract:
    row=123
    col=123

    dice_value=[[0]*3 for _ in range(4)]

if __name__=="__main__":
    dice = dice_abstract()
    row_size,col_size,dice.row,dice.col,command_num=map(int,input().split())
    a=[list(map(int,input().split())) for _ in range(row_size)]

    command=list(map(int,input().split()))

    for i in range(command_num):

        if command[i]==1:
            dice.col=dice.col+1
            if dice.col>=col_size:
                dice.col=dice.col-1
                continue
            temp=dice.dice_value[1][0]
            for i in range(0,2):
                dice.dice_value[1][i]=dice.dice_value[1][i+1]
            temp2=dice.dice_value[3][1]
            dice.dice_value[3][1]=temp
            dice.dice_value[1][2]=temp2

        elif command[i]==2:
            dice.col = dice.col - 1
            if dice.col<0:
                dice.col=dice.col+1
                continue
            temp = dice.dice_value[1][2]
            for i in range(1, -1,-1):
                dice.dice_value[1][i+1] = dice.dice_value[1][i]
            temp2 = dice.dice_value[3][1]
            dice.dice_value[3][1] = temp
            dice.dice_value[1][0] = temp2

        elif command[i]==3:
            dice.row=dice.row-1
            if dice.row<0:
                dice.row=dice.row+1
                continue
            temp = dice.dice_value[3][1]
            for i in range(2,-1,-1):
                dice.dice_value[i + 1][1] = dice.dice_value[i][1]
            dice.dice_value[0][1]=temp

        elif command[i]==4:
            dice.row=dice.row+1
            if dice.row>=row_size:
                dice.row=dice.row-1
                continue
            temp = dice.dice_value[0][1]
            for i in range(1,4):
                dice.dice_value[i-1][1]=dice.dice_value[i][1]
            dice.dice_value[3][1]=temp

        if a[dice.row][dice.col]==0:
            a[dice.row][dice.col]=dice.dice_value[1][1]

        elif a[dice.row][dice.col]!=0:
            dice.dice_value[1][1]=a[dice.row][dice.col]
            a[dice.row][dice.col]=0

        print(dice.dice_value[3][1])



