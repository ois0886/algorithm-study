import sys
from bisect import bisect_right
input = sys.stdin.readline

N = int(input())
cranes = list(map(int, input().split()))
M = int(input())
freight = list(map(int, input().split()))

cranes.sort(reverse=True)
freight.sort()

count = 0
if cranes[0] < freight[-1]:
    print(-1)
else:
    while freight:
        for crane in cranes:
            if not freight:
                break
            idx = bisect_right(freight, crane) - 1
            if idx >= 0:
                freight.pop(idx)
        count += 1
    print(count)
