import sys 
input = sys.stdin.readline
n = int(input().strip())

result = 0

save = [0 for _ in range(1001)]#소수저장되는곳

max = 0 #입력받은수중에서가장큰숫자

num = [0]*n

num = list(map(int,input().split()))

for x in range(n):    
    if max < num[x]:
        max = num[x]
    save[num[x]] = 1
    
for x in range(2,max+1):
    multiple = x
    while True:
        multiple += x
        if multiple > max:
            break
        if save[multiple] == 1:
            save[multiple] = 0
            
for x in range(max+1):
    if save[x] == 1:
        result+= 1
        
if save[1] == 1:
    result -=1
    
print(result)