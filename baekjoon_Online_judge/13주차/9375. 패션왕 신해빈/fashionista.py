from collections import Counter
for _ in range(int(input())):
  cloth = []
  for _ in range(int(input())):
    c, t = input().split()
    cloth.append(t)
  num = 1
  result = Counter(cloth)
  for key in result:
    num *= result[key] + 1
  print(num - 1)
