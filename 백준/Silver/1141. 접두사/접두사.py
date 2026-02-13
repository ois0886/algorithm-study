import sys
input = sys.stdin.readline

def solve():
    n = int(input())                            # 단어 개수 입력
    words = [input().strip() for _ in range(n)] # n개의 단어를 한 줄씩 입력받아 리스트로 저장

    words.sort(key=lambda x: -len(x))           # 길이 내림차순 정렬 (긴 단어부터 처리)

    selected = []                               # 접두사X 집합에 선택된 단어들
    for w in words:                             # 긴 단어부터 하나씩 확인
        conflict = False                        # 충돌 여부 플래그
        for s in selected:                      # 이미 선택된 단어들과 비교
            if s.startswith(w) or w.startswith(s):  # w가 s의 접두어이거나, s가 w의 접두어이면
                conflict = True                 # 충돌 발생
                break                           # 더 볼 필요 없이 탈출
        if not conflict:                        # 충돌이 없으면
            selected.append(w)                  # 집합에 추가

    print(len(selected))                        # 최대 부분집합 크기 출력

solve()
