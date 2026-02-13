def solve():
    s = input().strip()
    chars = sorted(s)                       # 정렬 (중복 문자 건너뛰기 위해)
    n = len(chars)
    used = [False] * n
    count = 0

    def bt(prev, depth):                    # prev: 이전 문자, depth: 현재까지 놓은 개수
        nonlocal count
        if depth == n:                      # 모든 자리를 다 채우면
            count += 1
            return
        for i in range(n):
            if used[i]:                     # 이미 사용한 문자 스킵
                continue
            if chars[i] == prev:            # 이전 문자와 같으면 스킵 (행운의 문자열 조건)
                continue
            if i > 0 and chars[i] == chars[i - 1] and not used[i - 1]:  # 중복 순열 방지
                continue
            used[i] = True
            bt(chars[i], depth + 1)
            used[i] = False

    bt('', 0)
    print(count)

solve()
