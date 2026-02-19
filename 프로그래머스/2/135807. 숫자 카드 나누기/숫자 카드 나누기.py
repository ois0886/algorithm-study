import math

def solution(arrayA, arrayB):
    # arrayA의 GCD: arrayA 모든 수를 나눌 수 있는 가장 큰 수
    gcd_a = arrayA[0]
    for i in arrayA:
        gcd_a = math.gcd(gcd_a, i)

    # arrayB의 GCD: arrayB 모든 수를 나눌 수 있는 가장 큰 수
    gcd_b = arrayB[0]
    for i in arrayB:
        gcd_b = math.gcd(gcd_b, i)

    # gcd_a가 arrayB의 어떤 수도 나누지 못하면 후보
    candidate_a = gcd_a
    for i in arrayB:
        if i % gcd_a == 0:
            candidate_a = 0
            break

    # gcd_b가 arrayA의 어떤 수도 나누지 못하면 후보
    candidate_b = gcd_b
    for i in arrayA:
        if i % gcd_b == 0:
            candidate_b = 0
            break

    return max(candidate_a, candidate_b)
