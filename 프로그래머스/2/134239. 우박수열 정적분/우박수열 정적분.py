def solution(k, ranges):
    # 1. 우박수열 생성
    seq = [k]
    while k != 1:
        if k % 2 == 0:
            k //= 2
        else:
            k = k * 3 + 1
        seq.append(k)

    n = len(seq) - 1  # 수열 길이 - 1 = 마지막 x좌표

    # 2. 각 구간 사다리꼴 넓이의 누적합
    prefix = [0] * (n + 1)
    for i in range(n):
        prefix[i + 1] = prefix[i] + (seq[i] + seq[i + 1]) / 2

    # 3. 각 range에 대해 정적분
    answer = []
    for a, b in ranges:
        end = n + b          # b는 0 이하이므로 실제 끝점 = n + b
        if a > end:          # 시작점이 끝점보다 크면 유효하지 않음
            answer.append(-1.0)
        else:
            answer.append(prefix[end] - prefix[a])

    return answer
