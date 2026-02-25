import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
numbers = list(map(int, input().split()))

dq = deque()
for i in range(N):
    dq.append((i + 1, numbers[i]))  # (번호, 종이값)

result = []
while dq:
    num, val = dq.popleft()  # 현재 풍선 터뜨리기
    result.append(num)
    if not dq:
        break
    if val > 0:
        dq.rotate(-(val - 1))  # 오른쪽으로 val칸 → 왼쪽으로 val-1 회전
    else:
        dq.rotate(-val)        # 왼쪽으로 |val|칸 → 오른쪽으로 |val| 회전

print(*result)
