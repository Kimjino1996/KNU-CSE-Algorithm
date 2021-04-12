def test_row(x):
    for i in range(map_size):
        for j in range(map_size):
            map_block[i][j]=0
    i=0
    false=-1
    success=1
    while (i<map_size-1):
        if map_contents[x][i+1]==map_contents[x][i]:
            i=i+1
        elif map_contents[x][i+1]+1==map_contents[x][i]:
            for j in range(1,angle_size+1):
                if i+j>=map_size:
                    return false
                if map_contents[x][i+j]+1!=map_contents[x][i]:
                    return false
                map_block[x][i+j]=1
            else:
                i=i+angle_size
        elif map_contents[x][i+1]-1==map_contents[x][i]:
            if map_block[x][i]!=0:
                return false
            for j in range(1,angle_size):
                if i-j<0:
                    return false
                if map_contents[x][i - j] != map_contents[x][i]:
                    return false
                if map_block[x][i-j]:
                    return false
            else:
                i = i + 1
        else :
            return false
    return success

def test_col(x):
    for i in range(map_size):
        for j in range(map_size):
            map_block[i][j]=0
    i=0
    false=-1
    success=1
    while (i<map_size-1):
        if map_contents[i+1][x]==map_contents[i][x]:
            i=i+1
        elif map_contents[i+1][x]+1==map_contents[i][x]:
            for j in range(1,angle_size+1):
                if i+j>=map_size:
                    return false
                if map_contents[i+j][x]+1!=map_contents[i][x] :
                    return false
                map_block[i+j][x]=1
            else:
                i=i+angle_size

        elif map_contents[i+1][x]-1==map_contents[i][x]:
            if map_block[i][x]!=0:
                return false
            for j in range(1,angle_size):
                if i-j<0:
                    return false
                if map_contents[i-j][x] != map_contents[i][x]:
                    return false
                if map_block[i-j][x]!=0:
                    return false
            else:
                i = i + 1
        else :
            return false
    return success

if __name__=="__main__":
    map_size, angle_size=map(int,input().split())
    map_contents=[list(map(int,input().split())) for _ in range(map_size)]
    count =0

    map_block = [[0] * map_size for _ in range(map_size)]

    for i in range(map_size):
        if test_row(i)>0:
            count=count+1

    for i in range(map_size):
        if test_col(i)>0 :
            count=count+1
    print(count)

