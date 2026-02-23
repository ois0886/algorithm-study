def solution(n):
    # 삼각형 2D 배열 초기화
    tri = [[0] * (i + 1) for i in range(n)]

    r, c = -1, 0  # 시작 위치 (첫 이동에서 (0,0)이 됨)
    num = 1

    for step in range(n, 0, -1):  # 채울 칸 수: n, n-1, n-2, ...
        direction = (n - step) % 3

        for _ in range(step):
            if direction == 0:  # 아래
                r += 1
            elif direction == 1:  # 오른쪽
                c += 1
            else:  # 대각선 위
                r -= 1
                c -= 1

            tri[r][c] = num
            num += 1

    # 2D 배열을 1D로 합치기
    return [x for row in tri for x in row]