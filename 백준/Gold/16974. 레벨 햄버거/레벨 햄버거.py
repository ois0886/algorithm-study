import sys
input = sys.stdin.readline

N, X = map(int, input().split())

# 레벨-L 버거 구조: B + (L-1버거) + P + (L-1버거) + B
# 0 - P
# 1 - BPPPB
# 2 - BBPPPBPBPPPBB

# 각 레벨의 총 크기와 패티 수를 미리 계산
size = [0] * (N + 1)
patty = [0] * (N + 1)
size[0] = 1
patty[0] = 1
for i in range(1, N + 1):
    size[i] = 2 * size[i - 1] + 3
    patty[i] = 2 * patty[i - 1] + 1

def solve(level, x):
    # 레벨-level 버거에서 아래 x장 먹었을 때 패티 수
    if level == 0:
        # 패티 1장뿐
        return 1 if x >= 1 else 0

    # 구간별로 나눠서 계산
    # 1) B (앞 번) - 1장
    if x <= 1:
        return 0

    # 2) L-1 버거 - size[level-1]장
    if x <= 1 + size[level - 1]:
        return solve(level - 1, x - 1)

    # 3) P (가운데 패티) - 1장
    if x <= 1 + size[level - 1] + 1:
        return patty[level - 1] + 1

    # 4) L-1 버거 - size[level-1]장
    if x <= 1 + size[level - 1] + 1 + size[level - 1]:
        return patty[level - 1] + 1 + solve(level - 1, x - 1 - size[level - 1] - 1)

    # 5) B (뒷 번) - 1장
    return patty[level]

print(solve(N, X))
