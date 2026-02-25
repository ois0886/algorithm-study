import sys

input = sys.stdin.readline

def solve():
    N = int(input())
    target = int(input())
    board = [[0] * N for _ in range(N)]

    # 방향: 아래, 오른쪽, 위, 왼쪽
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    x, y = 0, 0
    d = 0
    tx, ty = 0, 0

    for num in range(N * N, 0, -1):
        board[x][y] = num
        if num == target:
            tx, ty = x + 1, y + 1  # 1-indexed 좌표

        # 다음 칸 계산
        nx, ny = x + dx[d], y + dy[d]
        # 범위 밖이거나 이미 채워진 칸이면 방향 전환
        if nx < 0 or nx >= N or ny < 0 or ny >= N or board[nx][ny] != 0:
            d = (d + 1) % 4
            nx, ny = x + dx[d], y + dy[d]
        x, y = nx, ny

    for row in board:
        print(*row)
    print(tx, ty)

solve()
