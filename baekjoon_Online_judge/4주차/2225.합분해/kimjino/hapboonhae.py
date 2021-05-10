
we=1
n,k=map(int,input().split())
for i in range(k-1):
    we*=(n+k-1-i)/(i+1)
    we= we%1000000000
    #print(we)
'''mit=1
for j in range(1,k):
    mit*=j
    mit=mit
#print(we/mit%100)
'''
print(int((we)))